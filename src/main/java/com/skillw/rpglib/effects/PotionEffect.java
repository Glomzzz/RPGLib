package com.skillw.rpglib.effects;

import com.skillw.rpglib.RPGLib;
import com.skillw.rpglib.api.effect.BaseEffect;
import com.skillw.rpglib.api.formula.Formula;
import com.skillw.rpglib.api.runnable.RPGScheduler;
import com.skillw.rpglib.util.MapUtils;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.LinkedList;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

/**
 * ClassName : com.skillw.classsystem.effects.PotionEffect
 * Created by Glom_ on 2021-03-26 21:24:14
 * Copyright  2021 user. All rights reserved.
 */
public class PotionEffect extends BaseEffect {
    private static final ConcurrentHashMap<UUID, LinkedList<PotionEffectType>> POTIONS_DATA = new ConcurrentHashMap<>();
    private final ConcurrentHashMap<String, String> potionTypeData = new ConcurrentHashMap<>();

    public PotionEffect() {
        super("potion");
    }

    public static ConcurrentHashMap<UUID, LinkedList<PotionEffectType>> getPotionsData() {
        return POTIONS_DATA;
    }

    @Override
    public void load(ConfigurationSection effectSection) {
        for (String potion : effectSection.getKeys(false)) {
            this.potionTypeData.put(potion, effectSection.getString(potion));
        }
    }

    @Override
    public void realize(Entity entity) {
        if (!(entity instanceof LivingEntity)) return;
        RPGScheduler rpgScheduler = new RPGScheduler();
        LivingEntity livingEntity = (LivingEntity) entity;
        LinkedList<PotionEffectType> potionEffectTypes = getPotionsData().get(entity.getUniqueId());
        if(potionEffectTypes != null){
            rpgScheduler.add(() -> potionEffectTypes.forEach(livingEntity::removePotionEffect));
            getPotionsData().remove(entity.getUniqueId());
        }
        for (Map.Entry<String, String> entry : this.potionTypeData.entrySet()) {
            String formula = entry.getValue();
            String potion = RPGLib.getRPGPlaceHolderAPI().replace(livingEntity, entry.getKey());
            PotionEffectType potionEffectType = PotionEffectType.getByName(potion);
            if (potionEffectType == null) return;
            int level = (int) new Formula(formula, livingEntity).result();
            rpgScheduler.add(() -> livingEntity.addPotionEffect(new org.bukkit.potion.PotionEffect(potionEffectType, Integer.MAX_VALUE, level,true)));
            MapUtils.add(getPotionsData(),livingEntity.getUniqueId(),potionEffectType);
        }
        rpgScheduler.run();
    }

    private void run(Runnable runnable){
        new BukkitRunnable(){
            @Override
            public void run() {
                runnable.run();
            }
        }.runTask(RPGLib.getInstance().getPlugin());
    }

    @Override
    public void unRealize(Entity entity) {
        LinkedList<PotionEffectType> potionEffectTypes = getPotionsData().get(entity.getUniqueId());
        if(potionEffectTypes != null && entity instanceof LivingEntity){
            run(() -> potionEffectTypes.forEach(((LivingEntity) entity)::removePotionEffect));
            getPotionsData().remove(entity.getUniqueId());
        }
    }

    @Override
    public BaseEffect clone() {
        return null;
    }
}

package com.skillw.rpglib.api.effect;

import com.skillw.rpglib.RPGLib;
import com.skillw.rpglib.api.able.Configurable;
import com.skillw.rpglib.effects.PotionEffect;
import com.skillw.rpglib.util.FileUtils;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.Entity;

import java.io.File;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

/**
 * ClassName : com.skillw.classsystem.effect.RPGEffect
 * Created by Glom_ on 2021-03-24 22:46:20
 * Copyright  2021 user. All rights reserved.
 */
public class RPGEffect implements Configurable<RPGEffect>,  com.skillw.rpglib.api.able.Cloneable<String,RPGEffect> {
    private final String key;
    private final ConcurrentHashMap<String, BaseEffect> BASE_EFFECT_DATA;

    public RPGEffect(String key, ConcurrentHashMap<String, BaseEffect> BASE_EFFECT_DATA) {
        this.key = key;
        this.BASE_EFFECT_DATA = BASE_EFFECT_DATA;
    }

    public RPGEffect(ConfigurationSection section) {
        this.BASE_EFFECT_DATA = new ConcurrentHashMap<>();
        if (section == null) {
            this.key = null;
            return;
        }
        this.key = section.getName();
        for (String key : section.getKeys(false)) {
            BaseEffect baseEffect = RPGLib.getBaseEffectManager().getObject(key);
            if (baseEffect == null) continue;
            baseEffect.load(section.getConfigurationSection(key));
            this.BASE_EFFECT_DATA.put(key, baseEffect);
        }
    }

    public void realize(Entity entity) {
        for (BaseEffect baseEffect : this.BASE_EFFECT_DATA.values()) {
            if(!baseEffect.getClass().equals(PotionEffect.class)){
                baseEffect.unRealize(entity);
            }
            baseEffect.realize(entity);
        }
    }

    public void unRealize(Entity entity,boolean force) {
        for (BaseEffect baseEffect : this.BASE_EFFECT_DATA.values()) {
            if(force || baseEffect.getClass().equals(PotionEffect.class)){
                baseEffect.unRealize(entity);
            }
        }
    }

    @Override
    public void register(){
        RPGLib.getRPGEffectManager().register(getKey(),this);
    }

    @Override
    public String getKey() {
        return this.key;
    }

    public ConcurrentHashMap<String, BaseEffect> getBaseEffectData() {
        return this.BASE_EFFECT_DATA;
    }

    @Override
    public RPGEffect clone() {
        return new RPGEffect(this.key, this.BASE_EFFECT_DATA);
    }

    public static RPGEffect load(ConfigurationSection section) {
        return new RPGEffect(section);
    }

    public static List<RPGEffect> loadMultiply(File file) {
        List<RPGEffect> rpgEffects = FileUtils.loadMultiply(file,RPGEffect.class);
        rpgEffects.forEach(RPGEffect::register);
        return rpgEffects;
    }
}

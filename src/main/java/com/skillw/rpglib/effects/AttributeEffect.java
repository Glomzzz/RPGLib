package com.skillw.rpglib.effects;

import com.skillw.attsystem.AttributeSystem;
import com.skillw.rpglib.RPGLib;
import com.skillw.rpglib.api.effect.BaseEffect;
import com.skillw.rpglib.api.formula.Formula;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.serverct.ersha.jd.AttributeAPI;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

import static com.skillw.rpglib.util.EntityUtils.isLiving;
import static com.skillw.rpglib.util.MapUtils.add;

/**
 * ClassName : com.skillw.classsystem.effects.PotionEffect
 * Created by Glom_ on 2021-03-26 21:24:14
 * Copyright  2021 user. All rights reserved.
 */
public class AttributeEffect extends BaseEffect {
    private static final ConcurrentHashMap<UUID, LinkedList<String>> ATTRIBUTE_DATA = new ConcurrentHashMap<>();
    private final ConcurrentHashMap<String, String> attributeData = new ConcurrentHashMap<>();

    public AttributeEffect() {
        super("attribute");
    }

    public static ConcurrentHashMap<UUID, LinkedList<String>> getAttributesData() {
        return ATTRIBUTE_DATA;
    }

    @Override
    public void load(ConfigurationSection effectSection) {
        for (String attribute : effectSection.getKeys(false)) {
            this.attributeData.put(attribute, effectSection.getString(attribute));
        }
    }

    @Override
    public void realize(Entity entity) {
        if (!isLiving(entity)) return;
        LivingEntity livingEntity = (LivingEntity) entity;
        this.attributeData.forEach((key, value) -> {
            double result = new Formula(value, livingEntity).result();
            List<String> attributes = new ArrayList<>();
            attributes.add(key + ": " + result);
            if (RPGLib.isAttributeSystem()) {
                AttributeSystem.getAttributeSystemAPI().addAttribute(livingEntity.getUniqueId(), "rpg-effect-attribute-" + livingEntity.getUniqueId(), attributes);
                add(getAttributesData(), livingEntity.getUniqueId(), "rpg-effect-attribute-" + livingEntity.getUniqueId());
            } else if (RPGLib.isIsAttributePlusEnable()) {
                AttributeAPI.getAttrData(livingEntity).addApiAttribute("rpg-effect-attribute-" + livingEntity.getUniqueId(),attributes,false);
                add(getAttributesData(), livingEntity.getUniqueId(), "rpg-effect-attribute-" + livingEntity.getUniqueId());
            }
        });
    }

    @Override
    public void unRealize(Entity entity) {
        if (!isLiving(entity)) return;
        LivingEntity livingEntity = (LivingEntity) entity;
        if (!getAttributesData().containsKey(livingEntity.getUniqueId())) return;
        if (RPGLib.isAttributeSystem()) {
            getAttributesData().get(livingEntity.getUniqueId()).forEach(key -> AttributeSystem.getAttributeSystemAPI().removeAttribute(entity, key));
        }
        getAttributesData().remove(livingEntity.getUniqueId());
    }

    @Override
    public BaseEffect clone() {
        return null;
    }
}

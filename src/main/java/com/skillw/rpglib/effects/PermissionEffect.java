package com.skillw.rpglib.effects;

import com.skillw.rpglib.RPGLib;
import com.skillw.rpglib.api.effect.BaseEffect;
import com.skillw.rpglib.api.formula.Formula;
import io.izzel.taboolib.TabooLibAPI;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffectType;

import java.util.Collections;
import java.util.LinkedList;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

/**
 * ClassName : com.skillw.classsystem.effects.PotionEffect
 * Created by Glom_ on 2021-03-26 21:24:14
 * Copyright  2021 user. All rights reserved.
 */
public class PermissionEffect extends BaseEffect {
    private static final ConcurrentHashMap<UUID, ConcurrentHashMap<String, Boolean>> PERMISSION_DATA = new ConcurrentHashMap<>();
    private final ConcurrentHashMap<String, Boolean> permissionData = new ConcurrentHashMap<>();

    public PermissionEffect() {
        super("permission");
    }

    public static ConcurrentHashMap<UUID, ConcurrentHashMap<String, Boolean>> getPermissionsData() {
        return PERMISSION_DATA;
    }

    @Override
    public void load(ConfigurationSection effectSection) {
        for (String permission : effectSection.getKeys(false)) {
            this.permissionData.put(permission, effectSection.getBoolean(permission));
        }
    }

    @Override
    public void realize(Entity entity) {
        if (!(entity instanceof Player)) return;
        Player player = (Player) entity;
        permissionData.forEach((key,value)->{
            if(value)
                TabooLibAPI.getPluginBridge().permissionAdd(player,key);
            else
                TabooLibAPI.getPluginBridge().permissionRemove(player,key);
        });
        getPermissionsData().put(player.getUniqueId(),permissionData);
    }

    @Override
    public void unRealize(Entity entity) {
        if (!(entity instanceof Player)) return;
        Player player = (Player) entity;
        ConcurrentHashMap<String, Boolean> permissionData = getPermissionsData().get(player.getUniqueId());
        if(permissionData == null) return;
        permissionData.forEach((key,value)->{
            if(!value)
                TabooLibAPI.getPluginBridge().permissionAdd(player,key);
            else
                TabooLibAPI.getPluginBridge().permissionRemove(player,key);
        });
        getPermissionsData().remove(player.getUniqueId());
    }

    @Override
    public BaseEffect clone() {
        return null;
    }
}

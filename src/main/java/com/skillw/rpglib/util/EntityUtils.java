package com.skillw.rpglib.util;

import org.bukkit.Bukkit;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;

import java.util.UUID;

/**
 * ClassName : com.skillw.rpglib.util.EntityUtils
 * Created by Glom_ on 2021-03-28 17:49:01
 * Copyright  2021 user. All rights reserved.
 */
public final class EntityUtils {
    public static LivingEntity getLivingEntityByUUID(UUID uuid) {
        Entity entity = Bukkit.getEntity(uuid);
        if (entity != null && isLiving(entity)) {
            return (LivingEntity) entity;
        }
        return null;
    }

    public static Player getPlayerByUUID(UUID uuid) {
        return Bukkit.getPlayer(uuid);
    }

    public static boolean isLiving(UUID uuid) {
        Entity entity = Bukkit.getEntity(uuid);
        return isLiving(entity);
    }

    public static boolean isLiving(Entity entity) {
        return entity instanceof LivingEntity && entity.getType() != EntityType.ARMOR_STAND && !entity.isDead();
    }


}

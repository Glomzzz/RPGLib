package com.skillw.rpglib.api;

import org.bukkit.entity.LivingEntity;

import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

/**
 * ClassName : com.skillw.rpglib.api.RPGPlaceHolderAPI
 * Created by Glom_ on 2021-03-28 15:41:27
 * Copyright  2021 user. All rights reserved.
 */
public interface RPGPlaceHolderAPI {
    String replace(LivingEntity livingEntity,String text);
    String replace(UUID uuid, String text);
}


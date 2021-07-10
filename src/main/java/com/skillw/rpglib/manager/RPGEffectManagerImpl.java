package com.skillw.rpglib.manager;

import com.skillw.rpglib.api.effect.BaseEffect;
import com.skillw.rpglib.api.manager.RPGEffectManager;

import java.util.concurrent.ConcurrentHashMap;

/**
 * ClassName : com.skillw.classsystem.api.manager.RPGEffectManager
 * Created by Glom_ on 2021-03-25 22:15:24
 * Copyright  2021 user. All rights reserved.
 */
public class RPGEffectManagerImpl extends RPGEffectManager {
    private static final ConcurrentHashMap<String, BaseEffect> LOADED_DATA = new ConcurrentHashMap<>();

    public static ConcurrentHashMap<String, BaseEffect> getLoadedBaseEffectData() {
        return LOADED_DATA;
    }
}

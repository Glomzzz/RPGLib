package com.skillw.rpglib.api.effect;

import com.skillw.rpglib.RPGLib;
import com.skillw.rpglib.api.able.Keyable;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.Entity;

/**
 * ClassName : com.skillw.classsystem.api.effect.BaseEffect
 * Created by Glom_ on 2021-03-25 21:56:13
 * Copyright  2021 user. All rights reserved.
 */
public abstract class BaseEffect implements Keyable<String> {
    protected final String key;

    protected BaseEffect(String key) {
        this.key = key;
    }

    public static <T extends BaseEffect> void register(Class<T> tClass) {
        RPGLib.getBaseEffectManager().register((Class<BaseEffect>) tClass);
    }

    @Override
    public final String getKey() {
        return this.key;
    }

    public abstract void load(ConfigurationSection effectSection);

    public abstract void realize(Entity entity);

    public abstract void unRealize(Entity entity);

    @Override
    public void register() {
        RPGLib.getBaseEffectManager().register((Class<BaseEffect>) this.getClass());
    }
}

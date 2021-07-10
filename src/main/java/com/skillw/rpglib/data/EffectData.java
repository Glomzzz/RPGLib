package com.skillw.rpglib.data;

import com.skillw.rpglib.RPGLib;
import com.skillw.rpglib.api.able.Keyable;
import com.skillw.rpglib.api.map.BaseMap;
import com.skillw.rpglib.api.effect.RPGEffect;
import com.skillw.rpglib.util.EntityUtils;
import org.bukkit.entity.Entity;

import java.util.List;
import java.util.UUID;

/**
 * ClassName : com.skillw.rpglib.data.EffectData
 * Created by Glom_ on 2021-04-04 19:57:07
 * Copyright  2021 user. All rights reserved.
 */
public class EffectData extends BaseMap<String, List<RPGEffect>> implements Keyable<UUID> {
    private final UUID uuid;

    public EffectData(UUID uuid) {
        this.uuid = uuid;
    }

    @Override
    public UUID getKey() {
        return uuid;
    }

    @Override
    public void register() {
        RPGLib.getEffectManager().register(this);
    }

    public UUID getUuid() {
        return uuid;
    }


    public void realize(){
        Entity entity = EntityUtils.getLivingEntityByUUID(uuid);
        if(entity != null)
            map.values().forEach(rpgEffects -> rpgEffects.forEach(rpgEffect -> rpgEffect.realize(entity)));
    }

    public void unRealize(){
        Entity entity = EntityUtils.getLivingEntityByUUID(uuid);
        if(entity != null)
            map.values().forEach(rpgEffects -> rpgEffects.forEach(rpgEffect -> rpgEffect.unRealize(entity,false)));
    }
}

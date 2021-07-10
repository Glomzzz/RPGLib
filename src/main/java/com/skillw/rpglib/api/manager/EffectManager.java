package com.skillw.rpglib.api.manager;


import com.skillw.rpglib.RPGLib;
import com.skillw.rpglib.api.map.KeyMap;
import com.skillw.rpglib.data.EffectData;
import com.skillw.rpglib.api.effect.RPGEffect;
import com.skillw.rpglib.util.EntityUtils;
import org.bukkit.entity.Entity;

import java.util.List;
import java.util.UUID;

/**
 * ClassName : com.skillw.classsystem.api.manager.BaseEffectManager
 * Created by Glom_ on 2021-03-25 22:09:21
 * Copyright  2021 user. All rights reserved.
 */
public abstract class EffectManager extends KeyMap<UUID, EffectData> {
    public void realize(Entity entity){
        if(map.containsKey(entity.getUniqueId()))
        map.get(entity.getUniqueId()).realize();
    }
    public void unRealize(Entity entity){
        if(map.containsKey(entity.getUniqueId()))
        map.get(entity.getUniqueId()).unRealize();
    }

    public void realize(UUID uuid){
        Entity entity = EntityUtils.getLivingEntityByUUID(uuid);
        if(map.containsKey(uuid) && entity != null)
            map.get(uuid).realize();
    }

    public void unRealize(UUID uuid){
        Entity entity = EntityUtils.getLivingEntityByUUID(uuid);
        if(map.containsKey(uuid) && entity != null)
            map.get(uuid).unRealize();
    }

    public void register(UUID uuid,String key,List<RPGEffect> rpgEffects){
        EffectData effectData = (RPGLib.getEffectManager().hasKey(uuid)) ? RPGLib.getEffectManager().get(uuid) : new EffectData(uuid);
        effectData.register(key, rpgEffects);
        RPGLib.getEffectManager().register(effectData);
    }

    public void unRegister(UUID uuid,String key){
        Entity entity = EntityUtils.getLivingEntityByUUID(uuid);
        if(entity == null) return;
        if(!RPGLib.getEffectManager().hasKey(uuid)) return;
        EffectData effectData = RPGLib.getEffectManager().get(uuid);
        if(!effectData.hasKey(key)) return;
        List<RPGEffect> rpgEffect = effectData.get(key);
        rpgEffect.forEach(rpgEffect1 -> rpgEffect1.unRealize(entity,true));
        effectData.removeByKey(key);
        this.map.put(uuid,effectData);
    }
}

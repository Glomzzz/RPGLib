package com.skillw.rpglib.script;

import com.skillw.rpglib.api.replace.RPGPlaceHolder;
import io.izzel.taboolib.TabooLibAPI;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * ClassName : com.skillw.rpglib.papihook.RPGPlaceHolderAPIHooker
 * Created by Glom_ on 2021-03-28 17:33:53
 * Copyright  2021 user. All rights reserved.
 */
public class ScriptHooker extends RPGPlaceHolder {
    public ScriptHooker() {
        super("js","Glom_","1.0.0");
    }

    @Override
    public String onPlaceHolderReplace(String replaced, LivingEntity livingEntity,String def) {
        if(livingEntity instanceof Player) {
            Map<String,Object> map = new ConcurrentHashMap<>();
            map.put("entity",livingEntity);
            replaced = String.valueOf(ScriptTool.invokePathWithFunction(replaced,map));
        }else {
            replaced = def;
        }
        return replaced;
    }

}

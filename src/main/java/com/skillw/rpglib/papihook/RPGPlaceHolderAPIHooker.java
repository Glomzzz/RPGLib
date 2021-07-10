package com.skillw.rpglib.papihook;

import com.skillw.rpglib.RPGLib;
import com.skillw.rpglib.api.replace.RPGPlaceHolder;
import io.izzel.taboolib.TabooLibAPI;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;

/**
 * ClassName : com.skillw.rpglib.papihook.RPGPlaceHolderAPIHooker
 * Created by Glom_ on 2021-03-28 17:33:53
 * Copyright  2021 user. All rights reserved.
 */
public class RPGPlaceHolderAPIHooker extends RPGPlaceHolder {
    public RPGPlaceHolderAPIHooker() {
        super("papi","Glom_","1.0.0");
    }

    @Override
    public String onPlaceHolderReplace(String replaced, LivingEntity livingEntity,String def) {
        if(livingEntity instanceof Player) {
            replaced = TabooLibAPI.getPluginBridge().setPlaceholders((Player) livingEntity,"%" + replaced + "%");
        }else {
            replaced = def;
        }
        return replaced;
    }

}

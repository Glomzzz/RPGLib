package com.skillw.rpglib.manager;

import com.skillw.rpglib.RPGLib;
import com.skillw.rpglib.api.RPGPlaceHolderAPI;
import com.skillw.rpglib.api.replace.Closure;
import com.skillw.rpglib.api.replace.RPGPlaceHolder;
import io.izzel.taboolib.TabooLib;
import io.izzel.taboolib.TabooLibAPI;
import io.izzel.taboolib.module.inject.TInject;
import io.izzel.taboolib.module.locale.chatcolor.TColor;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;

import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static com.skillw.rpglib.util.EntityUtils.getLivingEntityByUUID;

/**
 * ClassName : com.skillw.rpglib.manager.RPGPlaceHolderAPIImpl
 * Created by Glom_ on 2021-03-28 15:42:18
 * Copyright  2021 user. All rights reserved.
 */
public class RPGPlaceHolderAPIImpl implements RPGPlaceHolderAPI {


    @Override
    public String replace(LivingEntity livingEntity, String text) {
        if(livingEntity == null) return text;
        if (text == null) return null;
        if(TabooLibAPI.getPluginBridge().placeholderHooked() && livingEntity instanceof Player){
            text = TabooLibAPI.getPluginBridge().setPlaceholders((Player) livingEntity,text);
        }
        Pattern pattern = Pattern.compile(String.format("\\%s((?<identifier>[a-zA-Z0-9]+)_)(?<parameters>[^%s%s]+)\\%s", Closure.PERCENT.head, Closure.PERCENT.head, Closure.PERCENT.tail, Closure.PERCENT.tail));
        Matcher matcher = pattern.matcher(text);
        if (!matcher.find()) {
            return text;
        } else {
            StringBuffer builder = new StringBuffer();
            do {
                String identifier = matcher.group("identifier");
                String parameters = matcher.group("parameters");
                RPGPlaceHolder rpgPlaceHolder = RPGLib.getPlaceHolderDataManager().get(identifier);
                if (rpgPlaceHolder != null) {
                    String requested = rpgPlaceHolder.onPlaceHolderReplace(parameters,livingEntity,"1");
                    matcher.appendReplacement(builder, requested != null ? requested : matcher.group(0));
                }
            } while(matcher.find());
            return TColor.translate(matcher.appendTail(builder).toString());
        }
    }

    @Override
    public String replace(UUID uuid, String text) {
        return replace(getLivingEntityByUUID(uuid), text);
    }
}

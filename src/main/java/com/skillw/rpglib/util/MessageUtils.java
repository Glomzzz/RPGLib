package com.skillw.rpglib.util;

import com.skillw.rpglib.RPGLib;
import com.skillw.rpglib.api.manager.ConfigManager;
import io.izzel.taboolib.module.locale.TLocale;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;

import static com.skillw.rpglib.util.ColorUtils.color;

/**
 * ClassName : com.skillw.classsystem.util.MessageUtils
 * Created by Glom_ on 2021-03-25 20:25:13
 * Copyright  2021 user. All rights reserved.
 */
public final class MessageUtils {
    private MessageUtils() {
    }

    public static void sendMessage(CommandSender sender, String msg) {
        sender.sendMessage(color(msg));
    }

    public static void sendWrong(String msg) {
        sendConsole(ConfigManager.getPluginPrefix()+ "&c" + msg);
    }

    public static void sendConsole(String msg) {
        sendMessage(Bukkit.getConsoleSender(), msg);
    }

    public static void debug(String msg){
        sendConsole(RPGLib.getConfigManager().getDebugPrefix() + msg);
    }
}

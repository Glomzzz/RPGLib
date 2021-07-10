package com.skillw.rpglib.api.manager;

import com.skillw.rpglib.RPGLib;
import io.izzel.taboolib.module.config.TConfig;
import io.izzel.taboolib.module.locale.TLocale;
import io.izzel.taboolib.module.locale.TLocaleLoader;
import io.izzel.taboolib.util.Ref;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.plugin.Plugin;

import java.util.Arrays;
import java.util.concurrent.ConcurrentHashMap;

import static io.izzel.taboolib.module.locale.TLocaleLoader.getLocalPriorityFirst;

/**
 * ClassName : com.skillw.classsystem.util.ConfigManager
 * Created by Glom_ on 2021-03-25 20:29:54
 * Copyright  2021 user. All rights reserved.
 */
public abstract class ConfigManager {
    private static final ConcurrentHashMap<Plugin, String> PREFIX_DATA = new ConcurrentHashMap<>();
    protected final Plugin plugin;
    private final TConfig config;
    private final String debugPrefix;

    protected ConfigManager(TConfig config, Plugin plugin) {
        this.config = config;
        this.plugin = plugin;
        debugPrefix = TLocale.asString("debug");
    }

    public static ConcurrentHashMap<Plugin, String> getPrefixData() {
        return PREFIX_DATA;
    }

    public static String getPluginPrefix() {
        Plugin plugin = Ref.getCallerPlugin();
        if (getPrefixData().containsKey(plugin))
            return PREFIX_DATA.get(plugin);
        else
            return RPGLib.getConfigManager().getPrefix();
    }

    public static void registerPrefix(Plugin plugin, String prefix) {
        getPrefixData().put(plugin, prefix);
    }

    public Plugin getPlugin() {
        return plugin;
    }

    public TConfig getConfig() {
        return config;
    }

    public String getPrefix() {
        return TLocaleLoader.asString(plugin,"prefix");
    }

    public String asString(String path,String... args){
        return TLocaleLoader.asString(plugin,path, args);
    }

    public int getVersion() {
        String version = plugin.getDescription().getVersion().replace(".", "");
        if (version.length() == 3) {
            version += "0";
        }
        return Integer.parseInt(version);
    }

    public String getLanguage() {
        if (config != null) {
            String lang = getLocalPriorityFirst(plugin);
            return "languages/" + lang + "/";
        }
        return null;
    }

    public String getString(String path, String... strings) {
        return asString(path, addPrefixAtFirst(strings));
    }

    public boolean isCheckVersion() {
        return config.getBoolean("options.check-version");
    }

    private String[] addPrefixAtFirst(String[] args) {
        String[] newStrings = Arrays.copyOf(args, args.length + 1);
        newStrings[0] = getPrefix();
        System.arraycopy(args, 0, newStrings, 1, args.length);
        return newStrings;
    }

    public void send(CommandSender sender, String path, String... args) {
        if (asString(path).isEmpty()) {
            return;
        }
        if (!(sender instanceof ConsoleCommandSender)) {
            TLocaleLoader.sendTo(plugin, path, sender, addPrefixAtFirst(args));
        } else {
            TLocaleLoader.sendTo(plugin, path, Bukkit.getConsoleSender(), addPrefixAtFirst(args));
        }
    }

    public abstract void reload();

    public String getDebugPrefix() {
        return debugPrefix;
    }
}

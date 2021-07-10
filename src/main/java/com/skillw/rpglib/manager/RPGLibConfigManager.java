package com.skillw.rpglib.manager;

import com.skillw.rpglib.RPGLib;
import com.skillw.rpglib.api.effect.RPGEffect;
import com.skillw.rpglib.api.inventory.DisplayInventory;
import com.skillw.rpglib.api.manager.ConfigManager;
import com.skillw.rpglib.script.ScriptTool;
import org.bukkit.Bukkit;
import org.bukkit.configuration.ConfigurationSection;

import java.io.File;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import static com.skillw.rpglib.script.ScriptTool.staticClass;

/**
 * ClassName : com.skillw.rpglib.manager.RPGLibConfigManager
 * Created by Glom_ on 2021-03-28 18:12:51
 * Copyright  2021 user. All rights reserved.
 */
public class RPGLibConfigManager extends ConfigManager {

    private final File server;
    private final Map<String, Object> staticMap = new ConcurrentHashMap<>();

    public RPGLibConfigManager() {
        super(RPGLib.getInstance().getConfig(), RPGLib.getInstance().getPlugin());
        this.server = RPGLib.getInstance().getPlugin().getDataFolder().getParentFile().getParentFile();
        this.getConfig().listener(() -> {
            this.send(Bukkit.getConsoleSender(), "reload.config");
            reloadStatic();
        });
    }

    public void reloadEffect() {
        RPGEffect.loadMultiply(RPGLib.getInstance().getEffects());
    }

    public void reloadDisplayInventory() {
        DisplayInventory.loadMultiply(RPGLib.getInstance().getInventories());
    }

    public Map<String, Object> getStaticMap() {
        return this.staticMap;
    }

    @Override
    public void reload() {
        this.reloadEffect();
        this.reloadDisplayInventory();
        ScriptTool.reload();
        reloadStatic();
    }

    public void reloadStatic() {
        this.staticMap.clear();
        ConfigurationSection staticSection = RPGLib.getConfigManager().getConfig().getConfigurationSection("script.static");
        for (String varKey : staticSection.getKeys(false)) {
            String className = staticSection.getString(varKey);
            Object staticClass = staticClass(className);
            if (staticClass == null) continue;
            this.staticMap.put(varKey, staticClass(className));
        }
    }

    public File getServer() {
        return this.server;
    }
}

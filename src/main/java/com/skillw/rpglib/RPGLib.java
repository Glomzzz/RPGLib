package com.skillw.rpglib;

import com.skillw.rpglib.api.RPGPlaceHolderAPI;
import com.skillw.rpglib.api.effect.BaseEffect;
import com.skillw.rpglib.api.fixed.FixedData;
import com.skillw.rpglib.api.manager.*;
import com.skillw.rpglib.api.manager.script.CompiledScriptManager;
import com.skillw.rpglib.api.manager.script.ScriptEventManager;
import com.skillw.rpglib.effects.AttributeEffect;
import com.skillw.rpglib.effects.PermissionEffect;
import com.skillw.rpglib.effects.PotionEffect;
import com.skillw.rpglib.listener.LegacyScriptListener;
import com.skillw.rpglib.listener.ScriptListener;
import com.skillw.rpglib.manager.*;
import com.skillw.rpglib.manager.script.CompiledScriptManagerImpl;
import com.skillw.rpglib.manager.script.ScriptEventManagerImpl;
import com.skillw.rpglib.papihook.RPGPlaceHolderAPIHooker;
import com.skillw.rpglib.papihook.ScriptHookPlaceHolderAPI;
import com.skillw.rpglib.script.ScriptHooker;
import com.skillw.rpglib.script.ScriptTool;
import io.izzel.taboolib.internal.apache.lang3.concurrent.BasicThreadFactory;
import io.izzel.taboolib.loader.Plugin;
import io.izzel.taboolib.module.config.TConfig;
import io.izzel.taboolib.module.db.sql.SQLHost;
import io.izzel.taboolib.module.db.sql.SQLTable;
import io.izzel.taboolib.module.db.sqlite.SQLiteColumn;
import io.izzel.taboolib.module.db.sqlite.SQLiteColumnType;
import io.izzel.taboolib.module.db.sqlite.SQLiteHost;
import io.izzel.taboolib.module.inject.TInject;
import io.izzel.taboolib.module.locale.TLocale;
import org.bukkit.Bukkit;
import org.bukkit.event.Listener;
import org.sqlite.SQLiteConfig;
import org.sqlite.SQLiteDataSource;

import javax.sql.DataSource;
import java.io.File;
import java.sql.DriverManager;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import static com.skillw.rpglib.util.FileUtils.saveResource;
import static com.skillw.rpglib.util.MessageUtils.sendConsole;

/**
 * ClassName : com.skillw.rpglib.RPGLib
 * Created by Glom_ on 2021-03-28 15:29:57
 * Copyright  2021 user. All rights reserved.
 */
public class RPGLib extends Plugin {
    private static final ScheduledThreadPoolExecutor THREAD_POOL = new ScheduledThreadPoolExecutor(20, (new BasicThreadFactory.Builder()).namingPattern("rpg-lib-schedule-pool-%d").daemon(true).build());
    private static DataSource database;
    private static SQLTable name_uuid;
    private static boolean isPlaceHolderAPIEnable;
    private static boolean isAttributeSystemEnable;

    public static boolean isIsAttributeSystemEnable() {
        return isAttributeSystemEnable;
    }

    public static boolean isIsAttributePlusEnable() {
        return isAttributePlusEnable;
    }

    private static boolean isAttributePlusEnable;
    /**
     * 静态实例区
     */
    private static RPGLib instance;
    private static RPGPlaceHolderAPI rpgPlaceHolderAPI;
    private static RPGLibConfigManager configManager;
    private static BaseEffectManager baseEffectManager;
    private static RPGEffectManager rpgEffectManager;
    private static DisplayInventoryManager displayInventoryManager;
    private static EffectManager effectManager;
    private static CompiledScriptManager compiledScriptManager;
    private static ScriptEventManager scriptEventManager;
    private static PlaceHolderDataManager placeHolderDataManager;
    private static String version;
    @TInject("config.yml")
    private TConfig config;
    private File effects;
    private File inventories;

    public static PlaceHolderDataManager getPlaceHolderDataManager() {
        return placeHolderDataManager;
    }

    public static CompiledScriptManager getCompiledScriptManager() {
        return compiledScriptManager;
    }

    public static ScriptEventManager getScriptEventManager() {
        return scriptEventManager;
    }

    public static RPGLib getInstance() {
        return instance;
    }

    public static ScheduledThreadPoolExecutor getThreadPool() {
        return THREAD_POOL;
    }

    public static RPGLibConfigManager getConfigManager() {
        return configManager;
    }

    public static boolean isIsPlaceHolderAPIEnable() {
        return isPlaceHolderAPIEnable;
    }

    public static RPGEffectManager getRPGEffectManager() {
        return rpgEffectManager;
    }

    public static BaseEffectManager getBaseEffectManager() {
        return baseEffectManager;
    }

    public static RPGPlaceHolderAPI getRPGPlaceHolderAPI() {
        return rpgPlaceHolderAPI;
    }

    public static boolean isAttributeSystem() {
        return isAttributeSystemEnable;
    }

    public static DisplayInventoryManager getDisplayInventoryManager() {
        return displayInventoryManager;
    }

    public static EffectManager getEffectManager() {
        return effectManager;
    }

    public static DataSource getDatabase() {
        return database;
    }

    public static SQLTable getName_uuid() {
        return name_uuid;
    }

    public static String getVersion() {
        return version;
    }

    public File getInventories() {
        return this.inventories;
    }

    public TConfig getConfig() {
        return this.config;
    }

    private void init() {
        instance = this;
        this.effects = new File(this.getPlugin().getDataFolder() + "/effects");
        this.inventories = new File(this.getPlugin().getDataFolder() + "/inventories");
        rpgPlaceHolderAPI = new RPGPlaceHolderAPIImpl();
        rpgEffectManager = new RPGEffectManagerImpl();
        configManager = new RPGLibConfigManager();
        baseEffectManager = new BaseEffectManagerImpl();
        displayInventoryManager = new DisplayInventoryManagerImpl();
        effectManager = new EffectManagerImpl();
        try {
//
            ScriptTool.StaticClass = System.getProperty("java.version").contains("1.8.") ? Class.forName("jdk.internal.dynalink.beans.StaticClass") : Class.forName("jdk.dynalink.beans.StaticClass");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        compiledScriptManager = new CompiledScriptManagerImpl();
        scriptEventManager = new ScriptEventManagerImpl();
        placeHolderDataManager = new PlaceHolderDataManagerImpl();
        if (!this.effects.exists()) {
            saveResource("effects/example.yml", true, this.getPlugin(), null);
        }
        if (!this.inventories.exists()) {
            this.inventories.mkdirs();
            /* saveResource("inventories/select.yml",true,this.getPlugin(),null);*/
        }
        BaseEffect.register(AttributeEffect.class);
        BaseEffect.register(PermissionEffect.class);
        BaseEffect.register(PotionEffect.class);
        configManager.reload();
        this.config.reload();
        isPlaceHolderAPIEnable = Bukkit.getPluginManager().isPluginEnabled("PlaceholderAPI");
        TLocale.reload();
        FixedData.load();
        version = Bukkit.getServer().getClass().getPackage().getName().replace(".", ",").split(",")[3];
        Listener listener;
        if (version.contains("1_16")) {
            listener = new ScriptListener();
        } else {
            listener = new LegacyScriptListener();
        }
        Bukkit.getPluginManager().registerEvents(listener, this.getPlugin());
    }

    @Override
    public void onLoad() {
        sendConsole("&d[&9RPGLib&d] " + "&eRPGLib 正在加载.... &6作者: Glom_ &3QQ: 88595433");
    }

    @Override
    public void onEnable() {
        this.init();
    }

    @Override
    public void onActive() {
        isAttributeSystemEnable = Bukkit.getPluginManager().isPluginEnabled("AttributeSystem");
        isAttributePlusEnable = Bukkit.getPluginManager().isPluginEnabled("AttributePlus");
        SQLHost sqlHost = new SQLHost(this.config.getConfigurationSection("mysql"), this.getPlugin());
        sendConsole(getConfigManager().getPrefix() + "&aRPGLib 正在启用.... &e作者: Glom_ &bQQ: 88595433");
        sendConsole(" &e地址: &6" + sqlHost.getConnectionUrl());
        sendConsole(" &e数据库名称: &6" + sqlHost.getDatabase());
        sendConsole(" &e用户: &6" + sqlHost.getUser());
        sendConsole(" &e密码: &6" + sqlHost.getPassword());
        boolean mysql = true;
        try {
            DriverManager.getConnection(
                    sqlHost.getConnectionUrl(),
                    sqlHost.getUser(), sqlHost.getPassword());
        } catch (Exception e) {
            mysql = false;
        }
        if (mysql) {
            database = sqlHost.createDataSource();
        } else {
            sendConsole(getConfigManager().getPrefix() + "&c未发现 &6Mysql &c数据库, 转为 &6SQLite &c数据库!");
            SQLiteHost sqLiteHost = new SQLiteHost(new File(this.getPlugin().getDataFolder(), "data.db"), this.getPlugin());
            sqLiteHost.getConnectionUrl();
            SQLiteConfig config = new SQLiteConfig();
            config.setSharedCache(true);
            config.enableRecursiveTriggers(true);
            config.setEncoding(SQLiteConfig.Encoding.UTF8);
            database = new SQLiteDataSource(config);
            ((SQLiteDataSource) database).setUrl(sqLiteHost.getConnectionUrl());
        }

        sendConsole("&5依赖信息: ");
        boolean isMythicMobsEnable = Bukkit.getPluginManager().isPluginEnabled("MythicMobs");
        sendConsole("  &b- &6AttributeSystem " + (isAttributeSystemEnable ? "&2&l√" : "&4&l×"));
        sendConsole(isAttributeSystemEnable ? "   &a已发现 &6AttributeSystem&a, 兼容!" : "   &c未发现 &6AttributeSystem&c, 跳过兼容!");
        sendConsole("  &b- &6AttributePlus " + (isAttributePlusEnable ? "&2&l√" : "&4&l×"));
        sendConsole(isAttributePlusEnable ? "   &a已发现 &6AttributePlus&a, 兼容!" : "   &c未发现 &6AttributePlus&c, 跳过兼容!");
        sendConsole("  &b- &6PlaceholderAPI " + (RPGLib.isIsPlaceHolderAPIEnable() ? "&2&l√" : "&4&l×"));
        sendConsole(RPGLib.isIsPlaceHolderAPIEnable() ? "   &a已发现 &6PlaceholderAPI&a, 兼容!" : "   &c未发现 &6PlaceholderAPI&c, 启动失败!");
        sendConsole("  &b- &6MythicMobs " + (isMythicMobsEnable ? "&2&l√" : "&4&l×"));
        sendConsole(isMythicMobsEnable ? "   &a已发现 &6MythicMobs&a, 兼容!" : "   &c未发现 &6MythicMobs&c, 启动失败!");
        /*RPGLib.isAttributeSystem()*//* && */ if ( RPGLib.isIsPlaceHolderAPIEnable() && isMythicMobsEnable) {
            sendConsole(getConfigManager().getPrefix() + "&aRPGLib 启用成功! &5作者: Glom_ &6QQ: 88595433");
        } else {
            Bukkit.getPluginManager().disablePlugin(this.getPlugin());
            return;
        }

        name_uuid = new SQLTable("name_uuid")
                .column(
                        new SQLiteColumn(SQLiteColumnType.TEXT, 100, "name"),
                        new SQLiteColumn(SQLiteColumnType.TEXT, 100, "uuid")
                );
        name_uuid.create(database);
        if (isPlaceHolderAPIEnable)
            new RPGPlaceHolderAPIHooker().register();
        new ScriptHooker().register();
        if (isAttributeSystemEnable || isAttributePlusEnable)
            new AttributeEffect().register();
        new PermissionEffect().register();
        new PotionEffect().register();
        getThreadPool().scheduleAtFixedRate(() -> {
            getEffectManager().getKeys().forEach(uuid -> getEffectManager().realize(uuid));
        }, 100, 500, TimeUnit.MILLISECONDS);
        new ScriptHookPlaceHolderAPI().register();
    }

    @Override
    public void onDisable() {
        sendConsole(getConfigManager().getPrefix() + "&cRPGLib 正在卸载.... &d作者: Glom_ &5QQ: 88595433");
    }

    public File getEffects() {
        return this.effects;
    }


}

package com.skillw.rpglib.manager.script;

import com.skillw.rpglib.RPGLib;
import com.skillw.rpglib.api.manager.script.ScriptEventManager;
import com.skillw.rpglib.script.ScriptTool;
import com.skillw.rpglib.script.ScriptEvent;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.event.Event;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

public class ScriptEventManagerImpl extends ScriptEventManager {

    @Override
    public void add(String event, ScriptEvent scriptEvent) {
        if (getEventData().containsKey(event)) {
            getEventData().get(event).add(scriptEvent);
        } else {
            getEventData().put(event, new HashSet<>(Collections.singletonList(scriptEvent)));
        }
    }

    @Override
    public void handleEvent(Event event) {
        String name = event.getEventName();
        if (getEventData().containsKey(name)) {
            getEventData().get(name).forEach(scriptEvent -> {
                Map<String, Object> map = new ConcurrentHashMap<>();
                map.put("event", event);
                ScriptTool.invoke(scriptEvent.getPath(), map, scriptEvent.getFunction());
            });
        }
    }

    @Override
    public void reload() {
        getEventData().clear();
        ConfigurationSection eventSection = RPGLib.getConfigManager().getConfig().getConfigurationSection("script.events");
        for (String key : eventSection.getKeys(false)) {
            List<String> strings = eventSection.getStringList(key);
            for (String str : strings) {
                RPGLib.getScriptEventManager().add(key, new ScriptEvent(str));
            }
        }
    }
}

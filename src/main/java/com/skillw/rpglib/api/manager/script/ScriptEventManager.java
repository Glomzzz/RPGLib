package com.skillw.rpglib.api.manager.script;

import com.skillw.rpglib.RPGLib;
import com.skillw.rpglib.api.map.BaseMap;
import com.skillw.rpglib.script.ScriptEvent;
import org.bukkit.event.Event;

import java.util.*;

public abstract class ScriptEventManager extends BaseMap<String, Set<ScriptEvent>> {
    public static Map<String, Set<ScriptEvent>> getEventData() {
        return RPGLib.getScriptEventManager().map;
    }
    public abstract void add(String event, ScriptEvent scriptEvent);

    public abstract void handleEvent(Event event);

    public abstract void reload();
}

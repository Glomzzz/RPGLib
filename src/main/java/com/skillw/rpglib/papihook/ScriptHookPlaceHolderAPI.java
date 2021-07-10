package com.skillw.rpglib.papihook;

import com.skillw.rpglib.script.ScriptTool;
import me.clip.placeholderapi.PlaceholderAPI;
import me.clip.placeholderapi.expansion.PlaceholderExpansion;
import org.bukkit.OfflinePlayer;
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class ScriptHookPlaceHolderAPI extends PlaceholderExpansion {
    @Override
    public @NotNull String getIdentifier() {
        return "rpglib";
    }

    @Override
    public @NotNull String getAuthor() {
        return "Glom_";
    }

    @Override
    public @NotNull String getVersion() {
        return "1.0.0";
    }

    @Override
    public String onRequest(OfflinePlayer player, @NotNull String params) {
        if(params.contains("js")){
            Map<String,Object> map = new ConcurrentHashMap<>();
            map.put("player",player);
            return String.valueOf(ScriptTool.invokePathWithFunction(params.replace("js:",""),map));
        }
        return "Nope";
    }
}

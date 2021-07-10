package com.skillw.rpglib.api.manager.script;

import com.skillw.rpglib.RPGLib;
import com.skillw.rpglib.api.map.BaseMap;

import javax.script.*;
import java.io.File;
import java.util.*;

public abstract class CompiledScriptManager extends BaseMap<File, CompiledScript> {
    public static Map<File, CompiledScript> getCompiledData() {
        return RPGLib.getCompiledScriptManager().map;
    }

    public static List<String> getFilePaths() {
        List<String> result = new ArrayList<>();
        getCompiledData().keySet().forEach(file -> result.add(file.getPath().replace("\\\\\\\\", "/")));
        return result;
    }

    public abstract String getJdkVersion();

    public abstract ScriptEngine getScriptEngine();

    public abstract ScriptEngineManager getScriptEngineManager();

    public abstract void reload();

    public abstract CompiledScript compileScript(File file);

    public abstract CompiledScript compileScript(String filePath);

    public abstract Optional<Map.Entry<File, CompiledScript>> searchScript(String filePath);

    public abstract Set<File> getFiles();


    public abstract Object invoke(String filePath, Map<String, Object> variables, Object... args);

    public abstract Object invokePathWithFunction(String filePathWithFunction, Map<String, Object> variables, Object... args);

    public abstract Object invoke(String filePath, Map<String, Object> variables, String function, Object... args);


}

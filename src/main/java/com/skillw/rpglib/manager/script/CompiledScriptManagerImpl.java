package com.skillw.rpglib.manager.script;

import com.skillw.rpglib.RPGLib;
import com.skillw.rpglib.api.manager.script.CompiledScriptManager;
import com.skillw.rpglib.util.FileUtils;
import com.skillw.rpglib.util.MessageUtils;
import jdk.internal.dynalink.beans.StaticClass;
import org.bukkit.configuration.ConfigurationSection;

import javax.script.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.*;

import static com.skillw.rpglib.script.ScriptTool.staticClass;

public class CompiledScriptManagerImpl extends CompiledScriptManager {
    private final Set<File> files = new HashSet<>();
    private final ScriptEngineManager scriptEngineManager = new ScriptEngineManager();
    private final String jdkVersion = System.getProperty("java.specification.version");
    private final ScriptEngine scriptEngine;

    public CompiledScriptManagerImpl() {
        this.scriptEngine = this.scriptEngineManager.getEngineByName("JavaScript");
        if (this.scriptEngine == null) {
            MessageUtils.sendWrong("尚未支持&6Jdk " + this.jdkVersion);
            return;
        }
        this.load();
    }


    @Override
    public void reload() {
        this.map.clear();
        this.files.forEach(
                folder -> FileUtils.listFiles(folder)
                        .forEach(file -> this.map.put(file, Objects.requireNonNull(this.compileScript(file))))
        );
    }

    @Override
    public String getJdkVersion() {
        return this.jdkVersion;
    }

    @Override
    public ScriptEngine getScriptEngine() {
        return this.scriptEngine;
    }

    @Override
    public ScriptEngineManager getScriptEngineManager() {
        return this.scriptEngineManager;
    }

    public void load() {
        File scriptsFile = new File(RPGLib.getInstance().getPlugin().getDataFolder(), "scripts");
        this.files.add(scriptsFile);
        if (!scriptsFile.exists()) {
            scriptsFile.mkdirs();
        }
        this.reload();
    }

    @Override
    public CompiledScript compileScript(File file) {
        try {
            return ((Compilable) this.scriptEngine).compile(new InputStreamReader(new FileInputStream(file), StandardCharsets.UTF_8));
        } catch (ScriptException | FileNotFoundException e) {
            MessageUtils.sendWrong("脚本文件 &6" + file.getPath() + " &c编译失败! 请检查.");
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public CompiledScript compileScript(String filePath) {
        File file = new File(RPGLib.getConfigManager().getServer(), filePath);
        return this.compileScript(file);
    }

    @Override
    public Optional<Map.Entry<File, CompiledScript>> searchScript(String filePath) {
        Optional<Map.Entry<File, CompiledScript>> optional = this.map
                .entrySet()
                .stream()
                .filter(fileCompiledScriptEntry ->
                        fileCompiledScriptEntry.getKey().getAbsolutePath().replace("\\\\", "/").endsWith(filePath))
                .findFirst();
        if (!optional.isPresent()) {
            CompiledScript compiledScript = this.compileScript(filePath);
            if (compiledScript != null) {
                File file = new File(RPGLib.getConfigManager().getServer(), filePath);
                this.map.put(file, compiledScript);
                return this.map.entrySet().stream()
                        .filter(fileCompiledScriptEntry ->
                                fileCompiledScriptEntry.getKey().equals(file))
                        .findFirst();
            } else {
                MessageUtils.sendWrong("未发现脚本文件 &6" + filePath + " &c! 请检查.");
            }
        }
        return optional;
    }

    @Override
    public Set<File> getFiles() {
        return this.files;
    }

    @Override
    public Object invoke(String filePath, Map<String, Object> variables, Object... args) {
        return this.invoke(filePath, variables, "main", args);
    }

    @Override
    public Object invokePathWithFunction(String filePathWithFunction, Map<String, Object> variables, Object... args) {
        String[] strings = filePathWithFunction.split("::");
        return this.invoke(strings[0], variables, strings[1], args);
    }

    @Override
    public Object invoke(String filePath, Map<String, Object> variables, String function, Object... args) {
        Optional<Map.Entry<File, CompiledScript>> optional = RPGLib.getCompiledScriptManager().searchScript(filePath);
        if (optional.isPresent()) {
            Map.Entry<File, CompiledScript> entry = optional.get();
            CompiledScript compiledScript = entry.getValue();
            ScriptEngine scriptEngine = compiledScript.getEngine();
            variables.forEach(scriptEngine::put);
            RPGLib.getConfigManager().getStaticMap().forEach(scriptEngine::put);
            try {
                compiledScript.eval(scriptEngine.getContext());
            } catch (ScriptException e) {
                MessageUtils.sendWrong("执行脚本文件 &6" + filePath + " &c中的函数 &d" + function + " &c时发生了错误 !");
                e.printStackTrace();
            }
            Invocable invocable = (Invocable) scriptEngine;
            try {
                return invocable.invokeFunction(function, args);
            } catch (ScriptException e) {
                MessageUtils.sendWrong("执行脚本文件 &6" + filePath + " &c中的函数 &d" + function + " &c时发生了错误(或不存在此函数)!");
                e.printStackTrace();
            } catch (NoSuchMethodException e) {
                MessageUtils.sendWrong("执行脚本文件 &6" + filePath + " &c中的函数 &d" + function + " &c时发生了错误!");
                e.printStackTrace();
            }
        }
        return null;
    }
}

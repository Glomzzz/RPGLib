package com.skillw.rpglib.script;

import com.skillw.rpglib.RPGLib;
import com.skillw.rpglib.util.MessageUtils;
import io.izzel.taboolib.util.item.Items;
import me.clip.placeholderapi.expansion.PlaceholderExpansion;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.plugin.Plugin;
import org.jetbrains.annotations.NotNull;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;


public class ScriptTool {
    public static Class<?> StaticClass;

    public static void reload() {
        RPGLib.getCompiledScriptManager().reload();
        RPGLib.getScriptEventManager().reload();
    }

    public static void placeHolder(String identifier,String author,String version,String path){
        new PlaceholderExpansion() {
            @Override
            public @NotNull String getIdentifier() {
                return identifier;
            }

            @Override
            public @NotNull String getAuthor() {
                return author;
            }

            @Override
            public @NotNull String getVersion() {
                return version;
            }

            @Override
            public String onRequest(OfflinePlayer player, @NotNull String params) {
                return String.valueOf(invokePathWithFunction(path,new HashMap<>(),player,params));
            }
        };
    }

    public static Object invoke(String filePath, Map<String, Object> variables, Object... args) {
        return invoke(filePath, variables, "main", args);
    }

    public static Object invokePathWithFunction(String filePathWithFunction, Map<String, Object> variables, Object... args) {
        String[] strings = filePathWithFunction.split("::");
        return invoke(strings[0], variables, strings[1], args);
    }

    public static Object invoke(String filePath, Map<String, Object> variables, String function, Object... args) {
        return RPGLib.getCompiledScriptManager().invoke(filePath, variables, function, args);
    }

    public static boolean isPluginEnabled(String pluginName) {
        return Bukkit.getPluginManager().isPluginEnabled(pluginName);
    }

    public static Plugin getPlugin(String pluginName) {
        return Bukkit.getPluginManager().getPlugin(pluginName);
    }

    public static Object staticClass(String className) {
        try {
            return StaticClass.getMethod("forClass", Class.class).invoke(null, Class.forName(className));
        } catch (ClassNotFoundException e) {
            MessageUtils.sendWrong("类 &6" + className + " &c不存在!");
            e.printStackTrace();
        } catch (InvocationTargetException | IllegalAccessException | NoSuchMethodException e) {
            e.printStackTrace();
        }
        return null;
    }

    //    public static Object construct(StaticClass staticClass, Object... args) {
//        Optional<Constructor<?>> optional = Arrays.stream(staticClass.getRepresentedClass().getConstructors())
//                .filter(constructor -> constructor.getParameterTypes().length == args.length)
//                .filter(constructor -> {
//                    Class<?>[] parameterTypes = constructor.getParameterTypes();
//                    boolean is = true;
//                    for (int i = 0, parameterTypesLength = parameterTypes.length; i < parameterTypesLength; i++) {
//                        Class<?> clazz = parameterTypes[i];
//                        is = clazz.isAssignableFrom(args[i].getClass());
//                        if (!is) break;
//                    }
//                    return is;
//                }).findFirst();
//        if (optional.isPresent()) {
//            try {
//                return optional.get().newInstance(args);
//            } catch (InstantiationException | IllegalAccessException | InvocationTargetException e) {
//                e.printStackTrace();
//            }
//        }
//        return null;
//    }
    public static Object construct(Object staticClass, Object... args) {
        Optional<Constructor<?>> optional = null;
        try {
            Object representedClass = StaticClass.getMethod("getRepresentedClass").invoke(staticClass);
            optional = Arrays.stream((Constructor<?>[]) representedClass.getClass().getMethod("getConstructors").invoke(representedClass))
                    .filter(constructor -> constructor.getParameterTypes().length == args.length)
                    .filter(constructor -> {
                        Class<?>[] parameterTypes = constructor.getParameterTypes();
                        boolean is = true;
                        for (int i = 0, parameterTypesLength = parameterTypes.length; i < parameterTypesLength; i++) {
                            Class<?> clazz = parameterTypes[i];
                            is = clazz.isAssignableFrom(args[i].getClass());
                            if (!is) break;
                        }
                        return is;
                    }).findFirst();
        } catch (IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
            e.printStackTrace();
        }
        if (optional != null && optional.isPresent()) {
            try {
                return optional.get().newInstance(args);
            } catch (InstantiationException | IllegalAccessException | InvocationTargetException e) {
                e.printStackTrace();
            }
        }
        return null;
    }
}

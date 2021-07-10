package com.skillw.rpglib.util;

import com.skillw.rpglib.api.able.Keyable;
import io.izzel.taboolib.module.locale.TLocaleLoader;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.Plugin;

import java.io.*;
import java.lang.reflect.InvocationTargetException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.logging.Level;

import static com.skillw.rpglib.util.ColorUtils.unColor;
import static com.skillw.rpglib.util.MessageUtils.sendWrong;


/**
 * @ClassName : com.skillw.randomitem.util.FileUtils
 * Created by Glom_ on 2021-02-26 03:13:03
 * Copyright  2020 user. All rights reserved.
 */
public final class FileUtils {
    private FileUtils() {

    }
    public static List<File> listFiles(File file) {
        List<File> files = new ArrayList<>();
        if (file.isDirectory()) {
            for (File listFile : file.listFiles()) {
                files.addAll(listFiles(listFile));
            }
        } else {
            files.add(file);
        }
        return files;
    }

    public static String readFromFile(File file) {
        return readFromFile(file, 1024, StandardCharsets.UTF_8);
    }

    public static String readFromFile(File file, int size, Charset encode) {
        try {
            FileInputStream fileInputStream = new FileInputStream(file);
            String content;
            try {
                BufferedInputStream bin = new BufferedInputStream(fileInputStream);
                try {
                    content = readFromStream(fileInputStream, size, encode);
                } catch (Throwable throwable) {
                    try {
                        bin.close();
                    } catch (Throwable var8) {
                        throwable.addSuppressed(var8);
                    }

                    throw throwable;
                }

                bin.close();
            } catch (Throwable throwable) {
                try {
                    fileInputStream.close();
                } catch (Throwable var7) {
                    throwable.addSuppressed(var7);
                }

                throw throwable;
            }

            fileInputStream.close();
            return content;
        } catch (IOException exception) {
            exception.printStackTrace();
            return null;
        }
    }

    public static String readFromStream(InputStream in, int size, Charset encode) {
        try {
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            String content;
            try {
                byte[] b = new byte[size];

                while (true) {
                    int i;
                    if ((i = in.read(b)) <= 0) {
                        content = byteArrayOutputStream.toString(encode.name());
                        break;
                    }

                    byteArrayOutputStream.write(b, 0, i);
                }
            } catch (Throwable throwable) {
                try {
                    byteArrayOutputStream.close();
                } catch (Throwable var7) {
                    throwable.addSuppressed(var7);
                }

                throw throwable;
            }

            byteArrayOutputStream.close();
            return content;
        } catch (IOException exception) {
            exception.printStackTrace();
            return null;
        }
    }
    public static <T extends Keyable<?>> List<T> loadMultiply(File mainFile, Class<T> tClass){
        if (mainFile == null) return Collections.emptyList();
        for (File file: FileUtils.getSubFilesFromFile(mainFile)) {
            if(file == null) continue;
            YamlConfiguration config = FileUtils.loadConfigFile(file);
            for (String key:config.getKeys(false)) {
                try {
                    Object obj = (tClass.getDeclaredMethod("load",ConfigurationSection.class).invoke(null,config.getConfigurationSection(key)));
                    if(Keyable.class.isAssignableFrom(obj.getClass())){
                        ((Keyable<?>) obj).register();
                    }
                } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
                    e.printStackTrace();
                }

            }
        }
        return Collections.emptyList();
    }

    public static YamlConfiguration loadConfigFile(File file) {
        if (file == null) {
            return null;
        }
        YamlConfiguration config = new YamlConfiguration();
        try {
            config.load(file);
        } catch (Exception e) {
            sendWrong("Wrong config!");
            sendWrong("Cause: " + unColor(e.getCause().getMessage()));
        }
        if (config.getKeys(false).isEmpty()) {
            return null;
        }
        return config;
    }

    public static List<File> getSubFilesFromFile(File file) {
        if(file == null) return null;
        List<File> files = new ArrayList<>();
        File[] allFiles = file.listFiles();
        if (allFiles == null) {
            return files;
        }
        for (File subFile : allFiles) {
            if (subFile.isFile() && subFile.getName().endsWith(".yml")) {
                files.add(subFile);
                continue;
            }
            files.addAll(getSubFilesFromFile(subFile));
        }
        return files;
    }

    //Form org.bukkit.plugin.java.JavaPlugin
    public static void saveResource( String resourcePath, boolean replace, Plugin plugin, String language) {
        if (resourcePath != null && !resourcePath.isEmpty()) {
            resourcePath = (language == null ? "" : language) + resourcePath.replace('\\', '/');
            InputStream in = plugin.getResource(resourcePath);
            if (in == null) {
                String lang = TLocaleLoader.getLocalPriorityFirst(plugin);
                sendWrong("The language &b" + lang + " &c doesn't exist!!");
                in = plugin.getResource(resourcePath.replace(lang, "en_US"));
            }
            if (resourcePath.contains("languages") && language != null) {
                resourcePath = resourcePath.split(language)[1];
            }
            File outFile = new File(plugin.getDataFolder(), resourcePath);
            int lastIndex = resourcePath.lastIndexOf(47);
            File outDir = new File(plugin.getDataFolder(), resourcePath.substring(0, Math.max(lastIndex, 0)));
            if (!outDir.exists()) {
                outDir.mkdirs();
            }
            try {
                if (outFile.exists() && !replace) {
                    plugin.getLogger().log(Level.WARNING, "Could not save " + outFile.getName() + " to " + outFile + " because " + outFile.getName() + " already exists.");
                } else {
                    OutputStream out = new FileOutputStream(outFile);
                    byte[] buf = new byte[1024];
                    int len;
                    while ((len = in.read(buf)) > 0) {
                        out.write(buf, 0, len);
                    }
                    out.close();
                    in.close();
                }
            } catch (IOException var10) {
                plugin.getLogger().log(Level.SEVERE, "Could not save " + outFile.getName() + " to " + outFile, var10);
            }
        } else {
            throw new IllegalArgumentException("ResourcePath cannot be null or empty");
        }
    }
}

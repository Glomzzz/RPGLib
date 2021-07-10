package com.skillw.rpglib.util;

import io.izzel.taboolib.module.locale.TLocale;

import java.util.List;

/**
 * ClassName : com.skillw.classsystem.util.ColorUtils
 * Created by Glom_ on 2021-03-25 20:26:01
 * Copyright  2021 user. All rights reserved.
 */
public final class ColorUtils {
    private ColorUtils() {
    }

    public static String color(String msg) {
        return TLocale.Translate.setColored(msg);
    }

    public static List<String> color(List<String> msgs) {
        return TLocale.Translate.setColored(msgs);
    }

    public static String unColor(String msg) {
        return TLocale.Translate.setUncolored(msg);
    }

    public static List<String> unColor(List<String> msgs) {
        return TLocale.Translate.setUncolored(msgs);
    }

}

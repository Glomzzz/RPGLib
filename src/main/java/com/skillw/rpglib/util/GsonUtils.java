package com.skillw.rpglib.util;

import io.izzel.taboolib.internal.gson.Gson;

public final class GsonUtils {
    private static final Gson GSON;

    static {
        GSON = new Gson();
    }

    private GsonUtils() {

    }

    public static Gson getGson() {

        return GSON;
    }

}

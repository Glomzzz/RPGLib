package com.skillw.rpglib.api.map;


import com.skillw.rpglib.api.able.Keyable;

/**
 * ClassName : com.skillw.classsystem.api.map.KeyMap
 * Created by Glom_ on 2021-03-26 22:03:17
 * Copyright  2021 user. All rights reserved.
 */
public abstract class KeyMap<K, V extends Keyable<K>> extends BaseMap<K, V> {
    protected K getId(V v) {
        return v.getKey();
    }

    public void register(V v) {
        this.map.put(this.getId(v), v);
    }

    public void removeByValue(V v) {
        this.map.remove(this.getId(v));
    }
}

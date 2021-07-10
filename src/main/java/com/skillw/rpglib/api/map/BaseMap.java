package com.skillw.rpglib.api.map;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

/**
 * ClassName : com.skillw.classsystem.api.map.BaseMap
 * Created by Glom_ on 2021-03-25 20:05:44
 * Copyright  2021 user. All rights reserved.
 */
public abstract class BaseMap<K, V> {
    protected ConcurrentHashMap<K, V> map = new ConcurrentHashMap<>();

    public void register(K k, V v) {
        this.map.put(k, v);
    }

    public void removeByKey(K k) {
        this.map.remove(k);
    }

    public void clear() {
        this.map.clear();
    }

    public Set<K> getKeys() {
        return new HashSet<>(this.map.keySet());
    }

    public List<V> getObjects() {
        return new LinkedList<>(this.map.values());
    }

    public boolean hasKey(K k) {
        return this.map.containsKey(k);
    }

    public boolean hasObject(V v) {
        return this.map.contains(v);
    }

    public V get(K k) {
        return this.map.get(k);
    }

}

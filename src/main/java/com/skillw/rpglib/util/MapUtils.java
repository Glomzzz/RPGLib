package com.skillw.rpglib.util;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

/**
 * ClassName : com.skillw.rpglib.util.MapUtils
 * Created by Glom_ on 2021-03-28 21:59:37
 * Copyright  2021 user. All rights reserved.
 */
public final class MapUtils {
    private MapUtils(){

    }

    public static <K,V>  Map<K, LinkedList<V>> add(Map<K, LinkedList<V>> map,K key,V value) {
        if (!map.containsKey(key)) map.put(key, new LinkedList<>(Collections.singletonList(value)));
        else map.get(key).add(value);
        return map;
    }

    public static <K,V,Z>  Map<K, Map<Z,V>> put(Map<K, Map<Z,V>> map,K key1,Z key2,V value) {
        if (!map.containsKey(key1)) {
            Map<Z,V> map1 = new ConcurrentHashMap<>();
            map1.put(key2,value);
            map.put(key1, map1);
        } else map.get(key1).put(key2,value);
        return map;
    }

    public static <K,Z,V>  List<V> getValues(Map<K, Map<Z,V>> map) {
        List<V> list = new LinkedList<>();
        map.forEach((k1,v1) -> v1.forEach((k2, z) -> list.add(z)));
        return list;
    }
}

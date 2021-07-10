package com.skillw.rpglib.api.map;

import com.skillw.rpglib.api.able.Keyable;

/**
 * ClassName : com.skillw.rpglib.api.map.CloneMap
 * Created by Glom_ on 2021-04-10 22:47:39
 * Copyright  2021 user. All rights reserved.
 */
public abstract class CloneMap<K, V extends com.skillw.rpglib.api.able.Cloneable<K,V>> extends KeyMap<K,V>{
    @Override
    public V get(K k){
        return map.get(k).clone();
    }
}

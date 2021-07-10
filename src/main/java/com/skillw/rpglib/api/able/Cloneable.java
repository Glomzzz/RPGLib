package com.skillw.rpglib.api.able;

/**
 * ClassName : com.skillw.rpglib.api.able.Cloneable
 * Created by Glom_ on 2021-04-10 22:46:16
 * Copyright  2021 user. All rights reserved.
 */
public interface Cloneable<K,V> extends Keyable<K> {
    V clone();
}

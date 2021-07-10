package com.skillw.rpglib.api.able;

/**
 * ClassName : com.skillw.classsystem.api.able.Keyable
 * Created by Glom_ on 2021-03-27 20:54:22
 * Copyright  2021 user. All rights reserved.
 */
public interface Keyable<K> {
    K getKey();
    void register();
}

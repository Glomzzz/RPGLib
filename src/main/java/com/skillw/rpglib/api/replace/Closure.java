package com.skillw.rpglib.api.replace;

/**
 * ClassName : com.skillw.rpglib.api.replace.Closure
 * Created by Glom_ on 2021-03-28 17:21:12
 * Copyright  2021 user. All rights reserved.
 */
public enum Closure {
    /*

     */
    BRACKET('{', '}'),
    PERCENT('%', '%');

    public final char head;
    public final char tail;

     private Closure(char head, char tail) {
        this.head = head;
        this.tail = tail;
    }
}
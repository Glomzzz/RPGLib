package com.skillw.rpglib.api.inventory;

import com.skillw.rpglib.util.NumberUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * ClassName : com.skillw.rpglib.api.inventory.ArraySlotList
 * Created by Glom_ on 2021-04-02 22:40:56
 * Copyright  2021 user. All rights reserved.
 */
public class ArraySlotList {
    public List<Integer> getArraySlotList() {
        return slots;
    }

    private final List<Integer> slots;

    public ArraySlotList(List<String> slots) {
        this.slots = new ArrayList<>();
        for (String slot:slots) {
            for (Integer integer:NumberUtils.getNumbers(slot,53)){
                if(!this.slots.contains(integer)) this.slots.add(integer);
            }
        }
    }
}

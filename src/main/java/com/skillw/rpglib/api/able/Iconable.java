package com.skillw.rpglib.api.able;

import org.bukkit.inventory.ItemStack;

/**
 * ClassName : com.skillw.rpglib.api.able.Iconable
 * Created by Glom_ on 2021-04-03 23:50:47
 * Copyright  2021 user. All rights reserved.
 */
public interface Iconable<T> extends Keyable<T>{
    ItemStack getIcon();
}

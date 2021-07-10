package com.skillw.rpglib.util

import com.skillw.rpglib.RPGLib
import com.skillw.rpglib.util.ItemUtils.getItem
import io.izzel.taboolib.module.nms.NMSImpl
import io.izzel.taboolib.util.item.Items
import io.lumine.xikage.mythicmobs.MythicMobs
import org.bukkit.entity.Player
import org.bukkit.inventory.ItemStack
import org.bukkit.inventory.meta.ItemMeta

/**
 * ClassName : com.skillw.classsystem.util.ItemUtils
 * Created by Glom_ on 2021-03-12 23:40:01
 * Copyright  2021 user. All rights reserved.
 */
object ItemUtils {
    @JvmStatic
    fun papiItem(item: ItemStack, player: Player): ItemStack {
        if (!item.hasItemMeta()) {
            return item
        }
        val meta = item.itemMeta
        meta as ItemMeta
        if (meta.hasDisplayName()) {
            meta.setDisplayName(RPGLib.getRPGPlaceHolderAPI().replace(player,meta.displayName));
        }
        if (meta.hasLore()) {
            val lores = ArrayList<String>()
            for (lore in meta.lore!!) {
                lores.add(RPGLib.getRPGPlaceHolderAPI().replace(player, lore))
            }
            meta.lore = lores
        }
        item.itemMeta = meta
        return item
    }

    @JvmStatic
    fun getItem(itemID: String, player: Player): ItemStack? {
        val item = MythicMobs.inst().itemManager.getItemStack(itemID)
        if (item != null) {
            return papiItem(item, player)
        }
        return null
    }

    @JvmStatic
    fun getItem(itemID: String): ItemStack? {
        val item = MythicMobs.inst()?.itemManager?.getItemStack(itemID)
        if (item != null) {
            return item
        }
        return null
    }

    @JvmStatic
    fun getItems(list:List<String>,player: Player?) : ArrayList<ItemStack>{
        val itemStacks: ArrayList<ItemStack> = java.util.ArrayList()
        for (id in list) {
            val itemStack = if (player == null) getItem(id) else getItem(
                id, player
            )
            if (itemStack != null) itemStacks.add(itemStack)
        }
        return itemStacks
    }

}
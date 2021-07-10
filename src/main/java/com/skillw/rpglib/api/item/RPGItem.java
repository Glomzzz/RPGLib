package com.skillw.rpglib.api.item;


import com.skillw.rpglib.RPGLib;
import com.skillw.rpglib.api.able.Keyable;
import com.skillw.rpglib.api.map.BaseMap;
import com.skillw.rpglib.util.GsonUtils;
import io.izzel.taboolib.internal.gson.Gson;
import io.izzel.taboolib.module.nms.NMSImpl;
import io.izzel.taboolib.module.nms.nbt.NBTBase;
import io.izzel.taboolib.module.nms.nbt.NBTCompound;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;

import java.util.Map;
import java.util.UUID;

public class RPGItem {
    private final ItemStack itemStack;
    private NBTCompound nbtCompound;

    public RPGItem(@NotNull Material type) {
        this.itemStack = new ItemStack(type, 1);
        nbtCompound = NMSImpl.handle().loadNBT(this.itemStack);
    }

    public RPGItem(@NotNull Material type, int amount) {
        this.itemStack = new ItemStack(type, amount, (short) 0);
        nbtCompound = NMSImpl.handle().loadNBT(this.itemStack);
    }

    public RPGItem(@NotNull ItemStack stack) {
        this.itemStack = new ItemStack(stack);
        nbtCompound = NMSImpl.handle().loadNBT(this.itemStack);
    }

    public NBTCompound getNbtCompound() {
        return nbtCompound;
    }

    public void setNbtCompound(NBTCompound nbtCompound) {
        this.nbtCompound = nbtCompound;
    }

    public ItemStack getItemStack() {
        return NMSImpl.handle().saveNBT(this.itemStack, nbtCompound);
    }

}

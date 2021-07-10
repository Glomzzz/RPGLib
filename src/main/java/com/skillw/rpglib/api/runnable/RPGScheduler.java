package com.skillw.rpglib.api.runnable;

import com.skillw.rpglib.RPGLib;
import org.bukkit.Bukkit;
import org.bukkit.scheduler.BukkitScheduler;

import java.util.LinkedList;
import java.util.List;

/**
 * ClassName : com.skillw.rpglib.api.runnable.RPGScheduler
 * Created by Glom_ on 2021-04-11 15:49:45
 * Copyright  2021 user. All rights reserved.
 */
public class RPGScheduler {
    private final List<RPGRunnable> rpgRunnableList = new LinkedList<>();
    public RPGScheduler(){

    }
    public List<RPGRunnable> add(Runnable runnable){
        rpgRunnableList.add(new RPGRunnable(runnable));
        return rpgRunnableList;
    }

    public List<RPGRunnable> getRpgRunnableList() {
        return rpgRunnableList;
    }

    public void run(){
        BukkitScheduler bukkitScheduler = Bukkit.getScheduler();
        bukkitScheduler.runTask(RPGLib.getInstance().getPlugin(), () -> rpgRunnableList.forEach(RPGRunnable::run));
    }
}

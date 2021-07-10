package com.skillw.rpglib.api.runnable;

/**
 * ClassName : com.skillw.rpglib.api.runnable.RPGRunnable
 * Created by Glom_ on 2021-04-11 15:48:14
 * Copyright  2021 user. All rights reserved.
 */
public class RPGRunnable {
    private final Runnable runnable;

    public Runnable getRunnable() {
        return runnable;
    }

    public RPGRunnable(Runnable runnable){
        this.runnable = runnable;
    }

    public void run(){
        runnable.run();
    }
}

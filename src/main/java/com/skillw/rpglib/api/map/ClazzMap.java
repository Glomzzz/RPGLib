package com.skillw.rpglib.api.map;

import com.skillw.rpglib.api.able.Keyable;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

/**
 * ClassName : com.skillw.classsystem.api.map.ClazzMap
 * Created by Glom_ on 2021-03-26 21:47:29
 * Copyright  2021 user. All rights reserved.
 */
public abstract class ClazzMap<T extends Keyable<String>> extends BaseMap<String, Class<T>> {

    public String getId(Class<T> clazz) {
        try {
            return (clazz.newInstance()).getKey();
        } catch (IllegalAccessException | InstantiationException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void register(Class<T> clazz) {
        this.map.put(this.getId(clazz), clazz);
    }

    public void removeByValue(Class<T> clazz) {
        this.map.remove(this.getId(clazz));
    }

    @Override
    public boolean hasObject(Class<T> clazz) {
        return this.map.containsKey(this.getId(clazz));
    }

    public T getObject(String key,Object... params) {
        if(!this.map.containsKey(key)) return null;
        try {
            Class<?>[] classes = null;
            if(params.length != 0){
                classes = new Class[params.length];
                for (int i = 0; i < params.length; i++) {
                    classes[i] =params[i].getClass();
                }
            }
            Constructor<T> constructor = (classes == null) ? this.map.get(key).getConstructor() : this.map.get(key).getConstructor(classes);
            return constructor.newInstance(params);
        } catch (InstantiationException | IllegalAccessException | NoSuchMethodException | InvocationTargetException e) {
            e.printStackTrace();
        }
        return null;
    }

    public T getObject(String key) {
        try {
            Constructor<T> constructor = this.map.get(key).getConstructor();
            return constructor.newInstance();
        } catch (InstantiationException | IllegalAccessException | NoSuchMethodException | InvocationTargetException e) {
            e.printStackTrace();
        }
        return null;
    }
}

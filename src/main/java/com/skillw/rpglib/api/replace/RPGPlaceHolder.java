package com.skillw.rpglib.api.replace;

import com.skillw.rpglib.RPGLib;
import com.skillw.rpglib.api.able.Keyable;
import org.bukkit.entity.LivingEntity;

/**
 * ClassName : com.skillw.rpglib.api.replace.RPGPlaceHolder
 * Created by Glom_ on 2021-03-28 15:40:22
 * Copyright  2021 user. All rights reserved.
 */
public abstract class RPGPlaceHolder implements Keyable<String> {
    private String author = null;
    protected String identifier;
    private String version = null;

    public RPGPlaceHolder( String identifier,String author, String version) {
        this.author = author;
        this.identifier = identifier;
        this.version = version;
    }

    @Override
    public String getKey(){
        return identifier;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getIdentifier() {
        return identifier;
    }

    public void setIdentifier(String identifier) {
        this.identifier = identifier;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public abstract String onPlaceHolderReplace(String replaced, LivingEntity livingEntity,String def);

    @Override
    public void register(){
        if(identifier != null) {
            RPGLib.getPlaceHolderDataManager().register(identifier,this);
        }
    }
}

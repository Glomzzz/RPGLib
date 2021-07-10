package com.skillw.rpglib.api.formula;

import com.skillw.rpglib.RPGLib;
import com.skillw.rpglib.util.CalculationUtils;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;

import java.util.concurrent.ConcurrentHashMap;

import static com.skillw.rpglib.util.CalculationUtils.getResult;

/**
 * ClassName : com.skillw.classsystem.formula.Formula
 * Created by Glom_ on 2021-03-25 20:49:58
 * Copyright  2021 user. All rights reserved.
 */
public class Formula {
    private final ConcurrentHashMap<String, String> replacements = new ConcurrentHashMap<>();
    private final String formula;
    private final LivingEntity livingEntity;

    public Formula(String formula, LivingEntity livingEntity) {
        this.formula = formula;
        this.livingEntity = livingEntity;
    }

    public Formula replace(String replaced, String replace) {
        this.replacements.put(replaced, replace);
        return this;
    }

    public String formula() {
        return RPGLib.getRPGPlaceHolderAPI().replace(livingEntity,CalculationUtils.replace(this.formula, this.replacements));
    }

    public double result() {
            return getResult(formula(), this.livingEntity, this.replacements);
    }
}

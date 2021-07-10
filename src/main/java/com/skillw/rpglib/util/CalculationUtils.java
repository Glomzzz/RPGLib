package com.skillw.rpglib.util;

import com.skillw.rpglib.RPGLib;
import com.skillw.rpglib.api.manager.ConfigManager;
import io.izzel.taboolib.TabooLibAPI;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.concurrent.ConcurrentHashMap;

import static com.skillw.rpglib.util.MessageUtils.sendConsole;

/**
 * @ClassName : com.skillw.randomitem.utils.CalculationUtils
 * Created by Glom_ on 2021-02-15 17:37:40
 * Copyright  2020 user. All rights reserved.
 */
public final class CalculationUtils {
    private static boolean isRightFormat = true;

    private CalculationUtils() {
    }

    public static double getResult(String formula, LivingEntity livingEntity, ConcurrentHashMap<String, String> replaceds) {
        for (String key : replaceds.keySet()) {
            formula = formula.replace(key, replaceds.get(key));
        }
        return getResult(RPGLib.getRPGPlaceHolderAPI().replace(livingEntity,formula));
    }

    public static String replace(String formula, ConcurrentHashMap<String, String> replaceds) {
        for (String key : replaceds.keySet()) {
            formula = formula.replace(key, replaceds.get(key));
        }
        return formula;
    }

    public static double getResult(String formula, ConcurrentHashMap<String, String> replaceds) {
        return getResult(replace(formula, replaceds));
    }

    /**
     * To get the result of the formula
     *
     * @param formula the formula
     * @return the result of the formula
     */
    public static double getResult(String formula) {
        double returnValue = 0;
        try {
            returnValue = doAnalysis(formula);
        } catch (NumberFormatException nfe) {
            sendConsole(ConfigManager.getPluginPrefix() + "&cFormula format error, please check: &e" + formula);
            return 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (!isRightFormat) {
            sendConsole(ConfigManager.getPluginPrefix() + "&cFormula format error, please check: &e" + formula);
            return 0;
        }
        return returnValue;
    }

    private static double doAnalysis(String formula) {
        double returnValue = 0;
        LinkedList<Integer> stack = new LinkedList<>();
        int curPos;
        String beforePart;
        String afterPart;
        String calculator;
        isRightFormat = true;
        while (isRightFormat && (formula.indexOf('(') >= 0 || formula.indexOf(')') >= 0)) {
            curPos = 0;
            for (char s : formula.toCharArray()) {
                if (s == '(') {
                    stack.add(curPos);
                } else if (s == ')') {
                    if (stack.size() > 0) {
                        beforePart = formula.substring(0, stack.getLast());
                        afterPart = formula.substring(curPos + 1);
                        calculator = formula.substring(stack.getLast() + 1, curPos);
                        formula = beforePart + doCalculation(calculator) + afterPart;
                        stack.clear();
                        break;
                    } else {
                        sendConsole(ConfigManager.getPluginPrefix() + "&cthere is an unclosed right bracket! &e" + formula);
                        isRightFormat = false;
                    }
                }
                curPos++;
            }
            if (stack.size() > 0) {
                sendConsole(ConfigManager.getPluginPrefix() + "&cthere is an unclosed left bracket! &e" + formula);
                break;
            }
        }
        if (isRightFormat) {
            returnValue = doCalculation(formula);
        }
        return returnValue;
    }

    private static double doCalculation(String formula) {
        ArrayList<Double> values = new ArrayList<>();
        ArrayList<String> operators = new ArrayList<>();
        int curPos = 0;
        int prePos = 0;
        int minus = 0;
        for (char s : formula.toCharArray()) {
            boolean is4 = (s == '+' || s == '-' || s == '*' || s == '/') && minus != 0 && minus != 2;
            if (is4) {
                values.add(Double.parseDouble(formula.substring(prePos, curPos).trim()));
                operators.add("" + s);
                prePos = curPos + 1;
                minus = minus + 1;
            } else {
                minus = 1;
            }
            curPos++;
        }
        values.add(Double.parseDouble(formula.substring(prePos).trim()));
        char op;
        for (curPos = 0; curPos <= operators.size() - 1; curPos++) {
            op = operators.get(curPos).charAt(0);
            switch (op) {
                case '*':
                    values.add(curPos, values.get(curPos) * values.get(curPos + 1));
                    values.remove(curPos + 1);
                    values.remove(curPos + 1);
                    operators.remove(curPos);
                    curPos = -1;
                    break;
                case '/':
                    values.add(curPos, values.get(curPos) / values.get(curPos + 1));
                    values.remove(curPos + 1);
                    values.remove(curPos + 1);
                    operators.remove(curPos);
                    curPos = -1;
                    break;
                default:
                    break;
            }
        }
        for (curPos = 0; curPos <= operators.size() - 1; curPos++) {
            op = operators.get(curPos).charAt(0);
            switch (op) {
                case '+':
                    values.add(curPos, values.get(curPos) + values.get(curPos + 1));
                    values.remove(curPos + 1);
                    values.remove(curPos + 1);
                    operators.remove(curPos);
                    curPos = -1;
                    break;
                case '-':
                    values.add(curPos, values.get(curPos) - values.get(curPos + 1));
                    values.remove(curPos + 1);
                    values.remove(curPos + 1);
                    operators.remove(curPos);
                    curPos = -1;
                    break;
                default:
                    break;
            }
        }
        return values.get(0);
    }
}

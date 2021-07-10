package com.skillw.rpglib.util;

import static com.skillw.rpglib.util.MessageUtils.sendWrong;

/**
 * ClassName : com.skillw.classsystem.util.EquationUtils
 * Created by Glom_ on 2021-03-25 21:13:38
 * Copyright  2021 user. All rights reserved.
 */
public final class EquationUtils {
    private EquationUtils() {

    }

    /**
     * 此类方法用于将字符串化为: aX+b 结构，结果返回a,b
     *
     * @param str:需要计算的字符串
     */
    private static Result translate(String str) {
        str = deleteKH(str);
        char[] chars = str.toCharArray();
        //先根据 + 号 将字符串分割完
        for (int i = 0, kuHaoNum = 0; i < chars.length; i++) {
            kuHaoNum = getBracketsNum(kuHaoNum, i, chars);
            if (kuHaoNum == 0 && ("+".equals(chars[i] + ""))) { //括号里面不分割
                String s01 = str.substring(0, i);
                String s02 = str.substring(i + 1, str.length());
                Result result01 = getResultFromString(s01);
                Result result02 = getResultFromString(s02);
                Result result = new Result();
                result.setA(result01.getA() + result02.getA());
                result.setB(result01.getB() + result02.getB());
                return result;
            }
        }
        //再根据 - 号 将字符串分割完  注意：- 号是从后往前分割
        for (int i = chars.length - 1, kuHaoNum = 0; i >= 0; i--) {
            kuHaoNum = getBracketsNum(kuHaoNum, i, chars);
            if (kuHaoNum == 0 && ("-".equals(chars[i] + ""))) { //括号里面不分割
                String s01 = str.substring(0, i);
                String s02 = str.substring(i + 1, str.length());
                Result result01 = getResultFromString(s01);
                Result result02 = getResultFromString(s02);
                Result result = new Result();
                result.setA(result01.getA() - result02.getA());
                result.setB(result01.getB() - result02.getB());
                return result;
            }
        }
        //最后根据 *，/ 号 将字符串分割
        for (int i = 0, kuHaoNum = 0; i < chars.length; i++) {
            kuHaoNum = getBracketsNum(kuHaoNum, i, chars);
            if (kuHaoNum == 0 && ("*".equals(chars[i] + "") || "/".equals(chars[i] + ""))) { //括号里面不分割
                String s01 = str.substring(0, i);
                String fuhao = str.substring(i, i + 1);
                String s02 = str.substring(i + 1, str.length());
                Result result01 = getResultFromString(s01);
                Result result02 = getResultFromString(s02);
                Result result = new Result();
                if (fuhao.equals("*")) {  //因为是一元一次方程  不会出现 （aX+b）*(aX+b)的情况
                    if (result01.getA() != 0) {
                        result.setA(result01.getA() * result02.getB());
                        result.setB(result01.getB() * result02.getB());
                    }
                    if (result02.getA() != 0) {
                        result.setA(result01.getB() * result02.getA());
                        result.setB(result01.getB() * result02.getB());
                    }
                    if (result01.getA() == 0 && result02.getA() == 0) {
                        result.setA(0.0);
                        result.setB(result01.getB() * result02.getB());
                    }
                } else if (fuhao.equals("/")) {
                    result.setA(result01.getA() / result02.getB());
                    result.setB(result01.getB() / result02.getB());
                }
                return result;
            }
        }
        return null;
    }


    /**
     * 此类方法用于获取从起始位置到当前位置经历了几个括号
     *
     * @param num:括号数量
     * @param index:字符数组的位置
     * @param chars:字符数组
     */
    private static int getBracketsNum(int num, int index, char[] chars) {
        if ("(".equals(chars[index] + "")) {
            num++;
        } else if (")".equals(chars[index] + "")) {
            num--;
        }
        return num;
    }

    /**
     * 此类方法用于将字符串转化为 aX+b 的形式  返回 Result
     *
     * @param str:需要转化的字符串
     */
    private static Result getResultFromString(String str) {
        Result result = new Result();
        if (str.equals("")) {
            result.setA(0.0);
            result.setB(0.0);
        } else if (isRightString(str)) {
            String standString = getStandardFormatString(str);
            standString = deleteKH(standString);
            result.setA(Double.parseDouble(standString.substring(0, standString.indexOf("x"))));
            result.setB(Double.parseDouble(standString.substring(standString.indexOf("x") + 1, standString.length())));
        } else if (isNum(str)) {
            result.setA(0.0);
            result.setB(Double.parseDouble(str));
        } else {
            result = translate(str);
        }
        return result;
    }

    /**
     * 此类方法用于计算方程
     *
     * @param string:需要计算的方程
     */
    public static double calculate(String string) {
        String string01 = string.substring(0, string.indexOf("="));
        String string02 = string.substring(string.indexOf("=") + 1);
        Result result01 = translate(string01);
        Result result02 = translate(string02);
        double a1 = result01.getA();
        double b1 = result01.getB();
        double a2 = result02.getA();
        double b2 = result02.getB();
        if (a1 == a2 && b1 != b2) {
            sendWrong("方程 &e" + string + " &c无解");
        } else if (a1 != a2) {
            return (b2 - b1) / (a1 - a2);
        }
        return 0.0;
    }

    /**
     * 此类方法用于判断字符串是否是数字
     *
     * @param str:需要判断的字符串
     */
    private static boolean isNum(String str) {
        boolean isNum = true;
        try {
            Double.parseDouble(str);
        } catch (Exception e) {
            isNum = false;
        }
        return isNum;
    }

    /**
     * 此类方法用于判断字符串是否是类似于  aX+b 格式
     *
     * @param s:需要判断的字符串
     */
    private static boolean isRightString(String s) {
        char[] c = s.toCharArray();
        int j = 0;
        for (int i = 0; i < c.length; i++) {
            if ("+".equals(c[i] + "") || "-".equals(c[i] + "")) {
                j++;
            }
        }
        if (s.contains("x") && j <= 1) {
            if (!s.contains("(")) {
                return true;
            } else if (s.contains("(") && s.indexOf("(") == 0) {
                return true;
            }
        }
        return false;
    }


    /**
     * 此类方法用于将字符串转化为  aX+b 标准格式
     *
     * @param s:需要转化的字符串
     */
    private static String getStandardFormatString(String s) {
        char[] c = s.toCharArray();
        int t = 0;
        StringBuffer s1 = new StringBuffer(s);
        if (s.contains("+") || s.contains("-")) {
            for (int i = 0; i < c.length; i++) {
                if ("+".equals(c[i] + "") || "-".equals(c[i] + "")) {
                    t = i;//t 用于记住 +,-号的位置
                    break;
                }
            }
            if (s.indexOf("x") < t) {
                return s;
            } else {
                String s2 = s.substring(0, t);
                String s3 = s.substring(t, s.length());
                StringBuffer ss = new StringBuffer(s3);
                ss.append(s2);
                return ss.toString();
            }
        } else {
            return s1.append("+0").toString();
        }
    }

    /**
     * 此类方法用于去掉字符串最外层的括号
     *
     * @param str:需要处理的字符串
     */
    private static String deleteKH(String str) {
        if (str.startsWith("(") && str.endsWith(")")) {
            str = str.substring(1, str.length() - 1);
        }
        return str;
    }

    /**
     * 此类定义了  aX+b 结构中的 a和b
     * 注意 这里的a,b可以为负,即aX-b也属于这种结构
     */
    private static class Result {
        double a;
        double b;

        public Result() {
        }

        public Result(double a, double b) {
            this.a = a;
            this.b = b;
        }

        public double getA() {
            return this.a;
        }

        public void setA(double a) {
            this.a = a;
        }

        public double getB() {
            return this.b;
        }

        public void setB(double b) {
            this.b = b;
        }

        @Override
        public String toString() {
            return "Result{" +
                    "a=" + this.a +
                    ", b=" + this.b +
                    '}';
        }
    }
}

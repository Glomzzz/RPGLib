package com.skillw.rpglib.util;

import java.io.UnsupportedEncodingException;
import java.util.Base64;

public class EDCodeUtils {

    final static Base64.Encoder ENCODER = Base64.getEncoder();
    final static Base64.Decoder DECODER = Base64.getDecoder();

    /**
     * 给字符串加密
     *
     * @param text
     * @return
     */
    public static String base64Encode(String text) {
        byte[] textByte = new byte[0];
        try {
            textByte = text.getBytes("UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return ENCODER.encodeToString(textByte);
    }

    /**
     * 将加密后的字符串进行解密
     *
     * @param encodedText
     * @return
     */
    public static String base64Decode(String encodedText) {
        String text = null;
        try {
            text = new String(DECODER.decode(encodedText), "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return text;
    }

}

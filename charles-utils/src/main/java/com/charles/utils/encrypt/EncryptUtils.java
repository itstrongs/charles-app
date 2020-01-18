package com.charles.utils.encrypt;

/**
 * Created by Charles on 2018/10/30 16:11 Tuesday
 */
public class EncryptUtils {

    public static void main(String[] args) {
        String msg = "凡人皆有一死";

        System.out.println("明文：" + msg);
        System.out.println("Base64加密：" + Base64.encode(msg));
        System.out.println("位运算加密：" + EncryptUtils.bitEncode(msg));
        System.out.println("位运算解密：" + EncryptUtils.bitEncode(EncryptUtils.bitEncode(msg)));
    }

    public static String bitEncode(String msg) {
        char[] array = msg.toCharArray();
        for (int i = 0; i < array.length; i++) {
            array[i] = (char) (array[i] ^ 5);
        }
        return new String(array);
    }
}
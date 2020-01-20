package com.charles.library.encrypt;

import java.security.MessageDigest;

public class MD5 {

    public static String encode(String x) {
        try {
            MessageDigest m = MessageDigest.getInstance("MD5");
            m.update(x.getBytes("UTF8"));
            byte s[] = m.digest();
            String result = "";
            for (int i = 0; i < s.length; i++) {
                result += Integer.toHexString((0x000000FF & s[i]) | 0xFFFFFF00)
                        .substring(6);
            }
            return result;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
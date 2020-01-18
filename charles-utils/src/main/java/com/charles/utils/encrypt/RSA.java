package com.charles.utils.encrypt;

import java.io.UnsupportedEncodingException;
import java.security.KeyFactory;
import java.security.PrivateKey;
import java.security.Signature;
import java.security.spec.PKCS8EncodedKeySpec;

/**
 * Created by Charles on 2018/9/6 10:03 星期四
 */
public class RSA {

    public static final String SIGN_ALGORITHMS = "SHA1WithRSA";

    /**
     * 使用{@code RSA}方式对字符串进行签名
     *
     * @param content 需要加签名的数据
     * @param privateKey {@code RSA}的私钥
     * @param charset 数据的编码方式
     * @return 返回签名信息
     */
    public static String sign(String content, String privateKey, String charset) {
        try {
            PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(Base64.decode(privateKey));

            KeyFactory keyf = KeyFactory.getInstance("RSA", "BC");
            PrivateKey priKey = keyf.generatePrivate(keySpec);

            return sign(content, priKey, charset);
        }
        catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }

    /**
     * 使用{@code RSA}方式对字符串进行签名
     *
     * @param content 需要加签名的数据
     * @param privateKey {@code RSA}的私钥
     * @param charset 数据的编码方式
     * @return 返回签名信息
     */
    public static String sign(String content, PrivateKey privateKey, String charset) {
        try {
            Signature signature = Signature.getInstance(SIGN_ALGORITHMS);

            signature.initSign(privateKey);
            signature.update(getContentBytes(content, charset));

            return Base64.encode(signature.sign());
        }
        catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }

    /**
     * 使用给定的 charset 将此 String 编码到 byte 序列，并将结果存储到新的 byte 数组。
     *
     * @param content 字符串对象
     *
     * @param charset 编码方式
     *
     * @return 所得 byte 数组
     */
    public static byte[] getContentBytes(String content, String charset) {
        if (charset == null || "".equals(charset)) {
            return content.getBytes();
        }

        try {
            return content.getBytes(charset);
        }
        catch (UnsupportedEncodingException ex) {
            throw new IllegalArgumentException("Not support:" + charset, ex);
        }
    }
}

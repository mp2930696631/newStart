package com.hz.encryption;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

/**
 * @author zehua
 * @date 2020/12/22 9:07
 */
public class EnDecrypt {

    public static byte[] sha1(String str) {
        byte[] sha1s = null;
        try {
            sha1s = MessageDigest.getInstance("SHA1").digest(str.getBytes());
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }

        return sha1s;
    }

    public static String base64(String str) {
        return Base64.getEncoder().encodeToString(str.getBytes());
    }

    public static String base64(byte[] bytes) {
        return Base64.getEncoder().encodeToString(bytes);
    }

    public static String base64ThenSha1(String str) {
        return base64(sha1(str));
    }

    public static String dBase64(String base64Str) {
        final byte[] bytes = Base64.getDecoder().decode(base64Str);
        return new String(bytes);
    }

    public static void main(String[] args) {
        String str = "user1:123456";
        System.out.println(sha1(str));
        final String s = base64(str);
        System.out.println(s);
        System.out.println(dBase64(s));
        System.out.println(base64ThenSha1(str));
    }

}

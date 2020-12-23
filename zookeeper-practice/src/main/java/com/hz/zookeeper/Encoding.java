package com.hz.zookeeper;

import java.security.MessageDigest;
import java.util.Base64;

/**
 * @author zehua
 * @date 2020/12/22 8:15
 */
public class Encoding {

    public static void main(String[] args) throws Exception {
        String usernameAndPassword = "user1:123456";
        byte digest[] = MessageDigest.getInstance("SHA1").digest(usernameAndPassword.getBytes());
        String encodeToString = Base64.getEncoder().encodeToString(digest);

        System.out.println(encodeToString);
    }

}

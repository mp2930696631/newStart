package com.zehua.review.net.UserLogin;

import java.io.Serializable;

/**
 * @Auther zehua
 * @Date 2020/10/30 9:31
 **/
public class User implements Serializable {
    private static final long serialVersionUID = -7172860886724770741L;

    private String username;
    private String passwd;

    public User(){

    }

    public User(String username, String passwd) {
        this.username = username;
        this.passwd = passwd;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPasswd() {
        return passwd;
    }

    public void setPasswd(String passwd) {
        this.passwd = passwd;
    }
}

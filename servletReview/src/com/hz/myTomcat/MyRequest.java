package com.hz.myTomcat;

import java.io.IOException;
import java.io.InputStream;

/**
 * @Auther zehua
 * @Date 2020/11/5 19:34
 */
public class MyRequest {
    private String uri;
    private String method;

    public MyRequest() {
    }

    public MyRequest(InputStream is) throws IOException {
        byte[] buffer = new byte[1024];
        int length = 0;
        if ((length = is.read(buffer)) > 0) {
            String str = new String(buffer, 0, length);
            String requestLine = str.split("\n")[0];
            String[] strs = requestLine.split(" ");
            System.out.println(strs[1]);
            this.setMethod(strs[0]);
            this.setUri(strs[1]);
        }
    }

    public String getUri() {
        return uri;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }
}

package com.hz.myTomcat;

import java.io.IOException;
import java.io.OutputStream;

/**
 * 注意：在写响应体报文中的状态行的时候，需要写全：协议 状态码 状态码描述（这个可以不写）
 * 在写完数据的时候需要调用flush
 *
 * @Auther zehua
 * @Date 2020/11/5 19:34
 */
public class MyResponse {
    private OutputStream os;

    public MyResponse() {
    }

    public MyResponse(OutputStream os) throws IOException {
        this.os = os;
    }

    public void write(String str) throws IOException {
        this.os.write((
                "HTTP/1.1 200 \n" +
                        "content-type: text/html\r\n" +
                        "\n" +
                        "<html>\n" +
                        "  <head>\n" +
                        "    <title>$Title$</title>\n" +
                        "  </head>\n" +
                        "  <body>\n" +
                        "  <h1>" + str + "</h1>" +
                        "  </body>\n" +
                        "</html>").getBytes());
        os.flush();
        os.close();
    }
}

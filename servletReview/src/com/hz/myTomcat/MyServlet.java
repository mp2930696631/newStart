package com.hz.myTomcat;

import java.io.IOException;

/**
 * @Auther zehua
 * @Date 2020/11/5 20:01
 */
public class MyServlet extends MyServletAbs {
    @Override
    public void doPost(MyRequest request, MyResponse response) {
        this.doGet(request, response);
    }

    @Override
    public void doGet(MyRequest request, MyResponse response) {
        System.out.println("test ok");
        try {
            response.write("hello myTomcat");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

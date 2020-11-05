package com.hz.myTomcat;

/**
 * @Auther zehua
 * @Date 2020/11/5 19:56
 */
public abstract class MyServletAbs {
    abstract public void doPost(MyRequest request, MyResponse response);

    abstract public void doGet(MyRequest request, MyResponse response);

    public void service(MyRequest request, MyResponse response) {
        String method = request.getMethod().toLowerCase();
        if ("get".equals(method)) {
            this.doGet(request, response);
        } else if ("post".equals(method)) {
            this.doPost(request, response);
        }
    }
}

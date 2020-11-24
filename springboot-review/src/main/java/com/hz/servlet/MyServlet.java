package com.hz.servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author zehua
 * @date 2020/11/24 12:31
 */
@WebServlet(name = "myServlet",
        urlPatterns = "/myServlet",
        loadOnStartup = 1,
        initParams = @WebInitParam(name = "zehua", value = "还好"))
public class MyServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 在测试session监听器的时候，需要加上这句话，不然不会创建session
        req.getSession();
        System.out.println("test servlet ok");
        final String initParameter = this.getServletConfig().getInitParameter("zehua");
        resp.getWriter().write("initParameter啊: " + "zehua-----" + initParameter);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doGet(req, resp);
    }
}

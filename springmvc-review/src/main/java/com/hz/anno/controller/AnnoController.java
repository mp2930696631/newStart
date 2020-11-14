package com.hz.anno.controller;

import com.hz.anno.entity.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * @author: zehua
 * @date: 2020/11/14 8:57
 */
@Controller
@RequestMapping("/anno")
@SessionAttributes("key")
public class AnnoController {

    @RequestMapping("/v1/{id}")
    public String v1(@PathVariable("id") int id, @RequestParam("name") String name, @RequestHeader("User-Agent") String head, @CookieValue("JSESSIONID") String sessionid) {
        System.out.println(id);
        System.out.println(name);
        System.out.println(head);
        System.out.println(sessionid);

        return "success";
    }

    @RequestMapping("/v2")
    public String v2(HttpServletRequest request, HttpServletResponse response, HttpSession session) throws IOException {
        System.out.println(request.getParameter("name"));
        // 不能write，会没有效果
        // response.getWriter().write("response");
        System.out.println(session.getAttribute("JSESSIONID"));

        return "success";
    }

    @RequestMapping("/v3")
    public String v3(User user, Model model) {
        System.out.println(user);

        return "success";
    }

    @ModelAttribute
    public void modelAttr(Model model) {
        User user = new User("zehua", 22);
        model.addAttribute("user", user);
        model.addAttribute("key", "123456");
    }

}

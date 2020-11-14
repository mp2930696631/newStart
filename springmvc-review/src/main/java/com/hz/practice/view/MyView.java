package com.hz.practice.view;

import org.springframework.web.servlet.View;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

/**
 * @author: zehua
 * @date: 2020/11/14 10:15
 */
public class MyView implements View {
    @Override
    public String getContentType() {
        return "text/html";
    }

    @Override
    public void render(Map<String, ?> model, HttpServletRequest request, HttpServletResponse response) throws Exception {
        Set<String> keySet = model.keySet();
        Iterator<String> iterator = keySet.iterator();
        StringBuilder stringBuilder = new StringBuilder();
        while (iterator.hasNext()) {
            String key = iterator.next();
            stringBuilder.append(key);
            stringBuilder.append("----------");
            stringBuilder.append(model.get(key));
            stringBuilder.append("<br/>");
        }
        String str = stringBuilder.toString();

        response.setContentType(getContentType());
        response.getWriter().write("<html>\n" +
                                           "  <head>\n" +
                                           "    <title>$Title$</title>\n" +
                                           "  </head>\n" +
                                           "  <body>\n" +
                                           str +
                                           "  </body>\n" +
                                           "</html>");
    }
}

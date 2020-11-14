package com.hz.practice.converter;

import com.hz.practice.entity.User;
import org.springframework.core.convert.converter.Converter;

/**
 * @author: zehua
 * @date: 2020/11/14 10:33
 */
public class MyUserConverter implements Converter<String, User> {
    @Override
    public User convert(String source) {
        System.out.println(source);
        if (source != null) {
            String[] split = source.split("-");
            User user = new User();
            user.setName(split[0]);
            user.setAge(Integer.valueOf(split[1]));

            return user;
        }

        return null;
    }
}

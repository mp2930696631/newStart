package com.hz.jdbcTemplate.controller;

import com.hz.jdbcTemplate.entity.Emp;
import com.hz.jdbcTemplate.service.EmpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.util.List;

/**
 * @Auther zehua
 * @Date 2020/11/6 18:18
 */
@Controller
public class EmpController {
    @Autowired
    private EmpService empService;

    public List<Emp> select(String sql, Object[] params){
        return empService.select(sql, params);
    }
}

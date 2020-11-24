package com.hz.mybatis.controller;

import com.hz.mybatis.entity.Emp;
import com.hz.mybatis.service.EmpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author zehua
 * @date 2020/11/24 14:05
 */
@RestController
@RequestMapping("/mybatis")
public class MyBatisController {

    @Autowired
    private EmpService empService;

    @RequestMapping("/emp/{empno}")
    public List<Emp> getEmps(@PathVariable("empno") int empno) {
        Emp emp = new Emp();
        emp.setEmpno(empno);

        return empService.getEmps(emp);
    }

}

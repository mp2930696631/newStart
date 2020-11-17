package com.hz.controller.emp;

import com.hz.entity.Emp;
import com.hz.service.EmpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author zehua
 * @date 2020/11/17 7:22
 */
@Controller
public class EmpController {

    @Autowired
    private EmpService empService;

    @ResponseBody
    @RequestMapping("/emp/{id}")
    public Emp getEmp(@PathVariable("id") int empno) {

        return empService.getEmp(empno);
    }
}

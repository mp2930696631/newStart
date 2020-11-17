package com.hz.controller;

import com.hz.entity.Dept;
import com.hz.entity.Emp;
import com.hz.service.impl.TxService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author zehua
 * @date 2020/11/17 9:32
 */
@Controller
public class TxController {
    @Autowired
    private TxService txService;

    @RequestMapping("/tx/{id}")
    public String txTest(Model model, @PathVariable("id") int empno) {
        Emp emp = new Emp();
        emp.setEname("zehua");
        emp.setEmpno(1234);
        Dept dept = new Dept();
        dept.setDeptno(50);
        dept.setDname("xixi");

        final Emp emp1 = txService.testTx(emp, dept, empno);
        model.addAttribute("emp", emp1);

        return "tx";
    }

}

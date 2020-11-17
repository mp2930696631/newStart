package com.hz.service.impl;

import com.hz.dao.DeptDao;
import com.hz.dao.EmpDao;
import com.hz.entity.Dept;
import com.hz.entity.Emp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author zehua
 * @date 2020/11/17 9:34
 */
@Service
public class TxService {
    @Autowired
    private EmpDao empDao;
    @Autowired
    private DeptDao deptDao;

    @Transactional
    public Emp testTx(Emp emp, Dept dept, int empno) {
        try {
            empDao.insertEmp(emp);
        }catch (Exception e){
            System.out.println("it ok...");
        }
        final Emp emp1 = empDao.selectById(empno);
        System.out.println("===================");
        // int result = 10 / 0;
        deptDao.insertDept(dept);

        return emp1;
    }

}

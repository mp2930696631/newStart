package com.hz.service.impl;

import com.hz.dao.EmpDao;
import com.hz.entity.Emp;
import com.hz.service.EmpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author zehua
 * @date 2020/11/17 7:23
 */
@Service
public class EmpServiceImpl implements EmpService {

    @Autowired
    private EmpDao empDao;

    @Override
    public Emp getEmp(int empno) {
        return empDao.selectById(empno);
    }
}

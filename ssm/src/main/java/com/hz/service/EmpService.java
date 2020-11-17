package com.hz.service;

import com.hz.dao.EmpDao;
import com.hz.entity.Emp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author zehua
 * @date 2020/11/17 7:21
 */
public interface EmpService {

    Emp getEmp(int empno) ;
}

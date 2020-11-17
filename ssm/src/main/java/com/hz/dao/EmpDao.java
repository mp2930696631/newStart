package com.hz.dao;

import com.hz.entity.Emp;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author zehua
 * @date 2020/11/17 7:02
 */
public interface EmpDao {
    Emp selectById(int empno);

    @Transactional
    int insertEmp(Emp emp);

    int deleteEmp(Emp emp);

    int updateEmp(Emp emp);
}

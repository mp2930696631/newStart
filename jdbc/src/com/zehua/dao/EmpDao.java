package com.zehua.dao;

import com.zehua.entity.Emp;

/**
 * @Auther zehua
 * @Date 2020/11/1 13:09
 */
public interface EmpDao {
    void insert(Emp emp);
    void update(Emp emp);
    void delete(Integer empno);
    Emp selectById(Integer empno);
}

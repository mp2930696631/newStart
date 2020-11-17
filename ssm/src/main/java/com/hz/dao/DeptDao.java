package com.hz.dao;

import com.hz.entity.Dept;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author zehua
 * @date 2020/11/17 9:25
 */
public interface DeptDao {
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    int insertDept(Dept dept);
}

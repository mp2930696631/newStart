package com.hz.mybatis.service;

import com.hz.mybatis.entity.Emp;

import java.util.List;

/**
 * @author zehua
 * @date 2020/11/24 14:07
 */
public interface EmpService {
    public List<Emp> getEmps(Emp emp);
}

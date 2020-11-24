package com.hz.mybatis.service.impl;

import com.hz.mybatis.entity.Emp;
import com.hz.mybatis.mapper.EmpMapper;
import com.hz.mybatis.service.EmpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author zehua
 * @date 2020/11/24 14:08
 */
@Service
public class EmpServiceImpl implements EmpService {

    @Autowired
    private EmpMapper empMapper;

    @Override
    public List<Emp> getEmps(Emp emp) {
        return empMapper.select(emp);
    }
}

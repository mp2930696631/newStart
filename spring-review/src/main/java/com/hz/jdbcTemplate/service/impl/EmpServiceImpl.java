package com.hz.jdbcTemplate.service.impl;

import com.hz.jdbcTemplate.dao.EmpDao;
import com.hz.jdbcTemplate.entity.Emp;
import com.hz.jdbcTemplate.service.EmpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Auther zehua
 * @Date 2020/11/6 18:19
 */
@Service
public class EmpServiceImpl implements EmpService {
    @Autowired
    private EmpDao empDao;

    @Override
    public List<Emp> select(String sql, Object[] params) {
        return empDao.select(sql, params);
    }
}

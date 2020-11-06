package com.hz.jdbcTemplate.service;

import com.hz.jdbcTemplate.entity.Emp;

import java.util.List;

/**
 * @Auther zehua
 * @Date 2020/11/6 18:18
 */
public interface EmpService {

    List<Emp> select(String sql, Object[] params);
}

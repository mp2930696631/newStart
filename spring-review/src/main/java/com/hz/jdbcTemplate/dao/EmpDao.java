package com.hz.jdbcTemplate.dao;

import com.hz.jdbcTemplate.entity.Emp;

import java.util.List;

/**
 * @Auther zehua
 * @Date 2020/11/6 18:18
 */
public interface EmpDao {

    List<Emp> select(String sql, Object[] params);
}

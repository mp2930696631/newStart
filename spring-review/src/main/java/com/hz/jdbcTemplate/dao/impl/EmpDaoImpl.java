package com.hz.jdbcTemplate.dao.impl;

import com.alibaba.druid.pool.DruidDataSource;
import com.hz.jdbcTemplate.dao.EmpDao;
import com.hz.jdbcTemplate.entity.Emp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapperResultSetExtractor;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

/**
 * @Auther zehua
 * @Date 2020/11/6 18:18
 */
@Repository
public class EmpDaoImpl implements EmpDao {
    @Autowired
    private JdbcTemplate template;

    @Override
    public List<Emp> select(String sql, Object[] params) {
        List<Emp> list = template.query(sql, new BeanPropertyRowMapper<>(Emp.class), params);

        return list;
    }
}

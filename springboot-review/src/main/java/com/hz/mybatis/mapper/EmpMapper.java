package com.hz.mybatis.mapper;

import com.hz.mybatis.entity.Emp;

import java.util.List;

/**
 * @author zehua
 * @date 2020/11/24 13:58
 */

public interface EmpMapper {
    List<Emp> select(Emp emp);
}

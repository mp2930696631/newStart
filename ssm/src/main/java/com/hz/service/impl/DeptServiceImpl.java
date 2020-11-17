package com.hz.service.impl;

import com.hz.dao.DeptDao;
import com.hz.entity.Dept;
import com.hz.service.DeptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author zehua
 * @date 2020/11/17 9:30
 */
@Service
public class DeptServiceImpl implements DeptService {
    @Autowired
    private DeptDao deptDao;

    @Override
    public int insertDept(Dept dept) {
        return deptDao.insertDept(dept);
    }
}

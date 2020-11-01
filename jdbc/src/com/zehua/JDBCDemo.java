package com.zehua;

import com.mysql.cj.jdbc.Driver;
import com.zehua.dao.EmpDao;
import com.zehua.dao.impl.EmpDaoImpl;
import com.zehua.entity.Emp;
import com.zehua.util.JDBCUtil;

import java.sql.*;
import java.sql.Date;

/**
 * @Auther zehua
 * @Date 2020/11/1 10:56
 */
public class JDBCDemo {


    public static void main(String[] args) {
        /*EmpDao empDao = new EmpDaoImpl();
        Emp emp = new Emp(1111, "zehua", "check", 7902, new Date(System.currentTimeMillis()), 1500.0, 800.0, 30);
        empDao.insert(emp);
        System.out.println(empDao.selectById(1111));*/
        /*long start = System.currentTimeMillis();
        EmpDaoImpl empDao = new EmpDaoImpl();
        empDao.batchAdd();
        System.out.println(System.currentTimeMillis() - start);*/

        System.out.println(JDBCUtil.getConn());
    }

}

package com.zehua.dao.impl;

import com.zehua.dao.EmpDao;
import com.zehua.entity.Emp;
import com.zehua.util.JDBCUtil;

import java.sql.*;

/**
 * @Auther zehua
 * @Date 2020/11/1 13:09
 */
public class EmpDaoImpl implements EmpDao {
    @Override
    public void insert(Emp emp) {
        Connection conn = JDBCUtil.getConn();
        String sql = "insert into emp values(?,?,?,?,?,?,?,?)";
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setInt(1, emp.getEmpno());
            preparedStatement.setString(2, emp.getEname());
            preparedStatement.setString(3, emp.getJob());
            preparedStatement.setInt(4, emp.getMgr());
            preparedStatement.setDate(5, emp.getHiredate());
            preparedStatement.setDouble(6, emp.getSal());
            preparedStatement.setDouble(7, emp.getComm());
            preparedStatement.setInt(8, emp.getDeptno());

            preparedStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCUtil.close(conn, preparedStatement);
        }
    }

    @Override
    public void update(Emp emp) {

    }

    @Override
    public void delete(Integer empno) {

    }

    @Override
    public Emp selectById(Integer empno) {
        Connection conn = JDBCUtil.getConn();
        String sql = "select * from emp where empno=?";
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        Emp emp = null;
        try {
            preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setInt(1, empno);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                emp = new Emp(resultSet.getInt("empno"),
                        resultSet.getString("ename"),
                        resultSet.getString("job"),
                        resultSet.getInt("mgr"),
                        resultSet.getDate("hiredate"),
                        resultSet.getDouble("sal"),
                        resultSet.getDouble("comm"),
                        resultSet.getInt("deptno")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return emp;
    }

    public void batchAdd() {
        Connection conn = JDBCUtil.getConn();
        String sql = "insert into emp(empno, ename) values(?,?)";
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = conn.prepareStatement(sql);
            for (int i = 0; i < 1000; i++) {
                preparedStatement.setInt(1, i);
                preparedStatement.setString(2, "zehua" + i);
                preparedStatement.addBatch();
            }
            preparedStatement.executeBatch();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCUtil.close(conn, preparedStatement);
        }
    }
}

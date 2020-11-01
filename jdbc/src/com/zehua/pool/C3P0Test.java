package com.zehua.pool;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import com.zehua.util.JDBCUtil;

import java.beans.PropertyVetoException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @Auther zehua
 * @Date 2020/11/1 21:13
 */
public class C3P0Test {

    public static void main(String[] args) throws PropertyVetoException {
        ComboPooledDataSource cpds = new ComboPooledDataSource();
        // cpds.setDriverClass( "org.postgresql.Driver" ); //loads the jdbc driver
        /*cpds.setJdbcUrl( "jdbc:mysql://localhost:3306/sqltest?serverTimezone=Asia/Shanghai" );
        cpds.setUser("root");
        cpds.setPassword("root");*/

        Connection conn = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            conn = cpds.getConnection();
            String sql = "select * from emp where empno=?";
            preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setInt(1, 7369);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                String ename = resultSet.getString("ename");
                System.out.println(ename);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCUtil.close(conn, preparedStatement, resultSet);
        }
    }

}

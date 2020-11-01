package com.zehua.util;

import java.sql.*;

/**
 * @Auther zehua
 * @Date 2020/11/1 13:11
 */
public class JDBCUtil {
    private static final String username = "root";
    private static final String passwd = "root";
    private static final String url = "jdbc:mysql://localhost:3306/sqltest?serverTimezone=Asia/Shanghai";
    private static final String driverClass = "com.mysql.cj.jdbc.Driver";

    static {
        try {
            Class.forName(driverClass);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static Connection getConn() {
        try {
            return DriverManager.getConnection(url, username, passwd);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    public static void close(Connection conn){
        try {
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public static void close(Connection conn, Statement statement){
        close(conn);
        try {
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void close(Connection conn, Statement statement, ResultSet resultSet){
        close(conn, statement);
        try {
            resultSet.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }




}

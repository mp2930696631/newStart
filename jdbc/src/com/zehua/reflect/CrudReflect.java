package com.zehua.reflect;

import com.zehua.entity.Dept;
import com.zehua.entity.Emp;
import com.zehua.reflect.annotation.TableId;
import com.zehua.util.JDBCUtil;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * 利用反射机制达到CRUD任意数据库表的效果
 * 主要是deleteById和select方法
 * @Auther zehua
 * @Date 2020/11/1 16:01
 */
public class CrudReflect {

    public static void main(String[] args) {
        // test insert
        // insert("insert into emp (empno, ename) values (?, ?)", new Object[]{1234, "zahua"});

        // test update
        // update("update emp set ename=? where empno=?", new Object[]{"xiaoming", 1234});

        // test delete
        // deleteById(1234, Emp.class);
        deleteById(50, Dept.class);

        // test select
        String sql = "select * from dept";
        List list = select(sql, new Object[]{}, Dept.class);
        list.stream().forEach(System.out::println);
    }

    public static void insert(String sql, Object[] params) {
        Connection conn = JDBCUtil.getConn();
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = conn.prepareStatement(sql);
            for (int i = 0; i < params.length; i++) {
                preparedStatement.setObject(i + 1, params[i]);
            }
            preparedStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCUtil.close(conn, preparedStatement);
        }
    }

    /**
     * 通过列名删除表中记录，需要使用自定义注解TableId
     * @param id
     * @param clazz
     */
    public static void deleteById(Integer id, Class<?> clazz) {
        Connection conn = JDBCUtil.getConn();
        String tableName = clazz.getName().toLowerCase();
        tableName = tableName.substring(tableName.lastIndexOf('.') + 1);
        String idName = getFieldNameByAnno(clazz, TableId.class);
        String sql = "delete from " + tableName + " where " + idName + "=?";
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setInt(1, id);
            preparedStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCUtil.close(conn, preparedStatement);
        }
    }

    public static void update(String sql, Object[] params) {
        insert(sql, params);
    }

    /**
     * 要求实体对象中的属性名都是小写
     * 注意：处理数值类型，其他类型java和mysql中的数据类型都是一一对应的关系，所以不需要进行判断
     *
     * @param sql
     * @param params
     * @param clazz
     * @return
     */
    public static List select(String sql, Object[] params, Class<?> clazz) {
        Connection conn = JDBCUtil.getConn();
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        List list = new ArrayList();
        try {
            preparedStatement = conn.prepareStatement(sql);
            for (int i = 0; i < params.length; i++) {
                preparedStatement.setObject(i + 1, params[i]);
            }
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Object instance = clazz.getConstructor().newInstance();
                ResultSetMetaData metaData = resultSet.getMetaData();
                int columnCount = metaData.getColumnCount();
                for (int i = 0; i < columnCount; i++) {
                    String columnName = metaData.getColumnName(i + 1);
                    Field declaredField = clazz.getDeclaredField(columnName.toLowerCase());
                    String setMethodName = getSetMethodName(columnName);
                    Object objectColumn = resultSet.getObject(i + 1);
                    Method setMethod = clazz.getDeclaredMethod(setMethodName, declaredField.getType());
                    if (objectColumn instanceof Number) {
                        if (objectColumn instanceof Integer) {
                            setMethod.invoke(instance, ((Number) objectColumn).intValue());
                        } else if (objectColumn instanceof Byte) {
                            setMethod.invoke(instance, ((Number) objectColumn).byteValue());
                        } else if (objectColumn instanceof Short) {
                            setMethod.invoke(instance, ((Number) objectColumn).shortValue());
                        } else if (objectColumn instanceof Long) {
                            setMethod.invoke(instance, ((Number) objectColumn).longValue());
                        } else if (objectColumn instanceof Double) {
                            setMethod.invoke(instance, ((Number) objectColumn).doubleValue());
                        } else if (objectColumn instanceof Float) {
                            setMethod.invoke(instance, ((Number) objectColumn).floatValue());
                        } else if (objectColumn instanceof BigDecimal) {
                            setMethod.invoke(instance, ((Number) objectColumn).doubleValue());
                        }
                    } else {
                        setMethod.invoke(instance, objectColumn);
                    }
                }
                list.add(instance);
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBCUtil.close(conn, preparedStatement, resultSet);
        }

        return list;
    }

    private static String getFieldNameByAnno(Class<?> clazz, Class<?> annoClass) {
        Field[] declaredFields = clazz.getDeclaredFields();
        for (Field f : declaredFields
        ) {
            Annotation[] annotations = f.getAnnotations();
            for (Annotation anno : annotations
            ) {
                if (anno.annotationType() == annoClass) {
                    return f.getName();
                }
            }
        }

        return null;
    }

    private static String getSetMethodName(String name) {
        return "set" + name.substring(0, 1).toUpperCase() + name.substring(1);
    }

}

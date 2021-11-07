package com.geekbang.exercise.char10.dao_.dao;


import com.geekbang.exercise.char10.JDBCUtilsByDruid;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

/**
 * DAO和增删改查通用方法-BasicDao
 * 1、ActorDAO完成对actor表的增删改查、GoodsDAO完成对goods表的增删改查、orderDAO完成对order表的增删改查
 * 2、提取共同操作为BasicDAO
 * domain（javaBean）：Actor类、Goods类、Order类
 * **/
public class BasicDao_<T> {
    private QueryRunner qr = new QueryRunner();

    // 开发通用的增删改查方法，针对任意表
    public int update(String sql, Object... parameters) {
        Connection connection = null;
        try {
            connection = JDBCUtilsByDruid.getConnection();
            int update = qr.update(connection, sql, parameters);
            return update;
        } catch (SQLException e) {
            throw new RuntimeException(e); // 将编译异常 -> 运行异常
        } finally {
            JDBCUtilsByDruid.close(null, null, connection);
        }
    }

    public List<T> queryMulti(String sql, Class<T> clazz, Object... parameters) {
        Connection connection = null;
        try {
            connection = JDBCUtilsByDruid.getConnection();
            return qr.query(connection, sql, new BeanListHandler<T>(clazz), parameters);
        } catch (SQLException e) {
            throw new RuntimeException(e); // 将编译异常 -> 运行异常
        } finally {
            JDBCUtilsByDruid.close(null, null, connection);
        }
    }

    public T querySingle(String sql, Class<T> clazz, Object... parameters) {
        Connection connection = null;
        try {
            connection = JDBCUtilsByDruid.getConnection();
            return qr.query(connection, sql, new BeanHandler<>(clazz), parameters);
        } catch (SQLException e) {
            throw new RuntimeException(e); // 将编译异常 -> 运行异常
        } finally {
            JDBCUtilsByDruid.close(null, null, connection);
        }
    }

    // 查询单行单列的方法，即返回单值的方法
    public Object queryScalar(String sql, Object... parameters) {
        Connection connection = null;
        try {
            connection = JDBCUtilsByDruid.getConnection();
            return qr.query(connection, sql, new ScalarHandler<>(), parameters);
        } catch (SQLException e) {
            throw new RuntimeException(e); // 将编译异常 -> 运行异常
        } finally {
            JDBCUtilsByDruid.close(null, null, connection);
        }
    }

}























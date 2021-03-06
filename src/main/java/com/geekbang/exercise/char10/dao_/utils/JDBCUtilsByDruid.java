package com.geekbang.exercise.char10.dao_.utils;

import com.alibaba.druid.pool.DruidDataSourceFactory;

import javax.sql.DataSource;
import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

public class JDBCUtilsByDruid {
    private static DataSource ds;

    // 在静态代码块完成，ds初始化 一次
    static {
        Properties properties = new Properties();
        try {
            properties.load(new FileInputStream("src/druid.properties"));
            ds = DruidDataSourceFactory.createDataSource(properties);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // 编写 getConnection 方法：这里指 连接池连接到mysql服务
    public static Connection getConnection() throws SQLException {
        return ds.getConnection();
    }

    /**
     * 在数据库连接池中，close 不是真的断掉连接，而是把使用的Connection对象放回到连接池
     * **/
    public static void close(ResultSet resultSet, Statement statement, Connection connection) {
        try {
            if (resultSet != null) {
                resultSet.close();
            }
            if (statement != null) {
                statement.close();
            }
            if (connection != null) {
                // 仅仅是将引用归还给连接池，而不是连接池与mysql服务断开
                connection.close();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}













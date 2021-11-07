package com.geekbang.exercise.char10;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import org.junit.jupiter.api.Test;

import java.beans.PropertyVetoException;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

// 数据连接池
// 使用C3P0 jar包，其实现来javax.sql.DataSource接口
public class C3P0_test {
    @Test
    public void testC3P0_01() throws IOException, PropertyVetoException, SQLException {
        // 1、创建一个数据源对象
        ComboPooledDataSource comboPooledDataSource = new ComboPooledDataSource();
        // 2、通过配置文件获取连接信息
        Properties properties = new Properties();
        properties.load(new FileInputStream("src/re.properties"));
        String url = properties.getProperty("url");
        String user = properties.getProperty("user");
        String password = properties.getProperty("password");
        String driver = properties.getProperty("driver");
        // 3、设置相关信息, 连接管理是由 comboPooledDataSource 来管理
        comboPooledDataSource.setDriverClass(driver);
        comboPooledDataSource.setJdbcUrl(url);
        comboPooledDataSource.setUser(user);
        comboPooledDataSource.setPassword(password);
        // 4、设置初始化连接数
        comboPooledDataSource.setInitialPoolSize(10);
        comboPooledDataSource.setMaxPoolSize(50); // 最大连接数
        // 测试连接池的效率，测试对mysql 5000次操作
        long start = System.currentTimeMillis();
        for (int i = 0; i < 5000; i++) {
            Connection connection = comboPooledDataSource.getConnection();// 获取Connection
            System.out.println("连接OK");
            connection.close();
        }
        long end = System.currentTimeMillis();
        System.out.println("C3P0 的5000次连接时间" + (start - end));
    }

    // 通过xml配置文件（src/c3p0-service.xml），使用C3P0
    public void testC3P0_02() throws SQLException {
        ComboPooledDataSource pooledDS = new ComboPooledDataSource("PooledDS");

        Connection connection = pooledDS.getConnection();
        System.out.println("连接OK");
        connection.close();
    }
}













































package com.geekbang.exercise.char10;


import com.alibaba.druid.pool.DruidDataSourceFactory;
import org.junit.jupiter.api.Test;

import javax.sql.DataSource;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.util.Properties;

/**
 * 测试 druid 的使用
 * **/
public class Druid_test {
    @Test
    public void testDruid() throws Exception {
        // 1、加入 Druid jar包
        // 2、配置 druid.properties
        Properties properties = new Properties();
        properties.load(new FileInputStream("src/druid.properties"));
        // 4、创建数据连接池
        DataSource dataSource = DruidDataSourceFactory.createDataSource(properties);
        // 测试连接50000次，耗时
        long start = System.currentTimeMillis();
        for (int i = 0; i < 500000; i++) {
            Connection connection = dataSource.getConnection();
            // System.out.println("连接成功！");
            connection.close();
        }
        long end = System.currentTimeMillis();
        System.out.println("druid连接池，操作500000次 耗时：" + (end - start)); // 2586
    }
}












































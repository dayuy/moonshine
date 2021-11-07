package com.geekbang.exercise.char10;

import com.mysql.cj.jdbc.Driver;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

// 连接数据库
/**
 * 连接数据库
 * 方式1：属于静态加载，灵活性差、依赖性强（直接使用com.mysql.cj.jdbc.Driver）
 * **/
public class jdbcConnect01 {
    public static void main(String[] args) throws SQLException {
        // 前置工作：在项目下创建一个文件夹 libs
        //          将 mysql.jar 拷贝到该目录下，点击add to project。。。加入到该项目中

        // 1、注册驱动
        Driver driver = new Driver();// 创建driver对象: mysql驱动 com.mysql.cj.jdbc.Driver实现了java.sql.Driver接口

        // 2、得到连接（通过网络连接到mysql dbms程序的端口3306）本质就是：socket连接
        //    jdbc:mysql://  表示协议，通过jdbc的方式连接mysql
        //    localhost 主机，可以是IP地址
        //    3306 表示mysql监听的端口
        //    test 表示要连接的数据库
        String url = "jdbc:mysql://localhost:3306/test";

        // 3、将用户名、密码放入到Properties对象
        Properties properties = new Properties();
        properties.setProperty("user", "root");
        properties.setProperty("password", "YANGq090909");

        Connection connect = driver.connect(url, properties);

        // 3、执行sql
        String sql = "INSERT INTO t1 VALUES ('{\"x3\": 17, \"y3\": \"red\", \"z3\": [3, 5, 7]}');";
        Statement statement = connect.createStatement(); // 执行静态SQL语句并返回其生成的结果对象
        int rows = statement.executeUpdate(sql); // 如果是dml语句，返回的是影响行数
        System.out.println( rows > 0 ? "成功" : "失败");

        // 4、关闭连接资源
        statement.close();
        connect.close();
    }
}

















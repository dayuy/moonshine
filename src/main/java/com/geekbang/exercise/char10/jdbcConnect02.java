package com.geekbang.exercise.char10;

import com.mysql.cj.jdbc.Driver;
import org.junit.jupiter.api.Test;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

// jdbc连接数据库
// 方式二：通过反射加载Driver类，动态加载，更加灵活，减少依赖
public class jdbcConnect02 {
    public static void main(String[] args) throws ClassNotFoundException, InstantiationException, IllegalAccessException, SQLException {
        Class<?> aClass = Class.forName("com.mysql.cj.jdbc.Driver");
        Driver driver = (Driver) aClass.newInstance();

        String url = "jdbc:mysql://localhost:3306/test";
        Properties properties = new Properties();
        properties.setProperty("user", "root");
        properties.setProperty("password", "YANGq090909");

        Connection connect = driver.connect(url, properties);
        System.out.println("方式二：" + connect);
    }

    // 方式3：使用 DriverManager 替代 driver 进行统一管理
    @Test
    public void connect3() throws ClassNotFoundException, InstantiationException, IllegalAccessException, SQLException {
        // 使用反射加载Driver
        Class<?> aClass = Class.forName("com.mysql.cj.jdbc.Driver");
        Driver driver = (Driver) aClass.newInstance();

        String url = "jdbc:mysql://localhost:3306/test";
        String user = "root";
        String password = "YANGq090909";

        DriverManager.registerDriver(driver); // 注册Driver驱动
        Connection connection = DriverManager.getConnection(url, user, password);
        System.out.println("第三种方式：" + connection);
    }

    // 方式四：使用Class.forName自动完成注册驱动，简化代码（推荐使用）
    /**
     * com.mysql.cj.jdbc.Driver 源码分析
     * 1、静态代码块，在类加载时，会执行一次。
     * static {
     *     try {
     *         DriverManager.registerDriver(new Driver());
     *     } catch (SQLException var1) {
     *         throw new RuntimeException("Can't register driver!")
     *     }
     * }
     * **/
    @Test
    public void connect04() throws ClassNotFoundException, SQLException, IOException {
        Properties properties = new Properties();
        properties.load(new FileInputStream("src/re.properties"));
        String url = properties.getProperty("url");
        String user = properties.getProperty("user");
        String password = properties.getProperty("password");
        String driver = properties.getProperty("driver");

        Class.forName(driver);
        Connection connection = DriverManager.getConnection(url, user, password);

        System.out.println("第四种方式：" + connection);
    }
}

















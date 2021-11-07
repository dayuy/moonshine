package com.geekbang.exercise.char10;

import org.junit.jupiter.api.Test;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.Properties;
import java.util.Scanner;

// 返回取出的结果 resultSet
@SuppressWarnings({"all"})
public class ResultSetTest {
    public static void main(String[] args) throws IOException, ClassNotFoundException, SQLException {
        Properties properties = new Properties();
        properties.load(new FileInputStream("src/re.properties"));
        String user = properties.getProperty("user");
        String password = properties.getProperty("password");
        String driver = properties.getProperty("driver");
        String url = properties.getProperty("url");

        Class.forName(driver);
        Connection connection = DriverManager.getConnection(url, user, password);
        Statement statement = connection.createStatement(); // 获取 Statement
        String sql = "select * from actor"; // 组织Sql语句
        ResultSet resultSet = statement.executeQuery(sql); // 执行给定的SQL语句，返回单个 ResultSet数据集
        /**
         * +------+------+------+---------------------+-------+
         * | id   | name | sex  | borndate            | phone |
         * +------+------+------+---------------------+-------+
         * | NULL | jack | 男   | 1990-11-11 00:00:00 | 112   |
         * | NULL | mary | 女   | 1998-02-02 00:00:00 | 332   |
         * +------+------+------+---------------------+-------+
         * **/
        // 5、使用while取出数据
        while (resultSet.next()) { // 让光标向后移动，如果没有更多行，返回false
            int id = resultSet.getInt(1); // 获取该行的第1列
            int id1 = resultSet.getInt("id"); // 也可以用"id"获取
            String name = resultSet.getString(2); // 获取该行的第2列
            String sex = resultSet.getString(3);
            Date date = resultSet.getDate(4);
            System.out.println(id + "\t" + name + "\t" + sex + "\t" + date);
        }

        // 6、关闭
        resultSet.close();
        statement.close();
        connection.close();
    }

    // 因为statement有依赖注入的问题，可以使用prepareStatement
    @Test
    public void prepareStatement_() throws Exception {
        Scanner scanner = new Scanner(System.in);
        System.out.println("请输入名字：");
        String name = scanner.nextLine();
        System.out.println("请输入密码：");
        String psd = scanner.nextLine();

        String url = "jdbc:mysql://localhost:3306/test";
        String user = "root";
        String password = "YANGq090909";
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection connection = DriverManager.getConnection(url, user, password); // 默认情况下，connection是默认自动提交

        // 使用 PreparedStatement
        // 1、组织Sql，Sql语句的？相当于占位符
        String sql = "select name, phone from actor where name=? and phone=?";
        String sql1 = "update actor set phone=? where name = ?";
        String sql2 = "delete from actor where name=?";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1, name); // 给 ？ 赋值
        preparedStatement.setString(2, psd);
        // 2、执行select语句，查询 使用 executeQuery
        //                  dml（create、insert、delete）使用 executeUpdate()
        ResultSet resultSet = preparedStatement.executeQuery();
        if (resultSet.next()) { // 如果查询到一条数据，说明存在
            System.out.println("登陆成功");
        } else {
            System.out.println("登陆失败");
        }

        resultSet.close();
        preparedStatement.close();
        connection.close();
    }

    // 事务
    @Test
    public void transactionTest() throws SQLException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        String sql2 = "insert into t18 values(8, 'lyly', 'lyly@sohu.com')";
        String sql1 = "insert into t18 values(9, 'loly', 'loly@sohu.com')";

        try {
            connection = JdbcUtils.connect(); // 默认情况下，connection是默认自动提交
            // 将 connection 设置为不自动提交
            connection.setAutoCommit(false);
            preparedStatement = connection.prepareStatement(sql2);
            preparedStatement.executeUpdate(); // 执行第一条语句

            // int i = 1/0; // 这里会报错，抛出异常
            preparedStatement = connection.prepareStatement(sql1);
            preparedStatement.executeUpdate();

            // 语句都正确执行完后，这里提交事务®
            connection.commit();

        } catch (Exception e) {
            System.out.println("出错了");
            // 如果执行到一半，出错，这里撤销执行的SQL
            // 默认回滚到事务开始的状态。
            try {
                connection.rollback();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        } finally {
            JdbcUtils.close(preparedStatement, connection);
        }
    }

    // 批处理
    @Test
    public void NoBatchTest() throws SQLException, ClassNotFoundException {
        Connection connect = JdbcUtils.connect();
        String sql = "insert into t18 values(?, ?, 'lily@sohu.com')";
        PreparedStatement preparedStatement = connect.prepareStatement(sql);
        System.out.println("开始执行。。。");
        long start = System.currentTimeMillis(); // 开始时间
        for (int i = 10; i < 5000; i++) {
            preparedStatement.setString(1, "" + i);
            preparedStatement.setString(2, "jack" + i);
            preparedStatement.executeUpdate();
        }
        long end = System.currentTimeMillis();
        System.out.println("传统（一一提交）耗时：" + (end - start)); // 10368

        JdbcUtils.close(preparedStatement, connect);
    }

    /**
     * 使用批处理，需要在url中加参数?rewriteBatchedStatements=true
     * **/
    @Test
    public void BatchTest() throws SQLException, ClassNotFoundException {
        Connection connect = JdbcUtils.connect();
        String sql = "insert into t18 values(?, ?, 'lily@sohu.com')";
        PreparedStatement preparedStatement = connect.prepareStatement(sql);
        System.out.println("开始执行。。。");
        long start = System.currentTimeMillis(); // 开始时间
        for (int i = 10000; i < 15000; i++) {
            preparedStatement.setString(1, "" + i);
            preparedStatement.setString(2, "jack" + i);
            // preparedStatement.executeUpdate(); // 用批处理代替
            /**
             * 批处理 .addBatch(); 源码解析
             * 1、创建 ArrayList - elementData => Object[] 存放预处理的sql语句
             * 2、当 elementData满后，就按照1.5倍扩容
             * 3、当添加到指定的值后，就executeBatch
             * 所以，批处理会减少发送sql语句的网络开销，减少编译次数，效率会高
             * public void addBatch(Object batch) {
             *     if (this.batchedArgs == null) {
             *         this.batchedArgs = new ArrayList<>();
             *     }
             *     this.batchedArgs.add(batch);
             * }
             * **/
            preparedStatement.addBatch();
            if ((i + 1) % 1000 == 0) { // 满1000条处理一次
                preparedStatement.executeBatch();
                preparedStatement.clearBatch(); // 处理完清空一次
            }
        }
        long end = System.currentTimeMillis();
        System.out.println("批处理（一一提交）耗时：" + (end - start)); // 308

        JdbcUtils.close(preparedStatement, connect);
    }
}







































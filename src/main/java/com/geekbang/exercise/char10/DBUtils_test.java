package com.geekbang.exercise.char10;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

/**
 * 使用Apache-DBUtils 工具类 + druid 完成对表的增删改查
 * **/
public class DBUtils_test {
    @Test
    public void testQueryMany() throws SQLException {
        // 1、连接 （通过druid）
        Connection connection = JDBCUtilsByDruid.getConnection();
        // 2、创建 DBUtils 类和接口 QueryRunner，先引入DBUtils相关jar包
        QueryRunner queryRunner = new QueryRunner();
        String sql = "select * from actress where user_name = ?";
        // new BeanListHandler<>(Actor.class) ：将resultset -> Actor对象 -> 封装到 ArrayList（底层使用反射机制获取Actor的属性，进行封装）
        // 1 是sql语句中的？，可以有多个值，因为是可变参数Object... params
        // 底层得到resultset，会在query关闭时，关闭PreparedStatement
        List<Actor> list = queryRunner.query(connection, sql, new BeanListHandler<>(Actor.class), "mary"); // 返回集合
        // List<Actor> list = queryRunner.query(connection, sql, new BeanHandler<>(Actor.class), "mary"); // 返回集合

        System.out.println("输出集合的信息");
        for (Actor actor : list) {
            System.out.println(actor);
        }

        JDBCUtilsByDruid.close(null, null, connection);
    }

    // 演示Apache-DButils + druid 完成dml（update、insert、delete）
    @Test
    public void testDML() throws SQLException {
        Connection connection = JDBCUtilsByDruid.getConnection();
        QueryRunner queryRunner = new QueryRunner();
        String sql = "update actress set phone = ? where user_name = ?";
        // 返回受影响的行数，如果为0，则表示没有生效
        int affectedRow = queryRunner.update(connection, sql, "136813099876", "mary");
        System.out.println(affectedRow > 0 ? "OK" : "No data");
        // 释放资源
        JDBCUtilsByDruid.close(null, null, connection);
    }




    /***
     * 以上虽然简化了JDBC开发，但还有不足：
     * 1、SQL语句是固定的，不能通过参数传入至sql语句
     * 2、对于select操作，如果有返回值，返回类型不能固定，需要使用泛型
     * 3、业务需求复杂时，不能只靠一个Java类完成
     * **/
    // DAO和增删改查通用方法-BasicDao
    // 1、ActorDAO完成对actor表的增删改查、GoodsDAO完成对goods表的增删改查、orderDAO完成对order表的增删改查
    // 2、提取共同操作为BasicDAO
    // domain（javaBean）：Actor类、Goods类、Order类
}








































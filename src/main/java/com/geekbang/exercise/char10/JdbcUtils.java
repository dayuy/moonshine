package com.geekbang.exercise.char10;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class JdbcUtils {
    public static Connection connect() throws SQLException, ClassNotFoundException {
        // String url = "jdbc:mysql://localhost:3306/test";
        String url = "jdbc:mysql://localhost:3306/test?rewriteBatchedStatements=true"; // 批处理需要加?rewriteBatchedStatements=true
        String user = "root";
        String password = "YANGq090909";
        String driver = "com.mysql.cj.jdbc.Driver";

        Class.forName(driver);
        Connection connection = DriverManager.getConnection(url, user, password);
        return connection;
    }

    public static void close(PreparedStatement preparedStatement, Connection connection) throws SQLException {
        preparedStatement.close();
        connection.close();
    }
}

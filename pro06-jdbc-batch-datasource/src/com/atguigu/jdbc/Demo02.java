package com.atguigu.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Arrays;

public class Demo02 {
    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.jdbc.Driver");
        String url="jdbc:mysql://localhost:3306/fruitdb?useSSL=false&useUnicode=true&characterEncoding=utf-8&rewriteBatchStatements=true";
        String user="root";
        String pwd="123456";
        Connection connection= DriverManager.getConnection(url,user,pwd);

        String sql="insert into fruitdb.t_fruit value(0,?,?,?,?)";
        PreparedStatement preparedStatement=connection.prepareStatement(sql);
        for (int i = 0; i < 10; i++) {
            preparedStatement.setString(1,"榴莲"+i);
            preparedStatement.setInt(2,15);
            preparedStatement.setInt(3,100);
            preparedStatement.setString(4,"榴莲是一种神奇的水果");
            preparedStatement.addBatch();
        }

        int[] count=preparedStatement.executeBatch();
        for (int i = 0; i < count.length; i++) {
            System.out.println(count[i]);
        }
        preparedStatement.close();
        connection.close();
    }
}

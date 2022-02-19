package com.atguigu.jdbc;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Demo08 {
    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.jdbc.Driver");
        String url="jdbc:mysql://localhost:3306/fruitdb?useSSL=false&useUnicode=true&characterEncoding=utf-8";
        String user="root";
        String pwd="123456";
        Connection connection= DriverManager.getConnection(url,user,pwd);

        String sql="select count(*) from fruitdb.t_fruit ";
        PreparedStatement preparedStatement=connection.prepareStatement(sql);

        ResultSet rs =preparedStatement.executeQuery();
        List<Fruit> fruitList=new ArrayList<>();
        while (rs.next()){
            int count=rs.getInt(1);
            System.out.println("总记录条数:"+count);
        }
        preparedStatement.close();
        connection.close();

    }
}

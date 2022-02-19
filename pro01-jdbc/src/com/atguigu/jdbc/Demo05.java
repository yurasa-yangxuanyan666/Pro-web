package com.atguigu.jdbc;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Demo05 {
    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.jdbc.Driver");
        String url="jdbc:mysql://localhost:3306/fruitdb?useSSL=false&useUnicode=true&characterEncoding=utf-8";
        String user="root";
        String pwd="123456";
        Connection connection= DriverManager.getConnection(url,user,pwd);

        String sql="select * from fruitdb.t_fruit";
        PreparedStatement preparedStatement=connection.prepareStatement(sql);
        ResultSet rs =preparedStatement.executeQuery();
        List<Fruit> fruitList=new ArrayList<>();
        while (rs.next()){
            int fid=rs.getInt(1);
            String fname=rs.getString("fname");
            int price=rs.getInt(3);
            int fcount=rs.getInt(4);
            String remark=rs.getString(5);
            Fruit fruit=new Fruit(fid,fname,price,fcount,remark);
            fruitList.add(fruit);
        }
        preparedStatement.close();
        connection.close();
        fruitList.forEach(System.out::println);
    }
}

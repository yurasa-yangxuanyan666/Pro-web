package com.atguigu.jdbc;

import java.sql.*;

public class Demo07 {
    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.jdbc.Driver");
        Connection conn= DriverManager.getConnection("jdbc:mysql://localhost:3306/fruitdb?useSSL=false&useUnicode=true&characterEncoding=utf-8","root","123456");
        String fname="红富士'or 1=1 or fname='";
        String sql="select * from fruitdb.t_fruit where fname ='"+fname+"'";

        Statement stmt=conn.createStatement();
        ResultSet rs=stmt.executeQuery(sql);

        if (rs.next()){
            System.out.println(rs.getInt(1));
            System.out.println(rs.getString("fname"));
            System.out.println(rs.getInt(3));
            System.out.println(rs.getInt("fcount"));
            System.out.println(rs.getString("remark"));
        }
    }
}

package com.atguigu.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Demo03 {
    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        Fruit fruit=new Fruit(33,"猕猴桃","猕猴桃是水果之王");
        Class.forName("com.mysql.jdbc.Driver");
        String url="jdbc:mysql://localhost:3306/fruitdb?useSSL=false&useUnicode=true&characterEncoding=utf-8";
        String user="root";
        String pwd="123456";
        Connection connection= DriverManager.getConnection(url,user,pwd);

        String sql="update fruitdb.t_fruit set fname=?,remark=?where fid=?";
        PreparedStatement preparedStatement=connection.prepareStatement(sql);
        preparedStatement.setString(1,fruit.getFname());
        preparedStatement.setString(2, fruit.getRemark());
        preparedStatement.setInt(3,fruit.getFid());
        int count=preparedStatement.executeUpdate();
        System.out.println(count>0?"修改成功!":"修改失败");
        preparedStatement.close();
        connection.close();
    }
}

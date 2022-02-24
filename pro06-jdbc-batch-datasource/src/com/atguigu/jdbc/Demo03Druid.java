package com.atguigu.jdbc;

import com.alibaba.druid.pool.DruidDataSource;

import java.sql.Connection;
import java.sql.SQLException;

public class Demo03Druid {
    public static void main(String[] args) throws SQLException {
        DruidDataSource dataSource=new DruidDataSource();
        dataSource.setDriverClassName("com.mysql.jdbc.Driver");
        dataSource.setUrl("jdbc:mysql://localhost:3306/fruitdb?useSSL=false&useUnicode=true&characterEncoding=utf-8");
        dataSource.setUsername("root");
        dataSource.setPassword("123456");
        dataSource.setInitialSize(2);
        dataSource.setMaxActive(10);
        dataSource.setMaxWait(5000);

        for (int i = 0; i < 10; i++) {
            Connection conn1=dataSource.getConnection();
            Connection conn2=dataSource.getConnection();
            System.out.println(conn1);
            System.out.println(conn2);

            if (i%3==0){
                conn1.close();
                conn2.close();
            }
        }


    }
}

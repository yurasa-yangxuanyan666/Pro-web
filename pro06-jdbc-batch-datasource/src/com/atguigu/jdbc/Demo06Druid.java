package com.atguigu.jdbc;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.pool.DruidDataSourceFactory;

import javax.sql.DataSource;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.util.Properties;

//读取外部的配置文件设置连接池
public class Demo06Druid {
    public static void main(String[] args) throws Exception {
        Properties properties=new Properties();

        InputStream is= Demo06Druid.class.getClassLoader().getResourceAsStream("jdbc2.properties");

        properties.load(is);
        DataSource datasource= DruidDataSourceFactory.createDataSource(properties);
        for (int i = 0; i < 10 ; i++) {
            Connection conn1=datasource.getConnection();
            System.out.println(i+"------>"+conn1);
        }
    }
}

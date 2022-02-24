package com.atguigu.jdbc;

import com.alibaba.druid.pool.DruidDataSource;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;
//读取外部的配置文件设置连接池
public class Demo04Druid {
    public static void main(String[] args) throws IOException, SQLException {
        Properties properties=new Properties();
        DruidDataSource datasource=new DruidDataSource();
        InputStream is=Demo04Druid.class.getClassLoader().getResourceAsStream("jdbc.properties");
        properties.load(is);
        datasource.setDriverClassName(properties.getProperty("jdbc.driverClassName"));
        datasource.setUrl(properties.getProperty("jdbc.url"));
        datasource.setUsername(properties.getProperty("jdbc.username"));
        datasource.setPassword(properties.getProperty("jdbc.pwd"));
        datasource.setInitialSize(Integer.parseInt(properties.getProperty("jdbc.initSize")));
        datasource.setMaxActive(Integer.parseInt(properties.getProperty("jdbc.MaxActive")));
        datasource.setMaxWait(Long.parseLong(properties.getProperty("jdbc.maxwait")));


        for (int i = 0; i < 10; i++) {
            Connection conn1=datasource.getConnection();
            System.out.println(i+"----->"+conn1);

        }
    }
}

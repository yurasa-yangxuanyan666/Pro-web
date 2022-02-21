package com.atguigu.fruit.dao.base;

import java.sql.*;

public abstract class BaseDAO {
    public final String url="jdbc:mysql://localhost:3306/fruitdb?useSSL=false&useUnicode=true&characterEncoding=utf-8";
    public final String user="root";
    public final String pwd="123456";
    public final String DRIVER="com.mysql.jdbc.Driver";

    protected Connection connection;
    protected PreparedStatement preparedStatement;
    protected ResultSet rs;
    protected Connection getConnection(){
        try {
            Class.forName(DRIVER);
            return DriverManager.getConnection(url,user,pwd);
        }catch(ClassNotFoundException | SQLException e){
            e.printStackTrace();
        }
        return null;
    }


    protected void close(ResultSet rs,PreparedStatement preparedStatement,Connection connection){
        try {
            if (rs!=null){
                rs.close();
            }
            if (preparedStatement!=null){
                preparedStatement.close();
            }
            if (connection!=null){
                connection.close();
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
    }
    //执行更新,返回影响行数
    protected int executeUpdate(String sql,Object... params){
        try {
            connection=getConnection();
            preparedStatement=connection.prepareStatement(sql);
            if (params!=null&&params.length>0){
                for (int i = 0; i < params.length; i++) {
                    preparedStatement.setObject(i+1,params[i]);
                }
            }
            return preparedStatement.executeUpdate();
        }catch (SQLException e){
            e.printStackTrace();
        }finally {
            close(rs,preparedStatement,connection);
        }
        return 0;
    }
}

package com.atguigu.fruit.dao.impl;

import com.atguigu.fruit.dao.FruitDAO;
import com.atguigu.fruit.pojo.Fruit;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class FruitDAOimpl implements FruitDAO {
    Connection connection;
    PreparedStatement preparedStatement;
    ResultSet rs;
    final String url="jdbc:mysql://localhost:3306/fruitdb?useSSL=false&useUnicode=true&characterEncoding=utf-8";
    final String user="root";
    final String pwd="123456";
    final String DRIVER="com.mysql.jdbc.Driver";

    private Connection getConnection(){
        try {
            Class.forName(DRIVER);
            return DriverManager.getConnection(url,user,pwd);
        }catch(ClassNotFoundException | SQLException e){
            e.printStackTrace();
        }
        return null;
    }


    private void close(ResultSet rs,PreparedStatement preparedStatement,Connection connection){
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

    @Override
    public List<Fruit> getFruitList() {
        List<Fruit> fruitList=new ArrayList<>();
        try {
             connection=getConnection();
            String sql="select * from fruitdb.t_fruit ";
            preparedStatement=connection.prepareStatement(sql);
            rs =preparedStatement.executeQuery();
            while (rs.next()){
                int fid=rs.getInt(1);
                String fname=rs.getString(2);
                int price=rs.getInt(3);
                int fcount=rs.getInt(4);
                String remark=rs.getString(5);
                Fruit fruit=new Fruit(fid,fname,price,fcount,remark);
                fruitList.add(fruit);
            }
        } catch (  SQLException e) {
            e.printStackTrace();
        }finally {
            close(rs,preparedStatement,connection);
        }
        return fruitList;
    }

    @Override
    public boolean addFruit(Fruit fruit) {
        try {
            Class.forName(DRIVER);
            connection= DriverManager.getConnection(url,user,pwd);
            String sql="insert into fruitdb.t_fruit value(0,?,?,?,?)";
            preparedStatement=connection.prepareStatement(sql);
            preparedStatement.setString(1, fruit.getFname());
            preparedStatement.setInt(2,fruit.getPrice());
            preparedStatement.setInt(3,fruit.getFcount());
            preparedStatement.setString(4, fruit.getRemark());
            return preparedStatement.executeUpdate()>0;
        } catch (ClassNotFoundException |SQLException e) {
            e.printStackTrace();
        }finally {
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
        return false;
    }

    @Override
    public boolean updateFruit(Fruit fruit) {
        try {
            connection=getConnection();
            String sql="update fruitdb.t_fruit set fcount=? where fid=?";
            preparedStatement=connection.prepareStatement(sql);
            preparedStatement.setInt(1,fruit.getFcount());
            preparedStatement.setInt(2,fruit.getFid());
            return preparedStatement.executeUpdate()>0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        finally {
            close(rs,preparedStatement,connection);
        }
        return false;
    }

    @Override
    public Fruit getFruitByFname(String fname) {
        try {
            connection=getConnection();
            String sql="select * from fruitdb.t_fruit where fname=?";
            preparedStatement=connection.prepareStatement(sql);
            preparedStatement.setString(1,fname);
            ResultSet rs =preparedStatement.executeQuery();
            if (rs.next()){
                int fid=rs.getInt(1);
                int price=rs.getInt(3);
                int fcount=rs.getInt(4);
                String remark=rs.getString(5);

                return new Fruit(fid,fname,price,fcount,remark);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        finally {
            close(rs, preparedStatement, connection);
        }
        return null;
    }

    @Override
    public boolean delFruit(String fname) {
        try {
            connection=getConnection();
            String sql="delete from fruitdb.t_fruit where fname=?";
            preparedStatement=connection.prepareStatement(sql);
            preparedStatement.setString(1,fname);
            return preparedStatement.executeUpdate()>0;
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            close(rs, preparedStatement, connection);
        }
        return false;
    }
}

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
    final String Driver="com.mysql.jdbc.Driver";
    @Override
    public List<Fruit> getFruitList() {
        List<Fruit> fruitList=new ArrayList<>();
        try {
            Class.forName(Driver);
            connection= DriverManager.getConnection(url,user,pwd);
            String sql="select * from fruitdb.t_fruit ";
            preparedStatement=connection.prepareStatement(sql);
            rs =preparedStatement.executeQuery();
            while (rs.next()){
                int fid=rs.getInt(1);
                String fname=rs.getString("fname");
                int price=rs.getInt(3);
                int fcount=rs.getInt(4);
                String remark=rs.getString(5);
                Fruit fruit=new Fruit(fid,fname,price,fcount,remark);
                fruitList.add(fruit);
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }finally {
            if (rs!=null){
                try {
                    rs.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (preparedStatement!=null){
                try {
                    preparedStatement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (connection!=null){
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return fruitList;
    }

    @Override
    public boolean addFruit(Fruit fruit) {
        try {
            Class.forName(Driver);
            connection= DriverManager.getConnection(url,user,pwd);
            String sql="insert into fruitdb.t_fruit value(0,?,?,?,?)";
            preparedStatement=connection.prepareStatement(sql);
            preparedStatement.setString(1,"榴莲");
            preparedStatement.setInt(2,15);
            preparedStatement.setInt(3,100);
            preparedStatement.setString(4,"榴莲是一种神奇的水果");
            int count=preparedStatement.executeUpdate();
            System.out.println(count>0?"添加成功!":"添加失败");
        } catch (ClassNotFoundException |SQLException e) {
            e.printStackTrace();
        }finally {
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (preparedStatement != null) {
                try {
                    preparedStatement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return false;
    }

    @Override
    public boolean updateFruit(Fruit fruit) {
        try {
            Class.forName(Driver);
            connection= DriverManager.getConnection(url,user,pwd);
            String sql="update fruitdb.t_fruit set fname=?,remark=?where fid=?";
            preparedStatement=connection.prepareStatement(sql);
            preparedStatement.setString(1,fruit.getFname());
            preparedStatement.setString(2, fruit.getRemark());
            preparedStatement.setInt(3,fruit.getFid());
            int count=preparedStatement.executeUpdate();
            System.out.println(count>0?"修改成功!":"修改失败");
        } catch (ClassNotFoundException |SQLException e) {
            e.printStackTrace();
        }
        finally {
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (preparedStatement != null) {
                try {
                    preparedStatement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return false;
    }

    @Override
    public Fruit getFruitByFname(String fname) {
        try {
            Class.forName(Driver);
            connection= DriverManager.getConnection(url,user,pwd);
            String sql="select * from fruitdb.t_fruit where fname=?";
            preparedStatement=connection.prepareStatement(sql);
            preparedStatement.setString(1,fname);
            ResultSet rs =preparedStatement.executeQuery();
            List<Fruit> fruitList=new ArrayList<>();
            while (rs.next()){
                int fid=rs.getInt(1);
                int price=rs.getInt(3);
                int fcount=rs.getInt(4);
                String remark=rs.getString(5);
                return  new Fruit(fid,fname,price,fcount,remark);
            }
        } catch (ClassNotFoundException |SQLException e) {
            e.printStackTrace();
        }
        finally {
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (preparedStatement != null) {
                try {
                    preparedStatement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return null;
    }

    @Override
    public boolean delFruit(String fname) {
        try {
            Class.forName(Driver);
            connection= DriverManager.getConnection(url,user,pwd);
            String sql="delete from fruitdb.t_fruit where fid=?";
            preparedStatement=connection.prepareStatement(sql);
            preparedStatement.setInt(1,6);
            int count=preparedStatement.executeUpdate();
            System.out.println(count>0?"删除成功!":"删除失败");
        } catch (ClassNotFoundException |SQLException e) {
            e.printStackTrace();
        }

        return false;
    }
}

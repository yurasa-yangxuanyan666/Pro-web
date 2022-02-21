package com.atguigu.fruit.dao.impl;

import com.atguigu.fruit.dao.FruitDAO;
import com.atguigu.fruit.dao.base.BaseDAO;
import com.atguigu.fruit.pojo.Fruit;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class FruitDAOimpl extends BaseDAO implements FruitDAO {


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
        String sql="insert into fruitdb.t_fruit value(0,?,?,?,?)";
        return super.executeUpdate(sql,fruit.getFname(),fruit.getPrice(),fruit.getFcount(),fruit.getRemark())>0;
    }

    @Override
    public boolean updateFruit(Fruit fruit) {
        String sql="update fruitdb.t_fruit set fcount=? where fid=?";
        return super.executeUpdate(sql,fruit.getFcount(),fruit.getFid())>0;
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
        String sql="delete from fruitdb.t_fruit where fname=?";
        return super.executeUpdate(sql,fname)>0;
    }
}

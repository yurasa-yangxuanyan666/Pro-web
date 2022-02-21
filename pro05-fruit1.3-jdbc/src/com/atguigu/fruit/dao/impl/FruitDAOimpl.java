package com.atguigu.fruit.dao.impl;

import com.atguigu.fruit.dao.FruitDAO;
import com.atguigu.fruit.dao.base.BaseDAO;
import com.atguigu.fruit.pojo.Fruit;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class FruitDAOimpl extends BaseDAO<Fruit> implements FruitDAO {


    @Override
    public List<Fruit> getFruitList() {
        return super.executeQuery("select * from fruitdb.t_fruit");
    }

    @Override
    public boolean addFruit(Fruit fruit) {
        String sql="insert into fruitdb.t_fruit value(0,?,?,?,?)";
        int count= super.executeUpdate(sql,fruit.getFname(),fruit.getPrice(),fruit.getFcount(),fruit.getRemark());
        //insert语句返回自增列而不是影响行数
        return count>0;
    }

    @Override
    public boolean updateFruit(Fruit fruit) {
        String sql="update fruitdb.t_fruit set fcount=? where fid=?";
        return super.executeUpdate(sql,fruit.getFcount(),fruit.getFid())>0;
    }

    @Override
    public Fruit getFruitByFname(String fname) {
        return super.load("select * from fruitdb.t_fruit where fname=?",fname);
    }
    @Override
    public boolean delFruit(String fname) {
        String sql="delete from fruitdb.t_fruit where fname=?";
        return super.executeUpdate(sql,fname)>0;
    }
}

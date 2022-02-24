package com.atguigu.fruit.dao;

import com.atguigu.fruit.pojo.Fruit;

import java.util.List;

public interface FruitDAO {
    //查询库存列表
    List<Fruit> getFruitList();
    boolean addFruit(Fruit fruit);
    boolean updateFruit(Fruit fruit);
    Fruit getFruitByFname(String fname);
    boolean delFruit(String fname);
}

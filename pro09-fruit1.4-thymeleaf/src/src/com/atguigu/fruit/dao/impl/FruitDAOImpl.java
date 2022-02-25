package src.com.atguigu.fruit.dao.impl;

import src.com.atguigu.fruit.dao.FruitDAO;
import src.com.atguigu.fruit.pojo.Fruit;
import src.com.atguigu.myssm.basedao.BaseDAO;

import java.util.List;

public class FruitDAOImpl extends BaseDAO<Fruit> implements FruitDAO {


    @Override
    public List<Fruit> getFruitList() {
        return super.executeQuery("select * from fruitdb.t_fruit");
    }
}

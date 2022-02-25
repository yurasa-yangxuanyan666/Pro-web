package src.com.atguigu.fruit.dao.impl;

import src.com.atguigu.fruit.dao.FruitDAO;
import src.com.atguigu.fruit.pojo.Fruit;
import src.com.atguigu.myssm.basedao.BaseDAO;

import java.util.List;

public class FruitDAOImpl extends BaseDAO<Fruit> implements FruitDAO {


    @Override
    public List<Fruit> getFruitList(Integer pageNO) {
        return super.executeQuery("select * from fruitdb.t_fruit limit ? ,5",(pageNO-1)*5);
    }

    @Override
    public Fruit getFruitByFid(Integer fid) {
        return super.load("select * from fruitdb.t_fruit where fid=?",fid);
    }

    @Override
    public void updateFruit(Fruit fruit) {
        String sql="update fruitdb.t_fruit set fname= ? , price= ? , fcount= ? ,remark= ? where fid=?";
        super.executeUpdate(sql,fruit.getFname(),fruit.getPrice(),fruit.getFcount(),fruit.getRemark(),fruit.getFid());
    }

    @Override
    public void delFruit(Integer fid) {
        super.executeUpdate("delete from fruitdb.t_fruit where fid= ? ",fid);
    }

    @Override
    public void addFruit(Fruit fruit) {
        String sql="insert into fruitdb.t_fruit values(0,?,?,?,?)";
        super.executeUpdate(sql,fruit.getFname(),fruit.getPrice(),fruit.getFcount(),fruit.getRemark());

    }

    @Override
    public int getFruitCount() {
        return ((Long) super.executeComplexQuery("select count(*) from fruitdb.t_fruit")[0]).intValue();
    }


}

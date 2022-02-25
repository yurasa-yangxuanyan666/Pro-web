package src.com.atguigu.fruit.dao;


import com.sun.org.apache.xml.internal.serialize.LineSeparator;
import src.com.atguigu.fruit.pojo.Fruit;

import java.util.List;

public interface FruitDAO {
     //获取初始化列表
     List<Fruit> getFruitList(Integer pageNO);
     //根据FID寻找Fruit
     Fruit getFruitByFid(Integer fid);

     void updateFruit(Fruit fruit);

     //根据fid删除指定的库存记录
     void delFruit(Integer fid);

     //添加方法
     void addFruit(Fruit fruit);
     int getFruitCount();
}

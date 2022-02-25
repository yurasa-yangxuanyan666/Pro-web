package src.com.atguigu.fruit.dao;


import com.sun.org.apache.xml.internal.serialize.LineSeparator;
import src.com.atguigu.fruit.pojo.Fruit;

import java.util.List;

public interface FruitDAO {
     List<Fruit> getFruitList();
}

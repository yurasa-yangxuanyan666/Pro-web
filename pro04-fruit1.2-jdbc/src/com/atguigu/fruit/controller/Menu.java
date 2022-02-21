package com.atguigu.fruit.controller;

import com.atguigu.fruit.dao.FruitDAO;
import com.atguigu.fruit.dao.impl.FruitDAOimpl;
import com.atguigu.fruit.pojo.Fruit;

import java.util.List;
import java.util.Scanner;


public class Menu {
    Scanner input=new Scanner(System.in);
    FruitDAO fruitDAO=new FruitDAOimpl();
    public int showMainMenu(){
        System.out.println("===========欢迎使用水果库存系统===========");
        System.out.println("1.查看水果库存列表");
        System.out.println("2.添加水果库存列表");
        System.out.println("3.查看特点水果库存信息");
        System.out.println("4.水果下架");
        System.out.println("5.退出");
        System.out.println("======================================");
        System.out.println("请选择:");
        int slt =input.nextInt();
        return slt;
    }
    public void showFruitList(){
     //   查看水果库存列表
        List<Fruit> fruitList=fruitDAO.getFruitList();
        System.out.println("---------------------------------------");
        System.out.println("编号\t\t名称\t\t单价\t\t库存\t\t备注");
        System.out.println("---------------------------------------");
        if (fruitList==null||fruitList.size()<=0){
            System.out.println("对不起库存为空");
        }else {
            for (int i = 0; i < fruitList.size(); i++) {
                Fruit fruit=fruitList.get(i);
                System.out.println(fruit.toString());
            }
        }
    }
    public void addFruit(){
        System.out.println("请输入水果名称");
        String fname=input.next();
        //查找水果在数据库里有没有
        Fruit fruit= fruitDAO.getFruitByFname(fname);
        if (fruit==null){
            System.out.println("请输入水果单价:");
            int price=input.nextInt();
            System.out.println("请输入水果库存:");
            int fcount=input.nextInt();
            System.out.println("请输入备注:");
            String remark=input.next();

            fruit=new Fruit(0,fname,price,fcount,remark);
            fruitDAO.addFruit(fruit);
        }else {
            System.out.println("请输入追加的库存:");
            int count=input.nextInt();
            fruit.setFcount(fruit.getFcount()+count);
            fruitDAO.updateFruit(fruit);
        }
        System.out.println("添加成功");
    }

    public void showFruitInfo(){
        System.out.println("请输入水果名称:");
        String fname=input.next();
        Fruit fruit= fruitDAO.getFruitByFname(fname);
        if (fruit==null){
            System.out.println("对不起,没有你想要的水果");
        }else{
            System.out.println("---------------------------------------");
            System.out.println("编号\t\t名称\t\t单价\t\t库存\t\t备注");
            System.out.println(fruit.toString());
            System.out.println("---------------------------------------");
        }

    }

    public void delFruit(){
        System.out.println("请输入水果名称:");
        String fname=input.next();
        Fruit fruit= fruitDAO.getFruitByFname(fname);
        if (fruit==null){
            System.out.println("对不起没有你需要的水果");
        }else {
            System.out.println("是否确认下架?(Y/N)");
            String slt=input.next();
            if ("Y".equalsIgnoreCase(slt))
                fruitDAO.delFruit(fname);
            System.out.println("删除成功");
        }
    }


    public boolean exit(){
        System.out.println("是否确认退出?(Y/N)");
        String slt=input.next();
        return !"Y".equalsIgnoreCase(slt);
    }
}

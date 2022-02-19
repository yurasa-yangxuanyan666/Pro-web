package com.atguigu.fruit.controller;

import com.atguigu.fruit.pojo.Fruit;

import java.util.List;
import java.util.Scanner;

public class Menu {
    Scanner input=new Scanner(System.in);
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
     //   List<Fruit>
    }
    public boolean exit(){
        System.out.println("是否确认退出?(Y/N)");
        String slt=input.next();
        return !"Y".equalsIgnoreCase(slt);
    }
}

package com.atguigu.fruit.view;

import com.atguigu.fruit.controller.Menu;

public class Client {
    public static void main(String[] args) {
        boolean flag=true;
        Menu menu=new Menu();
        while (flag){
            //调用显示主菜单的方法
           int slt= menu.showMainMenu();
            switch (slt){
                case 1:
                    menu.showFruitList();
                    break;
                case 2:
                    menu.addFruit();
                    break;
                case 3:
                    menu.showFruitInfo();
                    break;
                case 4:
                    menu.delFruit();
                    break;
                case 5:
                    flag=menu.exit();
                    break;
                default:
                    System.out.println("你不按套路出牌");
                    break;
            }
        }
    }
}

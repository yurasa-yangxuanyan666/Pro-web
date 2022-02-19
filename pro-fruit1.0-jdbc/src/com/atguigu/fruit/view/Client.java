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
                    break;
                case 2:
                    break;
                case 3:
                    break;
                case 4:
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

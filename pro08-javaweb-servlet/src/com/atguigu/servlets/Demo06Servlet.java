package com.atguigu.servlets;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class Demo06Servlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        System.out.println("demo06....");
        //服务器内部转发...地址栏不会有变化
       // req.getRequestDispatcher("demo07").forward(req,resp);
        //重定向到demo07地址栏变化使用resp,
        resp.sendRedirect("demo07");
    }
}

package src.com.atguigu.fruit.demo;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
//演示request保存作用域
@WebServlet("/demo01")
public class Demo01Servlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //1.向request保存作用域保存数据
        req.setAttribute("uname","lili");
        //resp.sendRedirect("demo02");
        req.getRequestDispatcher("demo02").forward(req,resp);
    }
}

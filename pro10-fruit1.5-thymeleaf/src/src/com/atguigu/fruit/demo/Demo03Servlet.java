package src.com.atguigu.fruit.demo;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
//向session保存作用域
@WebServlet("/demo03")
public class Demo03Servlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //1.向request保存作用域保存数据
        req.getSession().setAttribute("uname","lili");
        //resp.sendRedirect("demo04");
        req.getRequestDispatcher("demo04").forward(req,resp);
    }
}

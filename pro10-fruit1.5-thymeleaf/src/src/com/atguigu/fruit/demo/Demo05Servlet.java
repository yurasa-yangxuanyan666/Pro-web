package src.com.atguigu.fruit.demo;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
//向application保存作用域
@WebServlet("/demo05")
public class Demo05Servlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //1.Servlet上下文
        ServletContext application= req.getServletContext();
        application.setAttribute("uname","lili");
        resp.sendRedirect("demo06");
        //req.getRequestDispatcher("demo06").forward(req,resp);
    }
}

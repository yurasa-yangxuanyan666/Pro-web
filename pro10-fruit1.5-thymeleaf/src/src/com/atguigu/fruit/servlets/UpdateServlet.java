package src.com.atguigu.fruit.servlets;

import src.com.atguigu.fruit.dao.FruitDAO;
import src.com.atguigu.fruit.dao.impl.FruitDAOImpl;
import src.com.atguigu.fruit.pojo.Fruit;
import src.com.atguigu.myssm.myspringmvc.ViewBaseServlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
@WebServlet("/update.do")
public class UpdateServlet extends ViewBaseServlet {
    private FruitDAO fruitDAO=new FruitDAOImpl();
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
       req.setCharacterEncoding("utf-8");

        String fidStr=req.getParameter("fid");
        int fid=Integer.parseInt(fidStr);

        String fname=req.getParameter("fname");
        String priceStr=req.getParameter("price");
        int price=Integer.parseInt(priceStr);
        String fcountStr=req.getParameter("fcount");
        int fcount=Integer.parseInt(fcountStr);
        String remark=req.getParameter("remark");
        //执行更新
        fruitDAO.updateFruit(new Fruit(fid,fname,price,fcount,remark));
        //资源跳转
        //super.processTemplate("index",req,resp);
        //此处要重定向目的为重新给index发起求重新获取fruitlist
        resp.sendRedirect("index");
    }
}

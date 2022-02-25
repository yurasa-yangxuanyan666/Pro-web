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
@WebServlet("/add.do")
public class AddServlet extends ViewBaseServlet {
    private FruitDAO fruitDAO=new FruitDAOImpl();
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");

        String fname = req.getParameter("fname");
        Integer price = Integer.parseInt(req.getParameter("price")) ;
        Integer fcount = Integer.parseInt(req.getParameter("fcount"));
        String remark = req.getParameter("remark");

        Fruit fruit = new Fruit(0,fname , price , fcount , remark ) ;

        fruitDAO.addFruit(fruit);

        resp.sendRedirect("index");

    }
}

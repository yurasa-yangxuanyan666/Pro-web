package src.com.atguigu.fruit.servlets;

import src.com.atguigu.fruit.dao.FruitDAO;
import src.com.atguigu.fruit.dao.impl.FruitDAOImpl;
import src.com.atguigu.fruit.pojo.Fruit;
import src.com.atguigu.myssm.myspringmvc.ViewBaseServlet;
import src.com.atguigu.myssm.util.StringUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.swing.plaf.SliderUI;
import java.io.IOException;
import java.util.List;

//Servlet3.0可以主机
@WebServlet("/index")
public class IndexServlet extends ViewBaseServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String pageNoStr=req.getParameter("pageNo");
        Integer pageNo=1;
        if (StringUtil.isNotEmpty(pageNoStr)){
            pageNo=Integer.parseInt(pageNoStr);
        }
        HttpSession session=req.getSession();
        session.setAttribute("pageNo",pageNo);
        FruitDAO fruitDAO=new FruitDAOImpl();
        List<Fruit> fruitList= fruitDAO.getFruitList(pageNo);
        //保存到session作用域

        session.setAttribute("fruitList",fruitList);

        //总记录条数
        int fruitCount=fruitDAO.getFruitCount();
        //总页数
        int pageCount=(fruitCount+5-1)/5;
        session.setAttribute("pageCount",pageCount);
        /*
        * 总记录条数  总页数
        * 1             1
        * 5             1
        * 6             2
        * 10            2
        * 11            3
        * fruitCount (fruitCount+5-1)/5
        * */

        super.processTemplate("index",req,resp);
    }
}

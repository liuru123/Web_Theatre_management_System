package xupt.se.ttms.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import xupt.se.ttms.dao.UserDAO;
import xupt.se.ttms.idao.DAOFactory;

@WebServlet(urlPatterns = "/LoginServlet")
public class LoginServlet extends HttpServlet
{
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException
    {
        doGet(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException
    {
        request.setCharacterEncoding("UTF-8");
        String emp_no = request.getParameter("emp_no");
        String emp_pass = request.getParameter("emp_pass");
        UserDAO dao = (UserDAO) DAOFactory.createUserDAO();
        if(dao.isExist(emp_no, emp_pass))
        {
            // TODO 接着判断用户类型，写入session，并跳转到不同页面
            int type = dao.findUserById(emp_no).getUser_type();
            if(type == 1)
            {
                request.getSession().setAttribute("login_flag", "ok");

                response.sendRedirect("employee/index.jsp");
            }
            else
            {
                request.getSession().setAttribute("login_flag", "ok");

                response.sendRedirect("modifyPassUser.jsp");
            }
        }
        else
        {
            request.setAttribute("desc", "用户名、密码错误!");
            request.getRequestDispatcher("/index.jsp").forward(request, response);
        }
    }
}

package xupt.se.ttms.servlet;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import xupt.se.ttms.dao.UserDAO;
import xupt.se.ttms.idao.DAOFactory;
import xupt.se.ttms.model.User;

@WebServlet("/user/UserServlet")
public class UserServlet extends HttpServlet
{
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException
    {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException
    {
        String method = request.getParameter("method");
        if(method.equalsIgnoreCase("add"))
        {
            add(request, response);
        }
        else
            if(method.equalsIgnoreCase("delete"))
            {
                delete(request, response);
            }
            else
                if(method.equalsIgnoreCase("search"))
                {
                    search(request, response);
                }
                else
                    if(method.equalsIgnoreCase("update"))
                    {
                        update(request, response);
                    }
                    else
                        if(method.equalsIgnoreCase("searchById"))
                        {
                            searchById(request, response);
                        }
    }

    private void update(HttpServletRequest request, HttpServletResponse response)
    {
        int type = Integer.parseInt(request.getParameter("user_type"));
        System.out.println("emp_pass");
        System.out.println(type);
        if(type == 1)
        {
            try
            {
                response.sendRedirect("update.jsp");
            }
            catch(IOException e)
            {

                e.printStackTrace();
            }
        }
        else
        {
            try
            {
                response.sendRedirect("modifyPass.jsp");
            }
            catch(IOException e)
            {

                e.printStackTrace();
            }
        }
    }

    public void search(HttpServletRequest request, HttpServletResponse response)
    {
        String emp_no = request.getParameter("emp_no");
        UserDAO dao = (UserDAO) DAOFactory.createUserDAO();
        ArrayList<User> list = null;
        User user = new User();
        if(emp_no == null || emp_no.equals(""))
            list = dao.findUserAll();
        else
            user = dao.findUserById(emp_no);

        try
        {
            request.setAttribute("search_emp_no", emp_no);
            request.setAttribute("list", list);
            request.setAttribute("user", user);
            request.getRequestDispatcher("index.jsp").forward(request, response);
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }

    public void searchById(HttpServletRequest request, HttpServletResponse response)
    {
        String emp_no = request.getParameter("emp_no");
        // int type = Integer.parseInt(request.getParameter("type"));

        if(!emp_no.equals(""))
        {
            System.out.print(emp_no + "我在userServlet中的searchById");
            UserDAO dao = (UserDAO) DAOFactory.createUserDAO();
            User user = dao.findUserById(emp_no);
            request.setAttribute("user", user);
            try
            {
                request.getRequestDispatcher("modifyPass.jsp").forward(request, response);
            }
            catch(Exception e)
            {
                e.printStackTrace();
            }
        }
    }

    private void delete(HttpServletRequest request, HttpServletResponse response)
    {
        String emp_no = request.getParameter("emp_no");
        UserDAO user = new UserDAO();
        boolean result = user.delete(emp_no);
        if(result)
        {
            try
            {
                // search(request, response);
                request.getRequestDispatcher("index.jsp").forward(request, response);
            }
            catch(Exception e)
            {

                e.printStackTrace();
            }

        }
    }

    private void add(HttpServletRequest request, HttpServletResponse response)
    {
        String emp_no = request.getParameter("emp_no");
        String emp_pass = request.getParameter("emp_pass");

        // System.out.println("user_type" + user_type);

        User user = new User();
        user.setEmp_no(emp_no);
        user.setEmp_pass(emp_pass);
        user.setUser_type(0);

        UserDAO userdao = new UserDAO();
        boolean result = userdao.insert(user);

        try
        {
            if(result)
            {
                request.setAttribute("user", user);
                request.setAttribute("user_type", 0);
                request.setAttribute("result", result);
                request.getRequestDispatcher("index.jsp").forward(request, response);
            }
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }
}

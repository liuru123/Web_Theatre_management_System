package xupt.se.ttms.servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import xupt.se.util.ConnectionManager;

@SuppressWarnings("serial")
public class myServlet extends HttpServlet
{

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException
    {
        doPost(request, response);
    }

    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException
    {
        String name = req.getParameter("uName");
        String passString = req.getParameter("uPass");

        try
        {
            Connection con = (Connection) ConnectionManager.getInstance().getConnection();

            PreparedStatement pre = con.prepareStatement("select * from user where emp_no= ? && emp_pass=?");
            pre.setString(1, name);
            pre.setString(2, passString);
            ResultSet rs = null;
            HttpSession session = req.getSession();
            rs = pre.executeQuery();
            session.setAttribute("admin", null);
            String desc = "";
            if(!rs.next())
            {
                desc = "请重新登录，您的密码错误";
                req.setAttribute("desc", desc);
                req.getRequestDispatcher("login_only.jsp").forward(req, resp);
            }
            else
            {
                if((rs.getString(3)).equals("1"))
                {
                    desc = " ";
                    session.setAttribute("desc", desc);
                    session.setAttribute("emp_no", rs.getString(1));
                    session.setAttribute("admin", "yes");
                    session.setAttribute("login", "ok");
                    req.getRequestDispatcher("index.jsp").forward(req, resp);
                }
                else
                {
                    session.setAttribute("login", "ok");
                    req.getRequestDispatcher("userLogin.jsp").forward(req, resp);
                }
                System.out.print("用户名" + rs.getString(1) + "密码" + rs.getString(2) + "类型"
                        + rs.getString(3));
            }
        }
        catch(SQLException e)
        {
            e.printStackTrace();
        }
    }
}

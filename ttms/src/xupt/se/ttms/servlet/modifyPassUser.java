package xupt.se.ttms.servlet;

import java.io.File;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.jsp.JspFactory;
import javax.servlet.jsp.PageContext;

import xupt.se.ttms.dao.UserDAO;
import xupt.se.ttms.model.User;

import com.jspsmart.upload.SmartUpload;
import com.jspsmart.upload.SmartUploadException;

/**
 * Servlet implementation class modifyPass
 */
@WebServlet("/modifyPassUser")
public class modifyPassUser extends HttpServlet
{
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException
    {
        doPost(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException
    {
        User user = new User();
        UserDAO userdao = new UserDAO();

        SmartUpload mySmartUpload = new SmartUpload();
        // PageContext是jsp的内置对象，在servlet不能直接使用，需要做一些处理
        JspFactory _jspxFactory = JspFactory.getDefaultFactory();
        PageContext pageContext = _jspxFactory.getPageContext(this, request, response, "", true,
                                                              8192, true);
        // 初始化
        mySmartUpload.initialize(pageContext);

        // 设置上载的最大值,注意:如果这里设置过大会出现问题!
        mySmartUpload.setMaxFileSize(10 * 1024 * 1024);
        // 上载文件
        try
        {
            mySmartUpload.upload();
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }

        // 取得上载的文件
        com.jspsmart.upload.File myFile = mySmartUpload.getFiles().getFile(0);

        String emp_no = (String) mySmartUpload.getRequest().getParameter("emp_no");

        String emp_pass = (String) mySmartUpload.getRequest().getParameter("emp_pass");
        String emp_password = (String) mySmartUpload.getRequest().getParameter("emp_password");
        System.out.println("emp_no" + emp_no + emp_pass + emp_password);
        int type = userdao.findUserById(emp_no).getUser_type();

        // 取得上载的文件的文件名
        String myFileName = myFile.getFileName();
        // 保存路径
        String aa = getServletContext().getRealPath("/") + "jsp\\";
        File aadir = new File(aa);
        if(!aadir.exists())
            aadir.mkdirs();
        String trace = aa + myFileName;
        // 将文件保存在服务器端(使用全路径)
        try
        {
            myFile.saveAs(trace, SmartUpload.SAVE_PHYSICAL);
        }
        catch(SmartUploadException e)
        {
            e.printStackTrace();
        }

        user.setEmp_no(emp_no);
        user.setEmp_pass(emp_password);
        user.setUser_type(type);
        user.setUserImage("jsp\\" + myFileName);

        userdao.upadte(user);

        request.setAttribute("user", user);
        // 带着user对象转发到result.java页
        try
        {
            request.getRequestDispatcher("result.jsp").forward(request, response);
        }
        catch(ServletException e)
        {
            e.printStackTrace();
        }

    }
}

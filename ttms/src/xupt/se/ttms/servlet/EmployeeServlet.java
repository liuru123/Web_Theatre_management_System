package xupt.se.ttms.servlet;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import xupt.se.ttms.dao.EmployeeDAO;
import xupt.se.ttms.idao.DAOFactory;
import xupt.se.ttms.model.Employee;

@WebServlet("/employee/EmployeeServlet")
public class EmployeeServlet extends HttpServlet
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
            add(request, response);
        else
            if(method.equalsIgnoreCase("delete"))
                delete(request, response);
            else
                if(method.equalsIgnoreCase("update"))
                    update(request, response);
                else
                    if(method.equalsIgnoreCase("search"))
                        search(request, response);
                    else
                        if(method.equalsIgnoreCase("searchById"))
                            searchById(request, response);
                        else
                            if(method.equalsIgnoreCase("searchByPage"))
                                searchByPage(request, response);
    }

    private void add(HttpServletRequest request, HttpServletResponse response)
    {
        String emp_no = request.getParameter("emp_no");
        String emp_name = request.getParameter("emp_name");
        String emp_tel_num = request.getParameter("emp_tel_num");
        String emp_addr = request.getParameter("emp_addr");
        String emp_email = request.getParameter("emp_email");
        Employee employee = new Employee();
        employee.setEmp_no(emp_no);
        employee.setEmp_name(emp_name);
        employee.setEmp_tel_num(emp_tel_num);
        employee.setEmp_addr(emp_addr);
        employee.setEmp_email(emp_email);
        EmployeeDAO dao = (EmployeeDAO) DAOFactory.creatEmployeeDAO();
        boolean result = dao.insert(employee);
        try
        {
            if(result)
                request.setAttribute("result", "添加成功!");
            else
                request.setAttribute("result", "添加失败!");
            request.getRequestDispatcher("add.jsp").forward(request, response);
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }

    private void delete(HttpServletRequest request, HttpServletResponse response)
    {
        boolean result = false;
        int emp_id = Integer.parseInt(request.getParameter("emp_id"));
        if(emp_id > 0)
        {
            EmployeeDAO dao = (EmployeeDAO) DAOFactory.creatEmployeeDAO();
            result = dao.delete(emp_id);
            if(result)
                request.setAttribute("result", "删除成功!");
            else
                request.setAttribute("result", "删除失败!");
            // 不分页时删除调用全查
            search(request, response);
            // 分页时删除调用分页全查:使用分页index1.jsp时，把这里注释打开
            searchByPage(request, response);
        }
    }

    private void update(HttpServletRequest request, HttpServletResponse response)
    {
        int emp_id = Integer.parseInt(request.getParameter("emp_id"));
        String emp_no = request.getParameter("emp_no");
        String emp_name = request.getParameter("emp_name");
        String emp_tel_num = request.getParameter("emp_tel_num");
        String emp_addr = request.getParameter("emp_addr");
        String emp_email = request.getParameter("emp_email");
        Employee employee = new Employee();
        employee.setEmp_id(emp_id);
        employee.setEmp_no(emp_no);
        employee.setEmp_name(emp_name);
        employee.setEmp_tel_num(emp_tel_num);
        employee.setEmp_addr(emp_addr);
        employee.setEmp_email(emp_email);
        EmployeeDAO dao = (EmployeeDAO) DAOFactory.creatEmployeeDAO();
        boolean result = dao.update(employee);
        try
        {
            if(result)
                request.setAttribute("result", "更新成功!");
            else
                request.setAttribute("result", "更新失败!");
            request.setAttribute("employee", employee);
            request.getRequestDispatcher("update.jsp").forward(request, response);
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }

    private void search(HttpServletRequest request, HttpServletResponse response)
    {
        String emp_name = request.getParameter("emp_name");
        EmployeeDAO dao = (EmployeeDAO) DAOFactory.creatEmployeeDAO();
        ArrayList<Employee> list = null;
        if(emp_name == null || emp_name.equals(""))
            list = dao.findEmployeeAll();
        else
            list = dao.findEmployeeByName(emp_name);
        try
        {
            request.setAttribute("search_emp_name", emp_name);
            request.setAttribute("list", list);
            request.getRequestDispatcher("index.jsp").forward(request, response);
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }

    private void searchById(HttpServletRequest request, HttpServletResponse response)
    {
        int emp_id = Integer.parseInt(request.getParameter("emp_id"));
        if(emp_id > 0)
        {
            EmployeeDAO dao = (EmployeeDAO) DAOFactory.creatEmployeeDAO();
            Employee emp = dao.findEmployeeById(emp_id);
            request.setAttribute("employee", emp);
            try
            {
                request.getRequestDispatcher("update.jsp").forward(request, response);
            }
            catch(Exception e)
            {
                e.printStackTrace();
            }
        }
    }

    private void searchByPage(HttpServletRequest request, HttpServletResponse response)
    {
        int currentPage = 1; // 当前页默认为第一页
        String strpage = request.getParameter("currentPage"); // 获取前台传入当前页
        if(strpage != null && !strpage.equals(""))
        {
            currentPage = Integer.parseInt(strpage) < 1 ? 1 : Integer.parseInt(strpage); // 将字符串转换成整型
        }
        String emp_name = request.getParameter("emp_name");
        EmployeeDAO dao = (EmployeeDAO) DAOFactory.creatEmployeeDAO();
        // 从UserDAO中获取所有用户信息
        ArrayList<Employee> list = dao.findEmployeeByPage(currentPage, emp_name);
        // 从UserDAO中获取总记录数
        int allCount = dao.getAllCount();
        // 从UserDAO中获取总页数
        int allPageCount = dao.getAllPageCount();
        // 从UserDAO中获取当前页
        currentPage = dao.getCurrentPage();

        // 存入request中
        request.setAttribute("allEmployee", list);
        request.setAttribute("allCount", allCount);
        request.setAttribute("allPageCount", allPageCount);
        request.setAttribute("currentPage", currentPage);
        request.setAttribute("search_emp_name", emp_name);

        try
        {
            request.getRequestDispatcher("index1.jsp").forward(request, response);
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }
}

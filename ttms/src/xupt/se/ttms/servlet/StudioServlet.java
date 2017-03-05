package xupt.se.ttms.servlet;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import xupt.se.ttms.dao.SeatDAO;
import xupt.se.ttms.dao.StudioDAO;
import xupt.se.ttms.idao.DAOFactory;
import xupt.se.ttms.model.Studio;

@WebServlet("/hall/StudioServlet")
public class StudioServlet extends HttpServlet
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
                        else
                            if(method.equalsIgnoreCase("searchByPage"))
                            {
                                searchByPage(request, response);
                            }
    }

    private void searchByPage(HttpServletRequest request, HttpServletResponse response)
    {
        int currentPage = 1;
        String strcpg = request.getParameter("currentPage");
        if(strcpg != null && !strcpg.equals(""))
        {
            currentPage = Integer.parseInt(strcpg) < 1 ? 1 : Integer.parseInt(strcpg);
        }
        String studio_name = request.getParameter("studio_name");
        StudioDAO dao = (StudioDAO) DAOFactory.createIStudioDAO();
        ArrayList<Studio> list = dao.searchStudioByPage(currentPage, studio_name);
        int allCount = dao.getAllCount();
        int allPageCount = dao.getAllPageCount();
        currentPage = dao.getCurrentPage();

        // 存入request
        request.setAttribute("allStudio", list);
        request.setAttribute("allCount", allCount);
        request.setAttribute("allPageCount", allPageCount);
        request.setAttribute("currentPage", currentPage);
        request.setAttribute("search_studio_name", studio_name);
        try
        {
            request.getRequestDispatcher("index.jsp").forward(request, response);
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }

    private void searchById(HttpServletRequest request, HttpServletResponse response)
    {
        int studio_id = Integer.parseInt(request.getParameter("studio_id"));
        if(studio_id > 0)
        {
            StudioDAO dao = (StudioDAO) DAOFactory.createIStudioDAO();
            Studio studio = dao.searchByStudioId(studio_id);
            request.setAttribute("studio", studio);
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

    private void update(HttpServletRequest request, HttpServletResponse response)
    {
        int studio_id = Integer.parseInt(request.getParameter("studio_id"));
        String studio_name = request.getParameter("studio_name");
        int studio_row_count = Integer.parseInt(request.getParameter("studio_row_count"));
        int studio_col_count = Integer.parseInt(request.getParameter("studio_col_count"));
        String studio_introduction = request.getParameter("studio_introduction");
        int studio_flag = Integer.parseInt(request.getParameter("studio_flag"));

        SeatDAO seatdao = new SeatDAO();
        boolean result1 = seatdao.deleteStudio(studio_id);
        if(result1)
        {
            System.out.print(result1 + "delete 成功");
        }
        Studio studio = new Studio();
        studio.setStudio_id(studio_id);
        studio.setStudio_name(studio_name);
        studio.setStudio_row_count(studio_row_count);
        studio.setStudio_col_count(studio_col_count);
        studio.setStudio_introduction(studio_introduction);
        studio.setStudio_flag(studio_flag);

        StudioDAO dao = (StudioDAO) DAOFactory.createIStudioDAO();
        boolean result = dao.update(studio);

        for(int i = 1; i <= studio_row_count; i++)
        {
            for(int j = 1; j <= studio_col_count; j++)
            {

                studio.setStudio_id(studio_id);
                studio.setStudio_row_count(i);
                studio.setStudio_col_count(j);
                seatdao.insert(studio);
            }

            try
            {
                if(result)
                {
                    request.setAttribute("result", "更新成功");
                    request.getRequestDispatcher("update.jsp").forward(request, response);
                }
                else
                {
                    request.setAttribute("result", "更新失败");
                    request.setAttribute("studio", studio);
                    request.getRequestDispatcher("update.jsp").forward(request, response);
                }
            }
            catch(Exception e)
            {
                e.printStackTrace();
            }
        }

    }

    private void search(HttpServletRequest request, HttpServletResponse response)
    {
        String studio_name = request.getParameter("studio_name");
        StudioDAO dao = (StudioDAO) DAOFactory.createIStudioDAO();
        ArrayList<Studio> list = null;
        if(studio_name == null || studio_name.equals(""))
        {
            list = dao.searchAll();

        }
        else
        {
            list = dao.searchByStudioName(studio_name);
        }
        try
        {
            request.setAttribute("search_studio_name", studio_name);
            request.setAttribute("list", list);
            request.getRequestDispatcher("index.jsp").forward(request, response);
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }

    private void delete(HttpServletRequest request, HttpServletResponse response)
    {
        boolean result = false;
        int studio_id = Integer.parseInt(request.getParameter("studio_id"));
        if(studio_id > 0)
        {
            StudioDAO dao = (StudioDAO) DAOFactory.createIStudioDAO();
            result = dao.delete(studio_id);
            if(result)
            {
                request.setAttribute("result", "删除成功");
            }
            else
            {
                request.setAttribute("result", "删除失败");
            }
            // 分页时删除调用分页删除
            searchByPage(request, response);
        }
    }

    private void add(HttpServletRequest request, HttpServletResponse response)
    {
        int studio_id = Integer.parseInt(request.getParameter("studio_id"));
        String studio_name = request.getParameter("studio_name");
        int studio_row_count = Integer.parseInt(request.getParameter("studio_row_count"));
        int studio_col_count = Integer.parseInt(request.getParameter("studio_col_count"));
        String studio_introduction = request.getParameter("studio_introduction");
        int studio_flag = Integer.parseInt(request.getParameter("studio_flag"));
        System.out.println(studio_id + studio_name + studio_row_count + studio_col_count);
        Studio studio = new Studio();
        studio.setStudio_id(studio_id);
        studio.setStudio_name(studio_name);
        studio.setStudio_row_count(studio_row_count);
        studio.setStudio_col_count(studio_col_count);
        studio.setStudio_introduction(studio_introduction);
        studio.setStudio_flag(studio_flag);
        int temp = 0;

        StudioDAO dao = (StudioDAO) DAOFactory.createIStudioDAO();
        SeatDAO seatdao = new SeatDAO();
        boolean result = dao.insert(studio);
        System.out.println(result);
        for(int i = 1; i <= studio_row_count; i++)
        {
            for(int j = 1; j <= studio_col_count; j++)
            {
                temp++;
                studio.setStudio_id(studio_id);
                studio.setStudio_row_count(i);
                studio.setStudio_col_count(j);
                seatdao.insert(studio);
            }
        }
        System.out.println(temp);
        try
        {
            if(result)
                request.setAttribute("result", "添加成功");
            else
                request.setAttribute("result", "添加失败");
            request.getRequestDispatcher("add.jsp").forward(request, response);
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }
}

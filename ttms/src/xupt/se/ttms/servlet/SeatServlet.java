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
import xupt.se.ttms.model.Seat;
import xupt.se.ttms.model.Studio;

@WebServlet("/seat/SeatServlet")
public class SeatServlet extends HttpServlet
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
        if(method.equalsIgnoreCase("searchByStudio"))
        {
            searchByStudio(request, response);
        }
        else
            if(method.equalsIgnoreCase("update"))
            {
                update(request, response);
            }
        // else
        // if(method.equalsIgnoreCase("searchUseSeat"))
        // {
        // searchUseSeat(request, response);
        // }
    }

    // private void searchUseSeat(HttpServletRequest request, HttpServletResponse response)
    // {
    // int studio_id = Integer.parseInt(request.getParameter("studio_id"));
    // Studio studio = new Studio();
    // studio.setStudio_id(studio_id);
    // SeatDAO seatdao = new SeatDAO();
    // int useSeat = seatdao.findUseSeat(studio);
    // int total = studio.getStudio_col_count() * studio.getStudio_row_count();
    // int unuseSeat = total - useSeat;
    // try
    // {
    // request.setAttribute("use", useSeat);
    // request.setAttribute("unuse", unuseSeat);
    // }
    // catch(Exception e)
    // {
    // e.printStackTrace();
    // }
    // }

    private void update(HttpServletRequest request, HttpServletResponse response)
    {
        int seat_id = Integer.parseInt(request.getParameter("seat"));
        int studio_id = Integer.parseInt(request.getParameter("studio_id"));
        SeatDAO seatdao = new SeatDAO();
        Seat seat = seatdao.getSeatById(seat_id);
        seat.setStudio_id(studio_id);

        if(seat.getSeat_status() == 1)
        {
            seat.setSeat_status(0);

        }
        else
        {
            seat.setSeat_status(1);

        }
        boolean result = seatdao.update(seat);

        try
        {

            searchByStudio(request, response);
            request.getRequestDispatcher("index.jsp").forward(request, response);
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }

    private void searchByStudio(HttpServletRequest request, HttpServletResponse response)
    {
        int studio_id;
        String studio_id1 = request.getParameter("studio_id");
        if(studio_id1 == null || studio_id1.equals(""))
        {
            studio_id = 1;
        }
        else
        {
            studio_id = Integer.parseInt(studio_id1);
        }
        SeatDAO seatdao = new SeatDAO();
        Studio studio = new Studio();
        StudioDAO studiodao = new StudioDAO();
        studio = studiodao.searchByStudioId(studio_id);
        int useSeat = seatdao.findUseSeat(studio);
        int total = studio.getStudio_col_count() * studio.getStudio_row_count();
        int unuseSeat = total - useSeat;

        SeatDAO seat = new SeatDAO();
        ArrayList<Seat> list = null;
        list = seat.getSeatByStudio(studio_id);

        try
        {
            request.setAttribute("use", useSeat);
            request.setAttribute("unuse", unuseSeat);
            request.setAttribute("list", list);
            request.setAttribute("search_studio_id", studio_id);
            request.setAttribute("studio", studio);
            request.getRequestDispatcher("index.jsp").forward(request, response);
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }

}

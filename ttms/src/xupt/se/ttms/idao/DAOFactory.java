package xupt.se.ttms.idao;

import xupt.se.ttms.dao.EmployeeDAO;
import xupt.se.ttms.dao.PlayDAO;
import xupt.se.ttms.dao.SeatDAO;
import xupt.se.ttms.dao.StudioDAO;
import xupt.se.ttms.dao.UserDAO;

public class DAOFactory
{
    public static IEmployee creatEmployeeDAO()
    {
        return new EmployeeDAO();
    }

    public static IUser createUserDAO()
    {
        return new UserDAO();
    }

    public static IPlay createPlayDAO()
    {
        return new PlayDAO();
    }

    public static IStudio createIStudioDAO()
    {
        return new StudioDAO();
    }

    public static ISeat createISeatDAO()
    {
        return new SeatDAO();
    }
}
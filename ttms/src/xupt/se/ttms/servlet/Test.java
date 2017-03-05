package xupt.se.ttms.servlet;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import xupt.se.util.ConnectionManager;

public class Test
{

    public static void main(String[] args)
    {
        // TODO 自动生成的方法存根
        try
        {
            Connection con = (Connection) ConnectionManager.getInstance().getConnection();

            // Connection con = (Connection)
            // DriverManager.getConnection("jdbc:mysql://localhost/test","root","root");

            Statement stmt = null;
            stmt = con.createStatement();
            ResultSet rs = null;
            rs = stmt.executeQuery("select  *  from user");
            while(rs.next())
            {
                System.out.println(rs.getString(1) + "     " + rs.getString(2));
            }
        }
        catch(SQLException e)
        {
            // TODO 自动生成的 catch 块
            e.printStackTrace();
        }

    }

}

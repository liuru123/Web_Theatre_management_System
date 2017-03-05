import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import xupt.se.util.ConnectionManager;

public class TestUserDAO extends Thread
{
    private static ConnectionManager manger = ConnectionManager.getInstance();

    // 使用连接池
    public void run()
    {
        try
        {
            Connection con = null;
            Statement stmt = null;
            ResultSet rSet = null;
            int count = 0;
            try
            {
                con = manger.getConnection();
                stmt = con.createStatement();
                rSet = stmt.executeQuery("select  count(*)  from User");
                if(rSet.next())
                {
                    count = rSet.getInt(1);
                }
            }
            finally
            {
                ConnectionManager.close(rSet, stmt, con);
            }
        }
        catch(Exception e)
        {
            // TODO: handle exception
            e.printStackTrace();
        }
    }

    // 未使用连接池
    public void runNopool(int i)
    {
        String dbUrl = "jdbc:mysql://localhost/test?useUnicode=true&amp;characterEncoding=utf-8&amp;useSSL=true";
        try
        {
            Connection con = DriverManager.getConnection(dbUrl, "root", "root");
            Class.forName("com.mysql.jdbc.Driver");
            Statement st = null;
            ResultSet rSet = null;
            int count = 0;

            st = con.createStatement();
            rSet = st.executeQuery("select  count(*) from User");
            if(rSet.next())
            {
                count = rSet.getInt(1);
                rSet.close();
                st.close();
                con.close();
            }
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }

    // 对使用线程池和不使用线程池的比较
    public void compare()
    {
        long start, end;

        // 使用连接池
        TestUserDAO[] test = new TestUserDAO[1000];
        start = System.currentTimeMillis();

        try
        {
            for(int i = 0; i < test.length; i++)
            {
                test[i] = new TestUserDAO();
                test[i].start();
                test[i].join();
            }
        }
        catch(InterruptedException e)
        {
            // TODO: handle exception
            e.printStackTrace();
        }
        end = System.currentTimeMillis();
        System.out.print("总用时间是" + (end - start) + "秒");

        // 不使用连接池
        start = System.currentTimeMillis();
        TestUserDAO[] test2 = new TestUserDAO[1000];

        for(int i = 0; i < test2.length; i++)
        {
            test2[i] = new TestUserDAO();
            test2[i].runNopool(i);
        }
        end = System.currentTimeMillis();
        System.out.print("不使用连接池的总用时是" + (end - start) + "秒");

        System.out.print("Over");
    }

    public static void main(String[] args)
    {
        TestUserDAO test = new TestUserDAO();
        test.compare();
    }
}

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import xupt.se.util.ConnectionManager;

public class TestEmployeeDAO extends Thread
{
    // 为什么定义成静态的?
    private static ConnectionManager manager = ConnectionManager.getInstance();

    // 使用连接池
    public void run()
    {
        try
        {
            Connection con = null;
            Statement stmt = null;
            ResultSet rs = null;
            int count = 0;
            try
            {
                con = manager.getConnection();
                stmt = con.createStatement();
                rs = stmt.executeQuery("SELECT count(*) FROM employee");
                if(rs.next())
                    count = rs.getInt(1);
            }
            finally
            {
                ConnectionManager.close(rs, stmt, con);
            }
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }

    // 未使用连接池
    public void runNoPool(int i)
    {
        String dbUrl = "jdbc:mysql://localhost/test?useUnicode=true&amp;characterEncoding=utf-8&amp;useSSL=true";
        try
        {
            Connection con = DriverManager.getConnection(dbUrl, "root", "root");
            Class.forName("com.mysql.jdbc.Driver");
            Statement stmt = null;
            ResultSet rs = null;
            int count = 0;

            stmt = con.createStatement();
            rs = stmt.executeQuery("SELECT count(*) FROM employee");
            if(rs.next())
                count = rs.getInt(1);
            rs.close();
            stmt.close();
            con.close();
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }

    }

    // 对使用线程池和不使用的比较
    private void compare()
    {
        long start, end;

        // -----------使用连接池-----------
        start = System.currentTimeMillis();
        TestEmployeeDAO[] test = new TestEmployeeDAO[1000];
        try
        {
            for(int i = 0; i < test.length; i++)
            {
                test[i] = new TestEmployeeDAO();
                test[i].start();
                test[i].join();
            }
        }
        catch(InterruptedException e)
        {
            e.printStackTrace();
        }
        end = System.currentTimeMillis();
        System.out.println("使用连接池总用时= " + (end - start) + " ms");

        // -----------不使用连接池-----------
        start = System.currentTimeMillis();
        TestEmployeeDAO[] test2 = new TestEmployeeDAO[1000];
        for(int i = 0; i < test2.length; i++)
        {
            test2[i] = new TestEmployeeDAO();
            test2[i].runNoPool(i);
        }
        end = System.currentTimeMillis();
        System.out.println("不使用连接池总用时= " + (end - start) + " ms");

        System.out.println("over");
    }

    public static void main(String[] args)
    {
        // 测试速度
        TestEmployeeDAO test = new TestEmployeeDAO();
        test.compare();

        // 正常使用DAO
        // EmployeeDAO dao = (EmployeeDAO) DAOFactory.creatEmployeeDAO();
        // ArrayList<Employee> list = dao.findEmployeeAll();
        // for(Employee e : list)
        // {
        // System.out.println(e.getEmp_name() + " : " + e.getEmp_addr());
        // }

    }
}

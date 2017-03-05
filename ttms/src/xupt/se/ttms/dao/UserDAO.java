package xupt.se.ttms.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import xupt.se.ttms.idao.IUser;
import xupt.se.ttms.model.User;
import xupt.se.util.ConnectionManager;

public class UserDAO implements IUser
{
    @SuppressWarnings("finally")
    public boolean isExist(String emp_no, String emp_pass)
    {
        boolean result = false;
        Connection con = ConnectionManager.getInstance().getConnection();
        ResultSet rs = null;
        PreparedStatement pstmt = null;
        String password = null;

        try
        {
            String sql = "select emp_pass from user where emp_no=?";
            pstmt = con.prepareStatement(sql);
            pstmt.setString(1, emp_no);
            rs = pstmt.executeQuery();
            if(rs.next())
            {
                password = rs.getString("emp_pass");
            }
            if(password != null && password.equals(emp_pass))
                result = true;
        }
        catch(SQLException e)
        {
            e.printStackTrace();
        }
        finally
        {
            ConnectionManager.close(rs, pstmt, con);
            return result;
        }
    }

    @SuppressWarnings("finally")
    public boolean insert(User user)
    {
        boolean result = false;
        if(user == null)
        {
            return result;
        }
        // 获取connection

        Connection con = ConnectionManager.getInstance().getConnection();
        PreparedStatement pstm = null;
        try
        {
            String sql = "insert into user(emp_no,emp_pass,user_type,userImage) values(?,?,?,?)";
            pstm = con.prepareStatement(sql);

            pstm.setString(1, user.getEmp_no());
            pstm.setString(2, user.getEmp_pass());
            pstm.setInt(3, user.getUser_type());
            pstm.setString(4, user.getUserImage());

            pstm.executeUpdate();
            result = true;
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        finally
        {
            ConnectionManager.close(null, pstm, con);
            return result;
        }

    }

    @SuppressWarnings("finally")
    @Override
    public boolean delete(String emp_no)
    {
        boolean result = false;
        if(emp_no.equals('0'))
        {
            return result;
        }
        Connection con = ConnectionManager.getInstance().getConnection();
        PreparedStatement pstm = null;

        try
        {
            String sql = "delete from user where emp_no=?";

            pstm = con.prepareStatement(sql);
            pstm.setString(1, emp_no);
            pstm.executeUpdate();
            ConnectionManager.close(null, pstm, con);
            result = true;
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        finally
        {
            ConnectionManager.close(null, pstm, con);
            return result;
        }
    }

    @SuppressWarnings("finally")
    @Override
    public User findUserById(String emp_no)
    {
        User userRes = null;
        if(emp_no.equals('0'))
        {
            return userRes;
        }

        Connection con = ConnectionManager.getInstance().getConnection();
        PreparedStatement pstm = null;
        ResultSet rs = null;

        try
        {
            String sql = "select * from user where emp_no=?";
            pstm = con.prepareStatement(sql);

            pstm.setString(1, emp_no);
            rs = pstm.executeQuery();
            if(rs.next())
            {
                userRes = new User();
                userRes.setEmp_no(emp_no);
                userRes.setEmp_pass(rs.getString("emp_pass"));
                userRes.setUser_type(rs.getInt("user_type"));
                userRes.setUserImage(rs.getString("userImage"));
            }
        }
        catch(Exception e)
        {
            // TODO: handle exception
            e.printStackTrace();
        }
        finally
        {
            ConnectionManager.close(rs, pstm, con);
            return userRes;
        }
    }

    @SuppressWarnings("finally")
    @Override
    public ArrayList<User> findUserAll()
    {
        // TODO 自动生成的方法存根
        ArrayList<User> list = new ArrayList<User>();
        PreparedStatement pstm = null;
        User user = null;
        // 获取连接
        Connection con = ConnectionManager.getInstance().getConnection();
        ResultSet rs = null;
        try
        {
            String sql = "select  *  from user";
            pstm = con.prepareStatement(sql);
            rs = pstm.executeQuery();
            while(rs.next())
            {
                user = new User();

                user.setEmp_no(rs.getString("emp_no"));
                user.setEmp_pass(rs.getString("emp_pass"));
                user.setUser_type(rs.getInt("user_type"));
                user.setUserImage(rs.getString("userImage"));

                list.add(user);
            }

        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        finally
        {
            ConnectionManager.close(rs, pstm, con);
            return list;
        }
    }

    @SuppressWarnings("finally")
    @Override
    public boolean upadte(User user)
    {
        boolean result = false;
        if(user == null)
        {
            return result;
        }
        Connection con = ConnectionManager.getInstance().getConnection();
        PreparedStatement pstm = null;

        try
        {
            String sql = "update user set  emp_pass=?,user_type=?, userImage=? where emp_no=?";
            pstm = con.prepareStatement(sql);

            pstm.setString(1, user.getEmp_pass());
            pstm.setInt(2, user.getUser_type());
            pstm.setString(3, user.getUserImage());
            pstm.setString(4, user.getEmp_no());

            pstm.executeUpdate();

            result = true;
        }
        catch(Exception e)
        {
            // TODO: handle exception
            e.printStackTrace();
        }
        finally
        {
            ConnectionManager.close(null, pstm, con);
            return result;
        }
    }

}

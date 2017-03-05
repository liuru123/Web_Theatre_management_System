package xupt.se.ttms.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import xupt.se.ttms.idao.IStudio;
import xupt.se.ttms.model.Studio;
import xupt.se.util.ConnectionManager;

public class StudioDAO implements IStudio
{
    public static final int PAGE_SIZE = 5;
    private int currentPage;
    private int allPageCount;
    private int allCount;
    public static int id = 1;

    public int getCurrentPage()
    {
        return currentPage;
    }

    public int getAllPageCount()
    {
        return allPageCount;
    }

    public int getAllCount()
    {
        return allCount;
    }

    @SuppressWarnings("finally")
    public ArrayList<Studio> searchStudioByPage(int cPage, String studio_name)
    {
        currentPage = cPage;
        ArrayList<Studio> list = new ArrayList<Studio>();
        // 若查询的姓名为空时，默认为全部查询
        if(null == studio_name || studio_name.equals("null"))
        {
            studio_name = "";
        }

        // 获取连接
        Connection con = ConnectionManager.getInstance().getConnection();
        PreparedStatement pstm = null;
        ResultSet rst = null;

        try
        {
            String sql1 = "select count(studio_id) as allRecord from studio where studio_name like ?";
            pstm = con.prepareStatement(sql1);
            pstm.setString(1, "%" + studio_name + "%");
            rst = pstm.executeQuery();
            if(rst.next())
            {
                allCount = rst.getInt("allRecord");
            }
            rst.close();
            pstm.close();

            // 计算总的页数
            allPageCount = (allCount + PAGE_SIZE - 1) / PAGE_SIZE;
            // 如果当前页大于总页数的时候赋予最后一页
            if(allPageCount > 0 && currentPage > allPageCount)
            {
                currentPage = allPageCount;
            }

            // 获取地currentPage页的内容
            String sql2 = "select * from studio where studio_name like ? limit ?,?";
            pstm = con.prepareStatement(sql2);
            pstm.setString(1, "%" + studio_name + "%");
            pstm.setInt(2, (currentPage - 1) * PAGE_SIZE);
            pstm.setInt(3, PAGE_SIZE);
            rst = pstm.executeQuery();
            Studio studio = null;
            while(rst.next())
            {
                studio = new Studio();
                studio.setStudio_id(rst.getInt("studio_id"));
                studio.setStudio_name(rst.getString("studio_name"));
                studio.setStudio_row_count(rst.getInt("studio_row_count"));
                studio.setStudio_col_count(rst.getInt("studio_col_count"));
                studio.setStudio_introduction(rst.getString("studio_introduction"));
                studio.setStudio_flag(rst.getInt("studio_flag"));

                list.add(studio);
            }
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        finally
        {
            ConnectionManager.close(rst, pstm, con);
            return list;
        }
    }

    @SuppressWarnings("finally")
    @Override
    public boolean insert(Studio studio)
    {
        Boolean result = false;
        if(studio == null)
        {
            return result;
        }
        Connection con = ConnectionManager.getInstance().getConnection();
        PreparedStatement pstm = null;
        try
        {
            String sql = "insert into studio (studio_id,studio_name,studio_row_count,studio_col_count,studio_introduction,studio_flag) values(?,?,?,?,?,?)";
            pstm = con.prepareStatement(sql);

            pstm.setInt(1, studio.getStudio_id());
            pstm.setString(2, studio.getStudio_name());
            pstm.setInt(3, studio.getStudio_row_count());
            pstm.setInt(4, studio.getStudio_col_count());
            pstm.setString(5, studio.getStudio_introduction());
            pstm.setInt(6, studio.getStudio_flag());

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
    public boolean delete(int studio_id)
    {
        Boolean result = false;
        if(studio_id == 0)
        {
            return result;
        }
        Connection con = ConnectionManager.getInstance().getConnection();
        PreparedStatement pstm = null;
        try
        {
            String sql = "delete from studio where studio_id = ?";
            pstm = con.prepareStatement(sql);

            pstm.setInt(1, studio_id);
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
    public boolean update(Studio studio)
    {
        Boolean result = false;
        if(studio == null)
        {
            return result;
        }
        Connection con = ConnectionManager.getInstance().getConnection();
        PreparedStatement pstm = null;
        try
        {
            String sql = "update studio set studio_name=?,studio_row_count =? , studio_col_count=?,studio_introduction=? ,studio_flag=? where studio_id=?";
            pstm = con.prepareStatement(sql);

            pstm.setString(1, studio.getStudio_name());
            pstm.setInt(2, studio.getStudio_row_count());
            pstm.setInt(3, studio.getStudio_col_count());
            pstm.setString(4, studio.getStudio_introduction());
            pstm.setInt(5, studio.getStudio_flag());
            pstm.setInt(6, studio.getStudio_id());

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
    public Studio searchByStudioId(int studio_id)
    {
        Studio studio = null;
        if(studio_id == 0)
        {
            return studio;
        }
        Connection con = ConnectionManager.getInstance().getConnection();
        PreparedStatement pstm = null;
        ResultSet rsSet = null;
        try
        {
            String sql = "select * from studio where studio_id= ?";
            pstm = con.prepareStatement(sql);
            pstm.setInt(1, studio_id);

            rsSet = pstm.executeQuery();
            if(rsSet.next())
            {

                studio = new Studio();

                studio.setStudio_id(studio_id);
                studio.setStudio_name(rsSet.getString("studio_name"));
                studio.setStudio_row_count(rsSet.getInt("studio_row_count"));
                studio.setStudio_col_count(rsSet.getInt("studio_col_count"));
                studio.setStudio_introduction(rsSet.getString("studio_introduction"));
                studio.setStudio_flag(rsSet.getInt("studio_flag"));

            }
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        finally
        {
            ConnectionManager.close(rsSet, pstm, con);
            return studio;
        }
    }

    @SuppressWarnings("finally")
    @Override
    public ArrayList<Studio> searchByStudioName(String studio_name)
    {
        ArrayList<Studio> list = new ArrayList<Studio>();
        Studio studio = null;
        if(studio_name.equals(" "))
        {
            return null;
        }

        Connection con = ConnectionManager.getInstance().getConnection();
        PreparedStatement pstm = null;
        ResultSet rsSet = null;
        try
        {
            String sql = "select * from studio where studio_name like ?";
            pstm = con.prepareStatement(sql);
            pstm.setString(1, "%" + studio_name + "%");

            rsSet = pstm.executeQuery();
            while(rsSet.next())
            {
                studio = new Studio();

                studio.setStudio_id(rsSet.getInt("studio_id"));
                studio.setStudio_name(studio_name);
                studio.setStudio_row_count(rsSet.getInt("studio_row_count"));
                studio.setStudio_col_count(rsSet.getInt("studio_col_count"));
                studio.setStudio_introduction(rsSet.getString("studio_introduction"));
                studio.setStudio_flag(rsSet.getInt(rsSet.getInt("studio_flag")));

                list.add(studio);
            }

        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        finally
        {
            ConnectionManager.close(rsSet, pstm, con);
            return list;
        }

    }

    @SuppressWarnings("finally")
    @Override
    public ArrayList<Studio> searchAll()
    {
        ArrayList<Studio> list = new ArrayList<Studio>();
        Studio studio = null;

        // 获取连接
        Connection con = ConnectionManager.getInstance().getConnection();
        PreparedStatement pstm = null;
        ResultSet rst = null;
        try
        {
            String sql = "select * from studio";
            pstm = con.prepareStatement(sql);
            rst = pstm.executeQuery();
            while(rst.next())
            {
                studio = new Studio();
                studio.setStudio_id(rst.getInt("studio_id"));
                studio.setStudio_name(rst.getString("studio_name"));
                studio.setStudio_row_count(rst.getInt("studio_row_count"));
                studio.setStudio_col_count(rst.getInt("studio_col_count"));
                studio.setStudio_introduction(rst.getString("studio_introduction"));
                studio.setStudio_flag(rst.getInt("studio_flag"));

                list.add(studio);

            }
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        finally
        {
            ConnectionManager.close(rst, pstm, con);
            return list;
        }

    }

}
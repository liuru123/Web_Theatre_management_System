package xupt.se.ttms.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import xupt.se.ttms.idao.IPlay;
import xupt.se.ttms.model.Play;
import xupt.se.util.ConnectionManager;

public class PlayDAO implements IPlay
{

    @SuppressWarnings("finally")
    @Override
    public boolean insert(Play play)
    {
        boolean result = false;
        if(play == null)
        {
            return result;
        }

        // 获取连接
        Connection con = ConnectionManager.getInstance().getConnection();
        PreparedStatement pstm = null;
        try
        {
            String sql = "insert into play (play_id,play_type_id,play_lang_id,play_name,play_introduction,play_image,play_length,play_ticket_price,play_status) values(?,?,?,?,?,?,?,?,?)";
            pstm = con.prepareStatement(sql);

            pstm.setInt(1, play.getPlay_id());
            pstm.setInt(2, play.getPlay_type_id());
            pstm.setInt(3, play.getPlay_lang_id());
            pstm.setString(4, play.getPlay_name());
            pstm.setString(5, play.getPlay_introduction());
            pstm.setString(6, play.getPlay_image());
            pstm.setInt(7, play.getPlay_length());
            pstm.setDouble(8, play.getPlay_ticket_price());
            pstm.setInt(9, play.getPlay_status());

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
    public boolean delete(int play_id)
    {
        boolean result = false;
        if(play_id == 0)
        {
            return result;
        }

        // 获取连接

        Connection con = ConnectionManager.getInstance().getConnection();
        PreparedStatement pstm = null;

        try
        {
            String sql = "delete from play where play_id = ?";

            pstm = con.prepareStatement(sql);

            pstm.setInt(1, play_id);
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
    public boolean update(Play play)
    {
        boolean result = false;
        if(play == null)
        {
            return result;
        }
        // 获取连接

        Connection con = ConnectionManager.getInstance().getConnection();
        PreparedStatement pstm = null;
        try
        {
            String sql = "update play set play_type_id=?,play_lang_id=?,play_name=?,play_introduction=?,play_image=?,play_length=?,play_ticket_price=?,play_status=? where  play_id=?";
            pstm = con.prepareStatement(sql);

            pstm.setInt(1, play.getPlay_type_id());
            pstm.setInt(2, play.getPlay_lang_id());
            pstm.setString(3, play.getPlay_name());
            pstm.setString(4, play.getPlay_introduction());
            pstm.setString(5, play.getPlay_image());
            pstm.setInt(6, play.getPlay_length());
            pstm.setDouble(7, play.getPlay_ticket_price());
            pstm.setInt(8, play.getPlay_status());
            pstm.setInt(9, play.getPlay_id());

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
    public ArrayList<Play> searchAllFilm()
    {
        ArrayList<Play> list = new ArrayList<Play>();
        PreparedStatement pstm = null;
        Play play = null;

        Connection con = ConnectionManager.getInstance().getConnection();
        ResultSet rs = null;
        try
        {
            String sql = "select * from play";

            pstm = con.prepareStatement(sql);
            rs = pstm.executeQuery();
            while(rs.next())
            {
                play = new Play();

                play.setPlay_id(rs.getInt("play_id"));
                play.setPlay_type_id(rs.getInt("play_type_id"));
                play.setPlay_lang_id(rs.getInt("play_lang_id"));
                play.setPlay_name(rs.getString("play_name"));
                play.setPlay_introduction(rs.getString("play_introduction"));
                play.setPlay_image(rs.getString("play_image"));
                play.setPlay_length(rs.getInt("play_length"));
                play.setPlay_ticket_price(rs.getDouble("play_ticket_price"));
                play.setPlay_status(rs.getInt("play_status"));

                list.add(play);
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
    public ArrayList<Play> getFilm(String play_name)
    {
        ArrayList<Play> list = new ArrayList<Play>();
        Play play = null;
        if(play_name.equals(' '))
        {
            return null;
        }
        Connection con = ConnectionManager.getInstance().getConnection();
        PreparedStatement pstm = null;
        ResultSet rs = null;
        try
        {
            String sql = " select * from play where play_name like ?";
            pstm = con.prepareStatement(sql);

            pstm.setString(1, " %" + play_name + "%");
            rs = pstm.executeQuery();
            while(rs.next())
            {
                play = new Play();

                play.setPlay_name(play_name);
                play.setPlay_id(rs.getInt("play_id"));
                play.setPlay_type_id(rs.getInt("play_type_id"));
                play.setPlay_lang_id(rs.getInt("play_lang_id"));
                play.setPlay_introduction(rs.getString("play_introduction"));
                play.setPlay_image(rs.getString("play_image"));
                play.setPlay_length(rs.getInt("play_length"));
                play.setPlay_ticket_price(rs.getDouble("play_ticket_price"));
                play.setPlay_status(rs.getInt("play_status"));

                list.add(play);
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
    public ArrayList<Play> getPlayByLang(int play_lang_id)
    {
        ArrayList<Play> list = new ArrayList<Play>();
        Play play = null;
        if(play_lang_id == 0)
        {
            return null;
        }
        Connection con = ConnectionManager.getInstance().getConnection();
        PreparedStatement pstm = null;
        ResultSet rsSet = null;
        try
        {
            String sql = "select * from play where play_lang_id = ?";
            pstm = con.prepareStatement(sql);

            pstm.setInt(1, play_lang_id);
            rsSet = pstm.executeQuery();
            while(rsSet.next())
            {
                play = new Play();

                play.setPlay_lang_id(play_lang_id);
                play.setPlay_id(rsSet.getInt("play_id"));
                play.setPlay_type_id(rsSet.getInt("play_type_id"));
                play.setPlay_name(rsSet.getString("play_name"));
                play.setPlay_introduction(rsSet.getString("play_introduction"));
                play.setPlay_image(rsSet.getString("play_image"));
                play.setPlay_length(rsSet.getInt("play_length"));
                play.setPlay_ticket_price(rsSet.getDouble("play_ticket_price"));
                play.setPlay_status(rsSet.getInt("play_status"));

                list.add(play);

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
    public ArrayList<Play> getPlayByType(int play_type_id)
    {
        ArrayList<Play> list = new ArrayList<Play>();
        if(play_type_id == 0)
        {
            return null;
        }
        Play play = null;

        Connection con = ConnectionManager.getInstance().getConnection();
        PreparedStatement pstm = null;
        ResultSet rst = null;
        try
        {
            String sql = "select * from play where play_type_id=?";
            pstm = con.prepareStatement(sql);
            pstm.setInt(1, play_type_id);

            rst = pstm.executeQuery();
            while(rst.next())
            {
                play = new Play();

                play.setPlay_type_id(play_type_id);
                play.setPlay_id(rst.getInt("play_id"));
                play.setPlay_lang_id(rst.getInt("play_lang_id"));
                play.setPlay_name(rst.getString("play_name"));
                play.setPlay_introduction(rst.getString("play_introduction"));
                play.setPlay_image(rst.getString("play_image"));
                play.setPlay_length(rst.getInt("play_length"));
                play.setPlay_ticket_price(rst.getDouble("play_ticket_price"));
                play.setPlay_status(rst.getInt("play_status"));
                list.add(play);
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

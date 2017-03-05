package xupt.se.ttms.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import xupt.se.ttms.idao.ISeat;
import xupt.se.ttms.model.Seat;
import xupt.se.ttms.model.Studio;
import xupt.se.util.ConnectionManager;

public class SeatDAO implements ISeat
{

    private int useSeat; // 可用的座位

    public int getUseSeat()
    {
        return useSeat;
    }

    @SuppressWarnings("finally")
    public int findUseSeat(Studio studio)
    {
        if(studio == null)
        {
            return 0;
        }

        Connection con = ConnectionManager.getInstance().getConnection();
        PreparedStatement pstm = null;
        ResultSet rs = null;
        try
        {
            String sql = "select count(seat_id) as AllRecord  from seat where studio_id =? and seat_status=?";
            pstm = con.prepareStatement(sql);
            pstm.setInt(1, studio.getStudio_id());
            pstm.setInt(2, 1);
            rs = pstm.executeQuery();

            if(rs.next())
                useSeat = rs.getInt("AllRecord");
            System.out.print(rs.getInt("AllRecord"));
            rs.close();
            pstm.close();
            return useSeat;
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        return 0;
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
            String sql = "insert into seat (studio_id,seat_row,seat_column,seat_status) values(?,?,?,?)";
            pstm = con.prepareStatement(sql);
            pstm.setInt(1, studio.getStudio_id());
            pstm.setInt(2, studio.getStudio_row_count());
            pstm.setInt(3, studio.getStudio_col_count());
            pstm.setInt(4, 1);
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
    public boolean delete(int seat_id)
    {
        Boolean result = false;
        if(seat_id == 0)
        {
            return result;
        }
        Connection con = ConnectionManager.getInstance().getConnection();
        PreparedStatement pstm = null;
        try
        {
            String sql = "delete from seat where seat_id=?";
            pstm = con.prepareStatement(sql);

            pstm.setInt(1, seat_id);
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
    public boolean deleteStudio(int studio_id)
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
            String sql = "delete from seat where studio_id=?";
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
    public boolean update(Seat seat)
    {
        Boolean result = false;
        if(seat == null)
        {
            return result;
        }
        Connection con = ConnectionManager.getInstance().getConnection();
        PreparedStatement pstm = null;
        try
        {
            String sql = "update seat set studio_id=?,seat_row=?,seat_column=?,seat_status=? where seat_id=?";
            pstm = con.prepareStatement(sql);

            pstm.setInt(1, seat.getStudio_id());
            pstm.setInt(2, seat.getSeat_row());
            pstm.setInt(3, seat.getSeat_column());
            pstm.setInt(4, seat.getSeat_status());
            pstm.setInt(5, seat.getSeat_id());

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
    public Seat getSeatById(int seat_id)
    {
        Seat seatTable = null;
        if(seat_id == 0)
        {
            return seatTable;
        }
        Connection con = ConnectionManager.getInstance().getConnection();
        PreparedStatement pstm = null;
        ResultSet rs = null;
        try
        {
            String sql = "select * from seat where seat_id =?";
            pstm = con.prepareStatement(sql);

            pstm.setInt(1, seat_id);
            rs = pstm.executeQuery();
            if(rs.next())
            {
                seatTable = new Seat();

                seatTable.setSeat_id(seat_id);
                seatTable.setStudio_id(rs.getInt("studio_id"));
                seatTable.setSeat_row(rs.getInt("seat_row"));
                seatTable.setSeat_column(rs.getInt("seat_column"));
                seatTable.setSeat_status(rs.getInt("seat_status"));

            }
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        finally
        {
            ConnectionManager.close(rs, pstm, con);
            return seatTable;
        }

    }

    @SuppressWarnings("finally")
    @Override
    public ArrayList<Seat> getSeatByStudio(int studio_id)
    {
        ArrayList<Seat> list = new ArrayList<Seat>();
        Seat seatTable = null;
        if(studio_id == 0)
        {
            return null;
        }
        Connection con = ConnectionManager.getInstance().getConnection();
        PreparedStatement pstm = null;
        ResultSet rs = null;
        try
        {
            String sql = "select * from seat where studio_id =?";
            pstm = con.prepareStatement(sql);

            pstm.setInt(1, studio_id);

            rs = pstm.executeQuery();
            while(rs.next())
            {
                seatTable = new Seat();

                seatTable.setSeat_id(rs.getInt("seat_id"));
                seatTable.setStudio_id(studio_id);
                seatTable.setSeat_row(rs.getInt("seat_row"));
                seatTable.setSeat_column(rs.getInt("seat_column"));
                seatTable.setSeat_status(rs.getInt("seat_status"));

                list.add(seatTable);
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

}

package xupt.se.ttms.idao;

import java.util.ArrayList;

import xupt.se.ttms.model.Seat;
import xupt.se.ttms.model.Studio;

public interface ISeat
{
    // 增
    public boolean insert(Studio studio);

    // 删
    public boolean delete(int seat_id);

    // 改
    public boolean update(Seat seat);

    // 根据座位id查找座位
    public Seat getSeatById(int seat_id);

    // 根据座位所在的影厅查找座位
    public ArrayList<Seat> getSeatByStudio(int studio_id);

    // 输入studio_id统计可用的座位与不可用的座位

    // 根据studio_id删除一个演出厅
    public boolean deleteStudio(int studio_id);
}

package xupt.se.ttms.idao;

import java.util.ArrayList;

import xupt.se.ttms.model.Studio;

public interface IStudio
{
    // 增
    public boolean insert(Studio studio);

    // 删
    public boolean delete(int studio_id);

    // 改
    public boolean update(Studio studio);

    // 按照影厅编号查找
    public Studio searchByStudioId(int studio_id);

    // 按照影厅名字查询
    public ArrayList<Studio> searchByStudioName(String studio_name);

    // 全部提取
    public ArrayList<Studio> searchAll();
}

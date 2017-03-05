package xupt.se.ttms.idao;

import java.util.ArrayList;

import xupt.se.ttms.model.Play;

public interface IPlay
{
    // 增
    public boolean insert(Play play);

    // 删
    public boolean delete(int paly_id);

    // 改
    public boolean update(Play play);

    // 查
    public ArrayList<Play> searchAllFilm();

    // 根据电影名查询电影
    public ArrayList<Play> getFilm(String play_name);

    // 根据电影语种查找电影
    public ArrayList<Play> getPlayByLang(int play_lang_id);

    // 根据电影类型查找电影
    public ArrayList<Play> getPlayByType(int play_type_id);
}

package xupt.se.ttms.idao;

import java.util.ArrayList;

import xupt.se.ttms.model.User;

/**
 * 定义对登陆用户表的增删改查接口
 * @author 张荣
 */
public interface IUser
{
    // 判断用户名、密码是否正确
    public boolean isExist(String userName, String userPass);

    // 增
    public boolean insert(User user);

    // 删
    public boolean delete(String emp_no);

    // 根据用户ID查找
    public User findUserById(String emp_no);

    // // 根据用户名查找
    // public ArrayList<User> findUserByName(User userName);

    // 查找所有的用户
    public ArrayList<User> findUserAll();

    // 改
    public boolean upadte(User user);
}

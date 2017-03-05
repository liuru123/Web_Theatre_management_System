package xupt.se.ttms.model;

/**
 * 仅演示使用，未完全按照数据库设计<br>
 * 实际开发中要重新设计该类
 */
public class User
{
    private String emp_no;
    private String emp_pass;

    private int user_type;
    private String userImage;

    public String getEmp_no()
    {
        return emp_no;
    }

    public void setEmp_no(String emp_no)
    {
        this.emp_no = emp_no;
    }

    public String getEmp_pass()
    {
        return emp_pass;
    }

    public void setEmp_pass(String user_pass)
    {
        this.emp_pass = user_pass;
    }

    public int getUser_type()
    {
        return user_type;
    }

    public void setUser_type(int user_type)
    {
        this.user_type = user_type;
    }

    public String getUserImage()
    {
        return userImage;
    }

    public void setUserImage(String userImage)
    {
        this.userImage = userImage;
    }

}
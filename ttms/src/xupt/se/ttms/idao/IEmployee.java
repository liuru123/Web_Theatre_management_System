package xupt.se.ttms.idao;

import java.util.ArrayList;

import xupt.se.ttms.model.Employee;

/**
 * 定义对员工表的增删改查接口
 * @author 张荣
 */
public interface IEmployee
{
    // 增
    public boolean insert(Employee employee);

    // 删
    public boolean delete(int employeeId);

    // 改
    public boolean update(Employee employee);

    // 查所有用户(一般用于和界面交互)
    public ArrayList<Employee> findEmployeeAll();

    // 根据用户名查(一般用于和界面交互)
    public ArrayList<Employee> findEmployeeByName(String employeeName);

    // 根据用户id查(一般用于数据内部关联操作)
    public Employee findEmployeeById(int employeeId);
}

package com.unexpect.service;


import com.unexpect.pojo.Employee;

import java.util.List;
import java.util.Map;

public interface EmployeeService {

    public Employee queryEmployeeByUserId(Integer userId);

    public Integer countDepartmentEmployee(Map map);

    public List<Employee> queryEmployeeAddressByDepartmentId(Map map);

    public Employee queryUserDepartment(Integer employeeId);

    //查询员工
    public List<Employee> queryEmployeeDepartment();
    //通过Id查询员工
    public Employee queryEmployeeById(Integer employeeId);
    //通过Name查询员工
    public Employee queryEmployeeByName(String employeeName);
    //增加员工
    public int addEmployee(Map<String, Object> employeeMap);

    public Integer addUserEmployee(Map map);
    //修改员工
    public int updateEmployee(Map<String, Object> employeeMap);
    //删除员工
    public void deleteEmployee(Integer[] employeeId);

    public Employee queryEmployeeAllById(Integer integer);

    public int updateEmployeeDepartment(Map map);

    public List<Employee> queryEmployeeByDepartmentId(Integer departmentId);

}

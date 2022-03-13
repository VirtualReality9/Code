package com.unexpect.service;

import com.unexpect.pojo.Department;

import java.util.List;
import java.util.Map;

public interface DepartmentService {

    public List<Department> queryEmployeeByRoleLevel(Map map);

    public List<Department> queryAllDepartmentEmployee(Map map);

    public List<Department> queryDepartmentEmployee(Integer employeeId);

    public List<Department> queryDepartment(Integer departmentId);

    public int addDepartment(Map map);

    public int updateDepartment(Map map);

    public int deleteDepartment(Integer departmentId);

    public Department queryDepartmentById(Integer departmentId);

}

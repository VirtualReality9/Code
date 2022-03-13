package com.unexpect.mapper;

import com.unexpect.pojo.Department;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Mapper
@Repository
public interface DepartmentMapper {

    public List<Department> queryEmployeeByRoleLevel(Map map);

    public List<Department> queryAllDepartmentEmployee(Map map);

    public List<Department> queryDepartmentEmployee(Integer employeeId);

    public List<Department> queryDepartment(Integer departmentId);

    public int addDepartment(Map map);

    public int updateDepartment(Map map);

    public int deleteDepartment(Integer departmentId);

    public Department queryDepartmentById(Integer departmentId);

}

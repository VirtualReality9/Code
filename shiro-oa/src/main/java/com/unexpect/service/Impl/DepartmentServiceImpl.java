package com.unexpect.service.Impl;

import com.unexpect.mapper.DepartmentMapper;
import com.unexpect.pojo.Department;
import com.unexpect.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class DepartmentServiceImpl implements DepartmentService {

    @Autowired
    DepartmentMapper departmentMapper;

    @Override
    public List<Department> queryEmployeeByRoleLevel(Map map) {
        return departmentMapper.queryEmployeeByRoleLevel(map);
    }

    @Override
    public List<Department> queryAllDepartmentEmployee(Map map) {
        return departmentMapper.queryAllDepartmentEmployee(map);
    }

    @Override
    public List<Department> queryDepartmentEmployee(Integer employeeId) {
        return departmentMapper.queryDepartmentEmployee(employeeId);
    }

    @Override
    public List<Department> queryDepartment(Integer departmentId) {
        return departmentMapper.queryDepartment(departmentId);
    }

    @Override
    public int addDepartment(Map map) {
        return departmentMapper.addDepartment(map);
    }

    @Override
    public int updateDepartment(Map map) {
        return departmentMapper.updateDepartment(map);
    }

    @Override
    public int deleteDepartment(Integer departmentId) {
        return departmentMapper.deleteDepartment(departmentId);
    }

    @Override
    public Department queryDepartmentById(Integer departmentId) {
        return departmentMapper.queryDepartmentById(departmentId);
    }

}

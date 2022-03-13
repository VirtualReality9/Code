package com.unexpect.service.Impl;

import com.unexpect.mapper.EmployeeMapper;
import com.unexpect.pojo.Employee;
import com.unexpect.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    EmployeeMapper employeeMapper;


    @Override
    public Employee queryEmployeeByUserId(Integer userId) {
        return employeeMapper.queryEmployeeByUserId(userId);
    }

    @Override
    public Integer countDepartmentEmployee(Map map) {
        return employeeMapper.countDepartmentEmployee(map);
    }

    @Override
    public List<Employee> queryEmployeeAddressByDepartmentId(Map map) {
        return employeeMapper.queryEmployeeAddressByDepartmentId(map);
    }

    @Override
    public Employee queryUserDepartment(Integer employeeId) {
        return employeeMapper.queryUserDepartment(employeeId);
    }

    @Override
    public List<Employee> queryEmployeeDepartment() {
        return employeeMapper.queryEmployeeDepartment();
    }

    @Override
    public Employee queryEmployeeById(Integer employeeId) {
        return employeeMapper.queryEmployeeById(employeeId);
    }

    @Override
    public Employee queryEmployeeByName(String employeeName) {
        return employeeMapper.queryEmployeeByName(employeeName);
    }

    @Override
    public int addEmployee(Map<String, Object> employeeMap) {
        return employeeMapper.addEmployee(employeeMap);
    }

    @Override
    public Integer addUserEmployee(Map map) {
        return employeeMapper.addUserEmployee(map);
    }

    @Override
    public int updateEmployee(Map<String, Object> employeeMap) {
        return employeeMapper.updateEmployee(employeeMap);
    }

    @Override
    public void deleteEmployee(Integer[] employeeId) {
        employeeMapper.deleteEmployee(employeeId);
    }

    @Override
    public Employee queryEmployeeAllById(Integer integer) {
        return employeeMapper.queryEmployeeAllById(integer);
    }

    @Override
    public int updateEmployeeDepartment(Map map) {
        return employeeMapper.updateEmployeeDepartment(map);
    }

    @Override
    public List<Employee> queryEmployeeByDepartmentId(Integer departmentId) {
        return employeeMapper.queryEmployeeByDepartmentId(departmentId);
    }
}

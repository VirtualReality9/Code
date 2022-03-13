package com.unexpect.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Department {

    //部门编号
    private Integer departmentId;
    //部门名字
    private String departmentName;
    //部门状态
    private String departmentStatus;
    //部门经理
    private String departmentManager;
    //部门规模
    private Integer departmentSize;
    //部门电话
    private String departmentPhone;
    //部门人员名单
    private List<Employee> employeeList;

}

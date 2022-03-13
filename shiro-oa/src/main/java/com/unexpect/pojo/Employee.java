package com.unexpect.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Employee {

    //员工编号
    private Integer employeeId;
    //员工名字
    private String employeeName;
    //
    private String employeeStatus;
    //    //员工性别
    private String employeeSex;
    //    //员工年龄
    private int employeeAge;
    //    //员工政治面貌
    private String employeePolitical;
    ////婚姻状态
    private String employeeMarriage;
    ////民族
    private String employeeNation;
    ////学历
    private String employeeEducation;
    ////毕业学校
    private String employeeGraduation;
    //生日
    private String employeeBirth;
    //身份证
    private String employeePid;
    //手机
    private String userPhone;
    //邮箱
    private String employeeEmail;
    //户籍
    private String employeeResidence;
    //居住地
    private String employeeAddress;
    // //启用时间
//    private Date employeeStartTime;
// //离职时间
//    private Date employeeEndTime;
   //职位
   private String employeePosition;
//     上司
//    public String employeeBoss;
//     部门
    private Integer departmentId;
//
//     private Integer departmentName;
    private Department department;

    private List<ToDoList> toDoList;

}

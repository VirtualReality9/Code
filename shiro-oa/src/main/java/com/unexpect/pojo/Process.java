package com.unexpect.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Process {

    private int processId;
    private String processName;
    private String processCreateTime;
    private String processStatus;
    private String processNote;
    private String processTime;
    private String processEmployeeName;
    private Integer processEmployeeId;
    private Integer departmentId;
    private String departmentName;
    private int processOrder;

}

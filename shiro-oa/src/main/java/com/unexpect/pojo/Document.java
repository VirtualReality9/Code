package com.unexpect.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Document {

    private Integer documentId;
    private String documentName;
    private String documentContent;
    private String documentCreateTime;
    private Integer documentStatus;
    private String documentEndTime;
    private String documentUrl;
    private String UUID;
    private Integer documentCollect;
    private Integer documentLabel;
    private Integer employeeId;
    private String employeeName;
    private int departmentId;
    private String isPublic;

    private Process process;

    private List<Process> processList;
}

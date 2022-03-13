package com.unexpect.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeSchedule {

    private Integer employeeId;
    private Integer scheduleId;
    private Integer Status;
    private String scheduleContent;
    private String employeeName;

    private List<ScheduleAttachment> scheduleAttachmentList;
}

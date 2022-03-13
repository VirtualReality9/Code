package com.unexpect.pojo;

import com.alibaba.druid.filter.AutoLoad;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ScheduleAttachment {

    private Integer attachmentId;
    private String attachmentName;
    private String attachmentUrl;
    private Integer employeeId;
    private String UUID;
    private String scheduleId;

}

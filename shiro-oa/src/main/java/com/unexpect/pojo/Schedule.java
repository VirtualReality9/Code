package com.unexpect.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Schedule {
    private int scheduleId;
    private Date scheduleTime;//创建时间
    private String schedulePlace;
    private String scheduleProfile;
    private String scheduleNote;
    private String scheduleTheme;
    private String scheduleLabel;
    private String scheduleCreater;
    private String scheduleLeader;
    private Integer status;
    private Date scheduleStartTime;
    private Date scheduleEndTime;

}

package com.unexpect.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Meeting {

    private Integer meetingId;
    private String meetingName;
    private Integer meetingStatus;
    private String meetingStartEndTime;
    private String meetingContent;
    private String meetingRemark;
    private String meetingNote;
    private Integer meetingRoomId;
    private Integer applicantId;
    private String applicantName;
    private Date applyDate;
    private String applicantDepartment;
    private Integer meetingSlot;

    private MeetingRoom meetingRoom;

}

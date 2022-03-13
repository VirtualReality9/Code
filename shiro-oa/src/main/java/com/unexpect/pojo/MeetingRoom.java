package com.unexpect.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MeetingRoom {

    private Integer meetingRoomId;
    private String meetingRoomName;
    private Integer meetingRoomStatus;
    private Date meetingRoomCreateTime;
    private Date meetingRoomEndTime;
    private Integer computerStatus;
    private Integer projectionStatus;
    private Integer airStatus;
    private Integer maxSize;
    private Integer one;
    private Integer two;
    private Integer three;
    private Integer four;
    private Integer five;

}

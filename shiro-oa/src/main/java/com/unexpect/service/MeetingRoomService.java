package com.unexpect.service;



import com.unexpect.pojo.MeetingRoom;

import java.util.List;
import java.util.Map;

public interface MeetingRoomService {
    //用Map模糊查询会议
    public List<MeetingRoom> selectMeetingRoomByMap(Map<String,Object> map);


    //插入会议室
    public int insertMeetingRoom(Map<String,Object> map);

    //根据meetingRoomId删除会议室，慎用！！！！！！！！！！
    public int deleteMeetingRoom(Integer meetingRoomId[]);

    //更改会议室信息
    public int updateMeetingRoom(Map<String,Object> map);

}

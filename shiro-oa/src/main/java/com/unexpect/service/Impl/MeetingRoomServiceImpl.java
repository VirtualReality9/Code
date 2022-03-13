package com.unexpect.service.Impl;

import com.unexpect.mapper.MeetingRoomMapper;
import com.unexpect.pojo.MeetingRoom;
import com.unexpect.service.MeetingRoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class MeetingRoomServiceImpl implements MeetingRoomService {
    @Autowired
    MeetingRoomMapper meetingRoomMapper;

    @Override
    public List<MeetingRoom> selectMeetingRoomByMap(Map<String, Object> map) {
        return meetingRoomMapper.selectMeetingRoomByMap(map);
    }


    @Override
    public int insertMeetingRoom(Map<String, Object> map) {
        return meetingRoomMapper.insertMeetingRoom(map);
    }

    @Override
    public int deleteMeetingRoom(Integer meetingRoomId[]) {
        return meetingRoomMapper.deleteMeetingRoom(meetingRoomId);
    }

    @Override
    public int updateMeetingRoom(Map<String, Object> map) {
        return meetingRoomMapper.updateMeetingRoom(map);
    }

}

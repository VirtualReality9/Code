package com.unexpect.service.Impl;

import com.unexpect.mapper.MeetingMapper;
import com.unexpect.pojo.Meeting;
import com.unexpect.pojo.MeetingRoom;
import com.unexpect.service.MeetingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class MeetingServiceImpl implements MeetingService {

    @Autowired
    MeetingMapper meetingMapper;

    @Override
    public Integer countEmployeeMeetingList(HashMap hashMap) {
        return meetingMapper.countEmployeeMeetingList(hashMap);
    }

    @Override
    public Integer countHasExaminedMeetingList() {
        return meetingMapper.countHasExaminedMeetingList();
    }

    @Override
    public List<Meeting> queryHasExaminedMeeting(Integer pageSize, Integer pageNum) {
        return meetingMapper.queryHasExaminedMeeting(pageSize,pageNum);
    }

    @Override
    public Integer deleteMeetingEmployee(Integer meetingId) {
        return meetingMapper.deleteMeetingEmployee(meetingId);
    }

    @Override
    public Integer countMeetingList(HashMap hashMap) {
        return meetingMapper.countMeetingList(hashMap);
    }

    @Override
    public List<Meeting> queryMeetingByDate(String Date) {
        return meetingMapper.queryMeetingByDate(Date);
    }

    @Override
    public List<Meeting> examineMeetingList(Integer pageSize,Integer pageNum) {
        return meetingMapper.examineMeetingList(pageSize,pageNum);
    }

    @Override
    public List<Meeting> findAllMeetingById(Integer employeeId,Integer pageSize,Integer pageNum) {
        return meetingMapper.findAllMeetingById(employeeId,pageSize,pageNum);
    }

    @Override
    public MeetingRoom selectMeetingRoomById(int meetingroomid) {
        return meetingMapper.selectMeetingRoomById(meetingroomid);
    }

    @Override
    public int updateMeetingRoom(Map<String, Object> map) {
        return meetingMapper.updateMeetingRoom(map);
    }

    @Override
    public Meeting getMeetingIdByMap(HashMap<String, Object> hashMap) {
        return meetingMapper.getMeetingIdByMap(hashMap);
    }

    @Override
    public int addMeetingEmployee(HashMap<String, Object> hashMap) {
        return meetingMapper.addMeetingEmployee(hashMap);
    }

    @Override
    public int addMeeting(HashMap<String, Object> map) {
        return meetingMapper.addMeeting(map);
    }

    @Override
    public int UpdateMeeting(HashMap<String, Object> hashMap) {
        return meetingMapper.UpdateMeeting(hashMap);
    }

    @Override
    public int deleteMeetingEmployeeByMap(HashMap<String,Object> hashMap) {
        return meetingMapper.deleteMeetingEmployeeByMap(hashMap);
    }

    @Override
    public int deleteMeetingByMeetingId(int meetingId) {
        return meetingMapper.deleteMeetingByMeetingId(meetingId);
    }

    @Override
    public int updateMeetingStatus(Map map) {
        return meetingMapper.updateMeetingStatus(map);
    }
}

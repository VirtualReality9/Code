package com.unexpect.mapper;

import com.unexpect.pojo.Meeting;
import com.unexpect.pojo.MeetingRoom;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
@Mapper
public interface MeetingMapper {

    public Integer countEmployeeMeetingList(HashMap hashMap);

    public Integer countHasExaminedMeetingList();

    public List<Meeting> queryHasExaminedMeeting(Integer pageSize,Integer pageNum);

    public Integer deleteMeetingEmployee(Integer meetingId);

    public Integer countMeetingList(HashMap hashMap);

    //审核会议查看需要审核的会议
    public List<Meeting> examineMeetingList(Integer pageSize,Integer pageNum);

    //根据指定的员工ID查找所有参加的会议,多对多查询
    public List<Meeting> findAllMeetingById(Integer employeeId,Integer pageSize,Integer pageNum);

    //查询某一天的非空闲会议室
    public List<Meeting> queryMeetingByDate(String Date);

    //根据meetingRoomId查询会议室信息
    public MeetingRoom selectMeetingRoomById(int meetingroomid);

    //更改会议室信息
    public int updateMeetingRoom(Map<String,Object> map);

    //根据传入的Map值进行匹配查询，返回meeting
    public Meeting getMeetingIdByMap(HashMap<String,Object> hashMap);

    //参加会议
    public int addMeetingEmployee(HashMap<String,Object> hashMap);

    //创建会议
    public int addMeeting(HashMap<String,Object> map);



    //修改会议（修改会议室或会议室占用时间）（修改会议表中其他信息）
    public int UpdateMeeting(HashMap<String,Object> hashMap);


    //取消会议，会议表删除信息，员工会议表删除信息，注意！！！！！！！！！会议室状态调为可用状态

    public int deleteMeetingEmployeeByMap(HashMap<String,Object> hashMap);
    public int deleteMeetingByMeetingId(int meetingId);


    //会议成功结束，将meetingStatus设置成1。
    public int updateMeetingStatus(Map map);

}

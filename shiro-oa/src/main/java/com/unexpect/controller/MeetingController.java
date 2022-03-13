package com.unexpect.controller;

import com.unexpect.pojo.Meeting;
import com.unexpect.pojo.MeetingRoom;
import com.unexpect.pojo.Meta;
import com.unexpect.service.MeetingRoomService;
import com.unexpect.service.MeetingService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class  MeetingController {

    Meta meta = new Meta();

    @Resource
    MeetingService meetingService;
    @Resource
    MeetingRoomService meetingRoomService;

    private static Logger logger= LogManager.getLogger(MeetingController.class);

    //查询待审会议
    @GetMapping("/met/examineMeeting")
    @ResponseBody
    public HashMap examineMeeting(@RequestParam("meetingStatus")Integer meetingStatus,
                                  @RequestParam(value = "pagesize",required = true) Integer pagesize,
                                  @RequestParam(value = "pagenum",required = true)Integer pagenum){
        logger.info("查询待审会议");
        HashMap hashMap = new HashMap();
        List<Meeting> meetingList=meetingService.examineMeetingList(pagesize,pagenum);
        hashMap.put("meetingStatus",meetingStatus);
        Integer total = meetingService.countMeetingList(hashMap);
        System.out.println("meetingList:"+meetingList);
        hashMap.put("data",meetingList);
        hashMap.put("total",total);
        meta.setStatus(200);
        meta.setMsg("获取待审核会议成功");
        hashMap.put("meta",meta);
        return hashMap;
    }

    //审核会议
    @PostMapping("/met/toexamine")
    @ResponseBody
    public HashMap toexamine(@RequestBody Map map){
        logger.info("审核会议");
        System.out.println("map:"+map);
        if(Integer.parseInt(map.get("meetingStatus").toString()) == 0){
            meetingService.deleteMeetingEmployee(Integer.parseInt(map.get("meetingId").toString()));
        }
        HashMap hashMap = new HashMap();
        meetingService.updateMeetingStatus(map);
        meta.setStatus(200);
        meta.setMsg("待审核会议成功");
        hashMap.put("meta",meta);
        return hashMap;
    }

    //查询已经自己审的会议
    @GetMapping("met/queryHasExaminedMeeting")
    @ResponseBody
    public HashMap queryHasExaminedMeeting(@RequestParam(value = "pagesize") Integer pagesize, @RequestParam(value = "pagenum")Integer pagenum){
        logger.info("查询已审的会议");
        HashMap hashMap = new HashMap();
        List<Meeting> meetingList=meetingService.queryHasExaminedMeeting(pagesize,pagenum);
        Integer total = meetingService.countHasExaminedMeetingList();
        meta.setStatus(200);
        meta.setMsg("待审核会议成功");
        hashMap.put("meta",meta);
        hashMap.put("total",total);
        hashMap.put("data",meetingList);
        return hashMap;
    }

    //根据员工ID查询他加入的所有会议
    @GetMapping("/met/queryMeeting")
    @ResponseBody
    public HashMap queryMeeting(@RequestParam(value = "pagesize") Integer pagesize,
                                           @RequestParam(value = "pagenum")Integer pagenum,
                                           @RequestParam(value = "employeeId",required = false) Integer employeeId){
        logger.info("查询参加的会议");
        System.out.println(employeeId);
        HashMap hashMap = new HashMap();
        hashMap.put("employeeId",employeeId);
        List<Meeting> meetingList=meetingService.findAllMeetingById(employeeId,pagesize,pagenum);
        Integer total = meetingService.countEmployeeMeetingList(hashMap);
        meta.setStatus(200);
        meta.setMsg("待审核会议成功");
        hashMap.put("meta",meta);
        hashMap.put("data",meetingList);
        hashMap.put("total",total);
        return hashMap;
    }

    @PostMapping("/met/orderMeeting")
    @ResponseBody
    public HashMap orderMeeting(@RequestBody HashMap map){
        logger.info("预约会议");
        HashMap hashMap = new HashMap();
        ArrayList<Integer> employeeId = (ArrayList<Integer>) map.get("employeeIdList");
        System.out.println("map:"+map);
        HashMap hashMap2 = new HashMap();
        meetingService.addMeeting(map);
        hashMap2.put("meetingId",map.get("meetingId"));
        for(Integer Id:employeeId){
            hashMap2.put("employeeId",Id);
            meetingService.addMeetingEmployee(hashMap2);
        }
        meta.setStatus(200);
        meta.setMsg("添加会议成功");
        hashMap.put("meta",meta);
        return hashMap;
    }

    @GetMapping("/met/queryMeetingByDate")
    @ResponseBody
    public HashMap queryMeetingByDate(@RequestParam("applyDate")String applyDate){
        logger.info("通过日期查询当天会议");
        HashMap hashMap = new HashMap();
        List<Meeting> meetingList = meetingService.queryMeetingByDate(applyDate);
        hashMap.put("data",meetingList);
        System.out.println(meetingList);
        meta.setStatus(200);
        meta.setMsg("查询成功");
        hashMap.put("meta",meta);
        return hashMap;
    }

    @GetMapping("/met/meetingOver")
    @ResponseBody
    public String meetingOver(){
        //会议结束后，会议表中的状态设置成1，会议室表状态设置成1
        logger.info("会议结束");
        int meetingId=1;
        HashMap<String,Object> hashMap = new HashMap<>();
        hashMap.put("meetingId",meetingId);
        Meeting meeting = meetingService.getMeetingIdByMap(hashMap);
        Map<String,Object> map = new HashMap<>();
        map.put("meetingRoomId",meeting.getMeetingRoomId());
        map.put("meetingRoomStatus",1);
        meetingService.updateMeetingRoom(map);
        meetingService.updateMeetingStatus(map);
        return "The meeting is over!";
    }
}

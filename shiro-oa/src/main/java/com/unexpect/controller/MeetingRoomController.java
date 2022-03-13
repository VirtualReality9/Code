package com.unexpect.controller;


import com.unexpect.pojo.Meeting;
import com.unexpect.pojo.MeetingRoom;
import com.unexpect.pojo.Meta;
import com.unexpect.service.MeetingRoomService;
import org.apache.ibatis.binding.MapperMethod;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.sql.Array;
import java.util.*;

@RestController
public class MeetingRoomController {

    Meta meta = new Meta();
    @Autowired
    MeetingRoomService meetingRoomService;

    private static Logger logger= LogManager.getLogger(MeetingRoomController.class);

    @PostMapping("/metr/addMeetingRoom")
    @ResponseBody
    public HashMap addMeetingRoom(@RequestBody Map map){
        logger.info("添加会议室");
        HashMap hashMap = new HashMap();
        meetingRoomService.insertMeetingRoom(map);
        meta.setMsg("添加成功");
        meta.setStatus(200);
        hashMap.put("meta",meta);
        return hashMap;
    }

    @PostMapping("/metr/queryMeetingRoom")
    @ResponseBody
    public HashMap selectMeetingRoom(@RequestBody Map map){
        logger.info("查询会议室");
        HashMap hashMap = new HashMap();
        List<MeetingRoom> meetingRoomList = meetingRoomService.selectMeetingRoomByMap(map);
        meta.setMsg("查询成功");
        meta.setStatus(200);
        hashMap.put("data",meetingRoomList);
        hashMap.put("meta",meta);
        return hashMap;
    }

    @GetMapping("/metr/queryMeetingRoomById")
    @ResponseBody
    public HashMap queryMeetingRoomById(@RequestParam("id")Integer meetingRoomId){
        logger.info("根据会议室Id查询会议室");
        HashMap hashMap = new HashMap();
        HashMap map = new HashMap();
        map.put("meetingRoomId",meetingRoomId);
        List<MeetingRoom> meetingRoomList = meetingRoomService.selectMeetingRoomByMap(map);
        meta.setMsg("查询成功");
        meta.setStatus(200);
        hashMap.put("data",meetingRoomList.get(0));
        hashMap.put("meta",meta);
        return hashMap;
    }

    @PostMapping("/metr/updateMeetingRoom")
    public HashMap updateMeetingRoom(@RequestBody Map map){
        logger.info("修改会议室");
        System.out.println("map:"+map);
        HashMap hashMap = new HashMap();
        meetingRoomService.updateMeetingRoom(map);
        meta.setMsg("修改成功");
        meta.setStatus(200);
        hashMap.put("meta",meta);
        return hashMap;
    }

    @PostMapping("/metr/deleteMeetingRoom")
    public HashMap deleteMeetingRoom(@RequestBody Integer[] MeetingId){
        logger.info("删除会议室");
        // System.out.println("MeetingId"+MeetingId);
        HashMap hashMap = new HashMap();
        meetingRoomService.deleteMeetingRoom(MeetingId);
        meta.setMsg("删除成功");
        meta.setStatus(200);
        hashMap.put("meta",meta);
        return hashMap;
    }
}

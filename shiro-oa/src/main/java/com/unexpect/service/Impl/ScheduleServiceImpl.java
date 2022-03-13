package com.unexpect.service.Impl;

import com.unexpect.Result.Result;
import com.unexpect.Result.ResultGenerator;
import com.unexpect.mapper.ScheduleMapper;
import com.unexpect.pojo.EmployeeSchedule;
import com.unexpect.pojo.Schedule;
import com.unexpect.service.ScheduleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ScheduleServiceImpl implements ScheduleService {
    @Autowired
    ScheduleMapper scheduleMapper;

    @Override
    public List<EmployeeSchedule> queryEmployeeScheduleDetail(Map map) {
        return scheduleMapper.queryEmployeeScheduleDetail(map);
    }

    @Override
    public List<Schedule> queryPublishScheduleList(Map map) {
        return scheduleMapper.queryPublishScheduleList(map);
    }

    @Override
    public List<Schedule> queryScheduleList(Map map) {
        return scheduleMapper.queryScheduleList(map);
    }

    @Override
    public Result getScheduleByEmployeeId(Integer employeeId, Integer pageSize, Integer pageNum) {
        Integer total=scheduleMapper.countAllSchedule(employeeId);
        Map<String,Object> map=new HashMap<String,Object>();
        map.put("pageSize",pageSize);
        map.put("pageNum",pageNum);
        map.put("employeeId",employeeId);
        List<Schedule> schedules=scheduleMapper.getScheduleByEmployeeId(map);
        Map<String,Object> map2=new HashMap<String,Object>();
        map2.put("total",total);
        map2.put("schedules",schedules);
        Result result= ResultGenerator.genSuccessResult(map2);
        return result;
    }

    @Override
    public Result delSchedule(Integer scheduleId) {
        scheduleMapper.delSchedule(scheduleId);
        scheduleMapper.delScheduleEmployee(scheduleId);
        Result result = new Result(200,"删除日程成功！",null,null);
        return result;
    }

//    @Override
//    public Result delScheduleEmployee(Integer[] scheduleId) {
//        scheduleMapper.delScheduleEmployee(scheduleId);
//        Result result=new Result(200,"删除日程成功！",null);
//        return result;
//    }


    @Override
    public void addSchedule(HashMap<String, Object> hashMap) {
        scheduleMapper.addSchedule(hashMap);
    }

    @Override
    public void addScheduleEmployee(HashMap<String, Object> hashMap) {
        scheduleMapper.addScheduleEmployee(hashMap);
    }

    @Override
    public Integer updScheduleEmployee(Map map) {
        return scheduleMapper.updScheduleEmployee(map);
    }

    @Override
    public Integer countQuerySchedule(Map map) {
        return scheduleMapper.countQuerySchedule(map);
    }

    @Override
    public Integer countPublicSchedule(Map map) {
        return scheduleMapper.countPublicSchedule(map);
    }

    @Override
    public Result updSchedule(Map<String, Object> map) {
        scheduleMapper.updSchedule(map);
        Result result=new Result(200,"修改日程成功！",null,null);
        return result;
    }

}

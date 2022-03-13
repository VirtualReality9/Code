package com.unexpect.service;

import com.unexpect.Result.Result;
import com.unexpect.pojo.EmployeeSchedule;
import com.unexpect.pojo.Schedule;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


public interface ScheduleService {

    public List<EmployeeSchedule> queryEmployeeScheduleDetail(Map map);

    public List<Schedule> queryPublishScheduleList(Map map);

    public List<Schedule> queryScheduleList(Map map);

    public Result getScheduleByEmployeeId(Integer employeeId, Integer pageSize, Integer pageNum);

    public Result delSchedule(Integer scheduleId);
//    public Result delScheduleEmployee(Integer[] ScheduleId);


    public void addSchedule(HashMap<String, Object> hashMap);

    public void addScheduleEmployee(HashMap<String, Object> hashMap);

    public Integer updScheduleEmployee(Map map);

    public Integer countQuerySchedule(Map map);

    public Integer countPublicSchedule(Map map);

    public Result updSchedule(Map<String, Object> map);


}

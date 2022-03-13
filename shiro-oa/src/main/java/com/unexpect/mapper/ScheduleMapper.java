package com.unexpect.mapper;

import com.unexpect.pojo.EmployeeSchedule;
import com.unexpect.pojo.Schedule;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
@Mapper
public interface ScheduleMapper {

    public List<EmployeeSchedule> queryEmployeeScheduleDetail(Map map);

    public List<Schedule> queryPublishScheduleList(Map map);

    public List<Schedule> queryScheduleList(Map map);

    public List<Schedule> getScheduleByEmployeeId(Map<String, Object> map);//只能查看自己的日程

    public void delSchedule(Integer scheduleId);

    public void addSchedule(HashMap<String,Object> hashMap);

    public void addScheduleEmployee(HashMap<String, Object> hashMap);

    public Integer updScheduleEmployee(Map map);

    public void updSchedule(Map<String, Object> map);

    public Integer countAllSchedule(Integer employeeId);

    public Integer countQuerySchedule(Map map);

    public Integer countPublicSchedule(Map map);

    public void delScheduleEmployee(Integer scheduleId);
}

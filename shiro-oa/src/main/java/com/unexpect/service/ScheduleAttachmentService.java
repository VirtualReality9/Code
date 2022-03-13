package com.unexpect.service;

import com.unexpect.pojo.ScheduleAttachment;

import java.util.List;
import java.util.Map;

public interface ScheduleAttachmentService {

    public List<ScheduleAttachment> queryAttachment(Map map);

    public Integer addAttachment(Map map);

    public Integer updateAttachment(Map map);

    public Integer deleteAttachment(Map map);

}

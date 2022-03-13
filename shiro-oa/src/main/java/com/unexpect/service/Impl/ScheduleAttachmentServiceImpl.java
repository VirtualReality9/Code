package com.unexpect.service.Impl;

import com.unexpect.mapper.ScheduleAttachmentMapper;
import com.unexpect.pojo.ScheduleAttachment;
import com.unexpect.service.ScheduleAttachmentService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@Service
public class ScheduleAttachmentServiceImpl implements ScheduleAttachmentService {

    @Resource
    ScheduleAttachmentMapper scheduleAttachmentMapper;

    @Override
    public List<ScheduleAttachment> queryAttachment(Map map) {
        return scheduleAttachmentMapper.queryAttachment(map);
    }

    @Override
    public Integer addAttachment(Map map) {
        return scheduleAttachmentMapper.addAttachment(map);
    }

    @Override
    public Integer updateAttachment(Map map) {
        return scheduleAttachmentMapper.updateAttachment(map);
    }

    @Override
    public Integer deleteAttachment(Map map) {
        return scheduleAttachmentMapper.deleteAttachment(map);
    }
}

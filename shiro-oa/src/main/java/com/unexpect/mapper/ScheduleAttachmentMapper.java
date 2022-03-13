package com.unexpect.mapper;

import com.unexpect.pojo.Attachment;
import com.unexpect.pojo.ScheduleAttachment;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
@Mapper
public interface ScheduleAttachmentMapper {

    public List<ScheduleAttachment> queryAttachment(Map map);

    public Integer addAttachment(Map map);

    public Integer updateAttachment(Map map);

    public Integer deleteAttachment(Map map);

}

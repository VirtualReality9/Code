package com.unexpect.mapper;

import com.unexpect.pojo.Attachment;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Mapper
@Repository
public interface AttachmentMapper {

    public List<Attachment> queryAttachment(Map map);

    public Integer addAttachment(Map map);

    public Integer updateAttachment(Map map);

    public Integer deleteAttachment(Map map);

}

package com.unexpect.service;

import com.unexpect.pojo.Attachment;

import java.util.List;
import java.util.Map;

public interface AttachmentService {

    public List<Attachment> queryAttachment(Map map);

    public Integer addAttachment(Map map);

    public Integer updateAttachment(Map map);

    public Integer deleteAttachment(Map map);

}

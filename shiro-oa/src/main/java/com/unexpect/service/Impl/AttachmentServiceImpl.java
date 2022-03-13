package com.unexpect.service.Impl;

import com.unexpect.mapper.AttachmentMapper;
import com.unexpect.pojo.Attachment;
import com.unexpect.service.AttachmentService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@Service
public class AttachmentServiceImpl implements AttachmentService {

    @Resource
    AttachmentMapper attachmentMapper;

    @Override
    public List<Attachment> queryAttachment(Map map) {
        return attachmentMapper.queryAttachment(map);
    }

    @Override
    public Integer addAttachment(Map map) {
        return attachmentMapper.addAttachment(map);
    }

    @Override
    public Integer updateAttachment(Map map) {
        return attachmentMapper.updateAttachment(map);
    }

    @Override
    public Integer deleteAttachment(Map map) {
        return attachmentMapper.deleteAttachment(map);
    }

}

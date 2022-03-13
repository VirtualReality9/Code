package com.unexpect.service.Impl;

import com.unexpect.mapper.DocumentProcessMapper;
import com.unexpect.pojo.Document;
import com.unexpect.service.DocumentProcessService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class DocumentProcessServiceImpl implements DocumentProcessService {

    @Autowired
    DocumentProcessMapper documentProcessMapper;

    @Override
    public Document queryDocumentProcess(Integer documentId) {
        return documentProcessMapper.queryDocumentProcess(documentId);
    }

    @Override
    public Integer addEmployeeProcess(Map map) {
        return documentProcessMapper.addEmployeeProcess(map);
    }

    @Override
    public Integer updateEmployeeProcess(Map map) {
        return documentProcessMapper.updateEmployeeProcess(map);
    }

    @Override
    public Integer deleteEmployeeProcess(Integer processId) {
        return documentProcessMapper.deleteEmployeeProcess(processId);
    }
}

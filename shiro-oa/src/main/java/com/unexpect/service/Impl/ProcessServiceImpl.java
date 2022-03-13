package com.unexpect.service.Impl;

import com.unexpect.mapper.ProcessMapper;
import com.unexpect.pojo.Process;
import com.unexpect.service.ProcessService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class ProcessServiceImpl implements ProcessService {

    @Autowired
    ProcessMapper processMapper;

    @Override
    public List<Process> queryProcessList(Integer documentId) {
        return processMapper.queryProcessList(documentId);
    }

    @Override
    public int addProcess(Map map) {
        return processMapper.addProcess(map);
    }

    @Override
    public int updateProcess(Map map) {
        return processMapper.updateProcess(map);
    }

    @Override
    public int deleteProcess(Integer departmentId) {
        return processMapper.deleteProcess(departmentId);
    }

    @Override
    public Process queryProcessById(Integer processId) {
        return processMapper.queryProcessById(processId);
    }
}

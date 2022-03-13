package com.unexpect.service;

import com.unexpect.pojo.Process;

import java.util.List;
import java.util.Map;

public interface ProcessService {

    public List<Process> queryProcessList(Integer documentId);

    public int addProcess(Map map);

    public int updateProcess(Map map);

    public int deleteProcess(Integer departmentId);

    public Process queryProcessById(Integer processId);

}

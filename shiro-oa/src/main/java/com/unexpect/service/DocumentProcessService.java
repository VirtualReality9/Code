package com.unexpect.service;

import com.unexpect.pojo.Document;

import java.util.Map;

public interface DocumentProcessService {

    public Document queryDocumentProcess(Integer documentId);

    public Integer addEmployeeProcess(Map map);

    public Integer updateEmployeeProcess(Map map);

    public Integer deleteEmployeeProcess(Integer processId);

}

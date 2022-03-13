package com.unexpect.service.Impl;


import com.unexpect.mapper.DocumentMapper;
import com.unexpect.pojo.Document;
import com.unexpect.service.DocumentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class DocumentServiceImpl implements DocumentService {

    @Autowired
    DocumentMapper documentMapper;

    @Override
    public List<Document> getDocuments(Map<String, Object> map) {
        return documentMapper.getDocuments(map);
    }

    @Override
    public Document getDocumentById(int id) {
        return documentMapper.getDocumentById(id);
    }

    @Override
    public void delDocuments(Integer ID[]) {
        documentMapper.delDocuments(ID);
    }

    @Override
    public Integer addDocument(Map<String, Object> map) {
        return documentMapper.addDocument(map);
    }

    @Override
    public void updDocumentById(Map<String, Object> map) {
        documentMapper.updDocumentById(map);
    }

    @Override
    public List<Document> getEmployeeDocuments(Integer employeeId) {
        return documentMapper.getEmployeeDocuments(employeeId);
    }
}

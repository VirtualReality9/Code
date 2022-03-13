package com.unexpect.service;


import com.unexpect.pojo.Document;

import java.util.List;
import java.util.Map;

public interface DocumentService {

    public List<Document> getDocuments(Map<String, Object> map);

    public Document getDocumentById(int id);

    public void delDocuments(Integer ID[]);

    public Integer addDocument(Map<String, Object> map);

    public void updDocumentById(Map<String, Object> map);

    public List<Document> getEmployeeDocuments(Integer employeeId);
}

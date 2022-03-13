package com.unexpect.mapper;


import com.unexpect.pojo.Document;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
@Mapper
public interface DocumentMapper {

    public List<Document> getDocuments(Map<String, Object> map);

    public Document getDocumentById(int id);


    public Integer delDocuments(Integer[] ID);

    public Integer addDocument(Map<String, Object> map);

    public Integer updDocumentById(Map<String, Object> map);

    public List<Document> getEmployeeDocuments(Integer employeeId);
}


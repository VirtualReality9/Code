package com.unexpect.mapper;


import com.unexpect.pojo.Document;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.Map;

@Repository
@Mapper
public interface DocumentProcessMapper {

    public Document queryDocumentProcess(Integer documentId);

    public Integer addEmployeeProcess(Map map);

    public Integer updateEmployeeProcess(Map map);

    public Integer deleteEmployeeProcess(Integer processId);

}

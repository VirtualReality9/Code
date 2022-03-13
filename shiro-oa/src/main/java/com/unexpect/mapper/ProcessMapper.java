package com.unexpect.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;
import com.unexpect.pojo.Process;
import java.util.List;
import java.util.Map;

@Repository
@Mapper
public interface ProcessMapper {

    public List<Process> queryProcessList(Integer documentId);

    public int addProcess(Map map);

    public int updateProcess(Map map);

    public int deleteProcess(Integer departmentId);

    public Process queryProcessById(Integer processId);

}

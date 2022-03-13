package com.unexpect.mapper;

import com.unexpect.Result.Result;
import com.unexpect.pojo.Log;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Mapper
@Repository
public interface LogMapper {
    int countAllLog();
    void deleteAllLog();
    List<Log> findLogByMessage(Map<String,Object> map);
    int countFindLogsByMessage(String message);
    List<Log> findAllLog(Map<String, Object> map);
}

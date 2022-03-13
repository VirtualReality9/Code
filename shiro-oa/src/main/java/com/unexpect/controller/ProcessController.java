package com.unexpect.controller;


import com.unexpect.mapper.DocumentProcessMapper;
import com.unexpect.pojo.Meta;
import com.unexpect.service.ProcessService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

@Controller
public class ProcessController {
    Meta meta = new Meta();
    @Resource
    ProcessService processService;
    @Resource
    DocumentProcessMapper documentProcessMapper;

    private static Logger logger= LogManager.getLogger(ProcessController.class);

    @GetMapping("/pro/queryProcess")
    @ResponseBody
    public HashMap queryProcess(@RequestParam(name = "documentId",required = false)Integer documentId){
        // Document document = documentProcessMapper.queryDocumentProcess(documentId);
        logger.info("查询公文流转进度");
        HashMap hashMap = new HashMap();
        hashMap.put("data",processService.queryProcessList(documentId));
        meta.setMsg("查询流程成功");
        meta.setStatus(200);
        hashMap.put("meta",meta);
        return hashMap;
    }

    @PostMapping("/pro/updateProcess")
    @ResponseBody
    public HashMap updateProcess(@RequestBody Map map){
        logger.info("修改公文流程");
        processService.updateProcess(map);
        // documentProcessMapper.updateEmployeeProcess(map);
        HashMap hashMap = new HashMap();
        meta.setMsg("修改流程成功");
        meta.setStatus(200);
        hashMap.put("meta",meta);
        return hashMap;
    }

    @DeleteMapping("/pro/deleteProcess")
    @ResponseBody
    public HashMap deleteProcess(@RequestParam("processId")Integer processId){
        logger.info("删除公文流程");
        processService.deleteProcess(processId);
        documentProcessMapper.deleteEmployeeProcess(processId);
        // documentProcessMapper.updateEmployeeProcess(map);
        HashMap hashMap = new HashMap();
        meta.setMsg("删除流程成功");
        meta.setStatus(200);
        hashMap.put("meta",meta);
        return hashMap;
    }

}

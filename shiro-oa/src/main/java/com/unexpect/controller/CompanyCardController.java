package com.unexpect.controller;

import com.unexpect.pojo.CompanyCard;
import com.unexpect.pojo.Meta;
import com.unexpect.service.CompanyCardService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.*;

@Controller
public class CompanyCardController {

    @Resource
    CompanyCardService companyCardService;

    private static Logger logger= LogManager.getLogger(CompanyCardController.class);

    Meta meta = new Meta();

    @GetMapping("/ccard/queryCCard")
    @ResponseBody
    public HashMap queryCCard(){
        logger.info("查询名片夹");
        HashMap hashMap= new HashMap();
        try{
            List<CompanyCard> departments = companyCardService.queryCompanyCard();
            hashMap.put("data",departments);
            meta.setMsg("查询成功");
            meta.setStatus(200);
            hashMap.put("meta",meta);
        }catch (Exception e){
            logger.error("查询异常");
            meta.setMsg("查询失败");
            meta.setStatus(500);
            hashMap.put("meta",meta);
        }
        return hashMap;
    }
    @PostMapping("/ccard/upload")
    @ResponseBody
    public HashMap addCCard(@RequestBody Map map){
        logger.info("查询名片夹图片");
        System.out.println("proofImage"+map.get("proofImage").toString());
        HashMap hashMap= new HashMap();
        try {
            companyCardService.addCompanyCard(map.get("proofImage").toString());
            meta.setMsg("添加成功");
            meta.setStatus(200);
            hashMap.put("meta",meta);
        }catch (Exception e){
            logger.error("添加异常");
            meta.setMsg("添加失败");
            meta.setStatus(500);
            hashMap.put("meta",meta);
        }

        return hashMap;
    }
    @DeleteMapping("/ccard/deleteCCard")
    @ResponseBody
    public HashMap queryDepartmentEmployee(@RequestParam("id") Integer ccId){
        logger.info("删除名片夹图片");
        HashMap hashMap= new HashMap();
        try {
            companyCardService.deleteCompanyCard(ccId);
            meta.setMsg("删除成功");
            meta.setStatus(200);
            hashMap.put("meta", meta);
        }catch (Exception e){
            logger.error("删除异常");
            meta.setMsg("删除失败");
            meta.setStatus(500);
            hashMap.put("meta", meta);
            return hashMap;
        }
        return hashMap;
    }

}

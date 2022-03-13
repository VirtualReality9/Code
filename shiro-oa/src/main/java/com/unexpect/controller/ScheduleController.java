package com.unexpect.controller;

import com.unexpect.Result.Result;
import com.unexpect.Result.ResultGenerator;
import com.unexpect.pojo.*;
import com.unexpect.service.ScheduleAttachmentService;
import com.unexpect.service.ScheduleService;
import com.unexpect.utils.HtmlToPdfUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.*;

@RestController
public class ScheduleController {
    @Resource
    ScheduleService scheduleService;
    @Resource
    ScheduleAttachmentService scheduleAttachmentService;
    @Resource
    HtmlToPdfUtils htmlToPdfUtils;

    private static Logger logger= LogManager.getLogger(ToDoListController.class);

    @RequestMapping("/schedule/queryEmployeeScheduleList")
    //@RequestParam("employeeId")
    public Result queryEmployeeScheduleList(@RequestBody Map map){
        //return toDoListService.getToDoListByEmployeeId(2);
        logger.info("查询所有员工工作情况");
        HashMap hashMap = new HashMap();
        try {
            List<EmployeeSchedule> employeeSchedules = scheduleService.queryEmployeeScheduleDetail(map);
            hashMap.put("employeeSchedules",employeeSchedules);
        } catch (Exception e){
            logger.error("查询异常");
            return Result.fail("查询失败");
        }
        return Result.succ("查询发布的事务成功",hashMap);
    }

    @RequestMapping("/schedule/queryEmployeeScheduleDetail")
    //@RequestParam("employeeId")
    public Result queryEmployeeScheduleDetail(@RequestBody Map map){
        //return toDoListService.getToDoListByEmployeeId(2);
        logger.info("查询员工详细工作情况");
        HashMap hashMap = new HashMap();
        try {
            System.out.println("map:"+map);
            List<ScheduleAttachment> scheduleAttachment = scheduleAttachmentService.queryAttachment(map);
            return Result.succ(scheduleAttachment);
        } catch (Exception e){
            logger.error("查询异常");
            return Result.fail("查询失败");
        }
    }

    @RequestMapping("/schedule/query")
    //@RequestParam("employeeId")
    public Result getToDoListByEmployeeId(Integer employeeId,Integer pageSize, Integer pageNum){
        //return toDoListService.getToDoListByEmployeeId(2);
        logger.info("查询待办。");
        System.out.println("employeeId"+employeeId);
        System.out.println("pageSize"+pageSize);
        System.out.println("pageNum"+pageNum);
        System.out.println(scheduleService.getScheduleByEmployeeId(employeeId,pageSize,pageNum));
        System.out.println("---------------------第二个employeeId="+employeeId+"pageSize="+pageSize);
        return scheduleService.getScheduleByEmployeeId(employeeId,pageSize,pageNum);
    }

    @RequestMapping("/schedule/querySchedule")
    //@RequestParam("employeeId")
    public Result queryToDoList(@RequestBody Map map){
        //return toDoListService.getToDoListByEmployeeId(2);
        List<Schedule> scheduleList = scheduleService.queryScheduleList(map);
        Integer total = scheduleService.countQuerySchedule(map);
        HashMap hashMap = new HashMap();
        hashMap.put("scheduleList",scheduleList);
        hashMap.put("total",total);
        return Result.succ("比较日期成功",hashMap);
    }

    @RequestMapping("/schedule/queryPublishSchedule")
    //@RequestParam("employeeId")
    public Result queryPublishSchedule(@RequestBody Map map){
        //return toDoListService.getToDoListByEmployeeId(2);
        List<Schedule> scheduleList = scheduleService.queryPublishScheduleList(map);
       Integer total = scheduleService.countPublicSchedule(map);
        HashMap hashMap = new HashMap();
        hashMap.put("scheduleList",scheduleList);
        hashMap.put("total",total);
        return Result.succ("查询发布的事务成功",hashMap);
    }

    @RequestMapping("/schedule/delete")
    //@RequestParam("todoId")Integer[] todoId
    public Result delete(@RequestParam("scheduleId")Integer scheduleId){
//        Integer[] todoId = new Integer[10];
//        todoId[0] = 1;
        System.out.println(scheduleId);
        logger.info("删除日程。");
        return scheduleService.delSchedule(scheduleId);
    }
//

    //
    @RequestMapping("/schedule/add")
    @ResponseBody
    public Result add(@RequestBody HashMap map){
        logger.info("添加日程。");
//        System.out.println("得到的="+map.get("todoStartTime"));
//        System.out.println("得到的="+map.get("todoContent"));
        ArrayList<Integer> employeeId = (ArrayList<Integer>) map.get("employeeIdList");
        ArrayList<String> employeeName = (ArrayList<String>) map.get("employeeNameList");
        System.out.println("map"+map);
        HashMap hashMap=new HashMap();
        scheduleService.addSchedule(map);
        System.out.println(map.get("scheduleId"));
        hashMap.put("scheduleId",map.get("scheduleId"));
        System.out.println("hashMap1"+hashMap);
        System.out.println("employeeId"+employeeId);
        hashMap.put("status",map.get("status"));
        for (int i = 0;i<employeeId.size();i++){
            hashMap.put("employeeId",employeeId.get(i));
            hashMap.put("employeeName",employeeName.get(i));
            scheduleService.addScheduleEmployee(hashMap);
        }
        logger.info("添加日程。");
        Result result= ResultGenerator.genSuccessResult();
        return result;
    }
//

    @RequestMapping("/schedule/update")
    //@RequestParam("map") Map map
    public  Result update(@RequestBody Map<String,Object> map){
//        Map<String, Object> map = new HashMap<String, Object>();
//        map.put("todoId",8);
//        map.put("todoNote","紧急");
        logger.info("更改日程。");
        return scheduleService.updSchedule(map);
    }


    //日程工作提交
    @RequestMapping("/schedule/updateEmployeeSchedule")
    //@RequestParam("map") Map map
    public  Result employeeUpdateSchedule(@RequestBody Map map){
//        Map<String, Object> map = new HashMap<String, Object>();
//        map.put("todoId",8);
//        map.put("todoNote","紧急");
        scheduleService.updScheduleEmployee(map);
        logger.info("员工提交工作。");
        return Result.succ("提交员工工作成功");
    }

    @PostMapping("/scheduleAttachment/upload")
    @ResponseBody
    public Result fileUploads(HttpServletRequest request, @RequestParam("file") MultipartFile[] files, @RequestParam("scheduleId") Integer scheduleId,@RequestParam("employeeId") Integer employeeId) throws IOException {

        System.out.println("scheduleId:"+scheduleId);
        System.out.println("employeeId:"+employeeId);
        File tmpFile = new File(htmlToPdfUtils.getScheduleAttachmentAbsolutePath());
        System.out.println("tmpFile:"+tmpFile);
        //判断是否存在该文件夹，若不存在则创建文件夹
        if(!tmpFile.exists()){
            tmpFile.mkdirs();
        }

        for(MultipartFile file:files){
            HashMap hashMap = new HashMap();
            //生成UUID
            UUID uuid = UUID.randomUUID();
            // 获取上传的文件名称
            String fileName = file.getOriginalFilename();
            // 得到文件保存的位置以及新文件名
            File dest = new File(htmlToPdfUtils.getScheduleAttachmentAbsolutePath() + uuid +fileName);
            try {
                // 上传的文件被保存了
                file.transferTo(dest);
                // 打印日志
                logger.info("上传成功，当前上传的文件保存在 {}",htmlToPdfUtils.getScheduleAttachmentAbsolutePath() + uuid + fileName);
                // 自定义返回的统一的 JSON 格式的数据，可以直接返回这个字符串也是可以的。
                hashMap.put("scheduleId",scheduleId);
                hashMap.put("employeeId",employeeId);
                hashMap.put("UUID",uuid.toString());
                System.out.println("UUID:"+uuid.toString());
                hashMap.put("attachmentName",fileName);
                System.out.println("fileName:"+fileName);
                hashMap.put("attachmentUrl",htmlToPdfUtils.getScheduleAttachmentAbsolutePath());
                scheduleAttachmentService.addAttachment(hashMap);
                Result result = Result.succ("上传附件成功",hashMap.get("attachmentId"));
                System.out.println("result:"+result);
                return result;
            } catch (IOException e) {
                logger.error(e.toString());
            }
        }
        // 待完成 —— 文件类型校验工作
        return Result.fail("上传附件异常！！！");

        // 下载工作附件

    }

    @PostMapping(value = "/scheduleAttachment/download")
    public ResponseEntity<byte[]> download(HttpServletResponse response, @RequestParam(name = "url",required = false) String url) throws Exception{

        //预下载文件路径******
        String preDownload_path = url;
        System.out.println(preDownload_path);
        //C:/Users/Administrator/Desktop/Learn/pdf/temp8.pdf
        //C:/Users/Administrator/Desktop/Learn/查询语句.docx
        int index = preDownload_path.lastIndexOf("/");
        //下载后的文件名
        String downloadName = preDownload_path.substring(index+1);
        System.out.println(index);
        System.out.println(downloadName);

        response.setCharacterEncoding("utf-8");
        response.setContentType("application/msword");
        response.setHeader("Content-Disposition", "attachment;fileName="+java.net.URLEncoder.encode(downloadName,"UTF-8"));
        System.out.println(downloadName);

        InputStream is = null;
        OutputStream os = null;
        try {
            is = new FileInputStream(new File(preDownload_path));
            os = response.getOutputStream();
            byte[] b = new byte[2048];
            int length;
            while ((length = is.read(b)) > 0) {
                os.write(b, 0, length);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            os.close();
            is.close();
        }
        return null;
    }
}

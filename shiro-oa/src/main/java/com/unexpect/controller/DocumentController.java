package com.unexpect.controller;

import com.unexpect.Result.Result;
import com.unexpect.mapper.AttachmentMapper;
import com.unexpect.pojo.*;
import com.unexpect.pojo.Process;
import com.unexpect.service.*;
import com.unexpect.utils.FileUtils;
import com.unexpect.utils.HtmlToPdfUtils;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.*;

@Controller
public class DocumentController {

    Meta meta = new Meta();
    @Resource
    DocumentService documentService;
    @Resource
    DocumentProcessService documentProcessService;
    @Resource
    ProcessService processService;
    @Resource
    DepartmentService departmentService;
    @Resource
    EmployeeService employeeService;
    @Resource
    HtmlToPdfUtils htmlToPdfUtils;
    @Resource
    AttachmentService attachmentService;

    SimpleDateFormat sdf = new SimpleDateFormat("/yyyy/MM/dd/");

    private Logger logger = LogManager.getLogger(DocumentController.class);

    @PostMapping("/attachment/upload")
    @ResponseBody
    public Result fileUploads(HttpServletRequest request, @RequestParam("file") MultipartFile[] files,@RequestParam("documentId") Integer documentId) throws IOException {

        logger.info("上传公文附件");
        System.out.println("documentId:"+documentId);
        // 得到格式化后的日期
        String format = sdf.format(new Date());
        File tmpFile = new File(htmlToPdfUtils.getAttachmentAbsolutePath());
        System.out.println("tmpFile:"+tmpFile);
        //判断是否存在该文件夹，若不存在则创建文件夹
        if(!tmpFile.exists()){
            System.out.println("创建文件夹===");
            tmpFile.mkdirs();
        }

        for(MultipartFile file:files){
            HashMap hashMap = new HashMap();
            //生成UUID
            UUID uuid = UUID.randomUUID();
            // 获取上传的文件名称
            String fileName = file.getOriginalFilename();
            // 时间 和 日期拼接
            String newFileName = format + "_" + fileName;
            // 得到文件保存的位置以及新文件名
            File dest = new File(htmlToPdfUtils.getAttachmentAbsolutePath() + uuid +fileName);
            try {
                // 上传的文件被保存了
                file.transferTo(dest);
                // 打印日志
                logger.info("上传成功，当前上传的文件保存在 {}",htmlToPdfUtils.getAttachmentAbsolutePath() + uuid + fileName);
                // 自定义返回的统一的 JSON 格式的数据，可以直接返回这个字符串也是可以的。
                hashMap.put("documentId",documentId);
                hashMap.put("UUID",uuid.toString());
                System.out.println("UUID:"+uuid.toString());
                hashMap.put("attachmentName",fileName);
                System.out.println("fileName:"+fileName);
                hashMap.put("attachmentUrl",htmlToPdfUtils.getAttachmentAbsolutePath());
                attachmentService.addAttachment(hashMap);
                Result result = Result.succ("上传附件成功",hashMap.get("attachmentId"));
                System.out.println("result:"+result);
                return result;
            } catch (IOException e) {
                logger.error(e.toString());
            }
        }
        // 待完成 —— 文件类型校验工作
        return Result.fail("上传附件异常！！！");
    }
    @PostMapping(value = "/attachment/download")
    public ResponseEntity<byte[]> download(HttpServletResponse response, @RequestParam(name = "url",required = false) String url) throws Exception{

        logger.info("下载公文附件");
        //预下载文件路径******
        String preDownload_path = url;
        System.out.println(preDownload_path);
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


    @GetMapping("/doc/documentDetails")
    @ResponseBody
    public Result queryProcess(@RequestParam(name = "documentId",required = false)Integer documentId){
        // Document document = documentProcessMapper.queryDocumentProcess(documentId);
        logger.info("查询公文详细信息");
        HashMap hashMap = new HashMap();
        hashMap.put("documentId",documentId);
        System.out.println("documentId:"+documentId);
        List<Document> document = documentService.getDocuments(hashMap);
        System.out.println(document);
        String basePath = document.get(0).getDocumentUrl();
        System.out.println("basePath:"+basePath);
        String UUID = document.get(0).getUUID();
        List<Process> processList = processService.queryProcessList(documentId);
        for(int i = 0;i<processList.size();i++){
            Integer employeeId = processList.get(i).getProcessEmployeeId();
            Employee employee = employeeService.queryEmployeeById(employeeId);
            processList.get(i).setDepartmentId(employee.getDepartment().getDepartmentId());
            processList.get(i).setDepartmentName(employee.getDepartment().getDepartmentName());
        }
        hashMap.put("document",document);
        hashMap.put("processList",processList);
        hashMap.put("departmentName",departmentService.queryDepartmentById(document.get(0).getDepartmentId()));
        hashMap.put("PDFUrl",basePath+UUID+".pdf");
        System.out.println("PDFUrl"+basePath+UUID+".pdf");
        List<Attachment> attachmentList = attachmentService.queryAttachment(hashMap);
        hashMap.put("attachmentList",attachmentList);
        return Result.succ("查询流程成功",hashMap);
    }

    /**
     *
     * @param employeeId 请求参数员工ID
     * @param pagenum 请求参数页号
     * @param pagesize 请求参数页大小
     * @return
     */
    @GetMapping("/doc/queryDocument")
    @ResponseBody
    public HashMap queryDocument( @RequestParam(name = "employeeId",required = false)Integer employeeId,
                                  @RequestParam(value = "query",required = false)String query,
                                  @RequestParam(name = "pagenum",required = false)Integer pagenum,
                                  @RequestParam(name = "pagesize",required = false)Integer pagesize,
                                  @RequestParam(name = "documentLabel",required = false)Integer documentLabel,
                                  @RequestParam(name = "documentCollect",required = false)Integer documentCollect,
                                  @RequestParam(name = "documentStatus",required = false)Integer documentStatus,
                                  @RequestParam(name = "isPublic",required = false)String isPublic
                                   ){
        logger.info("查询公文列表");
        HashMap hashmap = new HashMap();
        HashMap map = new HashMap();
        System.out.println("employeeId:"+employeeId);
        hashmap.put("employeeId",employeeId);
        hashmap.put("documentLabel",documentLabel);
        hashmap.put("documentCollect",documentCollect);
        hashmap.put("documentStatus",documentStatus);
        hashmap.put("isPublic",isPublic);
        if(pagesize != null) {
            try {
                int pagenum_pagesize = (pagenum - 1) * pagesize;
                List<Document> list = documentService.getDocuments(hashmap);
                Document[] array = new Document[pagesize];
                int len = list.size();
                int length;
                int lengthsub;
                if (pagenum == 1) {
                    if (len < pagesize) {
                        length = len;
                    } else {
                        length = pagesize;
                    }
                } else {
                    lengthsub = list.size() - pagenum_pagesize;
                    if (lengthsub < pagesize) {
                        length = (lengthsub);
                    } else {
                        length = pagesize;
                    }
                }
                Document d;
                for (int j = 0; j < length; j++) {
                    d = list.get(j + pagenum_pagesize);
                    array[j] = d;
                }
                meta.setMsg("查询成功");
                meta.setStatus(200);
                map.put("meta", meta);
                map.put("total", len);
                map.put("data", array);
            } catch (Exception e) {

            }
        }else {
            try {
                List<Document> list = documentService.getDocuments(hashmap);
                meta.setMsg("查询成功");
                meta.setStatus(200);
                map.put("meta", meta);
                map.put("data", list);
            }catch (Exception e){

            }
        }
        System.out.println("map:"+map);
        return map;
    }

    /**
     *
     * @param employeeId 请求参数员工ID
     * @param pagenum 请求参数页号
     * @param pagesize 请求参数页大小
     * @return
     */
    @GetMapping("/doc/examineDocument")
    @ResponseBody
    public HashMap queryExamineDocument( @RequestParam(name = "employeeId")Integer employeeId,
                                  @RequestParam(value = "query",required = false)String query,
                                  @RequestParam(name = "pagenum",required = false)Integer pagenum,
                                  @RequestParam(name = "pagesize",required = false)Integer pagesize){
        logger.info("查询待审公文");
        HashMap hashmap = new HashMap();
        HashMap map = new HashMap();
        System.out.println("employeeId:"+employeeId);
        hashmap.put("employeeId",employeeId);
        List<String> departmentNameList = new ArrayList<>();
        List<Document> list2 = new ArrayList<>();
        if(pagesize != null) {
            try {
                int pagenum_pagesize = (pagenum - 1) * pagesize;
                List<Document> list = documentService.getEmployeeDocuments(employeeId);
                for(int j = 0; j < list.size(); j++){
                    if(list.get(j).getDocumentStatus() == list.get(j).getProcess().getProcessOrder()){
                        list2.add(list.get(j));
                        departmentNameList.add(departmentService.queryDepartmentById(list.get(j).getDepartmentId()).getDepartmentName());
                    }
                }
                Document[] array = new Document[pagesize];
                int len = list2.size();
                int length;
                int lengthsub;
                if (pagenum == 1) {
                    if (len < pagesize) {
                        length = len;
                    } else {
                        length = pagesize;
                    }
                } else {
                    lengthsub = list.size() - pagenum_pagesize;
                    if (lengthsub < pagesize) {
                        length = (lengthsub);
                    } else {
                        length = pagesize;
                    }
                }
                Document d;
                for (int j = 0; j < length; j++) {
                    d = list2.get(j + pagenum_pagesize);
                    String departmentName = departmentService.queryDepartmentById(d.getDepartmentId()).getDepartmentName();
                    departmentNameList.add(departmentName);
                    array[j] = d;
                }
                meta.setMsg("查询成功");
                meta.setStatus(200);
                map.put("meta", meta);
                map.put("total", len);
                map.put("data", array);
                map.put("departmentName",departmentNameList);
            } catch (Exception e) {
                logger.error("查询异常");
                meta.setMsg("查询失败");
                meta.setStatus(500);
                map.put("meta", meta);
            }
        }else {
            try {
                List<Document> list = documentService.getEmployeeDocuments(employeeId);
                for(int j = 0; j < list.size(); j++){
                    if(list.get(j).getDocumentStatus() == list.get(j).getProcess().getProcessOrder()){
                        list2.add(list.get(j));
                        departmentNameList.add(departmentService.queryDepartmentById(list.get(j).getDepartmentId()).getDepartmentName());
                    }
                }
                meta.setMsg("查询成功");
                meta.setStatus(200);
                map.put("meta", meta);
                map.put("data", list2);
                map.put("departmentName",departmentNameList);
            }catch (Exception e){
                logger.error("查询异常");
                meta.setMsg("查询失败");
                meta.setStatus(500);
                map.put("meta", meta);
            }
        }
        System.out.println("map:"+map);
        return map;
    }


    /**
     *
     * @param map 请求参数map
     * @return 返回数据
     */
    @PostMapping("/doc/addDocument")
    @ResponseBody
    public Result addDepartment(@RequestBody Map map){
        logger.info("增加公文");
        System.out.println("map:"+map);
        HashMap hashMap= new HashMap();
        String documentContent = map.get("documentContent").toString();
        System.out.println(documentContent);
        try {
            Integer i = 1;
            Integer processId;
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String documentCreateTime = simpleDateFormat.format(new Date());
            map.put("documentCreateTime",documentCreateTime);
            map.put("documentStatus",2);
            //添加公文
            //生成UUID
            // UUID uuid = UUID.randomUUID();
            // map.put("UUID",uuid.toString());
            map.put("documentCollect",0);
            documentService.addDocument(map);
            Integer documentId = Integer.parseInt(map.get("documentId").toString());
            System.out.println("documentId:"+documentId);
            HashMap hashMap1 = new HashMap();
            String processCreateTime = simpleDateFormat.format(new Date());

            //添加流程
            hashMap1.put("documentId",documentId);
            hashMap1.put("processCreateTime",processCreateTime);
            hashMap1.put("processStatus","未审核");
            hashMap1.put("processEmployeeName",map.get("ApprovalEmployeeName"));
            hashMap1.put("processEmployeeId",Integer.parseInt(map.get("ApprovalEmployeeId").toString()));
            hashMap1.put("processOrder",++i);
            processService.addProcess(hashMap1);
            processId = Integer.parseInt(hashMap1.get("processId").toString());
            System.out.println("processId:"+processId);
            hashMap1.put("processId",processId);
            documentProcessService.addEmployeeProcess(hashMap1);
            hashMap1.put("processEmployeeName",map.get("ExamineEmployeeName"));
            hashMap1.put("processEmployeeId",Integer.parseInt(map.get("ExamineEmployeeId").toString()));
            hashMap1.put("processOrder",++i);
            processService.addProcess(hashMap1);
            processId = Integer.parseInt(hashMap1.get("processId").toString());
            System.out.println("processId:"+processId);
            hashMap1.put("processId",processId);
            documentProcessService.addEmployeeProcess(hashMap1);

            meta.setMsg("添加成功");
            meta.setStatus(200);
        }catch (Exception e){
            logger.error("添加异常");
            meta.setMsg("错误");
            meta.setStatus(500);
            return Result.fail(500,"错误");
        }
        return Result.succ(meta,map.get("documentId"));
    }
    /**
     *
     * @param map 审核参数map
     * @return 返回数据
     */
    @PostMapping("/doc/toexamine")
    @ResponseBody
    public HashMap examine(@RequestBody Map map){
        logger.info("审核公文");
        Integer documentStatus = Integer.parseInt(map.get("documentStatus").toString());
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String processTime = simpleDateFormat.format(new Date());
        map.put("processTime",processTime);
        if(documentStatus == 1){
            Document document = documentProcessService.queryDocumentProcess(Integer.parseInt(map.get("documentId").toString()));
            // List<Process> processList = document.getProcessList();
            map.put("processStatus","未通过");
            map.put("processId",map.get("processId"));
            processService.updateProcess(map);
        }else {
            map.put("processStatus","已审核");
            map.put("processId",map.get("processId"));
            System.out.println("processId:"+map.get("processId"));
            processService.updateProcess(map);
            File tmpFile = new File(htmlToPdfUtils.getPdfAbsolutePath());
            if(!tmpFile.exists()){
                System.out.println("创建文件夹===");
                tmpFile.mkdirs();
            }
            if(documentStatus == 4){
                try {
                    UUID uuid = UUID.randomUUID();
                    System.out.println("uuid"+uuid);
                    String pdfFile = htmlToPdfUtils.getPdfAbsolutePath()+uuid+".pdf";
                    htmlToPdfUtils.parseHtml2PdfByString(pdfFile,map.get("documentContent").toString());
                    map.put("UUID",uuid.toString());
                    map.put("documentUrl",htmlToPdfUtils.getPdfResourceHandler());
                }catch (Exception e){

                }
            }
        }
        documentService.updDocumentById(map);
        HashMap hashMap = new HashMap();
        meta.setMsg("请求成功");
        meta.setStatus(200);
        hashMap.put("meta",meta);
        // hashMap.put("data",departmentService.queryDepartmentById(id));
        return hashMap;
    }

    /**
     *
     * @param map 请求参数map
     * @return 返回数据
     */
    @PostMapping("/doc/toupdate")
    @ResponseBody
    public HashMap updateDocument(@RequestBody Map map){
        HashMap hashMap= new HashMap();
        map.put("documentStatus",0);
        documentService.updDocumentById(map);
        HashMap hashMap1 = new HashMap();
        hashMap1.put("processStatus","未审核");
        hashMap1.put("processNote","");
        hashMap1.put("processTime","");
        Integer processID = Integer.parseInt(map.get("ApprovalProcessId").toString());
        hashMap1.put("processId",processID);
        processService.updateProcess(hashMap1);
        processID = Integer.parseInt(map.get("ExamineProcessId").toString());
        hashMap1.put("processId",processID);
        processService.updateProcess(hashMap1);
        meta.setMsg("修改成功");
        meta.setStatus(200);
        hashMap.put("meta",meta);
        return hashMap;
    }
    /**
     *
     * @param map 请求参数map
     * @return 返回数据
     */
    @PostMapping("/doc/collect")
    @ResponseBody
    public HashMap collectDocument(@RequestBody Map map){
        logger.info("收藏公文");
        documentService.updDocumentById(map);
        HashMap hashMap = new HashMap();
        meta.setMsg("收藏成功");
        meta.setStatus(200);
        hashMap.put("meta",meta);
        return hashMap;
    }

    /**
     *
     * @param id 请求参数部门ID
     * @return 返回数据
     */
    @GetMapping("/doc/deleteDocument")
    @ResponseBody
    public HashMap todelete(@RequestParam("id")Integer[] id){
        logger.info("删除公文");
        HashMap hashMap= new HashMap();
        try {
            documentService.delDocuments(id);
            meta.setMsg("查询其它部门成功");
            meta.setStatus(200);
            hashMap.put("meta",meta);
        }catch (Exception e){
            meta.setMsg("错误");
            meta.setStatus(200);
            hashMap.put("meta",meta);
        }
        return hashMap;
    }


}

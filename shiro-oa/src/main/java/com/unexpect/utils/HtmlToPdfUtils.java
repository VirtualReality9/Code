package com.unexpect.utils;

import com.itextpdf.text.Document;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.tool.xml.XMLWorkerHelper;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.io.*;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.util.UUID;

/**
 * PDF文件工具类
 *
 * @author Tom
 * @date 2020-02-15
 */
@Component
@Data
public class HtmlToPdfUtils {

    //windows地址
    // public String pdfAbsolutePath = "C:/upload/pdf/";
    // public String pdfResourceHandler = "/upload/pdf/";
    // public String attachmentResourceHandler = "/upload/attachment/";
    // public String attachmentAbsolutePath = "C:/upload/attachment/";
    // public String scheduleAttachmentResourceHandler = "upload/scheduleAttachment/";
    // public String scheduleAttachmentAbsolutePath = "C:/upload/scheduleAttachment/";


    //linux下地址
    public String pdfAbsolutePath = "/home/upload/pdf/";
    public String pdfResourceHandler = "/upload/pdf/";
    public String attachmentResourceHandler = "/upload/attachment/";
    public String attachmentAbsolutePath = "/home/upload/attachment/";
    public String scheduleAttachmentResourceHandler = "upload/scheduleAttachment/";
    public String scheduleAttachmentAbsolutePath = "/home/upload//scheduleAttachment/";
    /**
     * 根据html文件生成pdf
     * @param pdfFilePath pdf文件生成路径
     * @param htmlFilePath html文件路径
     */
    public static void parseHtml2PdfByFilePath(String pdfFilePath, String htmlFilePath) {
        Document document = new Document();
        PdfWriter writer = null;
        FileOutputStream fileOutputStream = null;
        FileInputStream fileInputStream = null;
        try {
            fileOutputStream = new FileOutputStream(pdfFilePath);
            writer = PdfWriter.getInstance(document, fileOutputStream);
            // 设置底部距离60，解决重叠问题
            document.setPageSize(PageSize.A4);
            document.setMargins(50, 45, 50, 60);
            document.setMarginMirroring(false);
            document.open();
            StringBuffer sb = new StringBuffer();
            fileInputStream = new FileInputStream(htmlFilePath);
            BufferedReader br = new BufferedReader(new InputStreamReader(fileInputStream, "UTF-8"));
            String readStr = "";
            while ((readStr = br.readLine()) != null) {
                sb.append(readStr);
            }
            XMLWorkerHelper.getInstance().parseXHtml(writer, document, new ByteArrayInputStream(sb.toString().getBytes("Utf-8")), null, Charset.forName("UTF-8"));
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (null != document) {
                document.close();
            }
            if (null != writer) {
                writer.close();
            }
            if (null != fileInputStream) {
                try {
                    fileInputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (null != fileOutputStream) {
                try {
                    fileOutputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * 根据html字符串内容生成pdf
     *
     * @param pdfFilePath pdf文件存储位置
     */
    public static void parseHtml2PdfByString(String pdfFilePath, String htmlContent) {
        System.out.println("执行html转pdf===================");
        Document document = new Document();
        PdfWriter writer = null;
        String COMPLETE_CONTENT = "<html><head></head><body style=\"font-family: SimSun;\">" + htmlContent + "</body></html>";
        try {
            writer = PdfWriter.getInstance(document, new FileOutputStream(pdfFilePath));
            // 设置底部距离60，解决重叠问题
            document.setPageSize(PageSize.A4);
            document.setMargins(50, 45, 50, 60);
            document.setMarginMirroring(false);
            document.open();
            XMLWorkerHelper.getInstance().parseXHtml(writer, document, new ByteArrayInputStream(COMPLETE_CONTENT.getBytes("UTF-8")), null, Charset.forName("UTF-8"));
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (null != document) {
                document.close();
            }
            if (null != writer) {
                writer.close();
            }
        }
    }

    public static void main(String[] args) {
        try {
            // 本地
            String htmlFile = "C:\\test.html";
            UUID uuid = UUID.randomUUID();
            System.out.println("uuid"+uuid);
            String pdfFile = "H:\\upload\\"+uuid+".pdf";
            String htmlContent = "<h1 style=\"text-align: center;\"><span style=\"color: #e03e2d;\">XXX公司文件</span></h1>\n" +
                    "<h3 style=\"text-align: center;\"><span style=\"color: #000000;\">xxx发【2020】1号</span></h3>\n" +
                    "<p style=\"text-align: center;\"><span style=\"color: #e03e2d;\"><strong>&mdash;&mdash;&mdash;&mdash;&mdash;&mdash;&mdash;&mdash;&mdash;&mdash;&mdash;&mdash;&mdash;&mdash;&mdash;&mdash;&mdash;&mdash;&mdash;&mdash;&mdash;&mdash;&mdash;&mdash;&mdash;&mdash;&mdash;&mdash;&mdash;&mdash;&mdash;&mdash;&mdash;&mdash;</strong></span></p>\n" +
                    "<p style=\"text-align: left;\">为什么不显示</p>";
            String COMPLETE_CONTENT = "<html><head></head><body style=\"font-family: SimSun;\">" + htmlContent + "</body></html>";
            parseHtml2PdfByString(pdfFile,COMPLETE_CONTENT);
//            parseHtml2PdfByFilePath(pdfFile,htmlFile);
        } catch (Exception e) {
            e.printStackTrace();
        }
        File tmpFile = new File("D:/upload/pdf/");
        if(!tmpFile.exists()){
            System.out.println("创建文件夹===");
            Boolean aBoolean = tmpFile.mkdirs();
            System.out.println(aBoolean+"执行结束");
        }
        File dir = new File("C:/Users/admin/Desktop/code/test");
        Boolean  aBoolean= dir.mkdirs();
        System.out.println(aBoolean);
    }
}

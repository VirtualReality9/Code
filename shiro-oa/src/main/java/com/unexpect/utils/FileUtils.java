package com.unexpect.utils;

import javax.servlet.http.HttpServletResponse;
import java.io.*;

public class FileUtils {

public static void getFile(byte[] bfile, String fileName) {    //创建文件
    File file=new File(fileName);
    try {
        if (!file.exists()){file.createNewFile();}
        FileOutputStream fos = new FileOutputStream(file);
        fos.write(bfile);
    } catch (Exception e) {
        e.printStackTrace();
    }
}

public static void responseTo(File file, HttpServletResponse res) {  //将文件发送到前端
    res.setHeader("content-type", "application/octet-stream");
    res.setContentType("application/octet-stream");
    res.setHeader("Content-Disposition", "attachment;filename=" + file.getName());
    byte[] buff = new byte[1024];
    BufferedInputStream bis = null;
    OutputStream os = null;
    try {
        os = res.getOutputStream();
        bis = new BufferedInputStream(new FileInputStream(file));
        int i = bis.read(buff);
        while (i != -1) {
            os.write(buff, 0, buff.length);
            os.flush();
            i = bis.read(buff);
        }
    } catch (IOException e) {
        e.printStackTrace();
    } finally {
        if (bis != null) {
            try {
                bis.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    System.out.println("success");
}
}

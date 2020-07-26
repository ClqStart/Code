package com.kuang.servlet;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLEncoder;
import java.nio.Buffer;
import java.util.Properties;

/*
 *@author:C1q
 */
public class fileLaod extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//         1. 要获取下载文件的路径

        String realPath = "D:\\JavaWeb\\java_Web_servlet\\servlet-02\\src\\main\\resource\\1.jpg";
        System.out.println("下载的路径为:"+realPath);
//        2. 下载的文件名是啥？
        String fileName = realPath.substring(realPath.lastIndexOf("\\") + 1);
//        3. 设置想办法让浏览器能够支持下载我们需要的东西
        resp.setHeader("Content-Disposition","attachment;filename="+ URLEncoder.encode(fileName,"UTF-8"));
//        4. 获取下载文件的输入流
        InputStream is = new BufferedInputStream(new FileInputStream(realPath));
//        5. 创建缓冲区
        int len=0;
        byte[] buffer = new byte[1024];
//        6. 获取OutputStream对象
        ServletOutputStream out = resp.getOutputStream();
//        7. 将FileOutputStream流写入到buffer缓冲区
        while ((len=is.read(buffer))>0){
            out.write(buffer,0,len);
        }
//        8. 使用OutputStream将缓冲区中的数据输出到客户端！
        is.close();
        out.close();
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doGet(req, resp);
    }
}

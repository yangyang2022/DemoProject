package com.yangyang.web;


import org.apache.commons.fileupload.FileItemIterator;
import org.apache.commons.fileupload.FileItemStream;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.fileupload.util.Streams;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

@WebServlet(name = "FileServlet",value = "/fileDemo")
public class FileServlet extends HttpServlet {

    protected void dofile01(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        boolean isMultipart = ServletFileUpload.isMultipartContent(request);
        if(isMultipart){
            ServletFileUpload upload = new ServletFileUpload();
            InputStream is = null;
            FileOutputStream fos = null;
            try {
                FileItemIterator iter = upload.getItemIterator(request);
                while (iter.hasNext()){
                    FileItemStream fis = iter.next();
                    is = fis.openStream();

                    if(fis.isFormField()){
                        //是普通表单域
                        System.out.println(fis.getFieldName());
                        //获取表单里面的值
                        System.out.println(Streams.asString(is));
                    }else {
                        //文件名称
                        System.out.println(fis.getName());
                        String path = request.getSession().getServletContext().getRealPath("/");
                        System.out.println(path);
                        fos = new FileOutputStream(path+"/upload/"+fis.getName());
                        byte[] buf = new byte[1024];
                        int len = 0;
                        while ((len = is.read(buf))>0){
                            fos.write(buf,0,len);
                        }
                    }
                }
            } catch (FileUploadException e) {
                e.printStackTrace();
            }finally {
                if(is != null) is.close();
                if (fos != null) fos.close();
            }

        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if(ServletFileUpload.isMultipartContent(request)){
            request = new MultipartRequestWrapper(request);
        }
        System.out.println("username: "+request.getParameter("username"));
        System.out.println("photo: "+request.getParameter("photo"));
        String[] values = request.getParameterValues("ints");
        for(String s:values){
            System.out.println(s);
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}

package com.yangyang.web;

import org.apache.commons.fileupload.FileItemIterator;
import org.apache.commons.fileupload.FileItemStream;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.fileupload.util.Streams;
import org.apache.commons.io.FilenameUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MulitpartWrapper extends HttpServletRequestWrapper {
    private Map<String,String[]> params = null;
    public static final String filePath = "D:\\web\\upload";
    private List<String> allowedFile = Arrays.asList("jpg","bmp","gif","png");

    public MulitpartWrapper(HttpServletRequest request) {
        super(request);
        params = new HashMap<>();
        setParams(request);

    }
    private void setParams(HttpServletRequest request){
        try {
            if(ServletFileUpload.isMultipartContent(request)){
                ServletFileUpload upload = new ServletFileUpload();
                FileItemIterator iter = upload.getItemIterator(request);
                InputStream is = null;
                while (iter.hasNext()){
                    FileItemStream fis = iter.next();
                    is = fis.openStream();
                    if(fis.isFormField()){
                        //form
                        setFieldParam(fis.getFieldName(),is);
                    }else {
                        //doc
                        uploadFile(fis,is,request);
                    }
                }
            }else {
                params = super.getParameterMap();
            }
        } catch (FileUploadException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private void uploadFile(FileItemStream fis,InputStream is,HttpServletRequest request) throws IOException {
        if(is.available() > 0 ){
            String fn = FilenameUtils.getName(fis.getName());
            String ext = FilenameUtils.getExtension(fis.getName());
            if(allowedFile.contains(ext)){
                Streams.copy(is,new FileOutputStream(filePath+"/img/"+fn),true);
                params.put(fis.getFieldName(),new String[]{fn});
            }else {
                //文件上传有错
                Map<String,String> errors = (Map<String, String>) request.getAttribute("errors");
                errors.put(fis.getFieldName(),"图片类型必须是jpg gif png bmp");
            }
        }
    }
    private void setFieldParam(String name,InputStream is) throws IOException {
          if(params.containsKey(name)){
                String[] vs = params.get(name);
                vs = Arrays.copyOf(vs,vs.length+1);
                vs[vs.length-1] = Streams.asString(is);
                params.put(name,vs);
          }else {
              params.put(name,new String[]{Streams.asString(is)});
          }
    }
    @Override
    public String getParameter(String name) {
        String[] v = params.get(name);
        if(v!=null ) return v[0];
        return null;
    }

    @Override
    public Map<String, String[]> getParameterMap() {

        return params;
    }

    @Override
    public String[] getParameterValues(String name) {
        String[] v = params.get(name);
        if(v!=null ) return v;
        return null;
    }
}

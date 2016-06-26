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
import java.util.Map;

public class MultipartRequestWrapper  extends HttpServletRequestWrapper {
    private static final String fileUploadPath = "D:\\web";

    private Map<String,String[]> params = new HashMap<>();
    private void setFormField(String fieldName,InputStream is) throws IOException {
        if(params.containsKey(fieldName)){
            //表单域中存在值
            String[] values = params.get(fieldName);
            values = Arrays.copyOf(values,values.length+1);
            values[values.length-1] = Streams.asString(is);
            params.put(fieldName,values);
        }else{
            params.put(fieldName,new String[]{Streams.asString(is)});
        }
    }
    private void setParams(HttpServletRequest request){
        boolean isMultipatFile = ServletFileUpload.isMultipartContent(request);
        try {
            if(isMultipatFile){
                ServletFileUpload upload = new ServletFileUpload();
                FileItemIterator iter = upload.getItemIterator(request);
                InputStream is = null;
                while (iter.hasNext()){
                    FileItemStream fis = iter.next();
                    is = fis.openStream();
                    if(fis.isFormField()){
                        setFormField(fis.getFieldName(),is);
                        //params.put(fis.getFieldName(), new String[]{Streams.asString(is)});
                    }else {
                        if(is.available() > 0){
                            //对于IE6 而言,会获取完整 绝对路径 --> 会报异常
                            //这个方法就是获取 文件名
                            String fname = FilenameUtils.getName(fis.getName());
                            //输入流 --> 输出流 --> 自动关闭
                            Streams.copy(is,new FileOutputStream(fileUploadPath+"/"+fname),true);
                            params.put(fis.getFieldName(),new String[]{fis.getName()});
                        }
                    }
                }
            }else {
                params = request.getParameterMap();
            }
        } catch (FileUploadException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public MultipartRequestWrapper(HttpServletRequest request) {
        super(request);
        setParams(request);
    }

    @Override
    public Map<String, String[]> getParameterMap() {
        return params;
    }
    //获取一个getParameter 值
    @Override
    public String getParameter(String name) {
        String[] values = params.get(name);
        if(values!=null) return values[0];
        return null;
    }
    //获取一个getParameter 数组值
    @Override
    public String[] getParameterValues(String name) {
        String[] values = params.get(name);
        if(values!=null) return values;
        return null;
    }
}

package com.yangyang.PoiUtil;

import com.yangyang.model.Student;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.*;

public class ExcelUtil {

    private String dirPath = "D:\\code\\excel\\";

    private static ExcelUtil eu = new ExcelUtil();
    private ExcelUtil(){}
    public static ExcelUtil getInstance(){return eu;}

    private List<ExcelHeader> getHeaderList(Class clz){
        List<ExcelHeader> headers = new ArrayList<>();
        for(Method method : clz.getDeclaredMethods()){
            String mn = method.getName();
            if(mn.startsWith("get")){
                if(method.isAnnotationPresent(ExcelResource.class)){
                    ExcelResource er = method.getAnnotation(ExcelResource.class);
                    headers.add(new ExcelHeader(er.title(),er.order(),mn));
                }
            }
        }
        return headers;
    }
    public void exportObj2ExcelByTemplate(String tempaltePath,String outputPath,Map<String,String>datas,
                                          List objs,Class clz,boolean isClassPath,boolean isInsertNum){
        List<ExcelHeader> headers = getHeaderList(clz);
        ExcelTemplate et = ExcelTemplate.getInstance();
        Collections.sort(headers);
        if(isClassPath){
            et.readTemplateByClassPath(tempaltePath);
        }else {
            et.readTemplateByPath(tempaltePath);
        }
        //输出模板标题
        for(ExcelHeader header : headers){
            et.createCell(header.getTitle());
        }
        //输出值
        try {
            for(Object obj : objs){
                et.createNewRow();
                for(ExcelHeader header:headers){
                    String mn = header.getMethodName();
                    //Method m = clz.getDeclaredMethod(mn);
                    //Object o = m.invoke(obj);
                    et.createCell(getInvokeString(header,obj,clz,mn));
                }
            }
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        et.repalceFinalData(datas);
        if(isInsertNum) et.insertSer();
        et.writeToFile(outputPath);
    }
    private String getInvokeString(ExcelHeader header,Object obj,Class clz,String methodName) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        String mn = header.getMethodName();
        Method m = clz.getDeclaredMethod(mn);
        Object o = m.invoke(obj);
        return o.toString();
    }
    public void exportObj2Excel(String outPath,List objs, Class clz, boolean isXssf,OutputStream os,boolean isOutPutStream){
        Workbook wb = null;
        if(isXssf){
            wb = new XSSFWorkbook(); //excel 2007
        }else {
            wb = new HSSFWorkbook();//excel 2003
        }
        Sheet sheet = wb.createSheet();
        Row row = sheet.createRow(0);
        List<ExcelHeader> headers = getHeaderList(clz);
        Collections.sort(headers);

        //写入标题
        for (int i = 0; i < headers.size(); ++i) {
            row.createCell(i).setCellValue(headers.get(i).getTitle());
        }
        //写数据
        Object obj = null;
        for (int i = 0; i < objs.size(); ++i) {
            row = sheet.createRow(i + 1);
            obj = objs.get(i);
            for (int j = 0; j < headers.size(); ++j) {
                try {
                    String s = getInvokeString(headers.get(j),obj,clz,headers.get(i).getMethodName());
                    row.createCell(j).setCellValue(s);
                } catch (NoSuchMethodException e) {
                    e.printStackTrace();
                } catch (InvocationTargetException e) {
                    e.printStackTrace();
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
        }
        try {
            if(isOutPutStream){
                wb.write(os);
            }else {
                wb.write(new FileOutputStream(new File(outPath)));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //maps里面 莫一列对应的方法名称
    private Map<Integer,String> getHeaderMap(Row titleRow,Class clz){
        List<ExcelHeader> headers = getHeaderList(clz);
        Map<Integer,String> maps = new HashMap<>();
        for(Cell cell : titleRow){
            String title = cell.getStringCellValue();
            for(ExcelHeader header:headers){
                if(header.getTitle().equals(title)){
                    //现在需要设置值 --> setXXX
                    maps.put(cell.getColumnIndex(),header.getMethodName().replace("get","set"));
                    break;
                }
            }
        }
        return maps;
    }
    //readLine --> 从第几行开始读
    private void handleExcel2Obj(Workbook wb,Class clz,int readLine,int tailLine){
        Sheet sheet = wb.getSheetAt(0);
        Row row = sheet.getRow(readLine);
        Map<Integer,String> maps = getHeaderMap(row,clz);
        List<Object> resObjs = new ArrayList<>();
        try {
            for (int i = readLine + 1; i < sheet.getLastRowNum()-tailLine; ++i) {
                row = sheet.getRow(i);
                Object obj = clz.newInstance();
                for(Cell cell : row){
                    int ci = cell.getColumnIndex();
                    String mn = maps.get(ci);
                    String value = cell.getStringCellValue();
                    System.out.println("mn: "+mn);

                    if(mn.equals("setId")){
                        continue;
                        //clz.getDeclaredMethod(mn).invoke(obj,Integer.valueOf(value));
                    }else {
                        clz.getDeclaredMethod(mn).invoke(obj,value);
                    }
                }
                resObjs.add(obj);
            }
            System.out.println((Student) resObjs.get(0));

        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
    }
    public void readExcel2Obj(String path,Class clz,int readLine,boolean isReadByClassPath){
        Workbook wb = null;
        try {
            if(isReadByClassPath){
                wb = WorkbookFactory.create(ExcelUtil.class.getResourceAsStream(path));
            }else {
                wb = WorkbookFactory.create(new File(path));
            }
            //tailLine = 0
            handleExcel2Obj(wb, clz,readLine,0);

        } catch (IOException e) {
            e.printStackTrace();
        } catch (InvalidFormatException e) {
            e.printStackTrace();
        }
    }
}

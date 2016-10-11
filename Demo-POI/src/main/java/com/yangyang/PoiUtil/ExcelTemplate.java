package com.yangyang.PoiUtil;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.*;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

public class ExcelTemplate {

    private static ExcelTemplate et = new ExcelTemplate();

    private Workbook wb;

    // 常量的定义
    public static final String DATA_LINE = "datas";
    public static final String STYLES = "styles";
    public static final String DEFAULT_STYLE = "defaultStyle";
    public static final String SER_NUMS = "sernums";//生成序列

    private int initRowIndex;
    private int initCellIndex;
    private int curtRowIndex;
    private int curtCellIndex;
    private Row curRow;//当前行
    private Sheet sheet;

    private int lastRowIndex;//最后一行

    private CellStyle defaultSyle;//默认样式
    private float rowHeight;//行高不在样式里面

    //存储莫一行的样式
    private Map<Integer,CellStyle> styles ;

    //插入序列的 列/行
    private int serColIndex;
    private int serRowIndex;

    private static final String Read_Template_Error = "读入的模板文件有错";
    public static final String DEFAULT_TEMPLATE_File = "/default.xlsx";

    private ExcelTemplate(){}

    public static ExcelTemplate getInstance(){
        return et;
    }

    //1: 读取相应的模板文件
    public ExcelTemplate readTemplateByClassPath(String path){
        try {
            wb = WorkbookFactory.create(ExcelTemplate.class.getResourceAsStream(path));
            initTemplate();
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("读入模板文件不存在!");
        } catch (InvalidFormatException e) {
            e.printStackTrace();
            throw new RuntimeException("读入模板文件格式错误!");
        }
        return this;
    }
    public ExcelTemplate readTemplateByPath(String path){
        try {
            wb = WorkbookFactory.create(new File(path));
            initTemplate();
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("读入模板文件不存在!");
        } catch (InvalidFormatException e) {
            e.printStackTrace();
            throw new RuntimeException("读入模板文件格式错误!");
        }
        return this;
    }

    private void initStyles(){
        styles = new HashMap<>();
        for(Row row : sheet){
            for(Cell cell : row){
                if(cell.getCellType() != Cell.CELL_TYPE_STRING) continue;
                String datasStr = cell.getStringCellValue().trim();
                if(datasStr.equals(DEFAULT_STYLE)){
                    defaultSyle = cell.getCellStyle();//获取样式
                }
                if(datasStr.equals(STYLES)){
                    styles.put(cell.getColumnIndex(),cell.getCellStyle());
                }
            }
        }
    }
    //插入序列
    public void insertSer(){
        int index = 1;
        Row row = null;
        Cell cell = null;
        for(int i = initRowIndex;i<curtRowIndex;i++){
            row = sheet.getRow(i);
            cell = row.createCell(serColIndex);
            setCellStyle(cell);
            cell.setCellValue(index++);
        }
    }
    private void initSer(){
        for(Row row : sheet){
            for(Cell cell : row){
                if(cell.getCellType() != Cell.CELL_TYPE_STRING) continue;
                String datasStr = cell.getStringCellValue().trim();
                if(datasStr.equals(SER_NUMS)){
                    serColIndex = cell.getColumnIndex();
                    serRowIndex = row.getRowNum();
                }
            }
        }
    }
    private void initConfig(){
        //寻找标识符 datas
        boolean findData = false;
        boolean findSer = false;

        for(Row row : sheet){
            if(findData) break;
            for(Cell cell : row){
                if(cell.getCellType() != Cell.CELL_TYPE_STRING) continue;
                String datasStr = cell.getStringCellValue().trim();
                if(datasStr.equals(SER_NUMS)){
                    //找到序列
                    serColIndex = cell.getColumnIndex();
                    serRowIndex = row.getRowNum();
                    findSer = true;
                }
                if(datasStr.equals(DATA_LINE)){
                    initRowIndex = cell.getRowIndex();
                    initCellIndex = cell.getColumnIndex();
                    curtRowIndex = initRowIndex;
                    curtCellIndex = initCellIndex;
                    defaultSyle = cell.getCellStyle();//获取样式
                    rowHeight = row.getHeightInPoints();//记录行高
                    initStyles();
                    findData = true;break;
                }
            }
        }
        if(!findSer){
            //如果没有找到
            initSer();
        }
    }
    public void writeToFile(String filePath){
        FileOutputStream fos = null;
        try {
            fos = new FileOutputStream(filePath);
            wb.write(fos);
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("写出的文件不存在!"+e.getMessage());
        }finally {
            try {
                if(fos!=null)fos.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    public void writeToOutputStream(OutputStream os){
        try {
            wb.write(os);
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("写入的流不存在!"+e.getMessage());
        }
    }

    private void setCellStyle(Cell cell){
        //设置样式
        if(styles.containsKey(curtCellIndex)){
            cell.setCellStyle(styles.get(curtCellIndex));
        }
        else cell.setCellStyle(defaultSyle);
    }
    public void createCell(Object obj){
        createCell(obj+"");
    }
    public void createCell(String value){
        Cell cell = curRow.createCell(curtCellIndex);
        cell.setCellValue(value);
        setCellStyle(cell);
        curtCellIndex++;
    }
    public void createNewRow(){
        if(lastRowIndex > curtRowIndex && curtRowIndex != initRowIndex){
            sheet.shiftRows(curtRowIndex,lastRowIndex,1,true,true);
            lastRowIndex++;
        }
        curRow = sheet.createRow(curtRowIndex);
        curRow.setHeightInPoints(rowHeight);//设置行高
        curtRowIndex++;
        curtCellIndex = initCellIndex;
    }

    //替换一些常量数据(根据map替换常量)
    public void repalceFinalData(Map<String,String> datas){
        if(datas == null){
            datas = new HashMap<>();
            datas.put("title","默认标题");
            datas.put("date", LocalDate.now()+"");
            datas.put("dep","默认部门");
        }
        for(Row row : sheet){
            for(Cell cell : row){
                if(cell.getCellType() != Cell.CELL_TYPE_STRING) continue;
                String datasStr = cell.getStringCellValue().trim();
                if(datasStr.startsWith("#")){
                    if(datas.containsKey(datasStr.substring(1)))
                        cell.setCellValue(datas.get(datasStr.substring(1)));
                }
            }
        }
    }
    //初始化模板
    private void initTemplate(){
        sheet = wb.getSheetAt(0);//获取第一个sheet
        initConfig();
        lastRowIndex = sheet.getLastRowNum();
        createNewRow();
    }
}

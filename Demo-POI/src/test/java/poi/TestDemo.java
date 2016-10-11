package poi;

import com.yangyang.PoiUtil.ExcelTemplate;
import com.yangyang.PoiUtil.ExcelUtil;
import com.yangyang.model.Student;
import com.yangyang.model.User;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.junit.Test;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TestDemo {

    private String filePath = "D:\\code\\excel\\demo.xlsx";
    private String dirPath = "D:\\code\\excel\\";

    @Test
    public void testDemo1() throws IOException, InvalidFormatException {
        Workbook wb = WorkbookFactory.create(new File(filePath));
        Sheet sheet = wb.getSheetAt(0);
        //第一行
        Row row = sheet.getRow(0);
        //第一列
        Cell cell = row.getCell(0);

        System.out.println(cell.getCellType());
        System.out.println(cell.getStringCellValue());
    }

    private String getCellValue(Cell cell){
        String s = null;
        switch (cell.getCellType()){
            case Cell.CELL_TYPE_BLANK:
                s = "";break;
            case Cell.CELL_TYPE_BOOLEAN:
                s = String.valueOf(cell.getBooleanCellValue());break;
            case Cell.CELL_TYPE_FORMULA:
                s = String.valueOf(cell.getCellFormula());break;
            case Cell.CELL_TYPE_NUMERIC:
                s = String.valueOf(cell.getNumericCellValue());break;
            case Cell.CELL_TYPE_STRING:
                s = cell.getStringCellValue();break;
            default:
                s = null;break;
        }
        return s;
    }
    @Test
    public void testListSheet() throws IOException, InvalidFormatException {
        Workbook wb = WorkbookFactory.create(new File(filePath));
        Sheet sheet = wb.getSheetAt(0);
        Row row = null;
        for (int i = 0; i <= sheet.getLastRowNum(); ++i) {
            row = sheet.getRow(i);
            for (int j = row.getFirstCellNum(); j < row.getLastCellNum(); ++j) {
                if(row.getCell(j) != null)
                 System.out.print(getCellValue(row.getCell(j)));
                else System.out.print("-");
            }
            System.out.println();
        }
    }

    @Test
    public void testList2() throws IOException, InvalidFormatException {
        Workbook wb = WorkbookFactory.create(new File(filePath));
        Sheet sheet = wb.getSheetAt(0);

        //增强for 循环(使用的不多,不一定从开头到结尾全部读取)
        for(Row row : sheet){
            for(Cell cell : row){
                if(cell != null)
                    System.out.print(getCellValue(cell));
                else System.out.print("-");
            }
            System.out.println();
        }
    }

    @Test
    public void testWrite() throws IOException {

        Workbook wb = new XSSFWorkbook();
        FileOutputStream fileOut = new FileOutputStream(dirPath+"demo2.xlsx");
        Sheet sheet = wb.createSheet("testSheet1");

        //设置样式
        CellStyle cs = wb.createCellStyle();
        cs.setAlignment(CellStyle.ALIGN_CENTER);

        Row row = sheet.createRow(0);
        Cell cell = row.createCell(0);
        cell.setCellValue("标识");
        cell.setCellStyle(cs);

        cell = row.createCell(1);
        cell.setCellValue("用户名");

        wb.write(fileOut);
        fileOut.close();
    }

    @Test
    public void testInitConfig() {
        ExcelTemplate et = ExcelTemplate.getInstance().readTemplateByClassPath(ExcelTemplate.DEFAULT_TEMPLATE_File);
        et.createCell("111");
        et.createCell("222");
        et.createCell("333");
        et.createCell("444");
        et.createNewRow();
        et.createCell("a111");
        et.createCell("a222");
        et.createCell("a333");
        et.createCell("a444");
        et.createNewRow();
        et.createCell(123);
        et.createCell(LocalDate.now());
        et.createCell(3.12);
        et.createCell(true);

        Map<String,String> finalDatas = new HashMap<>();
        finalDatas.put("title","这是一个标题");
        finalDatas.put("dep","招生就业部门");
        finalDatas.put("date", LocalDate.now()+"");

        et.repalceFinalData(finalDatas);
        et.insertSer();//插入序号
        et.writeToFile(dirPath+"01.xlsx");
    }


    @Test
    public void testDemo01() {
        Map<String,String> finalDatas = new HashMap<>();
        finalDatas.put("title","这是一个标题new");
        finalDatas.put("dep","招生就业部门");
        finalDatas.put("date", LocalDate.now()+"");

        List<User> users = Arrays.asList(
                new User(1,"zhangsan","123123","张三",11),
                new User(2,"lisi","123123","李四",22),
                new User(3,"wangwu","123123","王五",33),
                new User(4,"zhaoliu","123123","赵六",44)
        );

        List<Student> stus = Arrays.asList(
                new Student(1,"stu1","201100329","男"),
                new Student(2,"stu2","201100330","男"),
                new Student(3,"stu3","201100331","女"),
                new Student(4,"stu4","201100332","男")
        );

        ExcelUtil eu = ExcelUtil.getInstance();

        eu.exportObj2Excel(dirPath+"stus_noTemplate.xlsx",stus,Student.class,true,null,false);

        //eu.exportObj2ExcelByTemplate(ExcelTemplate.DEFAULT_TEMPLATE_File,dirPath+"student.xlsx",finalDatas
        //        ,users, User.class,true,false);
    }

    @Test
    public void testBeanUtils() throws Exception{
        Class us = User.class;
        Object obj = us.newInstance();
        BeanUtils.copyProperty(obj,"username","张三");
        String str = BeanUtils.getProperty(obj,"username");
        System.out.println(str);
    }


    @Test
    public void testRead2Obj() {
        String readFilePath = dirPath+"stus_noTemplate.xlsx";
        ExcelUtil eu = ExcelUtil.getInstance();
        eu.readExcel2Obj(readFilePath,Student.class,0,false);
    }

    @Test
    public void testMethod() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException, InstantiationException {
         String mn = "setId";
        Class clz = Student.class;
        Student stu = (Student) clz.newInstance();
        Method m = clz.getDeclaredMethod(mn,Integer.class);
        m.invoke(stu,123);
        System.out.println(stu.getId());

        //Object obj = clz.newInstance();
        //for(Method me : clz.getDeclaredMethods()){
        //    if(me.getName().equals(mn)){
        //        me.invoke(obj,123);
        //    }
        //}
        //System.out.println(((Student)obj).getId());

    }
}

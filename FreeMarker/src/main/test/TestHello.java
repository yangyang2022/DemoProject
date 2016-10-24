import com.yangyang.model.Employer;
import com.yangyang.util.FreeMarkerUtil;
import freemarker.ext.dom.NodeModel;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import freemarker.template.TemplateModelException;
import org.junit.Before;
import org.junit.Test;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TestHello {

    public TestHello() throws TemplateModelException {
    }

    @Test
    public void testDemo1() {

        try {
            //1: 获取 配置信息
            Configuration cfg = new Configuration(Configuration.VERSION_2_3_23);

            //2: 设置类路径
            cfg.setClassForTemplateLoading(TestHello.class,"/ftl");

            //3: 获取模板文件
            Template temp = cfg.getTemplate("hello.ftl");

            //4: 创建数据文件,类似ognl,使用map来保存文件
            Map<String,Object> root = new HashMap<>();
            root.put("username","洋洋");

            //5: 通过某班和数据文件输出
            temp.process(root,new PrintWriter(System.out));


        } catch (IOException e) {
            e.printStackTrace();
        } catch (TemplateException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testDemo2() {
        try {
            //1: 获取 配置信息
            Configuration cfg = new Configuration(Configuration.VERSION_2_3_23);

            //2: 设置类路径
            cfg.setClassForTemplateLoading(TestHello.class,"/ftl");

            //3: 获取模板文件
            Template temp = cfg.getTemplate("hello2.ftl");

            //4: 创建数据文件,类似ognl,使用map来保存文件
            Map<String,Object> root = new HashMap<>();
            root.put("username","洋洋");

            //5: 通过某班和数据文件输出
            temp.process(root,new FileWriter("C:\\code\\freemarker\\hello.html"));


        } catch (IOException e) {
            e.printStackTrace();
        } catch (TemplateException e) {
            e.printStackTrace();
        }

    }

    private FreeMarkerUtil util = FreeMarkerUtil.getInstance("/ftl");
    private Map<String,Object> root = new HashMap<>();

    private String filePath = "C:\\code\\freemarker\\";
    private String ftlPath = "/test/";

    @Before
    public void testSetUp() {
        root.put("username","洋洋");
    }

    @Test
    public void testDemo3() {
        util.sprint(root,"hello2.ftl");
        util.fprint(root,"hello2.ftl",filePath+"\\hellotest.html\\");
    }

    @Test
    public void testDemo4() {
        Employer emp = new Employer(1,"小李",3);
        root.put("emp",emp);
        util.sprint(root,"01.ftl");
    }

    @Test
    public void testDemo5() {
        Employer emp1 = new Employer(1, "悟空", 17);
        Employer emp2 = new Employer(2, "八戒", 24);
        Employer emp3 = new Employer(3, "唐僧", 54);
        Employer emp4 = new Employer(4, "沙僧", 63);

        List<Employer> emps = Arrays.asList(emp1,emp2,emp3,emp4);

        root.put("emps",emps);

        util.sprint(root,"04.ftl");
        //util.fprint(root,"02.ftl",filePath+"\\01.html");
    }

    @Test
    public void testDemo6() {
        Employer emp1 = new Employer(1, "悟空", 17);
        root.put("emp",emp1);

        util.sprint(root,"05_null.ftl");

    }

    @Test
    public void testDemo7() {
        root.put("test", "你好</br>大家好");

        util.sprint(root,"06.ftl");
        util.fprint(root,"06.ftl",filePath+"03.html");
    }

    @Test
    public void testDemo8() {
        root.put("username", "杨阿姨那个");
        util.sprint(root,"07.ftl");
        util.fprint(root,"07.ftl",filePath+"03.html");
    }

    @Test
    public void testDemo9() {
        root.put("name", "测试select");
        util.sprint(root, "08.ftl");
        util.fprint(root, "08.ftl", filePath + "select.html");
    }

    @Test
    public void testDemo10() {
        Employer emp1 = new Employer(1, "悟空", 17);
        Employer emp2 = new Employer(2, "八戒", 24);
        Employer emp3 = new Employer(3, "唐僧", 54);
        Employer emp4 = new Employer(4, "沙僧", 63);

        List<Employer> emps = Arrays.asList(emp1, emp2, emp3, emp4);

        root.put("emps", emps);

        util.sprint(root, "08.ftl");
        util.fprint(root, "08.ftl", filePath + "select.html");
    }

    private static List<Employer> getEmps(){
        Employer emp1 = new Employer(1, "悟空", 17);
        Employer emp2 = new Employer(2, "八戒", 24);
        Employer emp3 = new Employer(3, "唐僧", 54);
        Employer emp4 = new Employer(4, "沙僧", 63);

        List<Employer> emps = Arrays.asList(emp1, emp2, emp3, emp4);

        return emps;
    }


    @Test
    public void testDemo11() {

        //root.put("emps", getEmps());
        root.put("name","yangyangname");
        util.sprint(root,ftlPath+"01.ftl");

    }

    @Test
    public void testDemo12() throws ParserConfigurationException, SAXException, IOException {
        NodeModel nodeModel = NodeModel.parse(new InputSource(TestHello.class.getResourceAsStream("/fields.xml")));

        root.put("doc",nodeModel);
        util.sprint(root,ftlPath+"03.ftl");
    }

    @Test
    public void testDemo13() throws ParserConfigurationException, SAXException, IOException {
        NodeModel nodeModel = NodeModel.parse(new InputSource(TestHello.class.getResourceAsStream("/fields.xml")));
        NodeModel nodeMode2 = NodeModel.parse(new InputSource(TestHello.class.getResourceAsStream("/objFilter.xml")));

        root.put("fieldDoc", nodeModel);
        root.put("filterDoc", nodeMode2);
        root.put("obj","student");

        util.sprint(root, ftlPath + "stu.ftl");
        util.fprint(root,ftlPath+"stu.ftl",filePath+"\\select.html");

    }

    //pager
    @Test
    public void testDemo14() {
        util.sprint(root, ftlPath + "pager.ftl");
        util.fprint(root,ftlPath+"pager.ftl",filePath+"\\pager.html");
    }
}

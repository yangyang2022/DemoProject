import com.yangyang.model.Classroom;
import com.yangyang.model.Student;
import org.junit.Test;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.StringReader;

public class TestJaxb {

    @Test
    public void testDemo() throws JAXBException { // marshore

        JAXBContext ctx = JAXBContext.newInstance(Student.class);
        Marshaller mars = ctx.createMarshaller();//object -- xml

        Student stu = new Student(1,"zhangsan",22,new Classroom(1,"计算机技术01班",2010));
        mars.marshal(stu,System.out);

    }

    @Test
    public void testDemo2() throws JAXBException {
        String xml = "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?><student><age>22</age><classroom><grade>2010</grade><id>1</id><name>计算机技术01班</name></classroom><id>1</id><name>zhangsan</name></student>\n";
        JAXBContext ctx = JAXBContext.newInstance(Student.class);
        Unmarshaller unMar = ctx.createUnmarshaller();
        Student stu = (Student) unMar.unmarshal(new StringReader(xml));

        System.out.println(stu.getName() +" : "+stu.getClassroom().getName());

    }
}

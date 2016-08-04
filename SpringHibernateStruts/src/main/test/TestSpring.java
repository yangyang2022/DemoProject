import org.junit.Test;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.xml.XmlBeanFactory;
import org.springframework.core.io.ClassPathResource;

import java.io.IOException;

//@ContextConfiguration("/beansSpring.xml")
//@RunWith(SpringJUnit4ClassRunner.class)
public class TestSpring {

    @Test
    public void testDemo1() throws IOException {
        BeanFactory bf = new XmlBeanFactory(new ClassPathResource("/beansSpring.xml"));
        //MyTestBean mybean = (MyTestBean) bf.getBean("testBean");
        //System.out.println(mybean.getStr());
        //Resource rs = new ClassPathResource("/beansSpring.xml");
        //InputStream is = rs.getInputStream();
        //FileOutputStream os = new FileOutputStream(new File("C:\\test.txt"));
        //StreamUtils.copy(is,os);
    }
    
}

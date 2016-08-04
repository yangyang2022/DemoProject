import com.yangyang.beans.TestMethodChange;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class TestUserBean {
    @Test
    public void testDemo() {
        ApplicationContext ctx = new ClassPathXmlApplicationContext("/beans-userTest.xml");
        //GetBeanTest bean = ctx.getBean("testBean",GetBeanTest.class);
        //bean.showMe();
        TestMethodChange cg = ctx.getBean("testMethod", TestMethodChange.class);
        cg.changeMe();

    }
}

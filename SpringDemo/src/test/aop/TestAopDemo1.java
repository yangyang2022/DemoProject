package aop;

import com.yangyang.aop.impl.ICalculator;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class TestAopDemo1 {
    private ApplicationContext ctx = null;

    @Before
    public void testSetup() {
        ctx = new ClassPathXmlApplicationContext("beans-aop.xml");
    }

    @Test
    public void testDemo1() {

        ICalculator calc = ctx.getBean(ICalculator.class);

        int result = calc.add(10,2);
        System.out.println("result: "+result);
        //int result = calc.div(10,0);
        //System.out.println("result: "+result);

    }
}

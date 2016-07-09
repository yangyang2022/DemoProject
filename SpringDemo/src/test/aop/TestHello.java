package aop;

import com.yangyang.aop.helloworld.Calculator;
import com.yangyang.aop.helloworld.CalculatorProxy;
import com.yangyang.aop.helloworld.ICalculator;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class TestHello {
    private ApplicationContext ctx = null;

    @Before
    public void testSetup() {
        ctx = new ClassPathXmlApplicationContext("beans-aop.xml");
    }

    @Test
    public void testDemo1() {
        ICalculator calu = new Calculator();
        int result = calu.add(10,10);
        System.out.println("result: "+result);
        result = calu.div(10,2);
        System.out.println("result: "+result);

    }

    @Test
    public void testProxy() {
        ICalculator target = new Calculator();
        ICalculator proxy = new CalculatorProxy(target).getProxy();
        int result = proxy.add(10,10);
        System.out.println("result: "+result);
        result = proxy.div(10,3);
        System.out.println("result: "+result);

    }
}

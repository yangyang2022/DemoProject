package springTest;

import com.yangyang.beanScanner.InvokeHolder;
import com.yangyang.beanScanner.Invoker;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/beans.xml")
public class TestDemo {

    @Test
    public void testDemo1() {
        Invoker invoker = InvokeHolder.getInvoker((short) 1, (short) 2);
        invoker.invoke(null);
    }

    @Test
    public void testDemo2() {
        
    }
}

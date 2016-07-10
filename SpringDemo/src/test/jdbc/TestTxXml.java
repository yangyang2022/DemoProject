package jdbc;

import com.yangyang.txxml.service.IBookShopService;
import com.yangyang.txxml.service.ICashier;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;
import java.util.Arrays;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/beans-jdbc-tx-xml.xml")
public class TestTxXml {

    @Resource
    private IBookShopService bookShopService;

    @Resource
    private ICashier cashier;

    @Test
    public void testDemo() {
        bookShopService.buy("AA","1001");
    }

    @Test
    public void testDemo2() {
        cashier.checkOut("AA", Arrays.asList("1001","1002"));
    }
}

package jdbc;

import com.yangyang.tx.BookShopDao;
import com.yangyang.tx.IBookShopService;
import com.yangyang.tx.ICashier;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;
import java.util.Arrays;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/beans-jdbc-tx.xml")
public class TestTx {

    @Resource
    private BookShopDao bookShopDao;
    @Resource
    private IBookShopService bookShopService;

    @Resource
    private ICashier cashier;

    @Test
    public void testDemo1() {
        //int prince = bookShopDao.findBookPriceByIsbn("1001");
        //System.out.println("price: "+prince);

        //bookShopDao.updateBookStock("1001");

        //bookShopDao.updateUserAccount("AA",100);
        //System.out.println(bookShopDao.getStockFromIsbn("1001"));
        //System.out.println(bookShopDao.getAccountFromUser("AA"));

        //bookShopService.buy("AA","1001");
        cashier.checkOut("AA", Arrays.asList("1001","1002"));
    }
}

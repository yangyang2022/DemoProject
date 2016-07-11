package springHibernate;

import com.yangyang.hibernate.dao.IBookShopDao;
import com.yangyang.hibernate.service.IBookShopService;
import com.yangyang.hibernate.service.ICashier;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;
import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.Arrays;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/beans-hibernate.xml")
public class TestDemo {
    @Resource
    private DataSource dataSource;

    @Resource
    private IBookShopDao bookShopDao;

    @Resource
    private IBookShopService bookShopService;

    @Resource
    private ICashier cashier;

    @Test
    public void testDataSource() throws SQLException {
        System.out.println(dataSource.getConnection());
    }

    @Test
    public void testDemo2() {
        //int price = bookShopDao.findBookPriceByIsbn("1001");
        //System.out.println("price: "+price);

        //bookShopService.buy("AA","1001");
        cashier.checkOut("AA", Arrays.asList("1001","1002"));

    }
}

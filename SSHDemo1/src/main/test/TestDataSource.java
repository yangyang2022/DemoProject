import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;
import javax.sql.DataSource;
import java.sql.SQLException;

@ContextConfiguration("/beans.xml")
@RunWith(SpringJUnit4ClassRunner.class)
public class TestDataSource {

    @Resource
    private DataSource dataSoucre;

    @Test
    public void testDataSoucre() throws SQLException {

        System.out.println(dataSoucre.getConnection());

    }

}

import com.yangyang.model.DepartmentDao;
import com.yangyang.model.EmployeeDao;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/beans.xml")
public class TestDao {

    @Resource
    private EmployeeDao employeeDao;

    @Resource
    private DepartmentDao departmentDao;
    
    @Test
    public void testDemo() {
        //Employee e = employeeDao.get(1001);
        //System.out.println(e.getLastName());

        //Department d = departmentDao.getDepByID(101);
        //System.out.println(d.getDepName());
        //System.out.println(employeeDao);

    }
}

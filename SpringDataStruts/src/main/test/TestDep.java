import com.yangyang.Iservice.IDepService;
import com.yangyang.Iservice.IEmployeeService;
import com.yangyang.model.Department;
import com.yangyang.model.Employee;
import com.yangyang.utils.NameUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Random;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/beans.xml")
public class TestDep {

    @Resource
    private IDepService depService;

    @Resource
    private IEmployeeService employeeService;

    @Test
    public void testDemo() {
        Department dep = new Department("国务院");
        depService.save(dep);

        int pageNum = 2-1;
        int pageSize = 2;
        PageRequest pages = new PageRequest(pageNum,pageSize);

        Page<Department> deps = depService.findAll(pages);
        System.out.println("总记录数: " + deps.getTotalElements());
        System.out.println("当前第几页: " + (deps.getNumber() + 1));
        System.out.println("总页数: " + deps.getTotalPages());
        System.out.println("当前页面的list: " + deps.getContent());
        System.out.println("当前页面的记录数: " + deps.getNumberOfElements());
    }

    @Test
    public void testAddEmployee() {
        Random rand = new Random();
        List<LocalDate> datas = NameUtil.getLocalDate(500);
        List<String> names = NameUtil.getNames(500);
        for (int i = 0; i < 500; ++i) {
            employeeService.save(new Employee(names.get(i),"admin@gmail.com",datas.get(i), LocalDateTime.now(),depService.findOne((1+rand.nextInt(3)))));
        }
    }
}

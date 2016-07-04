import com.yangyang.model.Department;
import com.yangyang.model.Manager;
import com.yangyang.utils.HibernateUtil;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

public class TestOne2One {
    private EntityManager manager;
    private EntityTransaction transcation;
    @Before
    public void testinit() {
        manager = HibernateUtil.getEntityManager();
        transcation = manager.getTransaction();
        transcation.begin();
    }

    @After
    public void testDestroy() {
        transcation.commit();
        manager.close();
    }

    /**
     * 双向 one-2-one 的关联关系~建议先保存 不维护管理的一端
     */
    @Test
    public void testAdd() {
        Manager manager1 = new Manager("老沈");
        Manager manager2 = new Manager("老莫");
        Manager manager3 = new Manager("老海");

        Department dep1 = new Department("爱情公寓1",manager1);
        Department dep2 = new Department("爱情公寓2",manager2);
        Department dep3 = new Department("爱情公寓3",manager3);

        HibernateUtil.save(manager,manager1,manager2,manager3,dep1,dep2,dep3);

    }

    @Test
    public void testFind() {
        //获取 维护关联关系的一端,会使用 left join 获取
        Department dep = manager.find(Department.class, 1);

        System.out.println(dep.getDepetName()+" : "+dep.getManager().getName());
    }
}

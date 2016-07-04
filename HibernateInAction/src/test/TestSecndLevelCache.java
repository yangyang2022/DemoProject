import com.yangyang.model.Custom;
import com.yangyang.utils.HibernateUtil;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

public class TestSecndLevelCache {
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

    @Test
    public void testFind() {
        /**
         * 这里使用的一级缓存,只发出一条sql
         */
        Custom cus = manager.find(Custom.class, 1);
        Custom cus1 = manager.find(Custom.class, 1);
        System.out.println(cus.getName());
        System.out.println(cus1.getName());

    }
    @Test
    public void testFind2() {
        /**
         * 这里使用的一级缓存,只发出一条sql
         */
        Custom cus = manager.find(Custom.class, 1);
        System.out.println(cus.getName());
        transcation.commit();

        manager = HibernateUtil.getEntityManager();
        transcation = manager.getTransaction();
        transcation.begin();
        Custom cus1 = manager.find(Custom.class, 1);
        System.out.println(cus1.getName());

    }
}

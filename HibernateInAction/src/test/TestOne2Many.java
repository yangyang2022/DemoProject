import com.yangyang.model.Custom;
import com.yangyang.model.Order;
import com.yangyang.utils.HibernateUtil;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import java.util.Date;

public class TestOne2Many {

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
    public void testAdd() {
        Custom cus = new Custom("洋哥2","yangyang@qq.com",23,new Date(),new Date());
        Order order1 = new Order("order1");
        Order order2 = new Order("order2");
        Order order3 = new Order("order3");

        cus.getOrders().add(order1);
        cus.getOrders().add(order2);
        cus.getOrders().add(order3);
        //"一"的一端维护关系
        HibernateUtil.save(manager,cus,order1,order2,order3);

    }

    @Test
    public void testFind() {
        Custom cus = manager.find(Custom.class, 1);
        System.out.println(cus.getName());
        cus.getOrders().forEach(e-> System.out.println(e.getOrderName()));
    }

    @Test
    public void testRemove() {
        /**
         * 默认删除"一"的一端,先把"多"的一端置空,然后删除
         */
        //加上 cascade = CascadeType.REMOVE 就会级联删除
        Custom cus = manager.find(Custom.class, 2);
        manager.remove(cus);

    }
    
}

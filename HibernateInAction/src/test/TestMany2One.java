import com.yangyang.model.Custom;
import com.yangyang.model.Order;
import com.yangyang.utils.HibernateUtil;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import java.util.Date;

public class TestMany2One {
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
        Order order1 = new Order("order1 ",cus);
        Order order2 = new Order("order2 ",cus);
        Order order3 = new Order("order3 ",cus);

        HibernateUtil.save(manager,cus,order1,order2,order3);
    }

    @Test
    public void testFind() {
        /**
         * 默认情况下使用左外连接方式获取 "多"的对象和其"一"的一端
         * 在many-2-one(fetch=lazy) 就会发出两条sql语句
         */
        Order order = manager.find(Order.class,1);
        //System.out.println(order.getOrderName()+" --> "+order.getCustom().getName());

    }

    @Test
    public void testRemove() {
        Order order = manager.find(Order.class, 1);
        manager.remove(order);

        //不能删除 "一" 的一端
        //Custom cus = manager.find(Custom.class,8);
        //manager.remove(cus);
    }

    @Test
    public void testUpdate() {
        Order order = manager.find(Order.class, 2);
        //order.getCustom().setName("fff");
    }
}

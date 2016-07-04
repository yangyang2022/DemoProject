import com.yangyang.model.Custom;
import com.yangyang.utils.HibernateUtil;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.Date;

public class Demo {
    private EntityManager manager;
    private EntityTransaction transcation;

    @Test
    public void testDemo1() {
        //1 创建EntiyManagerFactory
        String persistanceUnitName = "jpa-1";
        EntityManagerFactory entityFactory = Persistence.createEntityManagerFactory(persistanceUnitName);

        //2 创建EntiyManager

        EntityManager entityManager = entityFactory.createEntityManager();

        //3 开启事物
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();

        //4 进行持久化操作
        Custom customer = new Custom();
        customer.setAge(12);
        customer.setName("洋洋2");
        customer.setEmail("123@qq.com");
        customer.setBorn(new Date());
        customer.setCreateTime(new Date());

        entityManager.persist(customer);

        //5 提交事务
        transaction.commit();

        //6 关闭EntiyManager
        entityManager.close();

        //7 关闭EntiyManagerFactory
        entityFactory.close();
    }


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
    public void testDemo2() {
        EntityManager manger = HibernateUtil.getEntityManager();
        EntityTransaction transaction = null;
        try {
            transaction = manger.getTransaction();
            transaction.begin();

            Custom customer = new Custom();
            customer.setAge(12);
            customer.setName("admin");
            customer.setEmail("123@qq.com");
            customer.setBorn(new Date());
            customer.setCreateTime(new Date());

            manger.persist(customer);

            transaction.commit();
        } catch (Exception e) {
            transaction.rollback();
            e.printStackTrace();
        } finally {
            HibernateUtil.closeEntity(manger);
        }
    }


    //like hibernate saveOrUpdate
    @Test
    public void testMerge1() {
        //若传入的是一个临时对象,把临时对象(cus) 复制 到新对象(rcus)中,持久化新对象(rcus)
        
        Custom cus = new Custom("洋哥1","163@qq.com",22,new Date(),new Date());

        Custom rcus = manager.merge(cus);
        System.out.println(cus.getId()+ " : "+rcus.getId());
    }
    //若传入的对象是游离对象,若database没有该对象,则按照第一个merge 创建,
    //如果database有该记录,jpa则查询该记录,返回该对象,最后update该对象
    @Test
    public void testMerge2() {
        Custom cus = new Custom("洋哥xxx","12314@qq.com",22,new Date(),new Date());
        cus.setId(6);
        Custom rcus = manager.merge(cus);
        System.out.println(cus.getId()+ " : "+rcus.getId());
    }

    @Test
    public void testMerge3() {
        Custom cus = new Custom("洋哥merge","merge@qq.com",22,new Date(),new Date());
        cus.setId(6);
        Custom rcus = manager.find(Custom.class,6);
        manager.merge(cus);
        //注意 此时缓存中有两个id(相同)的两个对象,这在hibernate中不允许
    }

    //like hibernate delete,从数据库移除对象
    //但是: 该方法只能移除 持久化对象,hibernate还可以移除游离对象
    @Test
    public void testremove() {
        // ----> error
        //Custom cus = new Custom();
        //cus.setId(1);
        //manager.remove(cus);

        Custom cus = manager.getReference(Custom.class,1);
        manager.remove(cus);

    }
    //persistence like save,但是不能设置id,否则报错,但是hibernate可以设置设置id
    @Test
    public void testPersistence() {
        Custom cus = new Custom("洋哥","163@qq.com",22,new Date(),new Date());
        manager.persist(cus);
    }
    @Test
    public void testDemo3() {
        // find like hibernate get
        //Custom cus = manager.find(Custom.class,1);

        //getReference like hibernate load
        Custom cus = manager.getReference(Custom.class,1);
        
        System.out.println("------------------------");
        System.out.println(cus.getClass().getName());
        System.out.println(cus.getInfo());

    }

    @Test
    public void testFlush() {
        Custom cus = manager.find(Custom.class, 3);
        cus.setName("习近平");
        //manager.flush();发出sql语句,但是没有提交事务,数据库没有跟新
        System.out.println("===============================");
    }
}

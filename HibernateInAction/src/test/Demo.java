import com.yangyang.model.Custom;
import org.junit.Test;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.Date;

public class Demo {
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
        customer.setName("洋洋");
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
}

import com.yangyang.model.Custom;
import com.yangyang.model.Order;
import com.yangyang.utils.HibernateUtil;
import org.hibernate.annotations.QueryHints;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import java.util.List;
import java.util.function.Consumer;

public class TestJPQL {

    private EntityManager manager;
    private EntityTransaction transcation;
    private Consumer<Custom> print = e -> System.out.println(e.getName());

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
    public void testJPQLDemo1() {
        //String sql = "from Custom c where c.age >:age";
        //Query query = manager.createQuery(sql);
        ////query.setParameter(1,1);
        //query.setParameter("age",1);
        //List<Custom> cus = query.getResultList();
        //System.out.println(cus.size());

        List<Custom> cus = (manager.createQuery("select c from Custom c where c.age > :age"))
                .setParameter("age", 1).getResultList();
        System.out.println(cus.size());
        cus.forEach(print);
    }

    @Test
    public void testSQLPart() {
        String jpql = "select c.name,c.age from Custom c where c.id>:id";
        List<Object[]> cus = manager.createQuery(jpql).setParameter("id", 1).getResultList();
        for (Object[] obj : cus) {
            System.out.println(obj[0] + " -- " + obj[1]);
        }
    }

    @Test
    public void testNameQuery() {
        Custom cus = (Custom) manager.createNamedQuery("selectCus")
                .setParameter("id", 2).getResultList().get(0);
        System.out.println(cus);
    }

    @Test
    public void testNativeQuery() {
        String sql = "select age from t_custom where id=?";
        Integer cus = (Integer) manager.createNativeQuery(sql).setParameter(1, 1).getSingleResult();
        System.out.println(cus);

    }

    @Test
    public void testQueryCache() {
        List<Custom> cus = (manager.createQuery("from Custom c where c.age > :age"))
                .setHint(QueryHints.CACHEABLE, true) //设置查询缓存
                .setParameter("age", 1).getResultList();
        System.out.println(cus.size());
        cus.forEach(print);
        System.out.println("=====================================");
        cus = (manager.createQuery("from Custom c where c.age > :age"))
                .setHint(QueryHints.CACHEABLE, true) //设置查询缓存
                .setParameter("age", 1).getResultList();
        System.out.println(cus.size());
        cus.forEach(print);

    }

    @Test
    public void testOrderBy() {
        /**
         * 可以使用 order by ....
         */
        List<Custom> cus = (manager.createQuery("from Custom c where c.age > :age order by c.age DESC"))
                .setHint(QueryHints.CACHEABLE, true) //设置查询缓存
                .setParameter("age", 1).getResultList();
        System.out.println(cus.size());
        cus.forEach(print);
    }

    @Test
    public void testGroupby() {
        /**
         * 查询order的数量大于2的custom
         * 可以使用 group by and count having
         */
        String jpql = "select o.custom from Order o group by o.custom having count(o.id)>=2 ";
        List<Custom> cus = manager.createQuery(jpql).getResultList();
        cus.forEach(print);
    }

    @Test
    public void testLeftJoin() {
        String jpql = "select c from Custom c left outer join fetch c.orders where c.id=:id ";
        Custom cus = (Custom) manager.createQuery(jpql).setParameter("id",1).getSingleResult();
        System.out.println(cus.getName());
        System.out.println(cus.getOrders().size());

    }

    @Test
    public void testSubQuery() {
        //查询所有 custum 的 name 为xx的所有items
        String jpql = "select o from Order o where o.custom = (select c from Custom c where c.name = :cname)";
        List<Order> orders = manager.createQuery(jpql).setParameter("cname","yangyang")
                .getResultList();
        System.out.println(orders.size());
        orders.forEach(e-> System.out.println(e.getOrderName()));
    }

    @Test
    public void testJPQLfuction() {
        /**
         * jpql也支持聚合函数
         * concate upper lower length locate(s1,s2 )
         * Math: abs mod sqrt size(求聚合元素的个数)
         * Date: current_date current_time current_timestamp
         * 返回服务器上的时间
         */
        String jpql = "select upper(c.email) from Custom c";
        List<String> cs = manager.createQuery(jpql).getResultList();
        cs.forEach(e-> System.out.println(e));

    }

    @Test
    public void testUpdate() {
        /**
         * 可以使用 jpql 完成update and delete
         */
        String jpql = "update Custom c set c.name=:name where c.id = :id";
        manager.createQuery(jpql)
                .setParameter("name","席金鸥").setParameter("id",1)
                .executeUpdate();
    }
}
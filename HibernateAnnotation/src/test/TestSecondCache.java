import com.yangyang.HQL.Student;
import com.yangyang.Utils.HibernateUtil;
import com.yangyang.model.User;
import org.hibernate.Session;
import org.junit.Test;

import java.util.Iterator;
import java.util.List;

import static com.sun.org.apache.xml.internal.security.keys.keyresolver.KeyResolver.iterator;

public class TestSecondCache {
    /**
     * 一级缓存 仅仅在session中 ,session一关闭就失效了
     * 二级缓存 是sessionFactory级别的,需要配置文件里面开启
     */
    @Test
    public void testDemo() {
        Session session = null;
        try {
            session = HibernateUtil.openSession();
            session.beginTransaction();

            //List<Student> stus = session.createQuery("from Student ")
            //        .setFirstResult(0).setMaxResults(50)
            //        .list();
            //Iterator<Student> is = stus.iterator();
            //for(;is.hasNext();){
            //    Student s = is.next();
            //    System.out.println(s.getName() + " : " + s.getSex());
            //}
            Student stu = session.load(Student.class,1);
            System.out.println(stu.getName());

            session.getTransaction().commit();
        } catch (Exception e) {
            session.getTransaction().rollback();
            e.printStackTrace();
        } finally {
            HibernateUtil.closeSession(session);
        }
        try {
            session = HibernateUtil.openSession();
            Student stu = session.load(Student.class,1);
            System.out.println(stu.getName());

        } catch (Exception e) {

        } finally {
            HibernateUtil.closeSession(session);
        }
    }

    @Test
    public void testDemo2() {
        Session session = HibernateUtil.openSession();
        session.beginTransaction();
        List<Student> users1 = session.createQuery("from Student").setCacheable(true)
                .setMaxResults(0).setMaxResults(50)
                .list();
        for(Student user : users1) {
            System.out.println(user.getName());
        }
        session.getTransaction().commit();
        session.close();

        Session session2 = HibernateUtil.openSession();
        session2.beginTransaction();


        List<Student> users2 = session2.createQuery("from Student").setCacheable(true)
                .setMaxResults(0).setMaxResults(50)
                .list();
        for(Student user : users2) {
            System.out.println(user.getName());
        }
        session2.getTransaction().commit();
        session2.close();
    }

    @Test
    public void testDemo3() {
        Session session = null;
        List<Student> stus = null;
        try {
            session = HibernateUtil.openSession();
            session.beginTransaction();
            /**
             * 二级缓存 缓存的是对象 ....
             * 查询缓存 缓存的是sql 语句 ,是id 不是对象
             */
            //List<Object[]> stus = session.createQuery("select stu.id,stu.name from Student stu")
            //        .setFirstResult(0).setMaxResults(20)
            //        .list();
             stus = session.createQuery(" from Student")
                    .setFirstResult(0).setMaxResults(50).list();
            session.getTransaction().commit();
        } catch (Exception e) {
            session.getTransaction().rollback();
            e.printStackTrace();
        } finally {
            HibernateUtil.closeSession(session);
        }
        session  = HibernateUtil.openSession();

        /***
         * 所有对象 都存在二级缓存中 ,只需取出id 即可去除所有 的对象
         * 这就是 iterator 的存在的意义
         * 所以 iterator 一般的 二级缓存存在的意义
         */
        Iterator<Student> is = stus.iterator();
        for(;is.hasNext();){
            Student s = is.next();
            System.out.println(s.getName());
        }
        //Student stu = session.load(Student.class,1);
        //Student stu2 = session.load(Student.class,2);
        //System.out.println(stu.getName()+" --> "+stu2.getName());
    }

    /**
     * 查询缓存也是sessionFactory级别的
     * 但是有2个缺点:
     *  1: 只要两个sql语句不一样就不会开启缓存
     *  2: 只有两个sql语句一样,并且参数都一样,才能使用查询缓存
     *  3: 使用查询缓存 必须开启 二级缓存 ,否则发出很多sql
     */
    @Test
    public void testDemo4() {
        Session session = null;
        try {
            session = HibernateUtil.openSession();
            session.beginTransaction();

            List<Student> stus = session.createQuery("from Student")
                    .setCacheable(true)//查询缓存也是sessionFactory级别的
                    .setFirstResult(0).setMaxResults(10)
                    .list();
            Iterator<Student> is = stus.iterator();
            while (is.hasNext()){
                Student s = is.next();
                System.out.println(s.getName());
            }
            stus = session.createQuery("from Student")
                    .setCacheable(true)//查询缓存也是sessionFactory级别的
                    .setFirstResult(0).setMaxResults(10)
                    .list();
            is = stus.iterator();

            //这里会发出2条sql语句(一样的sql)
            System.out.println("========================================");
            while (is.hasNext()){
                Student s = is.next();
                System.out.println(s.getName());
            }
            session.getTransaction().commit();
        } catch (Exception e) {
            session.getTransaction().rollback();
            e.printStackTrace();
        } finally {
            HibernateUtil.closeSession(session);
        }
    }
}

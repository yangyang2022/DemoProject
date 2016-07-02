import com.yangyang.HQL.Student;
import com.yangyang.Utils.HibernateUtil;
import org.hibernate.Session;
import org.junit.Test;

import java.util.Iterator;
import java.util.List;

import static jdk.nashorn.internal.objects.Global.load;

public class TestCache {
    @Test
    public void testDemo1() {
        Session session = null;
        try {
            session = HibernateUtil.openSession();
            session.beginTransaction();

            // 此时 会发出一条 sql
            List<Student> stus = session.createQuery("from Student")
                    .setFirstResult(0).setMaxResults(50)
                    .list();
            stus.forEach(e-> System.out.println(e.getName()));

            session.getTransaction().commit();
        } catch (Exception e) {
            session.getTransaction().rollback();
            e.printStackTrace();
        } finally {
            HibernateUtil.closeSession(session);
        }
    }

    @Test
    public void testDemo2() {
        Session session = null;
        try {
            session = HibernateUtil.openSession();
            session.beginTransaction();

            /**
             * 如果使用 iterator 返回列表,对于hibernate而言 ,他仅仅发出取id列表的sql
             * 在取出相应的学生的时候又发出sql
             *  N+1 问题: 先发出一条sql查询id,然后取每一个对象又重新发出一条sql
             *
             */
            Iterator<Student> stus = session.createQuery("from Student")
                    .setFirstResult(0).setMaxResults(50).iterate();
            for(;stus.hasNext();){
                Student stu = stus.next();
                System.out.println(stu.getName());
            }
            session.getTransaction().commit();
        } catch (Exception e) {
            session.getTransaction().rollback();
            e.printStackTrace();
        } finally {
            HibernateUtil.closeSession(session);
        }
    }

    @Test
    public void testDemo3() {
        Session session = null;
        try {
            session = HibernateUtil.openSession();
            session.beginTransaction();

            /**
             * 使用iterator 存在session一级缓存中
             */
            Iterator<Student> stus = session.createQuery("from Student")
                    .setFirstResult(0).setMaxResults(50).iterate();
            for(;stus.hasNext();){
                Student stu = stus.next();
                System.out.println(stu.getName());
            }

            stus = session.createQuery("from Student")
                    .setFirstResult(0).setMaxResults(50).iterate();
            for(;stus.hasNext();){
                Student stu = stus.next();
                System.out.println(stu.getName());
            }
            session.getTransaction().commit();
        } catch (Exception e) {
            session.getTransaction().rollback();
            e.printStackTrace();
        } finally {
            HibernateUtil.closeSession(session);
        }
    }

    @Test
    public void testDemo4() {
        Session session = null;
        try {
            session = HibernateUtil.openSession();
            session.beginTransaction();

            List<Student> stus = session.createQuery("from Student")
                    .setFirstResult(0).setMaxResults(50)
                    .list();
            stus.forEach(e-> System.out.println(e.getName()));
            //由于在缓存中已经存在值,所以一下直接使用itearator查询id即可
            Iterator<Student> is = stus.iterator();
            for(;is.hasNext();){
                Student s = is.next();
                System.out.println(s.getName());
            }

            // 一级缓存 在session中,只要session不关闭就行了 ...
            //session一关闭就重新取数据
            Student loads = session.load(Student.class,1);
            System.out.println(loads.getName());

            session.getTransaction().commit();
        } catch (Exception e) {
            session.getTransaction().rollback();
            e.printStackTrace();
        } finally {
            HibernateUtil.closeSession(session);
        }
    }
}

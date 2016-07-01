import com.yangyang.HQL.Special;
import com.yangyang.HQL.Student;
import com.yangyang.Utils.HibernateUtil;
import org.hibernate.Session;
import org.junit.Test;

import java.util.List;

public class TestHqlDemo {
    @Test
    public void test() {
        Session session = null;
        try {
            session = HibernateUtil.openSession();
            session.beginTransaction();



            session.getTransaction().commit();
        } catch (Exception e) {
            session.getTransaction().rollback();
            e.printStackTrace();
        } finally {
            HibernateUtil.closeSession(session);
        }
    }

    @Test
    public void testDemo1() {
        Session session = null;
        try {
            session = HibernateUtil.openSession();

            List<Special> sps = session.createQuery("from Special").list();
            sps.forEach(e-> System.out.println(e.getName()+" : "+e.getType()));

        } catch (Exception e) {
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

            //List<Student> sps = session.createQuery("from Student where name like '%沈%'").list();
            List<Student> sps = session.createQuery("from Student where name like ?")
                    .setParameter(0,"%沈%")
                    .list();
            sps.forEach(e-> System.out.println(e.getName()));

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            HibernateUtil.closeSession(session);
        }
    }
}

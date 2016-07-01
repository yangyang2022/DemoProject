
import com.yangyang.Utils.HibernateUtil;
import org.hibernate.Session;
import org.junit.Test;

public class TestHQL {
    @Test
    public void testOne() {
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
}

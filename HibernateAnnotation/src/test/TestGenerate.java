import com.yangyang.Utils.HibernateUtil;
import com.yangyang.model.Book;
import org.hibernate.Session;
import org.junit.Test;

public class TestGenerate {
    @Test
    public void testAssigned() {
        Session session = null;
        try {
            session = HibernateUtil.openSession();
            session.beginTransaction();
            Book book = new Book("think in c++",22.22);

            session.save(book);

            session.getTransaction().commit();
        } catch (Exception e) {
            session.getTransaction().rollback();
            e.printStackTrace();
        } finally {
            HibernateUtil.closeSession(session);
        }
    }
}

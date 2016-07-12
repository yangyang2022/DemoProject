import com.yangyang.model.Message;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.service.ServiceRegistry;
import org.junit.Before;
import org.junit.Test;

public class TestHibernate {
    private SessionFactory sessionFactory = null;
    @Before
    public void setup() {
        ServiceRegistry sevice = new StandardServiceRegistryBuilder().configure("hibernate.cfg.xml").build();
        sessionFactory = new MetadataSources(sevice).buildMetadata().buildSessionFactory();
    }

    @Test
    public void testDemo1() {
        Session session = sessionFactory.openSession();
        session.beginTransaction();

        Message message = new Message();
        message.setText("习近平1");
        session.save(message);

        session.getTransaction().commit();
    }
}

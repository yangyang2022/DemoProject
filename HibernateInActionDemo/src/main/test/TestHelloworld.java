import com.yangyang.model.Message;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import java.util.ArrayList;
import java.util.List;

import static com.yangyang.util.HibernateUtil.getEntityManager;
import static com.yangyang.util.HibernateUtil.save;

public class TestHelloworld {

    private EntityManager em = null;
    private EntityTransaction transcation = null;
    @Before
    public void testSetup() {

        em = getEntityManager();
        transcation = em.getTransaction();
        transcation.begin();
    }

    @After
    public void testTeardown() {
        transcation.commit();
        em.close();
    }
    @Test
    public void testHello() {

        Message msg = new Message();
        msg.setText("helloworld");
        List<Message> msgs = new ArrayList<>();
        for (int i = 0; i < 100; ++i) {
            msg = new Message();
            msg.setText("messgae "+i);
            msgs.add(msg);
        }
        msgs.forEach(e->save(em,e));
    }

    @Test
    public void testLoadDemo1() {
        List<Message> msgs = em.createQuery("select m from Message m ").getResultList();
        System.out.println(msgs.size());
        msgs.get(0).setText("hello yangyang");
    }
}

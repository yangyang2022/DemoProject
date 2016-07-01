import com.yangyang.Utils.HibernateUtil;
import com.yangyang.model.Admin;
import com.yangyang.model.Role;
import org.hibernate.Session;
import org.junit.Test;

public class TestMany2Many {
    /**
     * 使用 many-2-many ,无论在哪一方维护关系都比较麻烦
     * 所以在实际开发中经常使用 两个一对多的
     *
     */
    @Test
    public void testAdd() {
        Session session = null;
        try {
            session = HibernateUtil.openSession();
            session.beginTransaction();

            Admin admin = new Admin();
            admin.setName("张三");
            Admin admin1 = new Admin();
            admin1.setName("李四");
            session.save(admin);session.save(admin1);

            Role r1 = new Role();
            r1.setName("超级管理员");
            Role r2 = new Role();
            r2.setName("财务管理员");

            r1.addAmin(admin);
            r1.addAmin(admin1);

            r2.addAmin(admin);
            r2.addAmin(admin1);

            session.save(r1);session.save(r2);



            session.getTransaction().commit();
        } catch (Exception e) {
            session.getTransaction().rollback();
            e.printStackTrace();
        } finally {
            HibernateUtil.closeSession(session);
        }
    }

    @Test
    public void testLoad() {
        Session session = null;
        try {
            session = HibernateUtil.openSession();
            session.beginTransaction();

            Admin a = session.load(Admin.class,3);
            System.out.println(a.getName());
            a.getRoles().forEach(e-> System.out.println(e.getName()));


            session.getTransaction().commit();
        } catch (Exception e) {
            session.getTransaction().rollback();
            e.printStackTrace();
        } finally {
            HibernateUtil.closeSession(session);
        }
    }
}

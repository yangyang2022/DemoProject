import com.yangyang.HQL.Student;
import com.yangyang.Utils.HibernateUtil;
import org.hibernate.Session;
import org.junit.Test;

import java.util.List;

public class TestSql {
    @Test
    public void testDemo1() {
        Session session = null;
        try {
            session = HibernateUtil.openSession();
            session.beginTransaction();

            List<Student> stus = session.createNativeQuery("select * from t_stu where name like ? ")
                    .addEntity(Student.class)
                    .setParameter(1,"%张%")
                    .setFirstResult(0).setMaxResults(10)
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
}

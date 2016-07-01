import com.yangyang.Utils.HibernateUtil;
import com.yangyang.model.ClassRoom;
import com.yangyang.model.Student;
import org.hibernate.Session;
import org.junit.Test;

public class TestMany2One {
    @Test
    public void testAdd() {
        Session session = null;
        try {
            session = HibernateUtil.openSession();
            session.beginTransaction();

            //一般 先添加 1 再添加 多
            ClassRoom room2 = new ClassRoom("计算机信息管理",2011);
            session.save(room2);

            Student stu1 = new Student("如来","005",room2);
            Student stu2 = new Student("观音","006",room2);
            session.save(stu1);
            session.save(stu2);

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

            // 这里发出 2条sql ,由于lazy造成的
            //使用 jpa 就只发一条 sql 配置 fetch = FetchType.LAZY,就起作用了
            Student stu = session.load(Student.class,3);
            System.out.println(stu.getName()+" --> "+stu.getClassRoom().getName());

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            HibernateUtil.closeSession(session);
        }
    }

    @Test
    public void testLoad2() {
        Session session = null;
        try {
            session = HibernateUtil.openSession();

            ClassRoom clz = session.load(ClassRoom.class,1);
            System.out.println(clz.getStudents().size());

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            HibernateUtil.closeSession(session);
        }
    }
    @Test
    public void testDelete() {
        Session session = null;
        try {
            session = HibernateUtil.openSession();
            session.beginTransaction();

            Student stu = session.load(Student.class,6);
            session.delete(stu);

            session.getTransaction().commit();
        } catch (Exception e) {
            session.getTransaction().rollback();
            e.printStackTrace();
        } finally {
            HibernateUtil.closeSession(session);
        }
    }
}

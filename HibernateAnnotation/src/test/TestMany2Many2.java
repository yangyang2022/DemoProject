import com.yangyang.Utils.HibernateUtil;
import com.yangyang.model.Course;
import com.yangyang.model.Teacher;
import com.yangyang.model.TeacherCourse;
import org.hibernate.Session;
import org.junit.Test;

public class TestMany2Many2 {

    @Test
    public void testAdd() {
        Session session = null;
        try {
            session = HibernateUtil.openSession();
            session.beginTransaction();

            Teacher t1 = new Teacher();
            t1.setName("老张");
            Teacher t2 = new Teacher();
            t2.setName("老牛");

            session.save(t1);session.save(t2);

            Course c1 = new Course();
            c1.setName("数据结构");
            Course c2 = new Course();
            c2.setName("计算机组成原理");

            session.save(c1);session.save(c2);

            TeacherCourse tc1 = new TeacherCourse();
            tc1.setScore(66.66);
            tc1.setTeacher(t1);tc1.setCourse(c1);
            session.save(tc1);

            tc1 = new TeacherCourse();
            tc1.setScore(78.9);
            tc1.setTeacher(t1);tc1.setCourse(c2);
            session.save(tc1);

            tc1 = new TeacherCourse();//老李的 数据结构 98 分
            tc1.setScore(67);
            tc1.setTeacher(t2);tc1.setCourse(c1);
            session.save(tc1);

            tc1 = new TeacherCourse(); //老李的 机原 98 分
            tc1.setScore(98);
            tc1.setTeacher(t2);tc1.setCourse(c1);
            session.save(tc1);

            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            session.getTransaction().rollback();
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

            /**
             * load 的时候由于lazy 会根据不同的情况 取出不同的数据,会发出很多 语句
             *  最佳实践: 一般不使用双向关联,特别不建议 "1"的这一方关联
             */
            Teacher t1 = session.load(Teacher.class,1);
            System.out.println(t1.getName());

            t1.getTcs().forEach(e-> System.out.println(e.getCourse().getName()+":"+e.getScore()));

            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            session.getTransaction().rollback();
        } finally {
            HibernateUtil.closeSession(session);
        }
    }
}

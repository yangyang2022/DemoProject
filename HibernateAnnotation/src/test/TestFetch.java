import com.yangyang.HQL.ClassRoom;
import com.yangyang.HQL.Student;
import com.yangyang.Utils.HibernateUtil;
import org.hibernate.Session;
import org.junit.Test;

import java.util.List;

public class TestFetch {
    @Test
    public void testDemo1() {
        Session session = null;
        try {
            session = HibernateUtil.openSession();
            session.beginTransaction();

            /**
             * 默认情况会发出 3条sql语句,每一条sql取相应的对象
             * 但是 annotation 做了优化,xml 没有
             * 缺点: 在xml中设置抓取策略,但是不取其他对象时也会抓取
             */
            Student stu = session.load(Student.class,1);
            System.out.println(stu.getName() + " : " + stu.getClassRoom().getName() + " : "
                   );

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
             * 以下是 xml
             * 使用了 fetch 就不能使用count
             * 如果要使用count 格式化sql fetch->""
             *
             * 这里是多的一方 抓取
             *
             * 在 annotation ,默认的many-2-one的抓取策略是eger ,
             * 当抓取 student 时会发出很多sql 抓取classroom 和 special
             *
             * 方法 1: 直接 一直 left join,会发出一条sql ,但是项目大时不适用
             * 方法 2: 将关联对象设置为lazy
             *
             *  lazy -> select
             *  eger -> join
             */
            List<Student> stus = session.createQuery("select stu from Student stu join fetch stu.classRoom clz join fetch clz.speacil").list();
            stus.forEach(e-> System.out.println(e.getName()+" : "+e.getClassRoom().getName()));

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
             *  针对 "1" 的一方抓取,这里是双向关联时使用,但是很少使用
             *
             *
             */
            //ClassRoom clz = session.load(ClassRoom.class,1);
            ////以下 需要两条sql
            //System.out.println(clz.getName());
            //clz.getStudents().forEach(e-> System.out.println(e.getName()));

            //取一组班级 对于xml来说 会发出很多sql
            List<ClassRoom> clz = session.createQuery("from ClassRoom").list();
            clz.forEach(e-> System.out.println(e.getName()));

            session.getTransaction().commit();
        } catch (Exception e) {
            session.getTransaction().rollback();
            e.printStackTrace();
        } finally {
            HibernateUtil.closeSession(session);
        }
    }
}

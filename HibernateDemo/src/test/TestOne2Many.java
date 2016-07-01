import com.yangyang.Utils.HibernateUtil;
import com.yangyang.model.ClassRoom;
import com.yangyang.model.Comment;
import com.yangyang.model.Message;
import com.yangyang.model.Student;
import org.hibernate.Session;
import org.junit.Test;

import java.util.HashSet;
import java.util.Set;

public class TestOne2Many {
    @Test
    public void testAdd() {
        Session session = null;
        try {
            session = HibernateUtil.openSession();
            session.beginTransaction();

            Comment c1 = new Comment("comment 1 ");
            Comment c2 = new Comment("comment 2 ");
            Comment c3 = new Comment("comment 3 ");

            session.save(c1);session.save(c2);
            session.save(c3);
            Message msg = new Message("msg Title1","msg content1");

            msg.addComment(c1);msg.addComment(c2);
            msg.addComment(c3);

            session.save(msg);

            //注意 此处 发出 7 条sql ,4条插入(必须) 3条更新(不是必须)
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

            Message msg = session.load(Message.class,1);
            
            //System.out.println(msg.getContent());
            //msg.getComments().forEach(e-> System.out.println(e.getContent()));

            //配置 lazy="extra" 就会智能的采取 sql count(*) 来提取数字
            System.out.println("size: "+msg.getComments().size());
            
            //one-2-many 不建议使用 单向
            session.getTransaction().commit();
        } catch (Exception e) {
            session.getTransaction().rollback();
            e.printStackTrace();
        } finally {
            HibernateUtil.closeSession(session);
        }
    }


    // ==================== one-2-many 双向 ====================== 
    @Test
    public void testAdd1() {
        Session session = null;
        try {
            session = HibernateUtil.openSession();
            session.beginTransaction();

            Student stu1 = new Student("猪八戒","2011",null);
            Student stu2 = new Student("孙悟空","2012",null);
            session.save(stu1);session.save(stu2);

            ClassRoom room1 = new ClassRoom("计算机应用技术",2011);
            Set<Student> stus = new HashSet<>();
            stus.add(stu1);stus.add(stu2);
            room1.setStudents(stus);
            session.save(room1);

            //此时也会发出5条sql, 不要使用 "1" 的一方维护关系
            session.getTransaction().commit();
        } catch (Exception e) {
            session.getTransaction().rollback();
            e.printStackTrace();
        } finally {
            HibernateUtil.closeSession(session);
        }

    }
    @Test
    public void testLoadx() {
        Session session = null;
        try {
            session = HibernateUtil.openSession();
            session.beginTransaction();

            ClassRoom c = session.load(ClassRoom.class,3);
            System.out.println(c.getName()+" : "+c.getStudents().size());
            Student stu = session.load(Student.class,6);
            System.out.println(stu.getName()+" : "+stu.getClassRoom().getName());

            session.getTransaction().commit();
        } catch (Exception e) {
            session.getTransaction().rollback();
            e.printStackTrace();
        } finally {
            HibernateUtil.closeSession(session);
        }
    }
    @Test
    public void testAdd2() {
        Session session = null;
        try {
            session = HibernateUtil.openSession();
            session.beginTransaction();

            ClassRoom room1 = new ClassRoom("计算机网络技术",2011);
            session.save(room1);

            Student stu1 = new Student("猪八戒1","2011",room1);
            Student stu2 = new Student("孙悟空1","2012",room1);
            session.save(stu1);session.save(stu2);

            session.getTransaction().commit();
        } catch (Exception e) {
            session.getTransaction().rollback();
            e.printStackTrace();
        } finally {
            HibernateUtil.closeSession(session);
        }
    }
}

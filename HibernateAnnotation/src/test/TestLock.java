import com.yangyang.HQL.Student;
import com.yangyang.Utils.HibernateUtil;
import org.hibernate.LockOptions;
import org.hibernate.Session;
import org.junit.Test;

public class TestLock {
    /**
     * 两个同事修改,会出现 更新丢失(并发带来的危害)
     *  解决办法:
     *   1: 悲观锁: 是hibernate基于数据库机制来实现的 ,同步实现,
     *   悲观锁-->读取时就加锁,修改完成后其他线程才能修改
     *   2: 乐观锁: 在数据库中加入version字段,每一次修改version+1,两次version不一样就报错
     *
     *   打开方式: load(xxx.class,id,LockOption.UPGREAD) --> 为其增加悲观锁
     *
     */
    @Test
    public void testUpdate01() {
        Session session = null;
        try {
            session = HibernateUtil.openSession();
            session.beginTransaction();


            Student stu = session.load(Student.class,1);
            //stu.setName("洋洋");
            System.out.println(stu.getName());

            session.getTransaction().commit();
        } catch (Exception e) {
            session.getTransaction().rollback();
            e.printStackTrace();
        } finally {
            HibernateUtil.closeSession(session);
        }
    }
    @Test
    public void testUpdate02() {
        Session session = null;
        try {
            session = HibernateUtil.openSession();
            session.beginTransaction();

            Student stu = session.load(Student.class,1,LockOptions.UPGRADE);
            stu.setSex("男");
            session.getTransaction().commit();
        } catch (Exception e) {
            session.getTransaction().rollback();
            e.printStackTrace();
        } finally {
            HibernateUtil.closeSession(session);
        }
    }
}

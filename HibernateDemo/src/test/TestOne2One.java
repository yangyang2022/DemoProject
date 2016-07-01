import com.yangyang.Utils.HibernateUtil;
import com.yangyang.model.IdCard;
import com.yangyang.model.Person;
import org.hibernate.Session;
import org.junit.Test;

public class TestOne2One {
    @Test
    public void testAdd() {
        Session session = null;
        try {
            session = HibernateUtil.openSession();
            session.beginTransaction();

            Person p = new Person("校长");
            session.save(p);
            IdCard idCard = new IdCard("999",p);
            session.save(idCard);

            session.getTransaction().commit();
        } catch (Exception e) {
            session.getTransaction().rollback();
            e.printStackTrace();
        } finally {
            HibernateUtil.closeSession(session);
        }
    }

    @Test
    public void testAdd01() {
        Session session = null;
        try {
            session = HibernateUtil.openSession();
            session.beginTransaction();

            /**
             * 由 IdCard维护关系 p.setIdCard(idCard); 无效,关系不会生效
             */
            //IdCard idCard = new IdCard("888",null);
            //session.save(idCard);
            //Person p = new Person("老沈");
            //p.setIdCard(idCard);
            //session.save(p);

            Person p = new Person("老沈");
            session.save(p);
            IdCard idCard = new IdCard("888",p);
            session.save(idCard);

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

            /**  由 IdCard 维护关系
             *  一条 sql 取出
             */
            Person p = session.load(Person.class,1);
            System.out.println(p.getName()+":"+p.getIdCard().getNo());

            /**
             *  发出 3 条 sql
             */
            //IdCard idc = session.load(IdCard.class,1);
            //System.out.println(idc.getNo()+" : "+idc.getPerson().getName());
            /**
             *
             *  所以 one-2-one 时尽量不使用双向,使用时 load时取不维护关系的那一段,否则
             *  会有很多冗余 数据
             *
             */
            session.getTransaction().commit();
        } catch (Exception e) {
            session.getTransaction().rollback();
            e.printStackTrace();
        } finally {
            HibernateUtil.closeSession(session);
        }
    }
}

import com.yangyang.Utils.HibernateUtil;
import com.yangyang.dao.UserDao;
import com.yangyang.model.User;
import org.hibernate.Session;
import org.junit.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

public class TestHibernate1 {
    private static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
    @Test
    public void testAdd() {
        Session session = null;
        try {
            session = HibernateUtil.openSession();
            session.beginTransaction();

            User user = new User("xiaomo2","123123","123123",sdf.parse("1987-09-09"));
            session.save(user);

            session.getTransaction().commit();
        }catch (Exception e){
        } finally {
            HibernateUtil.closeSession(session);
        }
    }

    @Test
    public void testUserLoad() {
        Session session = null;
        try {
            session = HibernateUtil.openSession();

            User u = (User) session.get(User.class,4);
            System.out.println(u);

        }catch (Exception e){
            session.getTransaction().rollback();
        } finally {
            HibernateUtil.closeSession(session);
        }
    }

    @Test
    public void testUpdate() {
        Session session = null;
        try {
            session = HibernateUtil.openSession();
            session.beginTransaction();

            User user = session.load(User.class,1);
            user.setNickname("战三");
            session.update(user);

            session.getTransaction().commit();
        }catch (Exception e){
            session.getTransaction().rollback();
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

            User user = new User();
            user.setId(2);
            session.delete(user);//根据id删除

            session.getTransaction().commit();
        }catch (Exception e){
            session.getTransaction().rollback();
        } finally {
            HibernateUtil.closeSession(session);
        }
    }

    @Test
    public void testUserList() {
        Session session = null;
        try {
            session = HibernateUtil.openSession();
            List<User> users = session.createQuery("from User")
                    .setFirstResult(0).setMaxResults(2)
                    .list();
            users.forEach(System.out::println);

        }catch (Exception e){
        } finally {
            HibernateUtil.closeSession(session);
        }
    }

    /**
     * hibernate 三种状态
     *   ------------------ 数据库 ---- session ----
     *  transient          -- 没有 -- -- 没有 --
     *  persistence        -- 有 --   -- 有 --
     *  detach             -- 有 --   -- 没有 --
     */
    @Test
    public void testTransient() {
        Session session = null;
        try {
            session = HibernateUtil.openSession();
            session.beginTransaction();

            User user = new User("yangyang","123123","洋洋",sdf.parse("1987-09-09"));

            //以上 user 是 transient 状态
            session.save(user);
            // now user is persistent
            session.getTransaction().commit();
        } catch (Exception e) {
            session.getTransaction().rollback();
            e.printStackTrace();
        } finally {
            HibernateUtil.closeSession(session);
        }
    }

    @Test
    public void testTransient02() {
        Session session = null;
        try {
            session = HibernateUtil.openSession();
            session.beginTransaction();

            //User user = new User("yangyang3","123123","洋洋",sdf.parse("1987-09-09"));
            //
            ////以上 user 是 transient 状态
            //session.save(user);
            //// now user is persistent
            //user.setNickname("洋哥");
            //now don't invoke update but also update

            //User u = session.load(User.class,1);
            //u.setUsername("heheheheheh");

            //User u = new User();
            //u.setId(6); //database have id=6 and session can't have user
            //session.update(u);//become persistent 只会 load id
            //
            //u.setUsername("111");
            //u.setNickname("aaa");
            //u.setPassword("123");
            //u.setBorn(sdf.parse("1998-12-12"));
            //session.update(u);

            User u = new User();
            u.setId(6);
            session.delete(u);
            u.setUsername("aaa"); // not effect,只会发一条sql,transient对象不会和缓存比较

            //session.clear(); clear all object

            session.getTransaction().commit();
        } catch (Exception e) {
            session.getTransaction().rollback();
            e.printStackTrace();
        } finally {
            HibernateUtil.closeSession(session);
        }
    }

    @Test
    public void testLazyOfLoad() {
        Session session = null;
        try {
            session = HibernateUtil.openSession();

            User user = session.load(User.class,1);
            System.out.println("id: "+user.getId());

            System.out.println(user.getUsername());
            HibernateUtil.closeSession(session);
        } catch (Exception e) {
            session.getTransaction().rollback();
            e.printStackTrace();
        }finally {
            HibernateUtil.closeSession(session);
        }
    }

    @Test
    public void testLazyOfGet() {
        Session session = null;
        try {
            session = HibernateUtil.openSession();

            User user = session.get(User.class,101);  // nullpointexception
            User user1 = session.load(User.class,101);// user1 is not empty! throw objectNotFoundException

            HibernateUtil.closeSession(session);
        } catch (Exception e) {
            session.getTransaction().rollback();
            e.printStackTrace();
        }finally {
            HibernateUtil.closeSession(session);
        }
    }

    @Test
    public void testLazyQuestion() throws ParseException {
        UserDao userDao = new UserDao();

        User u = new User("习近平","12222","主席",sdf.parse("1967-09-09"));
        u.setId(1);
        userDao.update(u);

    }
}

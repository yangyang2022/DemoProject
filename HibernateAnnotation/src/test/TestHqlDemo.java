import com.yangyang.HQL.Special;
import com.yangyang.HQL.Student;
import com.yangyang.HQL.StudentDto;
import com.yangyang.Utils.HibernateUtil;
import org.hibernate.Session;
import org.junit.Test;

import java.util.List;

public class TestHqlDemo {
    @Test
    public void test() {
        Session session = null;
        try {
            session = HibernateUtil.openSession();
            session.beginTransaction();



            session.getTransaction().commit();
        } catch (Exception e) {
            session.getTransaction().rollback();
            e.printStackTrace();
        } finally {
            HibernateUtil.closeSession(session);
        }
    }

    @Test
    public void testDemo1() {
        Session session = null;
        try {
            session = HibernateUtil.openSession();

            List<Special> sps = session.createQuery("from Special").list();
            sps.forEach(e-> System.out.println(e.getName()+" : "+e.getType()));

        } catch (Exception e) {
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
            /**
             * 基于 ? 的条件查询查询 --> jdbc最小下标是0,hibernate 是1
             */
            //List<Student> sps = session.createQuery("from Student where name like '%沈%'").list();
            List<Student> sps = session.createQuery("from Student where name like ?")
                    .setParameter(0,"%沈%")
                    .list();
            sps.forEach(e-> System.out.println(e.getName()));

        } catch (Exception e) {
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
            /**
             *  基于别名查询 使用:说明别名名称
             */
            //List<Student> sps = session.createQuery("from Student where name like '%沈%'").list();
            List<Student> sps = session.createQuery("from Student where name like :name and sex=:sex")
                    .setParameter("name","%沈%")
                    .setParameter("sex","男")
                    .list();
            sps.forEach(e-> System.out.println(e.getName()+" : "+e.getSex()));

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            HibernateUtil.closeSession(session);
        }
    }

    @Test
    public void testDemo4() {
        Session session = null;
        try {
            session = HibernateUtil.openSession();
            /**
             *
             *
             *
             */
            //List<Student> sps = session.createQuery("from Student where name like '%沈%'").list();
            //List<Long> sps = session.createQuery("select count (*) from Student where name like :name and sex=:sex")
            //        .setParameter("name","%沈%")
            //        .setParameter("sex","男")
            //        .list();

            // 返回唯一的一个值 返回一个对象也可以
            Long sps_count = session.createQuery("select count (*) from Student where name like :name and sex=:sex",Long.class)
                    .setParameter("name","%沈%")
                    .setParameter("sex","男")
                    .uniqueResult(); //

            System.out.println("count: "+sps_count);

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            HibernateUtil.closeSession(session);
        }
    }

    /**
     * 基于投影的查询
     *
     *  查询 男生 女生 各多少人
     *
     */
    @Test
    public void testDemo5() {
        Session session = null;
        try {
            session = HibernateUtil.openSession();

            List<Object[]> stus = session.createQuery("select stu.sex,count(*) from Student stu group by stu.sex").list();

            for(Object[] obj :stus){
                System.out.println(obj[0]+" : "+obj[1]);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            HibernateUtil.closeSession(session);
        }
    }

    /**
     * 基于投影的查询
     *
     *在班级1中的男生
     */
    @Test
    public void testDemo6() {
        Session session = null;
        try {
            session = HibernateUtil.openSession();

            //List<Object[]> stus = session.createQuery("select stu.sex,count(*) from Student stu where stu.classRoom.id=:id group by stu.sex")
            //        .setParameter("id",1)
            //        .list();
            //
            //for(Object[] obj :stus){
            //    System.out.println(obj[0]+" : "+obj[1]);
            //}

            // 可以使用in 基于列表的查询,此处查询需要使用 别名查询
            //setParameter 必须在setParameterList 前面
            //List<Student> stus = session.createQuery("select stu from Student stu where stu.classRoom.id in(:clas) and stu.name like:name")
            //        .setParameter("name","%张%")
            //        .setParameterList("clas",new Integer[]{1,2})
            //        .list();


            //分页查询
            //List<Student> stus = session.createQuery("select stu from Student stu where stu.classRoom.id in(:clas) and stu.name like:name")
            //        .setParameter("name","%张%")
            //        .setParameterList("clas",new Integer[]{1,2})
            //        .setFirstResult(0).setMaxResults(5) //分页条件
            //        .list();


            //可以通过 is null 查询为空 的对象
            List<Student> stus = session.createQuery("select stu from Student stu where stu.classRoom .id=1 ")
                    .list();
            stus.forEach(e-> System.out.println(e.getName()+" : "+e.getSex()));
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            HibernateUtil.closeSession(session);
        }
    }

    // =====  链接查询 ==== 最好不要使用导航查询~ 基于全连接,效率不高~
    @Test
    public void testDemo7() {
        Session session = null;
        try {
            session = HibernateUtil.openSession();

            //List<Student> stus = session.createQuery("select stu from Student stu join stu.classRoom clz where clz.id=1")
            //        .setFirstResult(0).setMaxResults(10)
            //        .list();
            //stus.forEach(e-> System.out.println(e.getName()+" --> "+e.getSex()));

            // 基于班级统计信息
            //List<Object[]> stus = session.createQuery("select clz.name,count(stu.id) from Student stu right join stu.classRoom clz group by clz.id")
            //        .list();
            //for(Object[] obj : stus){
            //    System.out.println(obj[0]+" --> "+obj[1]);
            //}

            List<StudentDto> stus = session.createQuery("select new com.yangyang.HQL.StudentDto" +
                    "(stu.id as sid,stu.name as sname,stu.sex as ssex,clz.name as sclz,spe.name as sspe)" +
                    " from Student stu left join stu.classRoom clz left join clz.speacil spe ")
                    .list();

            System.out.println(stus.get(0).getSname());

            //for(Object[] obj : stus){
            //    System.out.println(obj[0]+" --> "+obj[1]+"--> "+obj[2]+" --> "+obj[3]+" --> "+obj[4]);
            //}
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            HibernateUtil.closeSession(session);
        }
    }

    //抓取策略

    @Test
    public void testDemo8() {
        Session session = null;
        try {
            session = HibernateUtil.openSession();
            session.beginTransaction();

            //查询每个专业的人数
            //List<Object[]> stus = session.createQuery("select spe.name,count(stu.id) from Student stu right join stu.classRoom.speacil spe" +
            //        " group by spe having count(stu.id)>150").list();
            List<Object[]> stus = session.createQuery("select stu.sex,spe.name,count(stu.id) from Student stu right join stu.classRoom.speacil spe" +
                    " group by spe,stu.sex").list();
            for(Object[] obj : stus){
                System.out.println(obj[0]+" : "+obj[1]+" : "+obj[2]);
            }

            session.getTransaction().commit();
        } catch (Exception e) {
            session.getTransaction().rollback();
            e.printStackTrace();
        } finally {
            HibernateUtil.closeSession(session);
        }
    }
}

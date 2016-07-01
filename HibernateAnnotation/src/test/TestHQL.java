import com.yangyang.HQL.ClassRoom;
import com.yangyang.HQL.Special;
import com.yangyang.HQL.Student;
import com.yangyang.Utils.HibernateUtil;
import org.hibernate.Session;
import org.junit.Test;

import java.util.Random;

public class TestHQL {
    Random ran = new Random();
    private String getName() {
        String[] name1 = new String[]{"孔","张","叶","李","叶入","孔令",
                "张立","陈","刘","牛","夏侯","令","令狐","赵","母","穆","倪",
                "张毅","称","程","王","王志","刘金","冬","吴","马","沈"};

        String[] name2 = new String[]{"凡","课","颖","页","源","都",
                "浩","皓","西","东","北","南","冲","昊","力","量","妮",
                "敏","捷","杰","坚","名","生","华","鸣","蓝","春","虎","刚","诚"};

        String[] name3 = new String[]{"吞","明","敦","刀","备","伟",
                "唯","楚","勇","诠","佺","河","正","震","点","贝","侠",
                "伟","大","凡","琴","青","林","星","集","财"};

        boolean two = ran.nextInt(50)>=45?false:true;
        if(two) {
            String n1 = name1[ran.nextInt(name1.length)];
            String n2;
            int n = ran.nextInt(10);
            if(n>5) {
                n2 = name2[ran.nextInt(name2.length)];
            } else {
                n2 = name3[ran.nextInt(name3.length)];
            }
            return n1+n2;
        } else {
            String n1 = name1[ran.nextInt(name1.length)];
            String n2 = name2[ran.nextInt(name2.length)];
            String n3 = name3[ran.nextInt(name3.length)];
            return n1+n2+n3;
        }
    }

    @Test
    public void testAddSpecial() {
        Session session = null;
        try {
            session = HibernateUtil.openSession();
            session.beginTransaction();

            // ==================== add Special =================

            session.save(new Special("计算机教育", "教育类"));
            session.save(new Special("计算机应用技术", "高职类"));
            session.save(new Special("计算机网络技术", "高职类"));
            session.save(new Special("计算机信息管理", "高职类"));
            session.save(new Special("数学教育", "教育类"));
            session.save(new Special("物理教育", "教育类"));
            session.save(new Special("化学教育", "教育类"));
            session.save(new Special("会计", "高职类"));
            session.save(new Special("英语教育", "教育类"));


            // ==================== add ClassRoom =================
            session.save(new ClassRoom("计算机教育1班",2009,new Special(1)));
            session.save(new ClassRoom("计算机教育2班",2009,new Special(1)));
            session.save(new ClassRoom("计算机教育班",2010,new Special(1)));
            session.save(new ClassRoom("计算机教育班",2011,new Special(1)));
            session.save(new ClassRoom("计算机应用技术",2009,new Special(2)));
            session.save(new ClassRoom("计算机应用技术",2010,new Special(2)));
            session.save(new ClassRoom("计算机应用技术",2011,new Special(2)));
            session.save(new ClassRoom("计算机网络技术",2009,new Special(3)));
            session.save(new ClassRoom("计算机网络技术",2010,new Special(3)));
            session.save(new ClassRoom("计算机网络技术",2011,new Special(3)));
            session.save(new ClassRoom("计算机信息管理",2009,new Special(4)));
            session.save(new ClassRoom("计算机信息管理",2010,new Special(4)));
            session.save(new ClassRoom("计算机信息管理",2011,new Special(4)));
            session.save(new ClassRoom("数学教育1班",2009,new Special(5)));
            session.save(new ClassRoom("数学教育2班",2009,new Special(5)));
            session.save(new ClassRoom("数学教育3班",2009,new Special(5)));
            session.save(new ClassRoom("数学教育1班",2010,new Special(5)));
            session.save(new ClassRoom("数学教育2班",2010,new Special(5)));
            session.save(new ClassRoom("数学教育1班",2011,new Special(5)));
            session.save(new ClassRoom("数学教育2班",2011,new Special(5)));
            session.save(new ClassRoom("物理教育",2009,new Special(6)));
            session.save(new ClassRoom("物理教育",2010,new Special(6)));
            session.save(new ClassRoom("物理教育",2011,new Special(6)));
            session.save(new Special("化学教育","教育类"));
            session.save(new ClassRoom("化学教育",2009,new Special(7)));
            session.save(new ClassRoom("化学教育",2010,new Special(7)));
            session.save(new ClassRoom("化学教育",2011,new Special(7)));
            session.save(new ClassRoom("会计",2009,new Special(8)));
            session.save(new ClassRoom("会计",2010,new Special(8)));
            session.save(new ClassRoom("会计",2011,new Special(8)));
            session.save(new ClassRoom("英语教育A班",2009,new Special(9)));
            session.save(new ClassRoom("英语教育B班",2009,new Special(9)));
            session.save(new ClassRoom("英语教育A班",2010,new Special(9)));
            session.save(new ClassRoom("英语教育B班",2010,new Special(9)));
            session.save(new ClassRoom("英语教育A班",2011,new Special(9)));
            session.save(new ClassRoom("英语教育B班",2011,new Special(9)));
            session.save(new ClassRoom("选修课班A",2011,null));
            session.save(new ClassRoom("选修课班B",2011,null));

            // ================ add Student ====================
            String[] sex = new String[]{"男","女"};
            //添加32个有班级的学生
            for(int i=1;i<=32;i++) {
                //每个班40个学生
                for(int j=1;j<=40;j++) {
                    session.save(new Student(getName(),sex[ran.nextInt(2)],new ClassRoom(i)));
                }
            }
            //100 个学生没有班级
            for(int j=0;j<100;j++){
                session.save(new Student(getName(),sex[ran.nextInt(2)],null));
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

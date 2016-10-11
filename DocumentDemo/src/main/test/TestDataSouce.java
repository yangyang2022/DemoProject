import com.yangyang.dao.idao.IDepartmentDao;
import com.yangyang.dto.AttachDto;
import com.yangyang.model.Department;
import com.yangyang.model.Message;
import com.yangyang.model.Pager;
import com.yangyang.model.User;
import com.yangyang.service.iservice.IDepartmentService;
import com.yangyang.service.iservice.IMessageService;
import com.yangyang.service.iservice.IUserService;
import com.yangyang.systemContext.SystemContext;
import com.yangyang.utils.AuthProperty;
import com.yangyang.utils.NameUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;
import javax.sql.DataSource;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/beans-hibernate.xml")
public class TestDataSouce {

    @Resource
    private DataSource dataSource;

    @Resource
    private IDepartmentDao departmentDao;

    @Resource
    private IDepartmentService departmentService;

    @Resource
    private IUserService userService;
    @Test
    public void testConnection() throws SQLException {

        System.out.println(dataSource.getConnection());
    }

    private static <T>void printList(List<T> deps){
        deps.forEach(System.out::println);
    }
    @Test
    public void testAddDep() {
        Department dep = new Department("财务处");
        departmentDao.add(dep);
    }

    @Test
    public void testAddDep1() {
        Department dep1 = new Department("学生处");
        Department dep2 = new Department("招就处");
        Department dep3 = new Department("办公室");
        Department dep4 = new Department("人事处");
        Department dep5 = new Department("团委");

        departmentService.add(dep1);
        departmentService.add(dep2);
        departmentService.add(dep3);
        departmentService.add(dep4);
        departmentService.add(dep5);

    }

    @Test
    public void testQuery() {
    }
    @Test
    public void testAddDepocpe() {

        departmentService.addScopeDeps(4, new int[]{1,3, 5, 6, 7});
        //printList(departmentService.listDepScopeDep(4));
        //departmentService.deleteScopeDep(4,2);
        //System.out.println("===============================");
        //printList(departmentService.listDepScopeDep(4));
    }

    @Test
    public void testremove() {
        List<Department> deps = departmentService.listAllDeps();
        Department dep = new Department();dep.setId(3);
        System.out.println("size: "+deps.size());
        deps.remove(dep);
        System.out.println("size: "+deps.size());
    }

    @Test
    public void testAddUser() {
        Random rand = new Random();
        List<Integer> depsId = Arrays.asList(2,3,4,5,6,7);
        List<String> names = NameUtil.getNames(100);

        //User user = new User("admin","admin","超级管理员",departmentService.load(4),"yangyang20222@gmail.com",1);
        //userService.add(user,4);
        String email = "yangyang20222@gmail.com";
        String password = "123123";
        String nickname = "nickname";

        for(String name:names){
            User u = new User(name,password,nickname,departmentService.load(depsId.get(rand.nextInt(5))),email,rand.nextInt(2));
            userService.add(u,u.getDepartment().getId());
        }
    }

    @Resource
    private AuthProperty authProperty;
    //private static AuthProperty authProperty = new AuthProperty();
    @Test
    public void testProperties() {
        System.out.println(authProperty.getAdminStr());
        System.out.println(authProperty.getUserStr());
    }

    @Resource
    private IMessageService messageService;

    @Test
    public void testUsers() throws IOException {
        messageService.add(new Message(),new Integer[]{2,3,4,5,7,8},new AttachDto(false));
    }

    @Test
    public void testMsg() throws IOException {
        User user = new User(2);
        SystemContext.setLoginUser(user);

        Message msg = new Message();
        msg.setTitle("小莫第二次来了");
        msg.setContent("来了吃了个饭");
        messageService.add(msg,new Integer[]{5,26,27},new AttachDto(false));
    }

    @Test
    public void testMsgDeleteRecive() {
        User user = new User(5);
        SystemContext.setLoginUser(user);
        messageService.deleteRecive(3);
    }

    @Test
    public void testMsgDeleteSend() {
        messageService.deleteSend(3);
    }

    @Test
    public void testFindSend() {
        User user = new User(2);
        SystemContext.setLoginUser(user);
        SystemContext.setPageSize(15);
        SystemContext.setPageOffset(0);

        Pager<Message> pagers = messageService.findSendMsg("莫");
        pagers.getDatas().forEach(e-> System.out.println(e.getTitle() + " : " + e.getContent()));
    }

    @Test
    public void testReciveMsg() {
        User user = new User(5);
        SystemContext.setLoginUser(user);
        SystemContext.setPageSize(15);
        SystemContext.setPageOffset(0);

        Pager<Message> pagers = messageService.findReceive("",1);
        pagers.getDatas().forEach(e-> System.out.println(e.getTitle() + " : " + e.getContent()));
    }

    @Test
    public void testListAllSendUser() {

        List<User> us = userService.listAllSendUser(103);
        System.out.println(us.get(0).getId()+" : "+us.get(0).getUsername());

    }
}

import com.yangyang.model.Department;
import org.apache.commons.io.FilenameUtils;
import org.junit.Test;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TestJava {
    private List<Integer> getCross(List<Integer> eids, List<Integer> sdeps){
        List<Integer> result = new ArrayList<>();
        result.addAll(eids);
        result.retainAll(sdeps);
        return result;
    }
    private List<Integer> getDelIds(List<Integer> eids,List<Integer> sdeps){
        List<Integer> result = new ArrayList<>();
        result.addAll(eids);
        result.removeAll(getCross(eids, sdeps));
        return result;
    }
    private List<Integer> getAddIds(List<Integer> eids,List<Integer> sdeps){
        List<Integer> result = new ArrayList<>();
        result.addAll(sdeps);
        result.removeAll(getCross(eids, sdeps));
        return result;
    }
    private static<T> void printList(List<T> ids){ids.forEach(System.out::println);}

    @Test
    public void testDemo1() {
        List<Integer> list1 = Arrays.asList(1,2,3,4,5); //old
        List<Integer> list2 = Arrays.asList(2,3,5,6,8); //new
        
        printList(getDelIds(list1,list2));//1 you 2 meiyou
        System.out.println("======== ");
        printList(getAddIds(list1,list2));

    }

    @Test
    public void testDemo2() {
        Department dep1 = new Department();
        Department dep2 = new Department();
        Department dep3 = new Department();
        dep1.setId(1);dep1.setName("hehe");
        dep2.setId(2);dep2.setName("haha");
        dep3.setId(3);dep3.setName("heiehi");

        List<Department> deps = new ArrayList<>();
        deps.add(dep1);deps.add(dep2);deps.add(dep3);

        deps.remove(dep3);
        printList(deps);
    }

    @Test
    public void testCountHql() {
        String hql = "select (ss) from User left join fetch Depte dep";
    }

    private String getNewName(String name){
        String s = LocalDateTime.now().format(DateTimeFormatter.ISO_LOCAL_DATE_TIME)+("."+ FilenameUtils.getExtension(name));
        return s.replaceAll(":","-");
    }
    @Test
    public void testFilename() throws InterruptedException {
        System.out.println(getNewName("aaa.txt"));
        Thread.sleep(1000);
        System.out.println(getNewName("aaa.txt"));
    }

    @Test
    public void testMail() {
        List<Integer> list = new ArrayList<>();
    }
}

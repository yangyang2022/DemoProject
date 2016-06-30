import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class TestDemo {
    
    private String getString(List<Integer> list1,List<String> list2){
        int len1 = list1.size();
        int len2 = list2.size();
        int len = list1.size() > list2.size() ? list1.size():list2.size();
        List<String> newList = new ArrayList<>(len);
        if(len % 2 == 1){
            list2.add(list2.get(0));
        }else {
            list1.add(list1.get(0));
        }
        if(len1>len2){
            //list1.size() > list2.size()
            for (int i = 0,j=0; i < len; ++i,++j) {
                newList.add(list1.get(i %len)+"");
                newList.add(list1.get((i+1)%len)+"");
                newList.add(list2.get((j)%len2));
                newList.add(list2.get((j+1)%len2));
            }
        }else {
            //list1.size() < list2.size()
            for (int i = 0,j=0; i < len; i+=2,j+=2) {
                newList.add(list2.get(i %len));
                newList.add(list2.get((i+1)%len));
                newList.add(list1.get((j)%len1)+"");
                newList.add(list1.get((j+1)%len1)+"");
            }
        }
        if(len%2==0){
            // 两个list 都是偶数 ,去掉最后2个
            int newlen = newList.size();
            newList.remove(newlen-1);
            newList.remove(newlen-2);
        }
        System.out.println(newList);
        return "";
    }
    @Test
    public void testDemo1() {
        List<Integer> list1 = new ArrayList<>();
        List<String> list2 = new ArrayList<>();
        list1.add(1);
        list1.add(2);
        list1.add(3);
        list1.add(4);

        list2.add("A");
        list2.add("B");
        list2.add("C");
        list2.add("D");
        list2.add("E");
        list2.add("F");

        getString(list1,list2);

    }

    @Test
    public void testDemo2() {
        Base base = new Child();
        System.out.println(base.method1());
        System.out.println(((Child)base).method1());
        System.out.println(base.str);
        System.out.println(((Child) base).str);
    }

    @Test
    public void testDemo3() {
        String path = "com.yangyang";
        FileScan.getClasses(path).forEach(e-> System.out.println(e.getSimpleName()+":"+e.getName()));
    }
}

class Base{
    String str = "base str ";
    String method1(){
        return "base method";
    }
}
class Child extends Base{
    String str = "child str ";
    String method1(){
        return "child method";
    }
}
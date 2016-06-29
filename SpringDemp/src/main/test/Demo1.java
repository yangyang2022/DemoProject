import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class Demo1 {
    private String getList(List<Integer> list1,List<String> list2){
        List<String> newlist = new ArrayList<>();
        if(list2.size() %2 == 1){
            list2.add(list2.get(0));
        }
        List<String> temp = new ArrayList<>(list2.size());

        for (int i = 0,j=0; j < list2.size(); ++i,++j) {
            if(i == list1.size()) i =0;
            temp.add(list1.get(i)+"");
        }
        for (int i = 0; i < list2.size(); i+=2) {
            newlist.add(list2.get(i));
            newlist.add(list2.get(i+1));
            if(i > list2.size()-3) continue;
            newlist.add(temp.get(i));
            newlist.add(temp.get(i+1));
        }
        System.out.println(newlist);
        return "";
    }
    @Test
    public void testDemo1() {
        List<Integer> list1 = new ArrayList<>();
        List<String> list2 = new ArrayList<>();
        list1.add(1);
        list1.add(2);
        list1.add(3);
        //list1.add(4);

        list2.add("a");
        list2.add("b");
        list2.add("c");
        list2.add("d");
        list2.add("e");
        //list2.add("f");
        getList(list1,list2);
    }
}

import org.junit.Test;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TestDemo {
    @Test
    public void testDemo1() {
        Map<String,String> map1 = new HashMap<>();
        map1.put("map1-key","map1-value");
        Map<String,String> map2 = new HashMap<>();
        map2.put("map2-key","map2-value");
        Map<String,String> map3 = new HashMap<>();
        map3.put("map3-key","map3-value");

        List<Map<String,String>> lists = Arrays.asList(map1,map2,map3);

        lists.stream().map(Map::keySet).forEach(System.out::println);

    }

    @Test
    public void testDemo2() {
        int a = 12345;
        String b = "12313131311414555555555555555555555555555555555121";
        int sum = Arrays.asList((a+"").split("")).stream().mapToInt(Integer::parseInt).sum();
        System.out.println("sum: "+sum);
        sum = Arrays.asList((b+"").split("")).stream().mapToInt(Integer::parseInt).sum();
        System.out.println("sum2: "+sum);
    }
}

package com.yangyang.java8;

import com.yangyang.interfaces.BUfferedReaderProcessor;
import com.yangyang.interfaces.Consumer;
import com.yangyang.model.Apple;
import com.yangyang.model.Dish;
import com.yangyang.model.Letter;
import com.yangyang.utils.AddressUtils;
import org.junit.Test;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.function.BiConsumer;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.stream.Collectors;


public class Demo1 {
    private Consumer print = System.out::println;
    private BiConsumer printMap = (k,v)-> System.out.println(k+" : "+v);

    private static List<Apple> inventory = Arrays.asList(
            new Apple(10,"red"),
            new Apple(150,"red"),
            new Apple(40,"red"),
            new Apple(120,"yellow"),
            new Apple(30,"white"),
            new Apple(40,"white"),
            new Apple(50,"white"),
            new Apple(60,"green"),
            new Apple(120,"green")
    );

    private List<Dish> meun = Arrays.asList(
            new Dish("fork",false,800, Dish.Type.MEAT),
            new Dish("beef",false,700, Dish.Type.MEAT),
            new Dish("chicken",false,400, Dish.Type.MEAT),
            new Dish("french fries",true,530, Dish.Type.OTHER),
            new Dish("rice",true,350, Dish.Type.OTHER),
            new Dish("season fruit",true,120, Dish.Type.OTHER),
            new Dish("pizza",true,550, Dish.Type.OTHER),
            new Dish("prawns",false,300, Dish.Type.FISH),
            new Dish("salmon",false,450, Dish.Type.FISH)
    );

    @Test
    public void testDemo() {
        LocalDateTime time1 = LocalDateTime.of(1900, 1, 1,0,0,0);
        LocalDateTime time2 = LocalDateTime.of(2012, 8, 1,0,0,0);
        Duration d = Duration.between(time1,time2);
        int dayTime = 3600*24;
        System.out.println(d.get(ChronoUnit.SECONDS) / dayTime);
    }

    @Test
    public void testReadFile() throws IOException {
        //AddressUtils.getProns().forEach(print);
        //AddressUtils.getCities().forEach(print);
        AddressUtils.getCountrys().forEach(e-> System.out.println(e.trim()));
    }
    
    private int findMax(List<Integer> datas){

        return 0;
    }
    @Test
    public void testDemo3() {
        List<Integer> datas = Arrays.asList(1,2,2,2,2,2,3,4,5);
        Map<Integer,Long> maps = datas.stream().collect(Collectors.groupingBy(Function.identity(),Collectors.counting()));
        int maxValue = maps.values().stream().mapToInt(Long::intValue).summaryStatistics().getMax();
        maps.keySet().stream().filter(e->maps.get(e).equals(Long.valueOf(maxValue))).forEach(System.out::println);
    }

    public static String processFile(BUfferedReaderProcessor processor) throws IOException {
        String filename = "C:\\mavenProject\\DemoProject\\Core-javaTest\\src\\resource\\data.txt";
        try (BufferedReader br = new BufferedReader(new FileReader(filename))){
            return processor.process(br);
        }
    }
    @Test
    public void testDemo4() throws IOException {
        System.out.println(processFile(e->e.readLine()+e.readLine()+e.readLine()));
    }
    public static <T> void Foreach(List<T> list, Consumer<T> c){
        for(T t:list){
            c.accept(t);
        }
    }
    public static <T,R>List<R> map(List<T> list, com.yangyang.interfaces.Function<T,R> f){
        List<R> rs = new ArrayList<R>();
        for(T t:list){
            //rs.add(f.apply(t));
        }
        return rs;
    }

    public static List<Apple> mapApple(List<Integer> inventory, BiFunction<Integer,String,Apple> f){
        List<Apple> rs = new ArrayList<>();
        for(Integer a:inventory){
            rs.add(f.apply(a,"yellow"));
        }
        return rs;
    }
    @Test
    public void testDemo5() {
        //Foreach(Arrays.asList(1,2,3,4,5),i-> System.out.println(i));
        //Foreach(map(Arrays.asList(1,2,3,4,5),e->e*2),print);
        //Foreach(map(Arrays.asList("hello","world","hello world"),(String e)->e.length()),print);
        
        //List<String> str = Arrays.asList("a","b","A","B");
        //str.sort(String::compareToIgnoreCase);
        //System.out.println(str);

        //Supplier<Map> maps = HashMap::new;
        //System.out.println(maps.get());

        List<Integer> weights = Arrays.asList(7,3,4,10);
        List<Apple> apples = mapApple(weights,Apple::new);
        //List<Apple> apples = mapApple(weights,e->new Apple(e,"red"));
        System.out.println(apples);
    }

    @Test
    public void testDemo6() {
        //inventory.sort(Comparator.comparing(Apple::getWeight).reversed().thenComparing(Apple::getColor));
        //System.out.println(inventory);

        //Predicate<Apple> redApple = e->e.getColor().equals("red");
        //Predicate<Apple> redAppleAndHeavy = redApple.and(e->e.getWeight()>100)
        //        .or(e->e.getColor().equals("green"));
        //
        //inventory.stream().filter(redAppleAndHeavy).forEach(System.out::println);
        
        //Function<Integer,Integer> f = x->x+1;
        //Function<Integer,Integer> g = x->x*2;
        //Function<Integer,Integer> h = f.andThen(g);
        //Function<Integer,Integer> hh = f.compose(g);
        //System.out.println(h.apply(2)); // g(f(x))
        //System.out.println(hh.apply(2)); //f(g(x))

        String text = "hello yangyang hello lamb hello world";
        Function<String,String> addHeader = Letter::addHeader;
        System.out.println("add header: \n"+addHeader.apply(text));
        Function<String,String> addAll = addHeader.andThen(Letter::checkSpelling).andThen(Letter::addFooter);
        System.out.println("addAll: \n"+addAll.apply(text));
    }

    @Test
    public void testDemo7() {
        List<String> ds = meun.stream().filter(e -> e.getCalories() > 300).map(Dish::getName).limit(3).collect(Collectors.toList());
        System.out.println(ds);
    }

    @Test
    public void testDemo8() {
        System.out.println("hello world");

    }
}

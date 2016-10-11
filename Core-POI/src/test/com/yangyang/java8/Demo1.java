package com.yangyang.java8;

import com.yangyang.interfaces.BUfferedReaderProcessor;
import com.yangyang.interfaces.Consumer;
import com.yangyang.model.*;
import com.yangyang.utils.AddressUtils;
import org.junit.Test;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.*;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveTask;
import java.util.function.BiConsumer;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.function.IntSupplier;
import java.util.stream.*;

import static java.util.Arrays.stream;
import static java.util.stream.Collectors.*;


public class Demo1 {
    private Consumer print = System.out::println;
    java.util.function.Consumer printTran = System.out::println;
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

    Trader raoul = new Trader("Raoul","Cambridge");
    Trader mario = new Trader("Mario","Milan");
    Trader alan = new Trader("Alan","Cambridge");
    Trader brian = new Trader("Brain","Cambridge");

    List<Transaction> trans = Arrays.asList(
            new Transaction(brian,2011,300),
            new Transaction(raoul,2012,1000),
            new Transaction(raoul,2011,400),
            new Transaction(mario,2012,710),
            new Transaction(mario,2012,700),
            new Transaction(alan,2012,950)
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
        Map<Integer,Long> maps = datas.stream().collect(Collectors.groupingBy(Function.identity(), counting()));
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
        String world = "hello worlds hello yangyang";
        stream(world.split(" "))
                .map(w->w.split(""))
                .flatMap(Arrays::stream)
                .distinct()
                .collect(Collectors.toList())
                .forEach(System.out::println);

    }
private static void printArray(int[] arr){
    stream(arr).forEach(System.out::print);
    System.out.println();
}
    @Test
    public void testDemo9() {
        List<Integer> nums1 = Arrays.asList(1,2,3);
        List<Integer> nums2 = Arrays.asList(3,4);
        List<int[]> list = nums1.stream().flatMap(i->nums2.stream().filter(j->(i+j)%3==0).map(j->new int[]{i,j})).collect(Collectors.toList());
        list.forEach(Demo1::printArray);
    }

    @Test
    public void testDemo10() {
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 3, 2,-9,-9,-9);
        Optional<Integer> minx = numbers.stream().reduce((x, y)->x<y?x:y);
        minx.ifPresent(x-> System.out.println(x));
        numbers.stream().reduce(Integer::min).ifPresent(System.out::print);
    }

    @Test
    public void testDemo11() {
        // 1:
        //trans.stream().filter(e->e.getYear() == 2011).sorted(Comparator.comparing(Transaction::getValue)).forEach(System.out::println);

        //2:
        //trans.stream().map(e->e.getTrader().getCity()).distinct().forEach(printTran);
        //trans.stream().map(e->e.getTrader().getCity()).collect(Collectors.toSet()).forEach(printTran);

        //3:
        //trans.stream().filter(e->e.getTrader().getCity().equals("Cambridge"))
        //        .sorted(Comparator.comparing(e->e.getTrader().getName()))
        //        .forEach(printTran);

        //4:
        //trans.stream().map(e->e.getTrader().getName()).distinct().sorted().forEach(printTran);
        //String str = trans.stream().map(e->e.getTrader().getName()).distinct().sorted().collect(Collectors.joining(" ,","[ "," ]"));
        //System.out.println(str);

        //5:
        //boolean isMilan = trans.stream().anyMatch(e->e.getTrader().getCity().equals("Milan"));
        //System.out.println(isMilan);

        //6:
        //trans.stream().filter(e->e.getTrader().getCity().equals("Cambridge"))
        //        .forEach(e-> System.out.println(e.getTrader().getName()+": "+e.getValue()));

        //7:
       //trans.stream().mapToInt(Transaction::getValue).reduce(Integer::max)
       //        .ifPresent(e-> System.out.println(e));
       // 
       // trans.stream().max(Comparator.comparing(Transaction::getValue)).ifPresent(e-> System.out.println(e));
        //8:
        //trans.stream().mapToInt(Transaction::getValue).reduce(Integer::min)
        //        .ifPresent(e-> System.out.println(e));
        
        //trans.stream().mapToInt(Transaction::getValue).max().ifPresent(e-> System.out.println(e));

        //IntStream.rangeClosed(1,100).boxed()
        //        .flatMap(a->IntStream.rangeClosed(a,100).mapToObj(b->new Tuple(a,b,Math.sqrt(a*a+b*b))).filter(e->e.getThree()%1==0)).limit(5).forEach(System.out::println);

        //int[] arr = {1,2,3,4,5};
        //int sum = Arrays.stream(arr).sum();
        //System.out.println(sum);
    }

    @Test
    public void testDemo12() throws IOException {
        long count = Files.lines(Paths.get("C:\\mavenProject\\DemoProject\\Core-javaTest\\src\\resource\\data.txt"), Charset.defaultCharset()).flatMap(line -> Arrays.stream(line.split(""))).distinct().count();
        System.out.println("count is: "+count);
    }

    @Test
    public void testDemo13() {

        //Stream.iterate(0,n->n+2).limit(10).forEach(printTran);
        //Stream.iterate(new int[]{0,1},t->new int[]{t[1],t[0]+t[1]}).limit(10).map(t->t[1]).forEach(printTran);
        //Stream.generate(Math::random).limit(10).forEach(printTran);
        IntSupplier fib = new IntSupplier() {
            private int prev = 0;
            private int curr = 1;
            @Override
            public int getAsInt() {
                int old_prev = this.prev;
                int nextValue = this.prev+this.curr;
                this.prev = this.curr;
                this.curr = nextValue;
                return old_prev;
            }
        };
        IntStream.generate(fib).limit(10).forEach(System.out::println);
    }

    @Test
    public void testDemo14() {
        //long sum = meun.stream().collect(counting());
        //System.out.println(sum);
        //meun.stream().collect(maxBy(Comparator.comparing(Dish::getCalories))).ifPresent(printTran);

       //IntSummaryStatistics sts =  meun.stream().collect(summarizingInt(Dish::getCalories));
       //System.out.println("sum: " + sts.getSum() + " ,max: " + sts.getMax() + " ,min: " + sts.getMin() + " ,count: " + sts.getCount() + " ,average: " + sts.getAverage());

        //String ms = meun.stream().map(Dish::getName).collect(joining(",","[","]"));
        //System.out.println(ms);

        //int sum = meun.stream().collect(reducing(0,Dish::getCalories,(i,j)->i+j));
        //System.out.println("sum: "+sum);

        //meun.stream().collect(reducing((d1,d2)->d1.getCalories()<d2.getCalories()?d1:d2)).ifPresent(printTran);
        
        //List<Integer> list = Arrays.asList(1,2,3,4,5);
        //List<Integer> newList = list.stream().collect(ArrayList::new,ArrayList::add,ArrayList::addAll);
        //
        //System.out.println(newList);
        //System.out.println(list == newList);

        //Map<Dish.Type,List<Dish>> ds = meun.stream().collect(groupingBy(Dish::getType));
        //System.out.println(ds);
        
        //Map<CaloricLevel,List<Dish>> dss = meun.stream().collect(groupingBy(dish->{
        //    if(dish.getCalories() <= 400) return CaloricLevel.DIET;
        //    else if(dish.getCalories() <= 700 ) return CaloricLevel.NORMAL;
        //    else return CaloricLevel.FAT;
        //}));
        //System.out.println(dss);

        //Map<Dish.Type,Map<CaloricLevel,List<Dish>>> ds = meun.stream().collect(groupingBy(Dish::getType,groupingBy(dish->{
        //    if(dish.getCalories() <= 400) return CaloricLevel.DIET;
        //    else if(dish.getCalories() <= 700) return CaloricLevel.NORMAL;
        //    else return CaloricLevel.FAT;
        //})));
        //System.out.println(ds);

        //Map<Dish.Type,Long> ds = meun.stream().collect(groupingBy(Dish::getType,counting()));
        //System.out.println(ds);

        //Map<Dish.Type,Optional<Dish>> ds = meun.stream().collect(groupingBy(Dish::getType,maxBy(Comparator.comparingInt(Dish::getCalories))));
        //System.out.println(ds);

        //Map<Dish.Type,Dish> ds = meun.stream().collect(groupingBy(Dish::getType,collectingAndThen(maxBy(Comparator.comparingInt(Dish::getCalories)),Optional::get)));
        //System.out.println(ds);

        //Map<Dish.Type,Integer> ds = meun.stream().collect(groupingBy(Dish::getType,summingInt(Dish::getCalories)));
        //System.out.println(ds);

        Map<Dish.Type,Set<CaloricLevel>> ds = meun.stream().collect(groupingBy(Dish::getType,mapping(dish -> {
            if(dish.getCalories() <= 400) return CaloricLevel.DIET;
            else if(dish.getCalories() <= 700 ) return CaloricLevel.NORMAL;
            else return CaloricLevel.FAT;
        },toCollection(HashSet::new))));

        System.out.println(ds);
    }

    private static boolean isPrime(int cadicate){
        int candidateRoot = (int) Math.sqrt(cadicate);
        return IntStream.rangeClosed(2,candidateRoot).noneMatch(i->cadicate%i == 0);
    }
    private static Map<Boolean,List<Integer>> partitionPrimes(int n){
        return IntStream.rangeClosed(2,n).boxed()
                .collect(partitioningBy(cadicate -> isPrime(cadicate)));
    }

    @Test
    public void testDemo15() {

        //partitioning

        //Map<Boolean,List<Dish>> ds = meun.stream().collect(partitioningBy(Dish::isVegetarian));
        //System.out.println(ds);
        //
        //List<Dish> dss = meun.stream().filter(Dish::isVegetarian).collect(Collectors.toList());
        //System.out.println(ds.get(true));
        //System.out.println(dss);

        //Map<Boolean,Map<Dish.Type,List<Dish>>> ds = meun.stream().collect(partitioningBy(Dish::isVegetarian,groupingBy(Dish::getType)));
        //
        //System.out.println(ds);

        //Map<Boolean,Dish> ds = meun.stream().collect(partitioningBy(Dish::isVegetarian,collectingAndThen(maxBy(Comparator.comparingInt(Dish::getCalories)),Optional::get)));
        //System.out.println(ds);

        //System.out.println(partitionPrimes(100).get(true));

        List<Dish> ds = meun.stream().collect(new ToListCollection<Dish>());
        System.out.println(ds);
        List<Dish> dss = meun.stream().collect(ArrayList::new,ArrayList::add,ArrayList::addAll);
        System.out.println(dss);
    }

    //parallel

    private static long sequenceSum(long n){
        return LongStream.iterate(1l, i->i+1).limit(n).parallel().sum();
    }
    private static long rangedSum(long n){
        return LongStream.rangeClosed(1,n).sum();
    }
    private static long rangedParallelSum(long n){
        return LongStream.rangeClosed(1,n).parallel().sum();
    }

    private static long measureSum(Function<Long,Long> adder,long n){
        long fastest = Long.MAX_VALUE;
        long sum = 0;
        for (int i = 0; i < 10; ++i) {
            long start = System.nanoTime();
            sum = adder.apply(n);
            long duration = (System.nanoTime() - start)/1_000_000;
            if(duration < fastest) fastest = duration;
        }
        System.out.println("result: "+sum);
        return fastest;
    }
    private static long oldAddSum(long n){
        long sum = 0;
        for (int i = 0; i <= n; ++i) {
            sum += i;
        }
        return sum;
    }

    private static String processFiles(processFile br,String fileName){
        try (BufferedReader nbr = new BufferedReader(new FileReader(fileName))){
            return br.process(nbr);
        } catch (Exception e) {
        }
        return null;
    }
    @Test
    public void testDemo20() {
        //long testNumber = 10_000_000;
        //String tailerString = " msecs";
        //
        //System.out.println(measureSum(Demo1::sequenceSum, testNumber)+tailerString);
        //System.out.println(measureSum(Demo1::oldAddSum,testNumber)+tailerString);
        //System.out.println(measureSum(Demo1::rangedSum, testNumber) + tailerString);
        //System.out.println(measureSum(Demo1::rangedParallelSum, testNumber) + tailerString);

        //String filename = "C:\\mavenProject\\DemoProject\\Core-javaTest\\src\\resource\\data.txt";
        //System.out.println(processFiles(br->br.readLine()+br.readLine(),filename));

    }

    private static long sideEffectSum(long n){
        Accumulator acc = new Accumulator();
        LongStream.rangeClosed(1,n).parallel().forEach(acc::add);
        return acc.total;
    }
    @Test
    public void testDemo21() {
        long testNumber = 10_000_000;
        String tailerString = " msecs";
        System.out.println(measureSum(Demo1::sideEffectSum, testNumber) + tailerString);
    }

    private static long forkJoinSum(long n){
        long[] numbers = LongStream.rangeClosed(1,n).toArray();
        ForkJoinTask<Long> task = new ForkJoinSumCalculator(numbers);
        return new ForkJoinPool().invoke(task);
    }
    @Test
    public void testDemo22() {
        long testNumber = 10_000_000;
        String tailerString = " msecs";
        System.out.println(measureSum(Demo1::forkJoinSum,testNumber)+tailerString);
    }

    private static int getWorldCount(String s){
        int counter = 0;
        boolean lastSpace = true;
        for(char c: s.toCharArray()){
            if(Character.isWhitespace(c)){
                lastSpace = true;
            }else{
                if(lastSpace) counter++;
                lastSpace = false;
            }
        }
        return counter;
    }
    private static int countWords(Stream<Character> stream){
        WordCounter wordCounter = stream.reduce(new WordCounter(0,true),WordCounter::accumulate,WordCounter::combine);
        return wordCounter.getCounter();
    }
    @Test
    public void testDemo23() {
        String str = "Nel   mezzo del cammin   " +
                " di nostra  vita mi ritrovai in una"
                +" selva oscura che la dritta via er smarrita ";

        System.out.println(getWorldCount(str) + " words");
        Stream<Character> stream = IntStream.range(0,str.length()).mapToObj(str::charAt);
        System.out.println(countWords(stream) + " words");

        Spliterator<Character> spliterator = new WordCounterSpliterator(str);
        Stream<Character> newStream = StreamSupport.stream(spliterator,true);
        System.out.println(countWords(newStream)+" new words");

    }
}
enum CaloricLevel{
    DIET,NORMAL,FAT
}

@FunctionalInterface
interface processFile{
    String process(BufferedReader br) throws IOException;
}

class Accumulator{
    public long total = 0;
    public void add(long value) {total += value;}
}

class ForkJoinSumCalculator extends RecursiveTask<Long>{

    private long[] numbers;
    private int start;
    private int end;

    private static final long THRESHOLD = 10_000;

    public ForkJoinSumCalculator(long[] numbers){
        this(numbers,0,numbers.length);
    }
    public ForkJoinSumCalculator(long[] numbers, int start, int end) {
        this.numbers = numbers;
        this.start = start;
        this.end = end;
    }

    private long computeSequence(){
        long sum = 0;
        for (int i = start; i < end; ++i) {
            sum += numbers[i];
        }
        return sum;
    }

    @Override
    protected Long compute() {

        int length = end - start;
        if(length <= THRESHOLD){
            return computeSequence();
        }
        ForkJoinSumCalculator leftTask = new ForkJoinSumCalculator(numbers,start,start+(length>>1));
        leftTask.fork();
        ForkJoinSumCalculator rightTask = new ForkJoinSumCalculator(numbers,start+(length>>1),end);

        long rightResult = rightTask.compute();
        long leftResult = leftTask.join();

        return leftResult+rightResult;
    }
}

class WordCounter{
    private int counter;
    private boolean lastSpace;

    public WordCounter(int counter, boolean lastSpace) {
        this.counter = counter;
        this.lastSpace = lastSpace;
    }

    //init --> new WordCounter(0,true)
    public WordCounter accumulate(Character c){
        if(Character.isWhitespace(c)){
            return lastSpace ? this:new WordCounter(counter,true);
        }else{
            return lastSpace ? new WordCounter(counter+1,false):this;
        }
    }
    public WordCounter combine(WordCounter wordCounter){
        return new WordCounter(counter + wordCounter.counter,wordCounter.lastSpace);
    }
    public int getCounter(){
        return counter;
    }
}
class WordCounterSpliterator implements Spliterator<Character>{

    private final String string;
    private int currentChar = 0;

    public WordCounterSpliterator(String string) {
        this.string = string;
    }

    @Override
    public boolean tryAdvance(java.util.function.Consumer<? super Character> action) {
        action.accept(string.charAt(currentChar++));
        return currentChar < string.length();
    }

    @Override
    public Spliterator<Character> trySplit() {
        int currentSize = string.length() - currentChar;
        if(currentSize < 10){
            return null;
        }
        for(int splitPos = currentSize/2+currentChar;splitPos < string.length();splitPos++){
            Spliterator<Character> spliterator = new WordCounterSpliterator(string.substring(currentChar,splitPos));
            currentChar = splitPos;
            return spliterator;
        }
        return null;
    }

    @Override
    public long estimateSize() {
        return string.length() - currentChar;
    }

    @Override
    public int characteristics() {
        return ORDERED+SIZED+SUBSIZED+NONNULL+IMMUTABLE;
    }
}
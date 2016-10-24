package com.yangyang.java8;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveTask;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.LongStream;
import java.util.stream.Stream;

@FunctionalInterface
interface BufferReaderProcessor{
    String process(BufferedReader br) throws IOException;
}

@FunctionalInterface
interface Predicate<T>{
    boolean test(T t);
}

interface myTask{
    void execute();
}
class Dish{

    public enum Type{MEAT,FISH,OTHER}

    private final String name;
    private final boolean vegetarian;
    private final int colories;
    private final Type type;

    public Dish(String name, boolean vegetarian, int colories, Type type) {
        this.name = name;
        this.vegetarian = vegetarian;
        this.colories = colories;
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public boolean isVegetarian() {
        return vegetarian;
    }

    public int getColories() {
        return colories;
    }

    public Type getType() {
        return type;
    }

    @Override
    public String toString() {
        return name;
    }
}
class Trader{
    private final String name;
    private final String city;

    public Trader(String name, String city) {
        this.name = name;
        this.city = city;
    }

    public String getName() {
        return name;
    }

    public String getCity() {
        return city;
    }

    @Override
    public String toString() {
        return "Trader: "+this.name+" in "+this.city;
    }
}
class Transaction{
    private final Trader trader;
    private final int year;
    private final int value;

    public Transaction(Trader trader, int year, int value) {
        this.trader = trader;
        this.year = year;
        this.value = value;
    }

    public Trader getTrader() {
        return trader;
    }

    public int getYear() {
        return year;
    }

    public int getValue() {
        return value;
    }

    @Override
    public String toString() {
        return "Transaction{" +
                "trader=" + trader +
                ", year=" + year +
                ", value=" + value +
                '}';
    }
}
public class Demo {

    private static final String path = "C:\\mavenProject\\DemoProject\\QrCode\\src\\main\\resources\\data.txt";
    private static Consumer print = System.out::println;
    private static BiConsumer printMap = (k,v)-> System.out.println(k+" : "+v);
    private static List<Transaction> transactions;
    static {
        Trader raoul = new Trader("Roual","Cabridge");
        Trader mario = new Trader("Mario","Milan");
        Trader alan = new Trader("Alan","Cabridge");
        Trader brian = new Trader("Brain","Cabridge");

         transactions = Arrays.asList(
                new Transaction(brian,2011,300),
                new Transaction(raoul,2012,1000),
                new Transaction(raoul,2011,400),
                new Transaction(mario,2012,710),
                new Transaction(mario,2012,700),
                new Transaction(alan,2012,950)
        );

    }
    private static List<Dish> menu = Arrays.asList(
            new Dish("pork",false,800, Dish.Type.MEAT),
            new Dish("beef",false,700, Dish.Type.MEAT),
            new Dish("chicken",false,400, Dish.Type.MEAT),
            new Dish("french",true,530, Dish.Type.OTHER),
            new Dish("rice",true,350, Dish.Type.OTHER),
            new Dish("season",true,120, Dish.Type.OTHER),
            new Dish("pizza",true,550, Dish.Type.OTHER),
            new Dish("prawns",false,300, Dish.Type.FISH),
            new Dish("salmon",false,450, Dish.Type.FISH)
    );

    private static <T> void printColl(Collection<T> coll){
        coll.forEach(System.out::println);
    }
    private static String processFile(BufferReaderProcessor brp) throws IOException{
        try(BufferedReader br = new BufferedReader(new FileReader(path))){
            return brp.process(br);
        }
    }

    private static <T> List<T> filter(List<T> list,Predicate<T> p){
        List<T> res = new ArrayList<T>();
        for(T t:list){
            if(p.test(t)) res.add(t);
        }
        return res;
    }

    private static <T,R> List<R> map(List<T> list,Function<T,R> fun){
        List<R> res = new ArrayList<R>();
        for(T t:list){
            res.add(fun.apply(t));
        }
        return res;
    }


    public static void main(String[] args) throws IOException {

        //Function<Integer,Integer> f = x ->x+1;
        //Function<Integer,Integer> g = x -> x * 2;
        //System.out.println(f.andThen(g).apply(2)); //g(f(x)) -> 6
        //System.out.println(f.compose(g).apply(2)); //f(g(x)) -> 5

        //List<Integer> number1 = Arrays.asList(1,2,3);
        //List<Integer> number2 = Arrays.asList(3,4);
        //
        //List<int[]> pairs = number1.stream()
        //        .flatMap(i -> number2.stream().filter(j->(i+j)%3==0).map(j -> new int[]{i,j}))
        //        .collect(Collectors.toList());
        //pairs.forEach(e-> System.out.println(e[0] + ":" + e[1]));

        //List<String> words = Arrays.asList("hello","world");
        //words.stream()
        //        .map(w->w.split(""))
        //        .flatMap(Arrays::stream)
        //        .distinct()
        //        .forEach(print);

        //menu.stream().filter(Dish::isVegetarian).findAny().ifPresent(print);

        //List<Integer> someNumbers = Arrays.asList(1,6,2,3,4,5);
        //someNumbers.stream().map(e->e*e).filter(e->e%3==0).findFirst().ifPresent(print);

        //int sum = someNumbers.stream().reduce(0,Integer::sum);
        //System.out.println("sum: "+sum);
        //
        //someNumbers.stream().reduce(Integer::max).ifPresent(print);
        //
        //long count  = menu.stream().count();
        //System.out.println("count: "+count);


        //1
        //transactions.stream().filter(e->e.getYear() == 2011)
        //        .sorted(Comparator.comparing(Transaction::getValue))
        //        .forEach(print);

        //2
        //transactions.stream().
        //        map(Transaction::getTrader)
        //        .map(Trader::getCity).distinct()
        //        .forEach(print);

        //3
        //transactions.stream()
        //        .map(Transaction::getTrader)
        //        .filter(e->e.getCity().equals("Cabridge"))
        //        .sorted(Comparator.comparing(Trader::getName))
        //        .forEach(print);
        //4
        //String names = transactions.stream()
        //        .map(Transaction::getTrader)
        //        .map(Trader::getName)
        //        .sorted(Comparator.naturalOrder())
        //        .collect(Collectors.joining(" ,","[","]"));
        //System.out.println("name: "+names);

        //5
        //transactions.stream()
        //        .map(Transaction::getTrader)
        //        .filter(e->e.getCity().equals("Milan"))
        //        .findAny().ifPresent(print);
        //6
        //transactions.stream()
        //        .filter(e->e.getTrader().getCity().equals("Cabridge"))
        //        .forEach(e-> System.out.println(e.getValue()));

        //7
        //transactions.stream()
        //        .map(Transaction::getValue)
        //        .reduce(Integer::min)
        //        .ifPresent(print);


        //Stream<int[]> pythagoreanTriples = IntStream.rangeClosed(1,100).boxed()
        //        .flatMap(a->IntStream.rangeClosed(a,100)
        //                .filter(b->Math.sqrt(a*a+b*b)%1==0)
        //                .mapToObj(b->new int[]{a,b,(int) Math.sqrt(a*a+b*b)}));
        //pythagoreanTriples.limit(5).forEach(e-> System.out.println(e[0] + " : " + e[1] + " : " + e[2]));

        //Stream<String> stream = Stream.of("java","in","action");
        //stream.map(String::toUpperCase).forEach(print);

        //int[] numbers = {2,3,5,7,11,13};
        //long sum = Arrays.stream(numbers).sum();
        //System.out.println("sum: "+sum);

        //String filePath = "C:\\code\\java\\users.txt";
        //long uniquewwords = Files.lines(Paths.get(filePath)).flatMap(line->Arrays.stream(line.split(" "))).distinct().count();
        //System.out.println("words: "+uniquewwords);

        //Stream.iterate(0,n->n+1).limit(10).forEach(print);
        //Stream.iterate(new int[]{0,1},t->new int[]{t[1],t[0]+t[1]})
        //        .limit(10)
        //        .map(t->t[0])
        //        .forEach(print);

        //Stream.generate(Math::random).limit(5).forEach(print);

        //IntSupplier fib = new IntSupplier() {
        //    private int previous = 0;
        //    private int current = 1;
        //
        //    @Override
        //    public int getAsInt() {
        //        int oldPrevious = this.previous;
        //        int nextValue = this.previous+this.current;
        //        this.previous = current;
        //        this.current = nextValue;
        //        return oldPrevious;
        //    }
        //};
        //IntStream.generate(fib).limit(100).forEach(System.out::println);


        //Map<Integer,List<Transaction>> tps = transactions.stream().collect(Collectors.groupingBy(Transaction::getYear));
        //tps.forEach(printMap);

        //menu.stream()
        //        .collect(Collectors.maxBy(Comparator.comparingInt(Dish::getColories)))
        //        .ifPresent(print);
        //
        //menu.stream().map(Dish::getColories).reduce(Integer::max).ifPresent(print);

        //long totalCalories = menu.stream()
        //        .collect(Collectors.summingInt(Dish::getColories));
        //System.out.println(totalCalories);
        //
        //IntSummaryStatistics summaryStatistics = menu.stream().collect(Collectors.summarizingInt(Dish::getColories));
        //
        //System.out.println(summaryStatistics.getAverage()+" : "+summaryStatistics.getCount()+":"+summaryStatistics.getMax());


        //String names = menu.stream().map(Dish::getName).collect(Collectors.joining(" ,","[","]"));
        //System.out.println(names);

        //menu.stream().collect(Collectors.reducing((d1,d2)->d1.getColories()>d2.getColories()?d1:d2)).ifPresent(print);

        //Map<Dish.Type,List<Dish>> ds = menu.stream().collect(Collectors.groupingBy(Dish::getType));
        //ds.forEach(printMap);


        //Map<ColoricLevel,List<Dish>> cs = menu.stream().collect(Collectors.groupingBy(dish->{
        //    if(dish.getColories() <= 400) return ColoricLevel.DIET;
        //    else if(dish.getColories() <= 700)return ColoricLevel.NORMAL;
        //    else return ColoricLevel.FAT;
        //}));
        //cs.forEach(printMap);

        //Map<Dish.Type,Map<ColoricLevel,List<Dish>>> dds = menu.stream().collect(Collectors.groupingBy(Dish::getType,
        //        Collectors.groupingBy(dish->{
        //            if(dish.getColories() <= 400) return ColoricLevel.DIET;
        //            else if(dish.getColories() <= 700)return ColoricLevel.NORMAL;
        //            else return ColoricLevel.FAT;
        //        })));
        //dds.forEach(printMap);

        //Map<Dish.Type,Long> ds = menu.stream()
        //        .collect(Collectors.groupingBy(Dish::getType,Collectors.counting()));
        //ds.forEach(printMap);

        //Map<Dish.Type,Dish> dds = menu.stream()
        //        .collect(Collectors.groupingBy(Dish::getType,
        //                Collectors.collectingAndThen(Collectors.maxBy(Comparator.comparing(Dish::getColories)),
        //        Optional::get)));
        //
        //dds.forEach(printMap);

        //Map<Dish.Type,Set<ColoricLevel>> dds =
        //menu.stream().collect(Collectors.groupingBy(Dish::getType,Collectors.mapping(dish->{
        //    if(dish.getColories() <= 400) return ColoricLevel.DIET;
        //    else if(dish.getColories() <=700) return ColoricLevel.NORMAL;
        //    else return ColoricLevel.FAT;
        //},Collectors.toCollection(HashSet::new))));
        //dds.forEach(printMap);

        //Map<Boolean,List<Dish>> dds = menu.stream().collect(Collectors.partitioningBy(Dish::isVegetarian));
        //dds.forEach(printMap);
        //List<Dish> ds = dds.get(true);
        //ds.forEach(print);

        //Map<Boolean,Map<Dish.Type,List<Dish>>> dds = menu.stream().collect(Collectors.partitioningBy(Dish::isVegetarian,Collectors.groupingBy(Dish::getType)));
        //dds.forEach(printMap);


        //menu.stream().collect(ArrayList::new,List::add,List::addAll).forEach(print);

        //partitionPrimers(100).forEach(printMap);


        long number = 1000_000;
        //System.out.println(meausureSum(Demo::sqeuetialSum,number));
        //System.out.println(meausureSum(Demo::iteratorSum,number));
        //System.out.println(meausureSum(Demo::randSum,number));

        //System.out.println(meausureSum(Demo::forkJoinSum,number));

        //String s = "hello world     hello yangyang";
        //System.out.println(countWordsIteratively(s));

        //doSome((Runnable) ()-> System.out.println("hello world"));

        //List<Integer> numbers = Arrays.asList(3,5,1,2,6);
        //numbers.sort(Comparator.naturalOrder());
        //
        //printColl(numbers);


        //Insurance insurance = new Insurance("ping an baoxian");
        //Person yangyang = new Person(Optional.of(new Car(Optional.empty())));
        //System.out.println(getCarInsuranceName(Optional.ofNullable(yangyang)));

        //Optional<Insurance> optInsurance = Optional.of(new Insurance("java"));
        //optInsurance.filter(e->e.getName().equals("java")).ifPresent(x -> System.out.println("ok"));

        //stringToInt("123").ifPresent(e-> System.out.println(e));

        Properties props = new Properties();
        props.put("a","5");
        props.put("b","true");
        props.put("c","-3");

        System.out.println(readDuration(props,"a"));
        System.out.println(readDuration(props,"b"));
        System.out.println(readDuration(props,"c"));

    }
    public static int readDuration(Properties props,String name){
        String value = props.getProperty(name);
        if(value != null){
            try {
                int i = Integer.parseInt(value);
                if( i>0) return i;
            } catch (NumberFormatException e) {
            }
        }
        return 0;
    }
    public static Optional stringToInt(String s){
        try {
            return Optional.of(Integer.parseInt(s));
        } catch (NumberFormatException e) {
            return Optional.empty();
        }
    }
    public static String getCarInsuranceName(Optional<Person> person){
        return person.flatMap(Person::getCar).flatMap(Car::getInsurance).map(Insurance::getName).orElse("Unkown");
    }
    static class Person{
        private Optional<Car> car;

        public Person() {
        }

        public Person(Optional<Car> car) {
            this.car = car;
        }

        public Optional<Car> getCar() {
            return car;
        }
    }
    static class Car{
        private Optional<Insurance> insurance;

        public Car() {
        }

        public Car(Optional<Insurance> insurance) {
            this.insurance = insurance;
        }

        public Optional<Insurance> getInsurance() {
            return insurance;
        }
    }
    static class Insurance{
        private String name;

        public Insurance() {
        }

        public Insurance(String name) {
            this.name = name;
        }

        public String getName() {
            return name;
        }
    }


    private static int divideByZero(int n){
        return n/0;
    }
    private static void doSome(Runnable r){r.run();}
    private static void doSome(myTask task){task.execute();}
    class WordCounter{

        private final int counter;
        private final boolean lastSpace;
        public WordCounter(int counter,boolean lastSpace){
            this.counter = counter;
            this.lastSpace = lastSpace;
        }
        public WordCounter accumulate(Character c){
            if(Character.isWhitespace(c))
                return lastSpace?this:new WordCounter(counter,true);
            else
                return lastSpace?new WordCounter(counter+1,false):this;
        }
        public WordCounter combin(WordCounter wordCounter){
            return new WordCounter(counter+wordCounter.counter,wordCounter.lastSpace);
        }
        public int getCounter(){return counter;}

    }
    private static int countWordsIteratively(String s){
        int counter = 0;
        boolean lastSpace = true;
        for(char c : s.toCharArray()){
            if(Character.isWhitespace(c)) lastSpace = true;
            else {
                if(lastSpace) counter++;
                lastSpace = false;
            }
        }
        return counter;
    }
    private static long forkJoinSum(long n){
        long[] numbers = LongStream.rangeClosed(1,n).toArray();
        ForkJoinTask<Long> task = new ForkJoinSumCalculator(numbers);
        return new ForkJoinPool().invoke(task);
    }
    static class ForkJoinSumCalculator extends RecursiveTask<Long>{

        private final long[] numbers;
        private final int start;
        private final int end;
        public static final long THRESHOLD = 10_000;

        public ForkJoinSumCalculator(long[] numbers){
            this(numbers,0,numbers.length);
        }
        private ForkJoinSumCalculator(long[] numbers,int start,int end){
            this.numbers = numbers;
            this.start = start;
            this.end = end;
        }

        private long computeSequence(){
            long sum = 0;
            for(int i =start;i<end;i++){
                sum += numbers[i];
            }
            return sum;
        }
        @Override
        protected Long compute() {
            int length = end - start;
            if(length <= THRESHOLD) return computeSequence();
            ForkJoinSumCalculator leftTask = new ForkJoinSumCalculator(numbers,start,start+length/2);
            leftTask.fork();
            ForkJoinSumCalculator rightTask = new ForkJoinSumCalculator(numbers,start+length/2,end);

            Long rightResult = rightTask.compute();
            Long leftResult = leftTask.join();
            return rightResult+leftResult;
        }
    }


    private static long iteratorSum(long n){
        long result = 0;
        for(long i = 0l;i<=n;i++){
            result+=i;
        }
        return result;
    }
    private static long randSum(long n){
        return LongStream.rangeClosed(0,n).parallel().sum();
    }
    private static long sqeuetialSum(long n){
        return Stream.iterate(1l,i->i+1).limit(n).parallel().reduce(0l,Long::sum);
    }
    private static long meausureSum(Function<Long,Long> adder,long n){
        long faster = Long.MAX_VALUE;
        long sum = 0 ;
        for (int i = 0; i < 10; ++i) {
            long start = System.nanoTime();
            sum = adder.apply(n);
            long duration = (System.nanoTime()-start)/1000_000;
            if(duration < faster) faster = duration;
        }
        System.out.println("result: "+sum);
        return faster;
    }
    private static boolean isPrime(int candicate){
        int candicateRoot = (int) Math.sqrt(candicate);
        return IntStream.rangeClosed(2,candicateRoot).noneMatch(i->candicate%i==0);
    }
    private static Map<Boolean,List<Integer>> partitionPrimers(int n){
        return IntStream.rangeClosed(2,n).boxed()
                .collect(Collectors.partitioningBy(i->isPrime(i)));
    }
    enum ColoricLevel {DIET,NORMAL,FAT}
}

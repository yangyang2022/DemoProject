package com.yangyang.java8;

import org.junit.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.temporal.*;
import java.util.*;
import java.util.function.DoubleUnaryOperator;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.IntStream;
import java.util.stream.LongStream;
import java.util.stream.Stream;

public class DemoTime {

    private static void showTimes(Object ...args){
        Arrays.stream(args).forEach(System.out::println);
    }
    @Test
    public void testDemo1() {
        //LocalDate data1 = LocalDate.parse("2011/09/09", DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        //System.out.println(data1);

        LocalDate data = LocalDate.parse("2014/03/08", DateTimeFormatter.ofPattern("yyyy/MM/dd"));
        LocalTime time = LocalTime.parse("13:45:20");

        LocalDateTime dt1 = LocalDateTime.of(2014, Month.MARCH,18,13,45,20);
        LocalDateTime dt2 = LocalDateTime.of(data,time);
        LocalDateTime dt3 = data.atTime(13,45,20);
        LocalDateTime dt4 = data.atTime(time);
        LocalDateTime dt5 = time.atDate(data);

        //showTimes(data,time,dt1,dt2,dt3,dt4,dt5);

        LocalDate data1 = dt1.toLocalDate();
        LocalTime time1 = dt1.toLocalTime();
        showTimes(data1,time1);

    }

    @Test
    public void testDemo2() {
        Instant it1 = Instant.ofEpochSecond(3);
        Instant it2 = Instant.ofEpochSecond(3,0);
        Instant it3 = Instant.ofEpochSecond(2,1_000_000_000);
        Instant it4 = Instant.ofEpochSecond(4,-1_000_000_000);

        showTimes(it1,it2,it3,it4);

    }

    @Test
    public void testDemo3() {
        Period period = Period.between(LocalDate.of(2015, 8, 1),LocalDate.now());
        System.out.println("days: "+period.getDays()+period.getYears());
    }

    @Test
    public void testDemo4() {
        //LocalDate data1 = LocalDate.of(2014, 3, 18);
        //LocalDate date2 = data1.withYear(2011);
        //LocalDate date3 = date2.withDayOfMonth(25);
        //LocalDate date4 = date3.with(ChronoField.MONTH_OF_YEAR,9);
        //showTimes(data1,date2,date3,date4);

        LocalDate date1 = LocalDate.of(2014,12,28);
        LocalDate date2 = date1.plusWeeks(1);
        LocalDate date3 = date2.minusYears(3);
        LocalDate date4 = date3.plus(6, ChronoUnit.MONTHS);

        showTimes(date1,date2,date3,date4);

    }

    @Test
    public void testDemo5() {
        LocalDate data1 = LocalDate.of(2016, 8, 12);
        LocalDate date2 = data1.with(TemporalAdjusters.nextOrSame(DayOfWeek.SUNDAY));
        LocalDate date3 = date2.with(TemporalAdjusters.lastDayOfMonth());

        //nextWorkingDay ---
        LocalDate date4 = data1.with(new NextWorkingDay());

        TemporalAdjuster t = (Temporal temporal)->{
            DayOfWeek dow = DayOfWeek.of(temporal.get(ChronoField.DAY_OF_WEEK));
            int dayToAdd = 1;
            if(dow == DayOfWeek.FRIDAY) dayToAdd = 3;
            else if(dow == DayOfWeek.SATURDAY) dayToAdd = 2;
            return temporal.plus(dayToAdd,ChronoUnit.DAYS);
        } ;
        TemporalAdjuster nextWorkingDay = TemporalAdjusters.ofDateAdjuster(
                temporal->{
                    DayOfWeek dow = DayOfWeek.of(temporal.get(ChronoField.DAY_OF_WEEK));
                    int dayToAdd = 1;
                    if(dow == DayOfWeek.FRIDAY) dayToAdd = 3;
                    else if(dow == DayOfWeek.SATURDAY) dayToAdd = 2;
                    return temporal.plus(dayToAdd,ChronoUnit.DAYS);
                }
        );

        LocalDate date5 = data1.with(t);
        LocalDate date6 = data1.with(nextWorkingDay);

        showTimes(data1,date2,date3,date4,date5,date6);

    }

    @Test
    public void testDemo6() {
        LocalDate date = LocalDate.of(2014, 3, 18);
        String s1 = date.format(DateTimeFormatter.BASIC_ISO_DATE);
        String s2 = date.format(DateTimeFormatter.ISO_LOCAL_DATE);

        LocalDate date1 = LocalDate.parse("20140318",DateTimeFormatter.BASIC_ISO_DATE);
        LocalDate date2 = LocalDate.parse("2014-03-18",DateTimeFormatter.ISO_LOCAL_DATE);
        showTimes(s1,s2,date1,date2);

        System.out.println("-------------------------------------------");
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        String formatDate = date.format(formatter);
        LocalDate dateFormate = LocalDate.parse(formatDate,formatter);
        showTimes(formatDate,dateFormate);
    }

    @Test
    public void testDemo7() {
        DateTimeFormatter formatter = new DateTimeFormatterBuilder()
                .appendText(ChronoField.DAY_OF_MONTH)
                .appendLiteral(".")
                .appendText(ChronoField.MONTH_OF_YEAR)
                .appendLiteral(".")
                .appendText(ChronoField.YEAR)
                .parseCaseInsensitive()
                .toFormatter(Locale.ITALIAN);

        LocalDate date = LocalDate.of(2014,3,18);
        showTimes(date.format(formatter));

    }

    private static int handStr(String s){
        Matcher m = Pattern.compile("\\d+").matcher(s);
        while (m.find()){
            System.out.println(Integer.parseInt(m.group()));
            nums.add(Integer.parseInt(m.group()));
        }
        return 0;
    }
    private static List<Integer> nums = new ArrayList<>();
    @Test
    public void testDemo8() throws IOException {

        String filePath = "C:\\mavenProject\\DemoProject\\Core-javaTest\\src\\resource\\data.txt";
        Files.readAllLines(Paths.get(filePath)).forEach(e->handStr(e));

        System.out.println("sum: "+nums.stream().mapToInt(e->e).sum());
    }

    private static Optional<Double> getDivide(double num1,double num2){
        return Optional.of(num1/num2);
    }
    @Test
    public void testDemo9() {
        getDivide(10,0).ifPresent(System.out::print);
    }

    private static List<List<Integer>> concat(List<List<Integer>> list1,List<List<Integer>> list2){
        List<List<Integer>> r = new ArrayList<>(list1);
        r.addAll(list2);
        return r;
        //list1.addAll(list2);
        //return list1;
    }
    private static List<List<Integer>> insertAll(Integer first,List<List<Integer>> lists){
        List<List<Integer>> result = new ArrayList<>();
        for(List<Integer> list:lists){
            List<Integer> copyList = new ArrayList<>();
            copyList.add(first);
            copyList.addAll(list);
            result.add(copyList);
        }
        return result;
    }
    private static List<List<Integer>> subsets(List<Integer> list){
        if(list.isEmpty()){
            List<List<Integer>> ans = new ArrayList<>();
            ans.add(Collections.emptyList());
            return ans;
        }
        Integer first = list.get(0);
        List<Integer> rest = list.subList(1,list.size());

        List<List<Integer>> subans = subsets(rest);
        List<List<Integer>> subans2 = insertAll(first,subans);
        return concat(subans,subans2);
    }
    @Test
    public void testDemo10() {

        System.out.println(subsets(Arrays.asList(1, 4, 9)));

    }

    private static long factoriallStream(long n){
        return LongStream.rangeClosed(1,n).reduce(1,(a,b)->a*b);
    }
    private static long factorialRecursive(long n){
        return n == 1?1:n*factorialRecursive(n-1);
    }
    @Test
    public void testDemo11() {
        System.out.println(factoriallStream(500));
        System.out.println(factorialRecursive(500));
    }

    @Test
    public void testDemo12() {
        String s = "123 hello world 12 ni3hao 9";
        Matcher m = Pattern.compile("(\\d+)(.*)").matcher(s);
        System.out.println(m.groupCount());

        long count = Arrays.asList(s.split(" ")).stream()
                .filter(e->e.matches("\\d+"))
                .mapToInt(Integer::parseInt)
                .sum();
        System.out.println("count: "+count);

    }

    private static DoubleUnaryOperator curriedCoverter(double f,double b){
        return (double x)->x*f+b;
    }
    @Test
    public void testDemo13() {
        DoubleUnaryOperator cToF = curriedCoverter(9.0/5,32);
        System.out.println(cToF.applyAsDouble(30));
    }

    private static Train TrainLink(Train a,Train b){
        if(a == null) return b;
        Train temp = a;
        while (temp.onward != null){
            temp = temp.onward;
        }
        temp.onward = b;
        return a;
    }

    private static Train append(Train a,Train b){
        return a == null ? b:new Train(a.place,append(a.onward,b));
    }
    private static void showRoad(Train t){
        while (t != null){
            System.out.println(t.place);
            t = t.onward;
        }
    }
    @Test
    public void testDemo14() {

        Train t1 = new Train("巢湖",null);
        Train t2 = new Train("蚌埠",t1);
        Train t3 = new Train("宿州",t2);

        //showRoad(t3);

        Train tt1 = new Train("北京",null);
        Train tt2 = new Train("南京",tt1);
        Train tt3 = new Train("徐州",tt2);

        //showRoad(tt3);

        //showRoad(TrainLink(t3,tt3));

        showRoad(append(t3,tt3));
    }

    private static void showTree(Tree tree){
        if(tree != null){
            showTree(tree.left);
            System.out.println(tree.value);
            showTree(tree.right);
        }
    }
    private static void showValue(int value){
        System.out.println("value: "+value);
    }
    @Test
    public void testDemo15() {
        Tree t1 = new Tree("shen",10,null,null);
        Tree t2 = new Tree("world",20,null,null);
        Tree t3 = new Tree("yang",30,t1,t2);
        Tree t4 = new Tree("hello",40,t3,t1);

        //showTree(t4);
        String key = "yang";
        showValue(TreeProcessor.lookup(key,-1,t4));
        Tree newTree = TreeProcessor.fupdate(key,99,t4);
        showValue(TreeProcessor.lookup(key,-1,newTree));
    }

    private static boolean isPrime(int candidate){
        int candidateRoot = (int) Math.sqrt(candidate);
        return IntStream.rangeClosed(2,candidateRoot).noneMatch(i -> candidate%i == 0 );
    }
    private static Stream<Integer> primes(int n){
        return Stream.iterate(2,i->i+1).filter(DemoTime::isPrime).limit(n);
    }

    private static int head(IntStream numbers){
        return numbers.findFirst().getAsInt();
    }
    private IntStream tail(IntStream numbers){
        return numbers.skip(1);
    }


    @Test
    public void testDemo16() {
        primes(10).forEach(System.out::println);
    }

    private static void showList(MyList<Integer> list){
        while (!list.isEmpty()){
            System.out.println(list.head());
            list = list.tail();
        }
    }
    private static void showListRecursive(MyList<Integer> list){
        if(!list.isEmpty()){
            System.out.println(list.head());
            showListRecursive(list.tail());
        }
    }

    private static MyList<Integer> appendList(MyList<Integer> list1,MyList<Integer> list2){
        return list1.isEmpty() ? list2 : new MyLinkList<>(list1.head(),appendList(list1.tail(),list2));
    }

    @Test
    public void testDemo17() {

        MyList<Integer> list = new MyLinkList<>(5,new MyLinkList<>(10,new MyLinkList<>(15,new EmptyList<>())));
        MyList<Integer> list2 = new MyLinkList<>(1,new MyLinkList<>(2,new MyLinkList<>(3,new EmptyList<>())));

        //showList(appendList(list,list2));
        //showListRecursive(appendList(list,list2));

        showListRecursive(list2);
        showListRecursive(list2.tail());

    }

    private static LazyList<Integer> from(int n){
        return new LazyList<>(n,()->from(n+1));
    }
    private static MyList<Integer> primes(MyList<Integer> nums){
        return new LazyList<>(nums.head(),()->primes(nums.tail().filter(n->n%nums.head() != 0 )));
    }

    @Test
    public void testDemo18() {
        //LazyList<Integer> numbers = from(2);
        //System.out.println(numbers.head() + " - " + numbers.tail().head() + " : " + numbers.tail().tail().head());

        showListRecursive(primes(from(2)));
    }

    private static boolean biserachelper(int[] arr,int key,int low,int high){
        int mid = (low+high)>>1;
        if(low > high) return false;
        int rs = Integer.compare(key ,arr[mid]) ;
        return  rs == 0 ? true : rs < 0 ? biserachelper(arr,key,low,mid-1):biserachelper(arr,key,mid+1,high);
    }
    private static boolean biserach(int[] arr,int key){
        return biserachelper(arr,key,0,arr.length-1);
    }
    private static int gcd(int m,int n){
        return  n == 0 ? m :gcd(n,m%n);
    }

    @Test
    public void testDemo19() {

        //int[] arr = {1,2,3,4,5,6,7,8,9,0};
        //int key = 65;
        //
        //System.out.println(biserach(arr, key));

        System.out.println(gcd(15, 12));

    }
}

interface MyList<T>{
    T head();
    MyList<T> tail();
    MyList<T> filter(Predicate<T> p);
    default boolean isEmpty(){return true;}
}
class LazyList<T> implements MyList<T>{
    private T head;
    private Supplier<MyList<T>> tail;

    public LazyList() {
    }

    public LazyList(T head, Supplier<MyList<T>> tail) {
        this.head = head;
        this.tail = tail;
    }

    @Override
    public T head() {
        return this.head;
    }

    @Override
    public MyList<T> tail() {
        return this.tail.get();
    }

    @Override
    public boolean isEmpty() {
        return false;
    }

    @Override
    public MyList<T> filter(Predicate<T> p) {
        return isEmpty()?this:p.test(head())?
                new LazyList<>(head(),()->tail().filter(p)):
                tail().filter(p);
    }
}
class MyLinkList<T> implements MyList<T>{

    private T head;
    private MyList<T> tail;

    public MyLinkList() {
    }

    public MyLinkList(T head, MyList<T> tail) {
        this.head = head;
        this.tail = tail;
    }

    @Override
    public T head() {
        return this.head;
    }

    @Override
    public MyList<T> tail() {
        return this.tail;
    }

    @Override
    public MyList<T> filter(Predicate<T> p) {
        return null;
    }

    @Override
    public boolean isEmpty() {
        return false;
    }
}
class EmptyList<T> implements MyList<T>{
    @Override
    public T head() {
        throw new UnsupportedOperationException();
    }

    @Override
    public MyList<T> tail() {
        throw new UnsupportedOperationException();
    }

    @Override
    public MyList<T> filter(Predicate<T> p) {
        return null;
    }
}
class Tree{
    public String key;
    public int value;
    public Tree left,right;

    public Tree(String key, int value, Tree left, Tree right) {
        this.key = key;
        this.value = value;
        this.left = left;
        this.right = right;
    }
}
class TreeProcessor{

    public static int lookup(String key,int defaultValue,Tree tree){
        if(tree == null ) return defaultValue;
        if(key.equals(tree.key)) return tree.value;
        return lookup(key,defaultValue,key.compareTo(tree.key) < 0 ? tree.right:tree.left);
    }

    public static Tree update(String key,int newValue,Tree tree){

        if(tree == null) tree = new Tree(key,newValue,null,null);
        else if(key.equals(tree.key)){
            tree.value = newValue;
        }
        else if(key.compareTo(tree.key) >0 ){
            tree.left = update(key,newValue,tree.left);
        }else{
            tree.right = update(key,newValue,tree.right);
        }
        return tree;
    }
    public static Tree fupdate(String key,int newValue,Tree tree){
        return (tree == null )? new Tree(key,newValue,null,null):
                key.equals(tree.key)? new Tree(key,newValue,tree.left,tree.right):
                        key.compareTo(tree.key) > 0 ?
                        new Tree(key,newValue,fupdate(key,newValue,tree.left),tree.right):
                        new Tree(key,newValue,tree.left,fupdate(key,newValue,tree.right));

    }

}
class Train{
    public String place;
    public Train onward;

    public Train(String place, Train onward) {
        this.place = place;
        this.onward = onward;
    }
}
class NextWorkingDay implements TemporalAdjuster{
    @Override
    public Temporal adjustInto(Temporal temporal) {
        DayOfWeek dow = DayOfWeek.of(temporal.get(ChronoField.DAY_OF_WEEK));
        int dayToAdd = 1;
        if(dow == DayOfWeek.FRIDAY) dayToAdd = 3;
        else if(dow == DayOfWeek.SATURDAY) dayToAdd = 2;

        return temporal.plus(dayToAdd,ChronoUnit.DAYS);
    }
}
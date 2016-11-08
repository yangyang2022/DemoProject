package com.yangyang.effectiveJava;

import com.sun.istack.internal.NotNull;
import com.yangyang.annotation.ExceptionTest;
import com.yangyang.annotation.ShopDI;

import java.lang.annotation.Annotation;
import java.lang.reflect.AnnotatedElement;
import java.util.*;

class NutritionFacts{
    private final int serveingSize;
    private final int servings;
    private final int calories;
    private final int fat;
    private final int soduium;
    private final int carbohydrate;

    public static class Builder{
        //required params
        private final int serveingSize;
        private final int servings;

        //options params
        private int calories;
        private int fat;
        private int soduium;
        private int carbohydrate;

        public Builder(int serveingSize, int servings) {
            this.serveingSize = serveingSize;
            this.servings = servings;
        }
        public Builder calories(int val){
            calories = val;
            return this;
        }
        public Builder fat(int val){
            fat = val;
            return this;
        }
        public Builder carbohydrate(int val){
            carbohydrate = val;
            return this;
        }
        public Builder sodium(int val){
            soduium = val;
            return this;
        }
        public NutritionFacts build(){return new NutritionFacts(this);}
    }

    //constructor
    private NutritionFacts(Builder builder){
        serveingSize = builder.serveingSize;
        servings = builder.servings;

        calories = builder.calories;
        fat = builder.fat;
        soduium = builder.soduium;
        carbohydrate = builder.carbohydrate;
    }

    @Override
    public String toString() {
        return "NutritionFacts{" +
                "serveingSize=" + serveingSize +
                ", servings=" + servings +
                ", calories=" + calories +
                ", fat=" + fat +
                ", soduium=" + soduium +
                ", carbohydrate=" + carbohydrate +
                '}';
    }
}

enum Demo{
    INSTACE;
    public void dosome(){
        System.out.println("hello world");
    }
    public void hello(String str){
        System.out.println("hello "+str);
    }
}

class PhoneNumber{
    private final short areaCode;
    private final short prefix;
    private final short lineNumber;

    public PhoneNumber(int areaCode, int prefix, int lineNumber) {
        rangeCheck(areaCode,999,"area code");
        rangeCheck(prefix,999,"prefix");
        rangeCheck(lineNumber,999,"line number");
        this.areaCode = (short)areaCode;
        this.prefix = (short)(short)prefix;
        this.lineNumber = (short)lineNumber;
    }
    private static void rangeCheck(int arg,int max,String name){
        if(arg < 0 || arg > max)
            throw new IllegalArgumentException(name+" : "+arg);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PhoneNumber that = (PhoneNumber) o;

        if (areaCode != that.areaCode) return false;
        if (prefix != that.prefix) return false;
        return lineNumber == that.lineNumber;

    }

    @Override
    public int hashCode() {
        int result = (int) areaCode;
        result = 31 * result + (int) prefix;
        result = 31 * result + (int) lineNumber;
        return result;
    }
}

class Stack implements Cloneable{

    private Object[] elements;
    private int size = 0;
    private static final int DEFAULT_INITAL_CAPACITY = 16;

    public Stack() {
        this.elements = new Object[DEFAULT_INITAL_CAPACITY];
    }
    public void push(Object e){
        ensureCapacity();
        elements[size++] = e;
    }
    public Object pop(){
        if(size == 0) throw new EmptyStackException();
        Object res = elements[size];
        elements[size--] = null;
        return res;
    }
    private void ensureCapacity(){
        if(elements.length == size)
            elements = Arrays.copyOf(elements,(size<<1) | 1);
    }

    public int getSize(){return size;}

    @Override
    protected Stack clone() throws CloneNotSupportedException {
        try {
            Stack res = (Stack) super.clone();
            res.elements = elements.clone();
            return res;
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
            throw new AssertionError();
        }
    }
}

enum StringLengthComparator implements Comparator<String> {

    INSTANCE;
    @Override
    public int compare(String o1, String o2) {
        return o1.length() - o2.length();
    }
}
public class BuilderPattern {

    private static void unsafeAdd(List list,Object o){
        list.add(o);
    }
    private static List<Integer> intArrayList(final int[] arr){
        if(arr == null) throw new NullPointerException();
        return new AbstractList<Integer>() {
            @Override
            public Integer get(int index) {
                return arr[index];
            }

            @Override
            public int size() {
                return arr.length;
            }
        };
    }

    public static void main(String[] args) throws CloneNotSupportedException, ClassNotFoundException {

        //NutritionFacts facts = new NutritionFacts.Builder(240,80)
        //        .calories(100).sodium(35).fat(99)
        //        .carbohydrate(27).build();
        //System.out.println(facts);

        //Demo d1 = Demo.INSTACE;
        //Demo d2 = Demo.INSTACE;
        //
        //System.out.println(d1 == d2);

        //Map<PhoneNumber,String> maps = new HashMap<>();
        //maps.put(new PhoneNumber(123,123,123),"yangyang");
        //System.out.println(maps.get(new PhoneNumber(123, 123, 123)));

        //Stack stack1 = new Stack();
        //Stack stack2 = stack1.clone();
        //
        //stack1.push("hello");
        //stack1.push("world");
        //
        //System.out.println("size: "+stack1.getSize()+" size2: "+stack2.getSize());

        //String[] arr = {"hello","world","he","hehe"};
        //Arrays.sort(arr,StringLengthComparator.INSTANCE);
        //for(String s: arr) System.out.println(s);

        //List<String> ss = new ArrayList<>();
        //unsafeAdd(ss,"hello");
        //System.out.println(ss.get(0));


        //List<Integer> intList = Arrays.asList(1,2,3,4);

        //int result = (int)reduce(intList,(a,b)->(int)a+(int)b,0);
        //System.out.println("result: "+result);

        //int res = reduceE(intList,Integer::sum,0);
        //System.out.println(res);
        //
        //List<String> stringList = Arrays.asList("hello","world","hehe");
        //String sres = reduceLE(stringList,(a,b)->a+b+" ","");
        //System.out.println(sres);

        //StackE<Integer> stackE = new StackE<>();
        //stackE.push(1);
        //stackE.push(2);
        //stackE.push(3);
        //
        //while (!stackE.isEmpty()){
        //    System.out.println(stackE.pop());
        //}

        //Set<String> guys = new HashSet<>(Arrays.asList("Tom","Dick","Harry"));
        //Set<String> stooges = new HashSet<>(Arrays.asList("Larry","Moe","Curly"));
        //Set<String> hehe = union(guys,stooges);
        //
        //LambdaUtil.printCollection(hehe);

        //StackE<Number> stackE = new StackE<>();
        //System.out.println("size1: "+stackE.size);
        //
        //Iterable<Integer> iter = new ArrayList<>(Arrays.asList(1,2,3));
        //
        //stackE.pushAll(iter);
        //System.out.println("size2: " + stackE.size);
        //
        //List<Number> dst = new ArrayList<>();
        //stackE.popAll(dst);

        //Set<Integer> ints = new HashSet<>(Arrays.asList(1,2,3));
        //Set<Double> dous = new HashSet<>(Arrays.asList(1.1,2.2,3.3));
        //
        //Set<Number> dst = union(ints,dous);
        //LambdaUtil.printCollection(dst);

        //Favorite favorite = new Favorite();
        //favorite.putFavorite(String.class,"hello world");
        //favorite.putFavorite(Integer.class,123);
        //favorite.putFavorite(Class.class,Favorite.class);
        //
        //System.out.println(favorite.getFavorite(Integer.class));

        //Annotation shop = getAnnotation(Favorite.class,ShopDI.class.getName());
        //System.out.println(shop.annotationType().getName());
        //for(Class clz : shop.getClass().getInterfaces()){
        //    System.out.println(clz.getName());
        //}


        //double x = 2.0 ,y = 4.0;
        //for(Operation op : Operation.values()){
        //    System.out.println(op);
        //
        //    System.out.printf("%f %s %f = %f%n",x,op,y,op.apply(x,y));
        //}


        //double pay = PayRollDay2.MONDAY.pay(10,0.4);
        //System.out.println(pay);
        //System.out.println(PayRollDay2.SUNDAY.pay(10, 0.4));



        List<Herb> herbs = Arrays.asList(
                new Herb("plant1", Herb.Type.PER_YERA),
                new Herb("plant2", Herb.Type.PER_YERA),
                new Herb("plant3", Herb.Type.DOUBLE_YEAR),
                new Herb("plant4", Herb.Type.DOUBLE_YEAR),
                new Herb("plant5", Herb.Type.DOUBLE_YEAR),
                new Herb("plant6", Herb.Type.MULTI_YEAR)
                );

        //Map<Herb.Type,List<Herb>> hbs = herbs.stream()
        //        .collect(Collectors.groupingBy(Herb::getType));
        //
        //System.out.println(hbs);

        //Map<Herb.Type,Set<Herb>> herbsByType = new EnumMap<>(Herb.Type.class);
        //for(Herb.Type t:Herb.Type.values()){
        //    herbsByType.put(t,new HashSet<>());
        //}
        //for(Herb h:herbs){
        //    herbsByType.get(h.type).add(h);
        //}
        //
        //System.out.println(herbsByType);


        //double x = 3,y = 4;
        //for(operationE op : BasicOperation.values()){
        //    System.out.println(op.apply(x, y));
        //}
        //System.out.println("--------------------------------");
        //for(operationE op : ExtensionOperation.values()){
        //    System.out.println(op.apply(x, y));
        //}

        //int tests = 0;
        //int pass = 0;
        //Class clz = Class.forName(BuilderPattern.class.getName());
        //for(Method method:clz.getDeclaredMethods()){
        //    if(method.isAnnotationPresent(ExceptionTest.class)){
        //        tests++;
        //        try {
        //            method.invoke(null);
        //        } catch (InvocationTargetException e) {
        //            Throwable exc = e.getCause();
        //            Class<? extends Exception> excType = method.getAnnotation(ExceptionTest.class).value();
        //            if(excType.isInstance(exc)){
        //                pass++;
        //            }else {
        //                System.out.printf("Test %s failed:expected %s got %s%n",method,excType.getName(),exc);
        //            }
        //        } catch (Exception e) {
        //            System.out.println("INVALIDE @Test: "+method);
        //        }
        //    }
        //}
        //System.out.println("test: "+tests+" pass: "+pass);

        getMethod("");

    }

    public static Object getMethod(@NotNull String length){
        return length;
    }
    @ExceptionTest(ArithmeticException.class)
    public static void m1(){
        int i = 0;
        i = i/i; //pass
    }
    @ExceptionTest(ArithmeticException.class)
    public static void m2(){
        int[] a = new int[0];
        int i = a[1];//should fail (wrong eception)
    }
    @ExceptionTest(ArithmeticException.class)
    public static void m3(){
        //should fail no exception
    }
    interface operationE{
        double apply(double x,double y);
    }
    enum ExtensionOperation implements operationE{
        MUL("^"){
            @Override
            public double apply(double x, double y) {
                return Math.pow(x,y);
            }
        },
        MOD("%"){
            @Override
            public double apply(double x, double y) {
                return x%y;
            }
        }
        ;
        private String symbol;

        ExtensionOperation(String symbol) {
            this.symbol = symbol;
        }

        @Override
        public String toString() {
            return symbol;
        }
    }
    enum BasicOperation implements operationE{
        PLUS("+"){
            @Override
            public double apply(double x, double y) {
                return x+y;
            }
        },
        MINUS("-"){
            @Override
            public double apply(double x, double y) {
                return x-y;
            }
        },
        TIMES("*"){
            @Override
            public double apply(double x, double y) {
                return x*y;
            }
        },
        DIVIDE("/"){
            @Override
            public double apply(double x, double y) {
                return x*y;
            }
        }
        ;
        private String symbol;

        BasicOperation(String symbol) {
            this.symbol = symbol;
        }

        @Override
        public String toString() {
            return symbol;
        }
    }
    static class Herb{
        public enum Type{PER_YERA,MULTI_YEAR,DOUBLE_YEAR}
        private final String name;
        private final Type type;

        public Herb(String name, Type type) {
            this.name = name;
            this.type = type;
        }

        public Type getType() {
            return type;
        }

        @Override
        public String toString() {
            return name;
        }
    }
    enum PayRollDay2{

        MONDAY(PayType.WEEKDAY),TUESDAY(PayType.WEEKDAY),THUSDAY(PayType.WEEKDAY),
        SUNDAY(PayType.WEEKEND)
        ;

        private final PayType payType;

        PayRollDay2(PayType payType) {
            this.payType = payType;
        }
        double pay(double hoursworkd,double payRate){
            return payType.pay(hoursworkd, payRate);
        }
        ;
        private enum PayType{
            WEEKDAY{
                @Override
                double overtimePay(double hrs, double payrate) {
                    return hrs < HOUSR_PER_SHIFT ? 0 : (hrs - HOUSR_PER_SHIFT)*payrate/2;
                }
            },
            WEEKEND{
                @Override
                double overtimePay(double hrs, double payrate) {
                    return hrs * payrate/2;
                }
            }
            ;
            private static final int HOUSR_PER_SHIFT = 8;
            abstract double overtimePay(double hrs,double payrate);
            double pay(double hourswork,double payRate){
                double basePay = hourswork * payRate;
                return basePay + overtimePay(hourswork, payRate);
            }
        }
    }
    enum PayrollDay{
        MONDAY,TUESDAY,WEDENSDAY,THURSDAY,FRIDAY,STAURDAY,SUNDAY
        ;
        private static final int HOURSE_PER_SHIFT = 8;
        double pay(double hoursWorked,double payRate){
            double basePay = hoursWorked * payRate;
            double overtimePay ;
            switch (this){
                case STAURDAY:case SUNDAY:
                    overtimePay = hoursWorked * payRate/2;break;
                default:
                    overtimePay = hoursWorked <= HOURSE_PER_SHIFT ? 0 :
                            (hoursWorked-HOURSE_PER_SHIFT)*payRate/2;
                    break;
            }
            return basePay+overtimePay;
        }
    }
    enum Operation{
        PLUS("+") {
            @Override
            double apply(double x, double y) {
                return x+y;
            }
        },
        MINUS("-"){
            @Override
            double apply(double x, double y) {
                return x-y;
            }
        },
        TIMES("*"){
            @Override
            double apply(double x, double y) {
                return x*y;
            }
        },
        DIVIDE("/"){
            @Override
            double apply(double x, double y) {
                return x/y;
            }
        }

        ;

        private final String symbol;
        private static final Map<String,Operation> stringToEnum = new HashMap<>();
        static {
            for(Operation op : values()) stringToEnum.put(op.toString(),op);
        }
        public static Operation fromString(String symbol){return stringToEnum.get(symbol);}

        Operation(String symbol) {
            this.symbol = symbol;
        }

        @Override
        public String toString() {
            return symbol;
        }
        abstract double apply(double x,double y);
    }
    static Annotation getAnnotation(AnnotatedElement element,String annotationTypeName){
        Class<?> annotationType = null;
        try {
            annotationType = Class.forName(annotationTypeName);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return element.getAnnotation(annotationType.asSubclass(Annotation.class));
    }
    @ShopDI
    static class Favorite{
        private Map<Class<?>,Object> favorites = new HashMap<>();
        public <T> void putFavorite(Class<T> type,T instance){
            if(type == null) throw new NullPointerException("Type is null");
            favorites.put(type,type.cast(instance));
        }

        @ShopDI
        public <T> T getFavorite(Class<T> type){return type.cast(favorites.get(type));}
    }

    static <E> Set<E> union(Set<? extends E> s1,Set<? extends E> s2){
        Set<E> res = new HashSet<E>(s1);
        res.addAll(s2);
        return res;
    }

    @SuppressWarnings("unchecked")
    static class StackE<E> {
        private E[] elements;
        private int size ;
        private static final int DEFAULT_INITAL_CAPCITY = 16;
        public StackE(){
            elements = (E[])new Object[DEFAULT_INITAL_CAPCITY];
            size = 0;
        }
        public void push(E e){
            ensureCapcity();
            elements[size++] = e;
        }
        public void pushAll(Iterable<? extends E> src){
            for(E e:src) push(e);
        }
        public void popAll(Collection<? super E> dst){
            while (!isEmpty()) dst.add(pop());
        }
        public E pop(){
            if(size == 0) throw new EmptyStackException();
            E res = elements[--size];//the last one
            elements[size] = null;
            return res;
        }
        public boolean isEmpty(){return size == 0;}
        private void ensureCapcity(){
            if(elements.length == size)
                elements = Arrays.copyOf(elements,2*size+1);
        }
    }
    static <E> E reduceLE(List<E> list,FunctionE<E> f,E initvalue){
        List<E> snapshot;
        synchronized (list){
            snapshot = new ArrayList<E>(list);
        }
        E res = initvalue;
        for(E e:snapshot) res = f.apply(res,e);
        return res;
    }

    static <E>E reduceE(List<E>list,FunctionE<E> f,E initValue){
        E[] snapshot = (E[])list.toArray();
        E res = initValue;
        for(E e:snapshot) res = f.apply(res,e);
        return res;
    }
    static Object reduce(List list,Function f,Object initval){
        synchronized (list){
            Object result = initval;
            for(Object obj:list) result = f.apply(result,obj);
            return result;
        }
    }
    interface FunctionE<E>{
        E apply(E arg1,E arg2);
    }
    interface Function{
        Object apply(Object arg1,Object arg2);
    }
}

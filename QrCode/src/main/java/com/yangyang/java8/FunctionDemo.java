package com.yangyang.java8;

import java.io.File;
import java.io.IOException;
import java.lang.annotation.Repeatable;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

class TrainJourney{
    public String name;
    public TrainJourney onward;

    public TrainJourney(String name, TrainJourney onward) {
        this.name = name;
        this.onward = onward;
    }
}

@Repeatable(Authors.class)
@interface Author{String name();}
@interface Authors{Author[] value();}

@Retention(RetentionPolicy.RUNTIME)
@interface Hehe{String value() default "";}


@Author(name = "yangyang")
class Book{}

@Hehe("demo")
class Deee{}

public class FunctionDemo {

    private static TrainJourney link(TrainJourney a,TrainJourney b){
        if(a == null) return b;
        TrainJourney t = a;
        while (t.onward != null){
            t = t.onward;
        }
        t.onward = b;
        return a;
    }
    private static TrainJourney append(TrainJourney a,TrainJourney b){
        return a == null ? b : new TrainJourney(a.name,append(a.onward,b));
    }

    private static void print(TrainJourney a){
        while (a != null) {
            System.out.print(a.name + " -> ");
            a = a.onward;
        }
        System.out.println();
    }
    private static String computeStr(String s){
        System.out.println("create ...");
        return "hello: "+s;
    }
    static Map<String,String> maps = new HashMap<>();
    private static String cacheStr(String s){
        System.out.println("size: "+maps.size());
        return maps.computeIfAbsent(s,FunctionDemo::computeStr);
    }
    public static void main(String[] args) throws IOException {
        //TrainJourney a = new TrainJourney("a",null);
        //TrainJourney b = new TrainJourney("b",a);
        //TrainJourney c = new TrainJourney("c",b);
        //
        //TrainJourney aa = new TrainJourney("aa",null);
        //TrainJourney bb = new TrainJourney("bb",aa);
        //TrainJourney cc = new TrainJourney("cc",bb);
        //
        //print(link(c,cc));
        //print(append(c,cc));

        //System.out.println(cacheStr("yangyang"));
        //System.out.println(cacheStr("yangyang"));
        //System.out.println(cacheStr("yangyang"));


        //Author[] authors = Book.class.getAnnotationsByType(Author.class);
        //
        //System.out.println("size: "+authors.length);
        //
        //Arrays.asList(authors).forEach(e-> System.out.println(e.name()));

        //Map<String,Integer> maps = new HashMap<>();
        //maps.put("hello123",123);
        //
        //Integer count = maps.getOrDefault("hello",-1);
        //System.out.println(count);

        //List<Integer> lists = new ArrayList<>();
        //lists.add(1);
        //lists.add(2);
        //lists.add(3);
        //lists.add(4);
        //
        //lists.replaceAll( e -> e*2);
        //lists.forEach(System.out::println);
        //
        //ConcurrentHashMap<String,Integer> maps = new ConcurrentHashMap<>();
        //maps.put("hello",123);
        //
        //System.out.println(maps.mappingCount());


        //int[] numbers = new int[10];
        //Arrays.setAll(numbers,i->i*2);
        //Arrays.stream(numbers).forEach(System.out::println);
        //
        //int[] ones = new int[10];
        //Arrays.fill(ones,1);
        //Arrays.stream(ones).forEach(System.out::println);

    }
}

package com.yangyang.Util;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.function.BiConsumer;
import java.util.function.Consumer;

public class LambdaUtil {

    public static Consumer println = System.out::println;
    public static Consumer print = System.out::print;

    public static BiConsumer printMap = (k,v) -> System.out.println(k+" : "+v);
    public static <T> void printList(List<T> list){ printCollection(list); }
    public static<T> void printCollection(Collection<T> coll){
        coll.forEach(println);
    }
    public static <K,V> void printMap(Map<K,V> map){
        map.forEach(printMap);
    }
}

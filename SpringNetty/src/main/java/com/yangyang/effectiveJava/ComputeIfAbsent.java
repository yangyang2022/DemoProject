package com.yangyang.effectiveJava;


import java.util.HashSet;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class ComputeIfAbsent {

    private static Map<Integer,Integer> cache = new ConcurrentHashMap<>();

    public static void main(String[] args) {

        //Map<String,HashSet<String>> map1 = new HashMap<>();
        //map1.computeIfAbsent("fruts",k->getValue(k)).add("apple");
        //map1.computeIfAbsent("fruts",k->getValue(k)).add("banana");
        //map1.computeIfAbsent("fruts",k->getValue(k)).add("peal");
        //
        //map1.computeIfAbsent("tree",k->getValue(k)).add("tree1");
        //map1.computeIfAbsent("tree",k->getValue(k)).add("tree2");
        //map1.computeIfAbsent("tree",k->getValue(k)).add("tree3");
        //
        //System.out.println(map1);

        //Map<String,String> map2 = new ConcurrentHashMap<>();
        //ExecutorService service = Executors.newCachedThreadPool();
        //for (int i = 0; i < 10; ++i) {
        //    service.execute(() -> {
        //        map2.computeIfAbsent("name",k->getValue2(k));
        //        map2.computeIfAbsent("addr",k->getValue2(k));
        //        map2.computeIfAbsent("email",k->getValue2(k));
        //        map2.computeIfAbsent("mobile",k->getValue2(k));
        //    });
        //}
        //service.shutdown();
        //SleepUtil.sleep3s();
        //System.out.println(map2);


        //fabonaci number
        cache.put(0,0);
        cache.put(1,1);
        System.out.println(fabonaci(20));
        System.out.println(fabonaci8(11));
        System.out.println(cache);

    }
    private static int fabonaci(int n){
        return n < 2? 1:fabonaci(n-1)+fabonaci(n-2);
    }
    private static int fabonaci8(int n){
        return cache.computeIfAbsent(n,key->fabonaci8(n-1)+fabonaci8(n-2));
    }
    private static HashSet<String> getValue(String str){
        return new HashSet<>();
    }
    private static String getValue2(String str){
        System.out.println("========");
        return str+"2";
    }
}

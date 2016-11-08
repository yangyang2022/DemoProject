package com.yangyang.effectiveJava;

import java.util.*;

public class OverrideAndOverload {

    static class CollectionClassifier{
        public static String classify(Set<?> s){return "set";}
        public static String classify(List<?> s){return "list";}
        public static String classify(Collection<?> s){return "unkown Collection";}
    }
    static class Wine{
        String name(){return "wine";}
    }
    static class SparklingWine extends Wine{
        @Override
        String name() {
            return "sparkling wine";
        }
    }
    static class Champagne extends Wine{
        @Override
        String name() {
            return "Champagne wine";
        }
    }

    public static void main(String[] args) {
        Collection<?>[] collections = {new HashSet<String>(),new ArrayList<String>(),new HashMap<String,String>().values()};

        for(Collection c:collections){
            System.out.println(CollectionClassifier.classify(c));
        }

        System.out.println("---------------------------------");

        Wine[] wines = {new Wine(),new SparklingWine(),new Champagne()};
        for(Wine wine:wines){
            System.out.println(wine.name());
        }
    }
}

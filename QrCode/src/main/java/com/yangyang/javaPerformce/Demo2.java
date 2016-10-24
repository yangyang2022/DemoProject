package com.yangyang.javaPerformce;


import java.io.*;
import java.util.*;


public class Demo2  {

    private static <E>void printCollection(Collection<E> colls){
        colls.forEach(System.out::println);
    }
    private static <E> List<E> withoutDuplication(List<E> original){
        return new ArrayList<E>(new LinkedHashSet<E>(original));
    }
    static public Object deepCopy(Object obj){
        try {
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            new ObjectOutputStream(bos).writeObject(obj);
            ByteArrayInputStream bin = new ByteArrayInputStream(bos.toByteArray());
            return new ObjectInputStream(bin).readObject();
        } catch (Exception e) {
            e.printStackTrace();
            throw new IllegalArgumentException(e);
        }
    }

    public static void main(String[] args) throws Exception {

        Integer[] arr = {3,1,4,1,5,9};
        Arrays.sort(arr, new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o1 < o2 ? -1 : (o1 > o2 ? 1 : 0);
            }
        });
        System.out.println(Arrays.toString(arr));

    }
}

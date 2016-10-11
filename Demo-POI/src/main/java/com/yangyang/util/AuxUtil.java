package com.yangyang.util;

import java.util.stream.Stream;

public class AuxUtil {

    public static boolean isSorted(Comparable[] arr){
        for (int i = 1; i < arr.length; ++i) {
            if(less(arr[i],arr[i-1])) return false;
        }
        return true;
    }
    public static boolean less(Comparable a,Comparable b ) {
        return a.compareTo(b) < 0;
    }
    public static void swap(Comparable[] arr,int i,int j){
        Comparable temp  = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
    public static void showArr(Comparable[] arr){
        Stream.of(arr).forEach(e-> System.out.print(e+" "));
        System.out.println();
    }
}

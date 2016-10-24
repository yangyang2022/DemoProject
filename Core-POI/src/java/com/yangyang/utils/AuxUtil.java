package com.yangyang.utils;

import java.util.Arrays;

public class AuxUtil {

    public static void swim(Comparable[] arr,int k){
        while (k>1 && less(arr,k/2,k)){
            swap(arr,k/2,k);
            k = k/2;
        }
    }
    public static void sink(Comparable[] arr,int k,int n){
        while (2*k <= n){
            int j = 2*k;
            if(j<n && less(arr,j,j+1)) j++;
            if(!less(arr,k,j)) break;
            swap(arr,k,j);
            k = j;
        }
    }
    public static void swap(Comparable[] arr,int i,int j){
        Comparable temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
    
    public static void showArr(Comparable[] arr){
        Arrays.asList(arr).stream().forEach(e-> System.out.print(e+" "));
        System.out.println();
    }
    private static int compare(Comparable[] arr,int i,int j){
        return arr[i].compareTo(arr[j]);
    }
    public static boolean less(Comparable[] arr,int i,int j){
        return compare(arr,i,j)< 0;
    }
    public static boolean equal(Comparable[] arr,int i,int j){
        return compare(arr,i,j) ==0;
    }
    public static boolean larger(Comparable[] arr,int i,int j){
        return compare(arr,i,j) > 0;
    }
}

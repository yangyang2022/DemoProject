package com.yangyang.Algo;

import com.algs4.stdlib.StdRandom;
import org.junit.Test;

public class Demo_Part1 {

    private static boolean less(Comparable v, Comparable w){
        return v.compareTo(w) < 0;
    }
    private static void swap(Comparable[] arr,int i,int j){
        Comparable temp = arr[i]; arr[i] = arr[j]; arr[j] = temp;
    }
    private static void show(Comparable[] arr){
        for(int i = 0 ;i<arr.length;i++){
            System.out.print(arr[i] +" ");
        }
        System.out.println();
    }

    private static void quick3Way(Comparable[] arr,int lo,int hi){
        if( hi <= lo) return;
        int lt = lo,i = lo+1,gt = hi;
        Comparable v = arr[lo];
        while (i <= gt){
            int cmp = arr[i].compareTo(v);
            if(cmp < 0 ) swap(arr,lt++,i);
            else if(cmp > 0) swap(arr,i,gt--);
            else i++;
        }
        quick3Way(arr,lo,lt-1);
        quick3Way(arr,gt+1,hi);
    }
    //private static void sortFlag(Comparable[] arr,int lo,int hi){
    //    Comparable v = "1";
    //    for(int lt = lo,i=lo+1,gt = hi;i <= gt;){
    //        int cmp = arr[i].compareTo(v);
    //        if(cmp < 0 ) swap(arr,lt++,i);
    //        else if(cmp >0) swap(arr,i,gt--);
    //        else i++;
    //    }
    //}


    private static int partition(Comparable[] arr,int lo,int hi){
        Comparable v = arr[lo];
        int i = lo,j = hi+1;
        while (true){
            while (less(arr[++i],v)) if(i >= hi) break;
            while (less(v,arr[--j])) if(j <= lo) break;
            if( i>= j) break;
            swap(arr,i,j);
        }
        swap(arr,lo,j);
        return j;
    }
    private static void _quickSort(Comparable[] arr,int lo,int hi){
        if(hi <= lo) return;
        int p = partition(arr,lo,hi);
        _quickSort(arr,lo,p-1);
        _quickSort(arr,p+1,hi);
    }
    private static void quickSort(Comparable[] arr){
        StdRandom.shuffle(arr);
        _quickSort(arr,0,arr.length-1);
    }

    private static Comparable[] aux;
    private static void _merge(Comparable[] arr,int lo,int mid,int hi){
        int i = lo,j = mid+1;
        for(int k = lo;k <= hi;k++){
            aux[k] = arr[k];
        }
        //for(int k=lo;k<=hi;k++){
        //    if(i > mid) arr[k] = aux[j++];
        //    else if( j > hi) arr[k] = aux[i++];
        //    else if(less(aux[i],aux[j])) arr[k] = aux[i++];
        //    else arr[k] = aux[j++];
        //}
    }
    private static void _mergeSort(Comparable[] arr,int lo,int hi){
        if(hi <= lo) return;
        int mid = lo + (hi - lo)/2;
        _mergeSort(arr,lo,mid);
        _mergeSort(arr,mid+1,hi);
        _merge(arr,lo,mid,hi);
    }
    private static void mergeSort(Comparable[] arr){
        aux = new Comparable[arr.length];
        _mergeSort(arr,0,arr.length-1);
    }

    private static void mergeSortBU(Comparable[] arr){
        int length = arr.length;
        aux = new Comparable[length];
        for(int sz = 1;sz < length;sz = sz+sz){
            for(int lo = 0;lo < length-sz; lo += sz+sz)
                _merge(arr,lo,lo+sz-1,Math.min(lo + sz+sz-1,length-1));
        }
    }
    @Test
    public void testDemo() {
        String s = "012012001201201201201201201201201201201201201";
        Comparable[] arr =s.split("");

        //sortFlag(arr,0,arr.length-1);
        //quick3Way(arr,0,arr.length-1);
        //quickSort(arr);
        //mergeSort(arr);
        mergeSortBU(arr);
        show(arr);
    }
}

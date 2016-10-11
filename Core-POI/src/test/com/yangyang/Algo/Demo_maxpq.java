package com.yangyang.Algo;

import com.algs4.stdlib.StdRandom;
import com.sun.applet2.Applet2;
import org.junit.Test;
public class Demo_maxpq {

    private static void swim(Comparable[] arr,int k){
        while ( k >1 && less(arr,k/2,k)){
            swap(arr,k/2,k);
            k = k/2;
        }
    }
    private static void sink(Comparable[] arr,int k,int n){

        while (2*k <= n){
            int j = 2*k;
            if(j < n && less(arr,j,j+1)) j++;
            if(!less(arr,k,j)) break;
            swap(arr,k,j);
            k = j;
        }
    }
    private static void heapSort(Comparable[] arr){
        int n = arr.length;
        for(int k = n/2;k>=1;k--){
            sink(arr,k,n);
            while (n>1){
                swap(arr,1,n--);
                sink(arr,1,n);
            }

        }
    }
    private static void selectSort(Comparable[] arr){
        for(int i=0;i<arr.length;i++){
            int min = i;
            for(int j=i;j<arr.length;j++)
                if(less(arr,j,min)) min = j;
            swap(arr,i,min);
        }
    }
    private static void insertSort(Comparable[] arr){
        for(int i =0;i<arr.length;i++){
            for(int j = i;j>=1;j--){
                if(less(arr,j,j-1)) swap(arr,j,j-1);
            }
        }
    }
    private static void quick3Way(Comparable[] arr,int lo,int hi){
        if(hi <= lo) return;
        int lt = lo,i=lo+1,gt = hi;
        Comparable v = arr[lo];
        while (i <= gt){
            int cmp = arr[i].compareTo(v);
            if(cmp < 0 ) swap(arr,lt++,i);
            else if(cmp > 0 ) swap(arr,i,gt--);
            else i++;
        }
        quick3Way(arr,lo,lt-1);
        quick3Way(arr,gt+1,hi);
    }

    private static Comparable[] aux;
    private static void merge(Comparable[] arr,int lo,int mid,int hi){
        int i = lo,j = mid+1;
        for(int k = lo;k<=hi;k++){
             aux[k] = arr[k];
        }
        for(int k = lo;k<=hi;k++){
            if(i > mid) arr[k] = aux[j++];
            else if(j > hi) arr[k] = aux[i++];
            else if(less(aux,i,j)) arr[k] = aux[i++];
            else arr[k] = aux[j++];
        }
    }
    private static void _mergeSort(Comparable[] arr,int lo,int hi){
        if(lo<hi){
            int mid = (lo+hi)/2;
            _mergeSort(arr,lo,mid);
            _mergeSort(arr,mid+1,hi);
            merge(arr,lo,mid,hi);
        }
    }
    private static void mergeSort(Comparable[] arr){
        aux = new Comparable[arr.length];
        _mergeSort(arr,0,arr.length-1);
    }

    private static void mergeSortBU(Comparable[] arr){
        aux = new Comparable[arr.length];
        for(int sz = 1;sz < arr.length;sz = sz+sz){
            for(int lo = 0;lo<arr.length-sz;lo += sz+sz)
                merge(arr,lo,lo+sz-1,Math.min(lo + 2*sz-1,arr.length-1));
        }
    }
    private static int partition(Comparable[] arr,int lo,int hi){
        int i = lo,j = hi+1;
        //Comparable temp = arr[lo];
        while (true){
            while (less(arr,++i,lo)) if(i == hi) break;
            while (less(arr,lo,--j)) if(j == lo) break;
            if( i >= j)break;
            swap(arr,i,j);
        }
        swap(arr,lo,j);
        return j;
    }
    private static void _quickSort(Comparable[] arr,int lo,int hi){
        if( hi <= lo) return;
        int partition = partition(arr,lo,hi);
        _quickSort(arr,lo,partition-1);
        _quickSort(arr,partition+1,hi);
    }
    private static void quickSort(Comparable[] arr){
        StdRandom.shuffle(arr);
        _quickSort(arr,0,arr.length-1);
    }
    //寻找第K小元素
    private static Comparable selectMin(Comparable[] arr,int k){
        StdRandom.shuffle(arr);
        int lo = 0,hi = arr.length-1;
        while (hi > lo){
            int j = partition(arr,lo,hi);
            if(j == k ) return arr[k];
            else if( j < k ) lo = j+1;
            else if( j > k) hi = j-1;
        }
        return arr[k];
    }
    @Test
    public void testDemo2() {
        Integer[] arr = {8, 4, 2, 1, 5, 3, 2, 8, 0, 4, 9};
        //String s = "sortexample";
        //String[] arr = s.split("");
        //quick3Way(arr);

        //quick3Way(arr,0,arr.length-1);
        //mergeSort(arr);
        //quickSort(arr);
        //selectSort(arr);
        //insertSort(arr);
        //mergeSortBU(arr);
        //heapSort(arr);
        System.out.println(selectMin(arr,arr.length/2));
        quick3Way(arr,0,arr.length-1);
        showArr(arr);
    }
    @Test
    public void testDemo1() {
        String s = "sprgtoaeihg";
        String[] ss = s.split("");
        MaxPQ<String> heap = new MaxPQ<String>(s.length());
        for (int i = 0; i < s.length(); ++i) {
            heap.insert(ss[i]);
        }
        System.out.println(heap.getMax());
        heap.delMax();
        System.out.println(heap.getMax());
    }

}

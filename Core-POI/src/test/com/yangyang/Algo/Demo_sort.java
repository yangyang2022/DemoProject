package com.yangyang.Algo;

import com.algs4.stdlib.StdRandom;
import com.algs4.stdlib.Stopwatch;
import org.junit.Test;

import java.util.Arrays;

public class Demo_sort {

    private static boolean less(Comparable v, Comparable w){
        return v.compareTo(w) < 0;
    }
    private static boolean equal(Comparable v,Comparable w) {return v.compareTo(w) == 0;}
    private static void changeValue(Comparable[] arr,int i,int j){
        Comparable temp = arr[i]; arr[i] = arr[j]; arr[j] = temp;
    }
    private static void show(Comparable[] arr){
        for(int i = 0 ;i<arr.length;i++){
            System.out.print(arr[i] +" ");
        }
        System.out.println();
    }

    private static boolean isSorted(Comparable[] arr){
        for(int i = 1;i<arr.length;i++){
            if(less(arr[i],arr[i-1])) return false;
        }
        return true;
    }

    private static void selectSort(Comparable[] arr){
        int length = arr.length;
        for(int i=0;i<length;i++){
            int min = i;
            for(int j = i;j<length;j++){
                if(less(arr[j],arr[min])) min = j;
            }
            changeValue(arr,i,min);
        }
    }

    private static void insertSort(Comparable[] arr){
        int length = arr.length;
        for(int i = 1;i<length;i++){
            for(int j = i;j>0 && less(arr[j],arr[j-1]) ; j--) {
                changeValue(arr,j,j-1);
            }
        }
    }
    private static void shellSort(Comparable[] arr){
        int length = arr.length;
        int h = 1;
        while (h < length/3) h = h*3+1;
        while (h >= 1){
            //sort h arr
            for(int i = h;i<length;i++){
                for(int j = i;j >= h && less(arr[j],arr[j-h]);j -= h){
                    changeValue(arr,j,j-h);
                }
            }
            h = h/3;
        }
    }

    private static Comparable[] aux;
    private static void merge(Comparable[] arr,int lo ,int mid,int hi){
        //merge arr[lo..mid] -- arr[mid+1,hi]
        int i = lo,j = mid+1;
        for(int k = lo;k<=hi;k++){
            aux[k] = arr[k];
        }
        for(int k = lo;k<=hi;k++){
            if(i > mid)                 arr[k] = aux[j++];
            else if( j > hi )           arr[k] = aux[i++];
            else if(less(aux[j],aux[i])) arr[k] = aux[j++];
            else                        arr[k] = aux[i++];
        }
    }
    private static void mergeHelper(Comparable[] arr,int lo,int hi){
        if(hi <= lo) return;
        int mid = lo + (hi - lo )/2;
        mergeHelper(arr,lo,mid);
        mergeHelper(arr,mid+1,hi);
        merge(arr,lo,mid,hi);
    }
    private static void mergeSort(Comparable[] arr){
        aux = new Comparable[arr.length];
        mergeHelper(arr,0,arr.length-1);
    }

    private static void mergeSortBU(Comparable[] arr){
        aux = new Comparable[arr.length];
        for(int sz = 1;sz < arr.length;sz = sz + sz){ //数组的大小
            for(int lo = 0;lo < arr.length - sz; lo += sz+sz) //lo的下标
                merge(arr,lo,lo+sz-1,Math.min(lo+2*sz-1,arr.length-1));
        }
    }

    private static int partition(Comparable[] arr,int lo,int hi){
        int i = lo,j = hi + 1;
        Comparable temp = arr[lo];
        while (true){
            while (less(arr[++i],temp)) if( i == hi ) break;
            while (less(temp,arr[--j])) if( j == lo ) break;
            if( i >= j) break;
            changeValue(arr,i,j);
        }
        changeValue(arr,lo,j);
        return j;
    }
    private static void quickSortHelper(Comparable[] arr,int lo,int hi){

        //这里插入其他算法,注意后面的return
        if( hi <= lo + 4) {insertSort(arr);return;}

        int j = partition(arr,lo,hi);
        quickSortHelper(arr,lo,j-1);
        quickSortHelper(arr,j+1,hi);
    }
    private static void quickSort(Comparable[] arr){
        StdRandom.shuffle(arr); //
        quickSortHelper(arr,0,arr.length-1);
    }

    //private static void quick3Way(Comparable[] arr,int lo,int hi){
    //    if(hi <= lo ) return;
    //    Comparable v = arr[lo];
    //    int lt = lo,i = lo+1,gt = hi;
    //    while (i <= gt){
    //        int cmp = arr[i].compareTo(v);
    //        if(cmp < 0 ) changeValue(arr,lt++,i);
    //        else if( cmp > 0 ) changeValue(arr,i,gt--);
    //        else i++;
    //    }
    //    //arr[lo .. i] <v arr[lt .. gt] == v arr[gt+1] > v
    //    quick3Way(arr,lo,lt-1);
    //    quick3Way(arr,gt+1,hi);
    //}

    //荷兰国旗问题
    private static void sortFlag(Comparable[] arr,int lo,int hi){
        int lt = lo,i=lo+1,gt = hi;
        Comparable v = "1";
        while (i <= gt){
            int cmp = arr[i].compareTo(v);
            if(cmp < 0 ) changeValue(arr,lt++,i);
            else if(cmp >0 ) changeValue(arr,i,gt--);
            else i++;
        }
    }
    @Test
    public void testDemo1() {
        Stopwatch timer = new Stopwatch();
        //String s = "SORTEXAMPLE";
        String s = "012012012000122000001120001212222211200012";
        String[] arr = s.split("");
        //selectSort(arr);      //A E E L M O P R S T X
        //insertSort(arr);      //A E E L M O P R S T X
        //shellSort(arr);       //A E E L M O P R S T X
        //mergeSort(arr);       //A E E L M O P R S T X
        //mergeSortBU(arr);     //A E E L M O P R S T X
        //quickSort(arr);         //A E E L M O P R S T X
        //quick3Way(arr,0,arr.length-1); //A E E L M O P R S T X
        sortFlag(arr,0,arr.length-1);

        show(arr);
        System.out.println(timer.elapsedTime());
    }

    private static boolean _binarySeach(Comparable[] arr,Comparable key,int lo,int hi){
        if(hi < lo) return false;
        int mid = (lo+hi)/2;
        int cmp = arr[mid].compareTo(key);
        return cmp == 0? true : cmp<0?_binarySeach(arr,key,mid+1,hi):_binarySeach(arr,key,lo,mid-1);
    }
    private static boolean binarySeach(Comparable[] arr,Comparable key){
        return _binarySeach(arr,key,0,arr.length-1);
    }

    private static boolean _demo(int[] arr,int key,int lo,int hi){
        if(lo > hi) return false;
        int mid = (lo + hi)/2;
        int cmp = Integer.compare(arr[mid],key);
        return cmp == 0?true:cmp<0?_demo(arr,key,mid+1,hi):_demo(arr,key,lo,mid-1);
    }
    private static boolean _demos(String[] strs,String key,int lo,int hi){
        if(lo > hi ) return false;
        int mid = (lo+hi)/2;
        int cmp = strs[mid].compareTo(key);
        return cmp == 0?true:cmp<0?_demos(strs,key,mid+1,hi):_demos(strs,key,lo,mid-1);
    }
    private static boolean demo(String[] arr,String key){
        //return _demo(arr,key,0,arr.length-1);
        return _demos(arr,key,0,arr.length-1);
    }
    @Test
    public void testDemo2() {
        //String s = "012012001201201201201201201201201201201201201";
        String s = "01235";
        String[] arr = s.split("");

        System.out.println(binarySeach(arr,"0"));
        System.out.println(binarySeach(arr,"-1"));
        System.out.println(binarySeach(arr,"2"));
        System.out.println(binarySeach(arr,"-3"));
        System.out.println(binarySeach(arr,"4"));
        System.out.println(binarySeach(arr,"5"));

        System.out.println("==============================");

        System.out.println(Arrays.binarySearch(arr,"0"));
        System.out.println(Arrays.binarySearch(arr,"-1"));
        System.out.println(Arrays.binarySearch(arr,"2"));
        System.out.println(Arrays.binarySearch(arr,"-3"));
        System.out.println(Arrays.binarySearch(arr,"4"));
        System.out.println(Arrays.binarySearch(arr,"5"));
    }

    @Test
    public void testDemo3() {

        int[] arr = {1,2,4};
        //System.out.println(demo(arr,1));
        //System.out.println(demo(arr,-2));
        //System.out.println(demo(arr,2));
        //System.out.println(demo(arr,10));

    }

}

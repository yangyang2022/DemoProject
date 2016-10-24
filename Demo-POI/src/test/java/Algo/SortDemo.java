package Algo;

import com.yangyang.util.AuxUtil;
import org.junit.Test;

import static com.yangyang.util.AuxUtil.less;
import static com.yangyang.util.AuxUtil.swap;

public class SortDemo {


    private static void insertSort(Comparable[] arr){
        int lengt = arr.length;
        for (int i = 1; i < lengt; ++i) {
            for (int j = i; j > 0 && less(arr[j], arr[j - 1]); j--)
                swap(arr,j,j-1);
        }
    }
    private static void selectSort(Comparable[] arr){
        int length = arr.length;
        for(int i=0;i<length;i++){
            int min = i;
            for(int j= i ;j<length;j++){
                if(less(arr[j],arr[min])) min = j;
            }
            swap(arr,i,min);
        }
    }
    private static void quick3Way(Comparable[] arr,int lo,int hi){
        if(hi <= lo) return;
        int lt=lo,i=lo+1,gt = hi;
        Comparable v = arr[lo];
        while (i <=gt){
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
        int i = lo, j = mid+1;
        for(int k = lo;k<=hi;k++){
            aux[k] = arr[k];
        }
        for(int k=lo;k<=hi;k++){
            if(i > mid) arr[k] = aux[j++];
            else if(j > hi) arr[k] =aux[i++];
            else if(less(arr[j],arr[i])) arr[k] = aux[j++];
            else arr[k] = aux[i++];
        }
    }
    private static void mergeHelper(Comparable[] arr,int lo,int hi){
        if(hi <= lo) return;
        int mid = (lo+hi)/2;
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
        for(int sz = 1;sz < arr.length;sz += sz){
            for(int lo=0;lo<arr.length-sz;lo += (2*sz))
                merge(arr,lo,lo+sz-1,Math.min(lo+(2*sz)-1,arr.length-1));
        }
    }
    @Test
    public void testSort1() {
        Comparable[] arr = {1,7,2,3,9,5,4,6,8,0};

        //quick3Way(arr,0,arr.length-1);
        //selectSort(arr);
        //insertSort(arr);
        //mergeSort(arr);
        mergeSortBU(arr);

        AuxUtil.showArr(arr);

    }
}

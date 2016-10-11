package com.yangyang.Algo;

import com.algs4.stdlib.StdRandom;
import com.yangyang.utils.AuxUtil;
import org.junit.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.IntStream;


public class Demo_unioFind {

    private static int _binarySerach(Comparable[] arr,Comparable key,int lo,int hi){

        if(lo > hi) return -1;
        int mid = (lo + hi)/2;
        int cmp = arr[mid].compareTo(key);
        return cmp == 0 ? mid : cmp < 0? _binarySerach(arr,key,mid+1,hi):_binarySerach(arr,key,lo,mid-1);
    }
    private static int binarySerach(Comparable[] arr,Comparable key){
        return _binarySerach(arr,key,0,arr.length-1);
    }

    //nlogn
    private static int count2Sum(Integer[] arr){
        Arrays.sort(arr);
        int count = 0;
        for(int i = 0;i<arr.length;i++){
            if(binarySerach(arr,-arr[i]) > i) count++;
        }
        return count;
    }
    private static Integer[] getIntData(){
        Integer[] arr = new Integer[1000];
        for (int i = 0; i < 1000; ++i) {
            arr[i] = StdRandom.uniform(-1000, 1000);
        }
        return arr;
    }

    //n^2logn
    private static int countThressSum(Integer[] arr){
        Arrays.sort(arr);
        int count = 0;
        for(int i = 0;i<arr.length;i++){
            for(int j = i+1;j<arr.length;j++)
                if(binarySerach(arr,-arr[i]-arr[j]) > j) {
                    count++;
                    System.out.println(arr[i]+"-"+arr[j]);
                }
        }
        return count;
    }
    @Test
    public void testDemo2() {

        Integer[] arr = {-1,1,-2,2,3,4,5,-4};
        //Integer[] arr = getIntData();
        System.out.println(count2Sum(arr));
        System.out.println(countThressSum(arr));

    }

    @Test
    public void testDemo3() throws FileNotFoundException {
        String filePath = "C:\\mavenProject\\DemoProject\\Core-javaTest\\src\\resource\\data.txt";
        Scanner input = new Scanner(new File(filePath));
        int n = input.nextInt();
        UnionFind uf = new UnionFind(n);
        while (input.hasNext()){
            int p = input.nextInt();
            int q = input.nextInt();
            if(uf.connect(p,q)) continue;
            uf.union(p,q);
            System.out.println(p+" - "+q);
        }
        System.out.println("count: "+uf.count());
    }

    private static void reverse(Integer[] arr,int lo,int hi){
        if(lo < hi){
            AuxUtil.swap(arr,lo,hi);
            reverse(arr,lo+1,hi-1);
        }
    }
    private static long squr(long a){return a*a;}
    private static long power2(int n){
        if(n == 0 )return 1;
        return (n&1) ==1 ? squr(power2(n>>1))<<1 : squr(power2(n>>1));
    }
    public static String reverseString(String s) {
        return (new StringBuilder(s).reverse()).toString();
    }
    private static Integer[] getCounts(int num){
        Integer[] arr = new Integer[num+1];
        for (int i = 0; i <= num; ++i) {
            arr[i] = Integer.bitCount(i);
        }
        return arr;
    }
    private static boolean isPrime(int n){
        int candidateRoot = (int) Math.sqrt(n);
        return IntStream.rangeClosed(2,candidateRoot).noneMatch(e->n%e == 0);
    }
    private static int countPrime(int n){
        int count = 0;
        while (n-- >= 2){
            if(isPrime(n)) count++;
        }
        return count;
    }

    @Test
    public void testStudent() {
        Integer[] arr = {1, 2, 3, 4, 5, 6, 7};
        //reverse(arr,0,arr.length-1);
        //AuxUtil.showArr(arr);
        //for (int i = 0; i < arr.length; ++i) {
        //    System.out.println(power2(i));
        //}
        //System.out.println(isPrime(7));

        //System.out.println(countPrime(10));
        //AuxUtil.showArr(getCounts(5));

        List<Integer> list = Arrays.asList(4, 4, 4, 1, 4);
    }
}

class UnionFind{

    private int[] id;
    private int count;
    private int[] sz;

    public UnionFind(int n){
        count = n;
        id = new int[n];
        sz = new int[n];
        for (int i = 0; i < n; ++i) {
            id[i] = i;
            sz[i] = 1;
        }
    }

    public int count(){return count;}

    public boolean connect(int p,int q){return find(p) == find(q);}

    public int find(int p){
        //return id[p];
        while (p != id[p]) p = id[p];
        return p;//此时是树的高度
    }
    public void union(int p,int q){
        //int pid = find(p);
        //int qid = find(q);
        //if(pid == qid) return;
        //for (int i = 0; i < id.length; ++i) {
        //    if (id[i] == pid) id[i] = qid;
        //}
        //count--;

        //quick-union
        int i = find(p);
        int j = find(q);
        if(i == j )return;
        //???
        if(sz[i] < sz[j]) {id[i] = j;sz[j] +=sz[i];}
        else {id[j] = i;sz[i] += sz[j];}
        count--;
    }

}

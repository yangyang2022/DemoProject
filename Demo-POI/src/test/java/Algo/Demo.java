package Algo;

import org.junit.Test;

import java.util.stream.IntStream;

public class Demo {
    private void showArr(int[] arr){
        IntStream.of(arr).forEach(e-> System.out.print(e+" "));
    }
    private int maxSubSum(int[] arr){
        int[] dp = new int[arr.length];
        dp[0] = 0;
        for (int i = 1; i < arr.length; ++i) {
            dp[i] = Integer.max(dp[i-1]+arr[i],arr[i]);
        }
        showArr(dp);
        return dp[arr.length-1];
    }
    @Test
    public void testDemo() {

        int[] arr = {1, -2, 3, 5, -3, 2};//8
        System.out.println("max Length: "+maxSubSum(arr));
    }

    private void showArr2(int[][] arr){
        for(int[] a:arr){
            for(int i:a){
                System.out.print(i+" ");
            }
            System.out.println();
        }
    }
    private int getMax(int[][] arr){
        int row = arr.length,col = arr[0].length;
        System.out.println("row: "+row+" col: "+col);
        return 0;
    }
    @Test
    public void testDemo2() {
        int[][] arr = {
                {7},
                {3,8},
                {8,1,0},
                {2,7,4,4},
                {4,5,2,6,2}
        };
        showArr2(arr);
        getMax(arr);

    }
}

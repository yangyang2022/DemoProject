package com.yangyang.Algo;

import com.algs4.stdlib.StdDraw;
import com.algs4.stdlib.StdIn;
import com.algs4.stdlib.StdRandom;

import java.util.Arrays;

public class Demo_StdOut {

    private static double getAverage(){
        double sum = 0.0;
        int cnt = 0;
        while (StdIn.readString() != ""){
            sum += StdIn.readDouble();
            cnt++;
        }
        return sum/cnt;
    }

    public static void main1(String[] args) {

        //get sum / average
        //double average = getAverage();
        //StdOut.printf("average is: %.5f\n",average);
    }

    public static void main2(String[] args) {
        int n = 100;
        StdDraw.setXscale(0,n);
        StdDraw.setYscale(0,n);
        StdDraw.setPenRadius(0.01);
        for(int i = 1;i<=n;i++){
            StdDraw.point(i,i);
            StdDraw.point(i,i*i);
            //StdDraw.point(i,i*Math.log(i));
        }
    }

    public static void main(String[] args) {
        int n = 50;
        double[] a = new double[n];
        for(int i=0;i<n;i++){
            a[i] = StdRandom.random();
        }
        Arrays.sort(a);

        for(int i =0;i<n;i++){
            double x = 1.0 * i/n;
            double y = a[i]/2.0;
            double rw = 0.4/n;
            double rh = a[i]/2.0;
            StdDraw.filledRectangle(x,y,rw,rh);
        }
    }
}

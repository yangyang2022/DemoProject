package com.yangyang.Algo;

import org.junit.Test;

public class Demo_algo {

    private static int gcd(int m,int n){
        return  n == 0 ? m :gcd(n,m%n);
    }

    //should set other
    private static int startPostI = 1;
    private static int startPostJ = 1;
    private static int endPostI = 7;
    private static int endPostJ = 7;

    /**
     * @param i startPostI
     * @param j startPostJ
     */
    private static void visit(int[][] cell,int i,int j){
        cell[i][j] = 1;
        if(i == endPostI && j == endPostJ){
            System.out.println("find a path: ");
            for (int m = 0; m < cell.length; ++m) {
                for(int n = 0;n<cell[0].length;n++){
                    if(cell[m][n] == 1) System.out.print("2");
                    else if(cell[m][n] == 0 ) System.out.print("*");
                    else System.out.print(" ");
                }
                System.out.println();
            }
        }
        //up down left right
    }
    @Test
    public void testDemo1() {
        //init maze

        int[][] maze = {
                {2,2,2,2,2,2,2,2,2},
                {2,0,0,0,0,0,0,0,2},
                {2,0,2,2,0,2,2,0,2},
                {2,0,2,0,0,2,0,0,2},
                {2,0,2,0,2,0,2,0,2},
                {2,0,0,0,0,0,2,0,2},
                {2,2,0,2,2,0,2,2,2},
                {2,0,0,0,0,0,0,0,2},
                {2,2,2,2,2,2,2,2,2}
        };

    }
}

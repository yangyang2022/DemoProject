package com.yangyang.Algo;

import java.util.IntSummaryStatistics;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ScoreDemo {
    enum LEVERL{不及格,及格,良好,优秀};
    public static void main(String[] args) {
        Integer[] score = new Integer[10];
        Scanner read = new Scanner(System.in);
        for (int i = 0; i < 10; ++i) {
            score[i] = read.nextInt();
        }
        IntSummaryStatistics state = Stream.of(score).mapToInt(Integer::valueOf).summaryStatistics();
        System.out.println("sum: "+state.getSum()+" max: "+state.getMax()+
                " min: "+state.getMin()+" average: "+state.getAverage());
        Map<LEVERL,List<Integer>> rs = Stream.of(score).collect(Collectors.groupingBy(e->{
            if( e < 60 ) return LEVERL.不及格;
            else if(e < 70) return LEVERL.及格;
            else if(e < 90) return LEVERL.良好;
            else return LEVERL.优秀;
        }));
        rs.forEach((k,v)-> System.out.println(k + " : " + v.size()));
    }
}

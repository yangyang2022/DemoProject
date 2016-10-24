package com.yangyang.java8;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.temporal.ChronoField;
import java.time.temporal.ChronoUnit;
import java.time.temporal.Temporal;
import java.time.temporal.TemporalAdjuster;
import java.util.stream.LongStream;

public class DateTime {
    public static void main(String[] args) {

        LocalDate date1 = LocalDate.of(2016,10,21);
        //LocalDate date2 = date1.with(TemporalAdjusters.nextOrSame(DayOfWeek.SUNDAY));
        //System.out.println(date2);
        //LocalDate date2 = date1.with(new NextDayOfWeek());
        //System.out.println(date2);
        //
        //String s1 = date2.format(DateTimeFormatter.BASIC_ISO_DATE);
        //String s2 = date2.format(DateTimeFormatter.ISO_LOCAL_DATE);
        //
        //System.out.println(s1 + " -> " + s2);

        //System.out.println(factory3(50));




    }

    private static long factory(int n){
        return  n == 1?1:factory(n-1)*n;
    }
    private static long helper(long acc,int n){
        return n == 1 ? acc : helper(acc*n, n-1);
    }
    private static long factory3(int n){
        return helper(1,n);
    }
    private static long factory2(int n){
        return LongStream.rangeClosed(1,n).reduce(1,(a,b)->a*b);
    }
    static class NextDayOfWeek implements TemporalAdjuster{
        @Override
        public Temporal adjustInto(Temporal temporal) {
            DayOfWeek dow = DayOfWeek.of(temporal.get(ChronoField.DAY_OF_WEEK));
            int dayToAdd = 1;
            if(dow == DayOfWeek.FRIDAY) dayToAdd = 3;
            else if(dow == DayOfWeek.SATURDAY) dayToAdd = 2;
            return temporal.plus(dayToAdd, ChronoUnit.DAYS);
        }
    }
}

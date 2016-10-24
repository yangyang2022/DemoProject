package com.yangyang.java8;

import com.yangyang.utils.OPtionalUtils;
import org.junit.Test;

import java.util.*;

public class Demo3_method {
    @Test
    public void testDemo1() {

        List<Integer> numbers = Arrays.asList(3,5,2,1,6);
        numbers.sort(Comparator.naturalOrder());
        System.out.println(numbers);

    }

    @Test
    public void testDemo2() {
        Insurance insurance = null;//new Insurance("人寿保险");
        Optional<Insurance> optInsurance = Optional.ofNullable(insurance);
        Optional<String> name = optInsurance.map(Insurance::getName);
        System.out.println(name.orElse("没有值"));
    }

    private static String getInsuranceName(Optional<Person> person){
        return person.flatMap(Person::getCar)
                .flatMap(Car::getInsurance)
                .map(Insurance::getName)
                .orElse("Unkonow!");
    }
    private static int readDuration(Properties props,String name){
        String value = props.getProperty(name);
        if(value != null){
            try {
                int i = Integer.parseInt(value);
                if(i > 0 ) return i;
            } catch (NumberFormatException e) {
            }
        }
        return Integer.MIN_VALUE;
    }
    private static int readDuration2(Properties props,String name){
        return Optional.ofNullable(props.getProperty(name))
                .flatMap(OPtionalUtils::stringToInt)
                .filter( i -> i>0)
                .orElse(Integer.MIN_VALUE);
    }
    @Test
    public void testDemo3() {
        Properties props = new Properties();
        props.put("a","5");
        props.put("b","true");
        props.put("c","-3");
        System.out.println(readDuration(props,"a"));
        System.out.println(readDuration(props,"b"));
        System.out.println(readDuration(props,"c"));
        System.out.println(readDuration(props,"d"));

        System.out.println("---------------------");

        System.out.println(readDuration2(props,"a"));
        System.out.println(readDuration2(props,"b"));
        System.out.println(readDuration2(props,"c"));
        System.out.println(readDuration2(props,"d"));
    }
}

class Insurance{
    private String name;

    public Insurance(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
class Car{
    private Optional<Insurance> insurance;

    public Optional<Insurance> getInsurance() {
        return insurance;
    }

}
class Person{
    private Optional<Car> car;

    public Optional<Car> getCar() {
        return car;
    }
}
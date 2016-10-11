package com.yangyang.model;

public class Tuple {

    private int one;
    private int two;
    private double three;

    public Tuple() {
    }

    public Tuple(int one, int two, double three) {
        this.one = one;
        this.two = two;
        this.three = three;
    }

    public int getOne() {
        return one;
    }

    public void setOne(int one) {
        this.one = one;
    }

    public int getTwo() {
        return two;
    }

    public void setTwo(int two) {
        this.two = two;
    }

    public double getThree() {
        return three;
    }

    public void setThree(double three) {
        this.three = three;
    }

    @Override
    public String toString() {
        return "Tuple{" +
                "one=" + one +
                ", two=" + two +
                ", three=" + three +
                '}';
    }
}

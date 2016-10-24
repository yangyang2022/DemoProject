package com.yangyang.designParttern;

class Rect{
    public double length;
    public double width;
}
class Triangle{
    public double base;
    public double hight;

    public Triangle(double base, double hight) {
        this.base = base;
        this.hight = hight;
    }
}
class Calculator {
    private Rect rectangle;
    public double getArea(Rect r){
        rectangle = r;
        return rectangle.length*rectangle.width;
    }
}
class CalculatorAdapter {
    private Calculator calculator;
    private Triangle triangle;
    public double getArea(Triangle t){
        calculator = new Calculator();
        triangle = t;
        Rect rect = new Rect();
        rect.width = triangle.base;
        rect.length = triangle.hight*0.5;
        return calculator.getArea(rect);
    }
}

public class Adapter {

    public static void main(String[] args) {
        CalculatorAdapter adapter = new CalculatorAdapter();
        Triangle triangle = new Triangle(10,20);
        System.out.println(adapter.getArea(triangle));

    }
}

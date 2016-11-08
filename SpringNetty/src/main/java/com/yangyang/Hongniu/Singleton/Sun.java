package com.yangyang.Hongniu.Singleton;

public class Sun {
    private int temperature;
    private int size;

    public Sun(int temperature, int size) {
        this.temperature = temperature;
        this.size = size;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public int getTemperature() {
        return temperature;
    }

    public void setTemperature(int temperature) {
        this.temperature = temperature;
    }

    public void light(){
        System.out.println("light all the way ...");
    }
}

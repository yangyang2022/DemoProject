package com.yangyang.DesignPattern;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

abstract class CarModel{

    private List<String> sequence = new ArrayList<>();

    protected abstract void start();
    protected abstract void stop();
    protected abstract void alarm();
    protected abstract void enginBoom();

    final public void run(){
        for (int i = 0; i < sequence.size(); ++i) {
            String actionName = this.sequence.get(i);
            if(actionName.equals("start")) this.start();
            else if(actionName.equals("stop")) this.stop();
            else if(actionName.equals("alarm")) this.alarm();
            else if(actionName.equals("engin")) this.enginBoom();
        }
    }
    final public void setSequence(List<String> sequence){
        this.sequence = sequence;
    }
}
class BenzModel extends CarModel{
    @Override
    protected void start() {
        System.out.println("benz start ...");
    }

    @Override
    protected void stop() {
        System.out.println("benz stop ... ");
    }

    @Override
    protected void alarm() {
        System.out.println("benz alarm ...");
    }

    @Override
    protected void enginBoom() {
        System.out.println("enginBoom ....");
    }
}
class BmwModel extends CarModel{
    @Override
    protected void start() {
        System.out.println("bmw start ...");
    }

    @Override
    protected void stop() {
        System.out.println("bmw stop ... ");
    }

    @Override
    protected void alarm() {
        System.out.println("bmw alarm ...");
    }

    @Override
    protected void enginBoom() {
        System.out.println("bmw enginboom ...");
    }
}
abstract class CarBuilder{

    public abstract void setSequnce(List<String> sequnce);
    public abstract CarModel getCarModel();

}
class BenzBuilder extends CarBuilder{
    private BenzModel benzModel = new BenzModel();

    @Override
    public void setSequnce(List<String> sequnce) {
        this.benzModel.setSequence(sequnce);
    }

    @Override
    public CarModel getCarModel() {
        return benzModel;
    }
}
public class BuilderModel {

    public static void main(String[] args) {

        List<String> sequence = Arrays.asList("start","alarm","engin","stop");

        BenzBuilder builder = new BenzBuilder();
        builder.setSequnce(sequence);

        BenzModel benz = (BenzModel) builder.getCarModel();
        benz.run();


    }
}

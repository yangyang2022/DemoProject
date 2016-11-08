package com.yangyang.DesignPattern;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

interface IWomen{
    int getType();
    String getRequest();
}
class Women implements IWomen{
    //1 normal 2 marry 3 hansband dead
    private int type = 0;
    private String request = "";

    public Women(int type, String request) {
        this.type = type;
        //this.request = request;
        switch (type){
            case 1:this.request = "daughter request is: "+request;
            case 2:this.request = "wife request is: "+request;
            case 3:this.request = "mother request is: "+request;
        }
    }

    @Override
    public int getType() {
        return this.type;
    }

    @Override
    public String getRequest() {
        return this.request;
    }
}
//interface IHandler{
//    void HandlerMessage(IWomen women);
//}
//class Father implements IHandler{
//    @Override
//    public void HandlerMessage(IWomen women) {
//        System.out.println("request: "+women.getRequest());
//        System.out.println("father: yes!");
//    }
//}
//class Husband implements IHandler{
//    @Override
//    public void HandlerMessage(IWomen women) {
//        System.out.println("request: "+women.getRequest());
//        System.out.println("Husband: yes!");
//    }
//}
//class Son implements IHandler{
//    @Override
//    public void HandlerMessage(IWomen women) {
//        System.out.println("request: "+women.getRequest());
//        System.out.println("Son: yes!");
//    }
//}


abstract class Handler{

    public final static int FATHER_LERVEL_REQUEST = 1;
    public final static int HUSBAND_LERVEL_REQUEST = 2;
    public final static int SON_LERVEL_REQUEST = 3;

    private int level = 0;

    private Handler nextHandler;

    public void setNextHandler(Handler nextHandler) {
        this.nextHandler = nextHandler;
    }

    public Handler(int level) {
        this.level = level;
    }
    public final void HandleMessage(IWomen women){
        if(women.getType() == this.level)
            this.response(women);
        else{
            if(this.nextHandler != null)
                this.nextHandler.HandleMessage(women);
            else {
                System.out.println(" ==== no ======");
            }
        }
    }
    public abstract void response(IWomen women);
}

class Father extends Handler{
    public Father() {
        super(Handler.FATHER_LERVEL_REQUEST);
    }

    @Override
    public void response(IWomen women) {
        System.out.println("=====request for father ====");
        System.out.println(women.getRequest());
        System.out.println("======Yes!=====");
    }
}
class Husband extends Handler{
    public Husband() {
        super(Handler.HUSBAND_LERVEL_REQUEST);
    }

    @Override
    public void response(IWomen women) {
        System.out.println("=====request for Husband ====");
        System.out.println(women.getRequest());
        System.out.println("======Yes!=====");
    }
}

class Son extends Handler{
    public Son() {
        super(Handler.SON_LERVEL_REQUEST);
    }

    @Override
    public void response(IWomen women) {
        System.out.println("=====request for son ====");
        System.out.println(women.getRequest());
        System.out.println("======Yes!=====");
    }
}
public class ResponseParttern {
    public static void main(String[] args) {

        Random random = new Random();
        List<IWomen> list = new ArrayList<>();
        for (int i = 0; i < 1; ++i) {
            list.add(new Women(1, "go out!"));
        }
        //
        //Father father = new Father();
        //Husband husband = new Husband();
        //Son son = new Son();
        //
        //for(IWomen women:list){
        //    if(women.getType() == 1){
        //        System.out.println("============== request for father =========");
        //        father.HandlerMessage(women);
        //    }else if(women.getType() == 2){
        //        System.out.println("================request for husband =============");
        //        husband.HandlerMessage(women);
        //    }else if(women.getType()==3){
        //        System.out.println("===============request for son =============");
        //        son.HandlerMessage(women);
        //    }else {
        //        System.out.println("hello world");
        //    }
        //}

        Handler father = new Father();
        Husband husband = new Husband();
        Son son = new Son();
        father.setNextHandler(husband);
        husband.setNextHandler(son);
        for(IWomen women : list)
            father.HandleMessage(women);
    }
}

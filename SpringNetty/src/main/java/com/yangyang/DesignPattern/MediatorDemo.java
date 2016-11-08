package com.yangyang.DesignPattern;

import java.util.Random;

//class Purchase{
//
//    public void buyIBMComputer(int number){
//        Stock stock = new Stock();
//        Sale sale = new Sale();
//
//        int saleStatus = sale.getSaleStatus();
//        if(saleStatus > 80){
//            System.out.println("sale good! buy : "+number+" computers!");
//            //stock.increase()
//        }else {
//            int buyNumber = number/2;
//            System.out.println("sale bad! buy: "+buyNumber+" computers!");
//        }
//    }
//    public void refuseBuyIBM(){
//        System.out.println("don't buy IBM computer!");
//    }
//}
//class Stock{
//
//    private static int COMPUTER_NUMBER = 100;
//
//    //add
//    public void increase(int number){
//        COMPUTER_NUMBER += number;
//        System.out.println("stock number: "+COMPUTER_NUMBER);
//    }
//    //decrease
//    public void decrease(int number){
//        COMPUTER_NUMBER -= number;
//        System.out.println("stock number: "+COMPUTER_NUMBER);
//    }
//    public int getStockNumber(){return COMPUTER_NUMBER;}
//
//    //clear stock
//    public void clearStock(){
//        Purchase purchase = new Purchase();
//        Sale sale = new Sale();
//
//        System.out.println("clear stock number: "+COMPUTER_NUMBER);
//        sale.offSale();
//        purchase.refuseBuyIBM();
//    }
//}
//class Sale{
//    //sale
//    public void sellIBMComputer(int number){
//        Stock stock = new Stock();
//        Purchase purchase = new Purchase();
//        if(stock.getStockNumber() < number )
//            purchase.buyIBMComputer(number);
//        System.out.println("sell: "+number);
//        stock.decrease(number);
//    }
//
//    //0 is sale bad , 100 is sale good!
//    public int getSaleStatus(){
//        Random random = new Random(System.currentTimeMillis());
//        int saleStatus = random.nextInt(100);
//        System.out.println("sale status: "+saleStatus);
//        return saleStatus;
//    }
//
//    //clear stock
//    public void offSale(){
//        Stock stock = new Stock();
//        System.out.println("off sale: "+stock.getStockNumber());
//    }
//}

abstract class AbtractMediator{
    protected Purchase purchase;
    protected Sale sale;
    protected Stock stock;

    public AbtractMediator(){
        purchase = new Purchase(this);
        sale = new Sale(this);
        stock = new Stock(this);
    }

    public abstract void execute(String str,Object...args);
}
class Meditor extends AbtractMediator{
    @Override
    public void execute(String str, Object... args) {
        if(str.equals("pur.buy")){
            this.buyComputer((Integer) args[0]);
        }else if(str.equals("sale.sell")){
            this.sellComputer((Integer) args[0]);
        }else if(str.equals("stock.clear")){
            this.clearStock();
        }else if(str.equals("offsale")){
            this.offsell();
        }
    }
    private void sellComputer(int number){
        if(super.stock.getStockNumber() < number)
            super.purchase.buyIBMComputer(number);
        super.stock.decrease(number);
    }
    private void offsell(){
        System.out.println("off sale: "+stock.getStockNumber());
        stock.decrease(stock.getStockNumber());
    }
    private void clearStock(){
        super.sale.offSale();
        super.purchase.refuseBuyIBM();
    }
    private void buyComputer(int number){
        int saleStatus = super.sale.getSaleStatus();
        if(saleStatus > 80){
            System.out.println("sale good!buy: "+number);
        }else {
            System.out.println("sale bad!buy: "+(number/2));
        }
    }
}


abstract class AbstractColleage{
    protected AbtractMediator mediator;
    public AbstractColleage(AbtractMediator mediator){
        this.mediator = mediator;
    }
}
class Purchase extends AbstractColleage{
    public Purchase(AbtractMediator mediator) {
        super(mediator);
    }

    public void buyIBMComputer(int number){
        super.mediator.execute("pur.buy",number);
    }
    public void refuseBuyIBM(){
        System.out.println("don't buy computer!");
    }
}
class Stock extends AbstractColleage{
    public Stock(AbtractMediator mediator) {
        super(mediator);
    }

    private static int COMPUTER_NUMBER = 100;

    public void increase(int number){
        COMPUTER_NUMBER += number;
        System.out.println("stock number: "+COMPUTER_NUMBER);
    }
    public void decrease(int number){
        COMPUTER_NUMBER -= number;
        System.out.println("stock number: "+COMPUTER_NUMBER);
    }
    public int getStockNumber(){return COMPUTER_NUMBER;}
    public void clearStock(){
        System.out.println("clear stock: "+COMPUTER_NUMBER);
        super.mediator.execute("stock.clear");
    }
}

class Sale extends AbstractColleage{
    public Sale(AbtractMediator mediator) {
        super(mediator);
    }
    public void sellIBMComputer(int number){
        super.mediator.execute("sale.sell",number);
        System.out.println("sell "+number);
    }
    //0 is sale bad , 100 is sale good!
    public int getSaleStatus(){
        Random random = new Random(System.currentTimeMillis());
        int saleStatus = random.nextInt(100);
        System.out.println("sale status: "+saleStatus);
        return saleStatus;
    }
    public void offSale(){
        super.mediator.execute("offsale");
    }
}
public class MediatorDemo {

    public static void main(String[] args) {
        AbtractMediator meditor = new Meditor();
        Purchase purchase = new Purchase(meditor);
        purchase.buyIBMComputer(100);

        Sale sale = new Sale(meditor);
        sale.sellIBMComputer(10);

        Stock stock = new Stock(meditor);
        System.out.println("stocks: "+stock.getStockNumber());
        stock.clearStock();
        System.out.println("stocks: "+stock.getStockNumber());
    }
}

package com.yangyang.java8;

import com.yangyang.javaPerformce.SleepUtils;

import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.concurrent.*;
import java.util.stream.Collectors;

public class CompleteFuture {

    private static Random random = new Random();

    public static void main(String[] args) throws Exception {

        // before jdk1.8
        //ExecutorService service = Executors.newCachedThreadPool();
        //Future<Double> future = service.submit(new Callable<Double>() {
        //    @Override
        //    public Double call() throws Exception {
        //        return 1.2;
        //    }
        //});
        //Double result = future.get(1, TimeUnit.SECONDS);
        //System.out.println(result);

        long NANO2MILL_TIME = 1_000_000;
        //Shop shop = new Shop("Best Shop");
        //long start = System.nanoTime();
        //Future<Double> future = shop.getPriceAsync("my favorite product");
        //long invocationTime = (System.nanoTime()-start)/NANO2MILL_TIME;
        //System.out.println("invocationTime: "+invocationTime);
        //
        //SleepUtils.sleepSeconds(3);//other thing
        //
        //double price  = future.get();
        //System.out.println("price: "+price);

        long start = System.nanoTime();

        System.out.println(Shop.findPrice2("myIphone2s"));

        System.out.println("seconds: " + (System.nanoTime() - start) / NANO2MILL_TIME);
        SleepUtils.sleepSeconds(3);

    }
    static class Quote{
        private final String shopName;
        private final double price;
        private final Discount.Code discountCode;

        public Quote(String shopName, double price, Discount.Code discountCode) {
            this.shopName = shopName;
            this.price = price;
            this.discountCode = discountCode;
        }

        public String getShopName() {
            return shopName;
        }

        public double getPrice() {
            return price;
        }

        public Discount.Code getDiscountCode() {
            return discountCode;
        }

        public static Quote parse(String s){
            String[] splite = s.split(":");
            String shopName = splite[0];
            double price = Double.parseDouble(splite[1]);
            Discount.Code discountCode = Discount.Code.valueOf(splite[2]);
            return new Quote(shopName,price,discountCode);
        }
    }
    static class Discount{
        private static void delay(){
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

        }
        public enum Code{
            NODE(0),SILVER(5),GOLD(10),PLATNUM(15),DIAMOND(20);
            private final int percentage;

            Code(int percentage) {
                this.percentage = percentage;
            }
        }
        public static String applyDiscount(Quote quote){
            return quote.getShopName()+" price is "+Discount.apply(quote.getPrice(),quote.getDiscountCode());
        }
        private static double apply(double price,Code code){
            delay();
            return price * (100 - code.percentage) / 100;
        }
    }
    static class Shop{
        private String shopName;

        public Shop(String shopName) {
            this.shopName = shopName;
        }

        public String getShopName() {
            return shopName;
        }

        public void setShopName(String shopName) {
            this.shopName = shopName;
        }

        private static List<Shop> shops = Arrays.asList(
                new Shop("BestPrice"),
                new Shop("LetsSaveBig"),
                new Shop("MyFavoriteShop"),
                new Shop("BuyItAll"),
                new Shop("test"),
                new Shop("BestPrice"),
                new Shop("LetsSaveBig"),
                new Shop("MyFavoriteShop"),
                new Shop("BuyItAll"),
                new Shop("test"),
                new Shop("BestPrice"),
                new Shop("LetsSaveBig"),
                new Shop("MyFavoriteShop"),
                new Shop("BuyItAll"),
                new Shop("test")
        );


        private static void delay(){
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

        }
        private static Executor executor = Executors.newFixedThreadPool(Math.min(shops.size(), 100), new ThreadFactory() {
            @Override
            public Thread newThread(Runnable r) {
                Thread t = new Thread(r);
                t.setDaemon(true);
                return t;
            }
        });



        public static List<String> findPrice2(String product){
            List<CompletableFuture<String>> priceFutures = shops.stream()
                    .map(shop -> CompletableFuture.supplyAsync(()->shop.getPrice(product),executor))
                    .map(future->future.thenApply(Quote::parse))
                    .map(future->future.thenCompose(quote->CompletableFuture.supplyAsync(()->Discount.applyDiscount(quote),executor)))
                    .collect(Collectors.toList());
            return priceFutures.stream().map(CompletableFuture::join).collect(Collectors.toList());
        }
        public static List<String> findPrice(String product){

            //return shops.parallelStream().map(shop->
            //        String.format("%s price is: %.2f",
            //                shop.getShopName(),
            //                shop.getPrice(product)))
            //        .collect(Collectors.toList());

            //List<CompletableFuture<String>> priceFuture = shops.stream()
            //        .map(shop -> CompletableFuture.supplyAsync(()->shop.getShopName()+" pricce is "+shop.getPrice(product),executor))
            //        .collect(Collectors.toList());
            //return priceFuture.stream().map(CompletableFuture::join).collect(Collectors.toList());

            return shops.stream().map(shop -> shop.getPrice(product))
                    .map(Quote::parse)
                    .map(Discount::applyDiscount)
                    .collect(Collectors.toList());

        }
        public Future<Double> getPriceAsync(String product){
            //CompletableFuture<Double> future = new CompletableFuture<>();
            //new Thread(()->{
            //    try {
            //        double price = calculatePrice(product);
            //        future.complete(price);
            //    } catch (Exception e) {
            //        future.completeExceptionally(e);
            //    }
            //}).start();
            //return future;
            return CompletableFuture.supplyAsync(()->calculatePrice(product));
        }
        public String getPrice(String product){
            double price = calculatePrice(product);
            Discount.Code code = Discount.Code.values()[random.nextInt(Discount.Code.values().length)];

            return String.format("%s:%.2f:%s",getShopName(),price,code);
        }
        private double calculatePrice(String product){
            delay();
            return random.nextDouble()*product.charAt(0)+product.charAt(1);
        }
    }
}

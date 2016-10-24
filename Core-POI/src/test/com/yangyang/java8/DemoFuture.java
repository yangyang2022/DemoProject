package com.yangyang.java8;

import com.yangyang.model.Discount;
import com.yangyang.model.Quote;
import com.yangyang.model.Shop;
import org.junit.Test;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.*;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class DemoFuture {

    private Consumer print = System.out::println;

    private static void printCollection(Collection collection){
        collection.forEach(System.out::println);
    }
    private static List<Shop> shops = Arrays.asList(
            new Shop("Best Price"),
            new Shop("LetaSaveBig"),
            new Shop("MyFavoriteShop"),
            new Shop("BuyItAll"),
            new Shop("Best Price"),
            new Shop("LetaSaveBig"),
            new Shop("MyFavoriteShop"),
            new Shop("BuyItAll"),
            new Shop("BuyItAll")
    );

    private static long measureTime(Function<Double,Double> function, String obj){
        long fastest = Long.MAX_VALUE;
        for (int i = 0; i < 10; ++i) {
            long start = System.nanoTime();
            //function.apply(obj);
            long duration = (System.nanoTime() - start)/1_000_000;
            if(duration < fastest) fastest = duration;
        }
        return fastest;
    }

    @Test
    public void testDemo1() {
        String second = " second.";
        String productName = "product4";
        Shop shop = new Shop("Test");

        long start = System.currentTimeMillis();
        //System.out.println(shop.getPrice(productName));
        Future<Double> price = shop.getPriceAsyn(productName);
        //System.out.println(price);
        System.out.println("time: "+(System.currentTimeMillis()-start) + second);
        try {
            Thread.sleep(1000);
            System.out.println(price.get(1000, TimeUnit.MILLISECONDS));
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (TimeoutException e) {
            e.printStackTrace();
        }
    }

    private List<String> findPrice(List<Shop> shops ,String product){
        return shops.parallelStream()
                .map(shop -> String.format("%s price is %.2f ",
                        shop.getShopName(),shop.getPrice(product)))
                .collect(Collectors.toList());
    }
    private final  Executor excutor = Executors.newFixedThreadPool(Math.min(shops.size(), 100), new ThreadFactory() {
        @Override
        public Thread newThread(Runnable r) {
            Thread t = new Thread(r);
            t.setDaemon(true);
            return t;
        }
    });

    private List<String> findPriceAsync(List<Shop> shops ,String product){
        List<CompletableFuture<String>> priceFuture = shops.stream()
                .map(shop -> CompletableFuture.supplyAsync(()->String.format("%s price is %.2f ",
                        shop.getShopName(),shop.getPrice(product)),excutor))
                .collect(Collectors.toList());

        return priceFuture.stream()
                .map(CompletableFuture::join)
                .collect(Collectors.toList());
    }

    private static void messeaure(Consumer<String> consumer,String s){
        long start = System.currentTimeMillis();
        consumer.accept(s);
        System.out.println("Time: "+(System.currentTimeMillis() - start) +" millis.");
    }

    @Test
    public void testDemo2() {
        String productName = "iphone7";

        //findPrice(shops,"iphone7").forEach(print);

        messeaure(e->findPrice(shops,e),productName);

        messeaure(e->findPriceAsync(shops,e),productName);
    }

    private static final String productName = "product1";

    private static List<String> findPrices(String product){
        return shops.stream()
                .map(shop -> shop.getPriceString(product))
                .map(Quote::parse)
                .map(Discount::applyDiscount)
                .collect(Collectors.toList());
    }

    private static Executor executor = Executors.newFixedThreadPool(Math.min(shops.size(),100));

    private static List<String> findPricesAsync(String product){
        List<CompletableFuture<String>> futurePrice = shops.stream()
                .map(shop -> CompletableFuture.supplyAsync(()->shop.getPriceString(product),executor))
                .map(future->future.thenApply(Quote::parse))
                .map(future->future.thenApply(Discount::applyDiscount))
                .collect(Collectors.toList());
                //.map(future ->future.thenCompose(
                //        quote->CompletableFuture.supplyAsync(()->Discount.applyDiscount(quote),executor)
                //)).collect(Collectors.toList());
        return futurePrice.stream().map(CompletableFuture::join).collect(Collectors.toList());
    }

    public Stream<CompletableFuture<String>> findPriceStream(String product){
        return shops.stream()
                .map(shop->CompletableFuture.supplyAsync(()->shop.getPriceString(product),executor))
                .map(future -> future.thenApply(Quote::parse))
                .map(future->future.thenApply(Discount::applyDiscount));
                //.map(future->future.thenCompose(quote->CompletableFuture.supplyAsync(()->Discount.applyDiscount(quote),executor)));
    }

    @Test
    public void testDemo3() {

        //findPrices(productName).forEach(print);
        //printCollection(findPricesAsync(productName));
        //findPriceStream(productName).map(f->f.thenAccept(System.out::println));


        long second = 1_000_000;
        long start = System.nanoTime();
        Consumer done = s-> System.out.println(s + " done in " + ((System.nanoTime() - start) / second) + " msecs");
        CompletableFuture[] fs = (CompletableFuture[]) findPriceStream(productName).map(f->f.thenAccept(done)).toArray(size->new CompletableFuture[size]);
        CompletableFuture.allOf(fs).join();
        System.out.println("all done!!! time: "+(System.nanoTime()-start)/second);

    }
}

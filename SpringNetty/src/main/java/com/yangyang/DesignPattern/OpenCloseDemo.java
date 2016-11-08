package com.yangyang.DesignPattern;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;

interface IBook{
    String getName();
    int getPrice();
    String getAuthor();
}
class NovelBook implements IBook{
    private String name;
    private int price;
    private String autor;

    public NovelBook(String name, int price, String autor) {
        this.name = name;
        this.price = price;
        this.autor = autor;
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public int getPrice() {
        return this.price;
    }

    @Override
    public String getAuthor() {
        return this.autor;
    }
}

class offNovelBook extends NovelBook{
    @Override
    public int getPrice() {
        int selfPrice = super.getPrice();
        int offprice = 0;
        if(selfPrice > 4000) offprice = selfPrice * 90 /100;
        else offprice = selfPrice * 80 /100;
        return offprice;
    }

    public offNovelBook(String name, int price, String autor) {

        super(name, price, autor);
    }
}

public class OpenCloseDemo {

    private final static List<IBook> books = new ArrayList<>();
    static {
        books.add(new offNovelBook("name1",3200,"author1"));
        books.add(new offNovelBook("name2",5600,"author2"));
        books.add(new offNovelBook("name3",3500,"author3"));
        books.add(new offNovelBook("name4",4300,"author4"));
    }
    public static void main(String[] args) {
        NumberFormat format = NumberFormat.getCurrencyInstance();
        format.setMaximumFractionDigits(2);
        System.out.println("-------------------book info ---------------");
        books.forEach(e-> System.out.println("bookname: "+e.getName()+
                " \tauthor: "+e.getAuthor()+
                " \tprice: "+format.format(e.getPrice()/100.0)));

    }
}

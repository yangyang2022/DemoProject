package com.yangyang.reflactor.chapter1;

public class Main {
    public static void main(String[] args) {

        Movie movie1 = new Movie("movie1",Movie.CHILDRENS);
        Movie movie2 = new Movie("movie1",Movie.CHILDRENS);
        Movie movie3 = new Movie("movie1",Movie.NEW_RELEASE);
        Movie movie4 = new Movie("movie1",Movie.NEW_RELEASE);
        Movie movie5 = new Movie("movie1",Movie.REGULAR);
        Movie movie6 = new Movie("movie1",Movie.REGULAR);

        Rental rental1 = new Rental(movie1,1);
        Rental rental2 = new Rental(movie2,2);
        Rental rental3 = new Rental(movie3,3);
        Rental rental4 = new Rental(movie4,4);
        Rental rental5 = new Rental(movie5,5);

        Customer customer1 = new Customer("yangyang");
        customer1.addRental(rental1);
        customer1.addRental(rental2);
        customer1.addRental(rental3);
        customer1.addRental(rental4);
        customer1.addRental(rental5);

        System.out.println(customer1.statement());
        

    }
}

package com.yangyang.reflactor.chapter1;

import java.util.Enumeration;
import java.util.Vector;

public class Customer {
    private String _name;
    private Vector<Rental> _rentals = new Vector<>();

    public Customer(String _name) {
        this._name = _name;
    }

    public String get_name() {
        return _name;
    }
    public void addRental(Rental rental){
        _rentals.add(rental);
    }

    private double getTotalCharge(){
        return   _rentals.stream().mapToDouble(Rental::getCharge).sum();
    }
    private int getTotalFreuentPoints(){
        return _rentals.stream().mapToInt(Rental::getFrequentRenterPoint).sum();
    }
    public String statement(){

        Enumeration rentals = _rentals.elements();

        String result = "Rental Record for "+get_name()+" \n";

        while (rentals.hasMoreElements()){
            Rental each = (Rental) rentals.nextElement();
            //show figures for this rental
            result += "\t"+each.get_movie().get_title()+" \t"+String.valueOf(each.getCharge())+" \n";
        }
        //add footer lines
        result += "Amount owed is "+String.valueOf(getTotalCharge())+" \n";
        result += "You earned "+String.valueOf(getTotalFreuentPoints())+ " frequent renter Points";
        return result;
    }
}

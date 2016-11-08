package com.yangyang.reflactor.chapter1;

public class Movie {

    public static final int CHILDRENS = 2;
    public static final int NEW_RELEASE = 1;
    public static final int REGULAR = 0;

    private String _title;

    private Price _price;

    public Movie(String _title, int _priceCode) {
        this._title = _title;
        set_priceCode(_priceCode);
    }

    public String get_title() {
        return _title;
    }

    public void set_priceCode(int _priceCode) {
        //this._priceCode = _priceCode;
        switch (_priceCode){
            case REGULAR:_price = new RegularPrice();break;
            case CHILDRENS:_price = new ChildrenPrice();break;
            case NEW_RELEASE:_price = new NewReleasePrice();break;
            default:
                throw new IllegalArgumentException("Incorrect Price Code");
        }
    }

    public double getCharge(int daysRented){
        return _price.getCharge(daysRented);
    }

    public int getFrequentRenterPoint(int daysRented){
        return _price.getFrequentRenterPoint(daysRented);
    }
}

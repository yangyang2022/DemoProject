package com.yangyang.reflactor.chapter1;

public class Rental {
    private Movie _movie;
    private int _daysRental;

    public Rental(Movie _movie, int _daysRental) {
        this._movie = _movie;
        this._daysRental = _daysRental;
    }

    public Movie get_movie() {
        return _movie;
    }

    public double getCharge(){
        return _movie.getCharge(_daysRental);
    }

    int getFrequentRenterPoint(){
        return _movie.getFrequentRenterPoint(_daysRental);
    }
}

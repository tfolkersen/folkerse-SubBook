package com.example.folkerse_subbook;

/**
 * Created by cf on 2018-02-03.
 */

public class NegativeValueException extends Exception {
    private double value;
    public NegativeValueException(double value){
        super();
        this.value = value;
    }

    @Override
    public String toString(){
        return "Negative Value: " + Double.toString(this.value);
    }


}

//NegativeValueException

package com.example.folkerse_subbook;

/**
 * Exception thrown when value entered is negative
 * @author folkerse
 * @version 1.0
 * @see Subscription
 */
public class NegativeValueException extends Exception {
    private double value;


    public NegativeValueException(double value) {
        super();
        this.value = value;
    }


    /**
     * @return Message explaining that the value is negative, shows value
     */
    @Override
    public String toString() {
        return "Negative Value: " + Double.toString(this.value);
    }

}

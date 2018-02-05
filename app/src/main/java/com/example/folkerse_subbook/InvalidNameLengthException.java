package com.example.folkerse_subbook;

/**
 * Exception thrown when name is blank or too long
 *
 * @author folkerse
 * @version 1.0
 * @see Subscription
 */

public class InvalidNameLengthException extends Exception {

    private String name;

    public InvalidNameLengthException(String name){
        this.name = name;
    }


    /**
     * @return Message showing name length and saying that it's invalid
     */
    @Override
    public String toString(){
        return "Invalid Name Length: " + name.length();
    }


}

package com.example.folkerse_subbook;

/**
 * Created by cf on 2018-02-03.
 */

public class InvalidNameLengthException extends Exception {

    private String name;

    public InvalidNameLengthException(String name){
        this.name = name;
    }

    @Override
    public String toString(){
        return "Invalid Name Length: " + name.length();
    }


}

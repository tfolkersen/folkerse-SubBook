package com.example.folkerse_subbook;

/**
 * Created by cf on 2018-02-03.
 */

public class NameTooLongException extends Exception {

    private String name;

    public NameTooLongException(String name){
        this.name = name;
    }

    @Override
    public String toString(){
        return this.name;
    }


}

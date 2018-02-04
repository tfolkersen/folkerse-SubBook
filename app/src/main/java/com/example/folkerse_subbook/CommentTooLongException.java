package com.example.folkerse_subbook;

/**
 * Created by cf on 2018-02-03.
 */

public class CommentTooLongException extends Exception{
    private String comment;

    public CommentTooLongException(String comment){
        this.comment = comment;
    }

    @Override
    public String toString(){
        return this.comment;
    }


}

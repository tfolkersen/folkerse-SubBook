package com.example.folkerse_subbook;

import android.os.Parcelable;

import org.w3c.dom.Comment;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
/**
 * Created by cf on 2018-02-03.
 */

public class Subscription implements Serializable{

    private String name;
    private Date date;
    private double charge;
    private String comment;

    public Subscription(){
    }


    public Subscription(String name, Date date, double charge, String comment)throws Exception{
        this.setName(name);
        this.setDate(date);
        this.setCharge(charge);
        this.setComment(comment);
    }


    public void setName(String name) throws NameTooLongException{
        if(name.length() > 20){
            throw new NameTooLongException(name);
        }
        this.name = name;
    }


    public String getName(){
        return this.name;
    }


    public void setDate(Date date){
        this.date = date;
    }


    public Date getDate(){
        return date;
    }

    public void setCharge(double charge) throws NegativeValueException{
        if(charge < 0){
            throw new NegativeValueException(charge);
        }

        this.charge = charge;
    }


    public double getCharge(){
        return charge;
    }


    public void setComment(String comment) throws CommentTooLongException{
        if(comment.length() > 30){
            throw new CommentTooLongException(comment);
        }

        this.comment = comment;
    }


    public String getComment(){
        return comment;
    }


    public String getDateString(){
        SimpleDateFormat fmt = new SimpleDateFormat("yyyy MMMM d");
        return fmt.format(date);
    }

    public String toString(){
        String dateString = this.getDateString();
        return name + " | " + dateString + " | " + Double.toString(charge);
    }




}

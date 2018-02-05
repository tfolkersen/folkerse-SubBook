//Subscription

package com.example.folkerse_subbook;

import java.io.Serializable;
import java.math.RoundingMode;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Date;


/**
 * Represents a subscription
 *
 * @author folkerse
 * @version 1.1
 * @see SubscriptionList
 * @see InvalidNameLengthException
 * @see CommentTooLongException
 * @see NegativeValueException
 */
public class Subscription implements Serializable {
    private String name;
    private Date date;
    private double charge;
    private String comment;

    /**
     * @param name 1-20 characters
     * @param date
     * @param charge not negative, rounded to nearest hundredth
     * @param comment 0-30 characters
     * @throws InvalidNameLengthException
     * @throws NegativeValueException
     * @throws CommentTooLongException
     */
    public Subscription(String name, Date date, double charge, String comment) throws Exception {
        this.setName(name);
        this.setDate(date);
        this.setCharge(charge);
        this.setComment(comment);
    }


    /**
     * @param name 1-20 characters
     * @throws InvalidNameLengthException
     */
    public void setName(String name) throws InvalidNameLengthException {
        if (name.length() > 20 || name.length() == 0) {
            throw new InvalidNameLengthException(name);
        }
        this.name = name;
    }


    public String getName() {
        return this.name;
    }


    public void setDate(Date date) {
        this.date = date;
    }


    public Date getDate() {
        return date;
    }


    /**
     * @param charge non-negative, rounded to nearest hundredth
     * @throws NegativeValueException
     */
    public void setCharge(double charge) throws NegativeValueException {
        if (charge < 0) {
            throw new NegativeValueException(charge);
        }

        NumberFormat fmt = NumberFormat.getInstance();
        fmt.setMaximumFractionDigits(2);
        fmt.setRoundingMode(RoundingMode.HALF_UP);
        this.charge = Double.parseDouble(fmt.format(charge).replaceAll(",",""));
    }


    public double getCharge() {
        return charge;
    }


    /**
     * @return The charge, formatted: X.yy
     */
    public String getChargeString() {
        NumberFormat fmt = NumberFormat.getInstance();
        fmt.setMinimumFractionDigits(2);
        fmt.setMaximumFractionDigits(2);
        return fmt.format(charge);
    }


    /**
     * @param comment 0-30 characters
     * @throws CommentTooLongException
     */
    public void setComment(String comment) throws CommentTooLongException {
        if (comment.length() > 30) {
            throw new CommentTooLongException(comment);
        }

        this.comment = comment;
    }


    public String getComment() {
        return comment;
    }


    /**
     * @return Date of subscription, formatted:
     * Year Month Day -- text text number
     */
    public String getDateString() {
        SimpleDateFormat fmt = new SimpleDateFormat("yyyy MMMM d");
        return fmt.format(date);
    }


    /**
     * @return Summary of Subscription, formatted; excludes comment
     */
    public String toString() {
        String dateString = this.getDateString();
        String chargeString = this.getChargeString();

        return name + " | " + dateString + " | " + "$" + chargeString;
    }


}

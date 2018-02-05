//SubscriptionList

package com.example.folkerse_subbook;

import android.content.Context;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.Type;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

/**
 * Contains Subscription objects
 * @author folkerse
 * @version 1.0
 * @see Subscription
 */
public class SubscriptionList {
    private ArrayList<Subscription> contents;
    private ArrayAdapter<Subscription> adapter;


    public SubscriptionList() {
        this.contents = new ArrayList<Subscription>();
    }


    /**
     * Initialize and bind SubscriptionList to a ListView
     *
     * @param context calling activity's context
     * @param resource ID of xml layout of list element
     * @param v ID of ListView in activity
     */
    public SubscriptionList(Context context, int resource, ListView v) {
        super();
        this.bindListView(context, resource, v);
    }



    //taken from lab
    /**
     * Load the data into the SubscriptionList, unbinds any bound ListView
     *
     * @param context calling activity's context
     */
    public void load(Context context, String filename) {

        try {
            FileInputStream fis = context.openFileInput(filename);
            BufferedReader in = new BufferedReader(new InputStreamReader(fis));

            Gson gson = new Gson();

            // Taken from https://stackoverflow.com/questions/12384064/gson-convert-from-json-to-a-typed-arraylistt
            // 2018-1-25
            Type listType = new TypeToken<ArrayList<Subscription>>(){}.getType();
            this.contents = gson.fromJson(in, listType);


        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            this.contents = new ArrayList<Subscription>();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            throw new RuntimeException();
        }
    }


    //taken from lab
    /**
     * Save the data from the SubscriptionList
     *
     * @param context calling activity's context
     */
    public void save(Context context, String filename) {
        try {
            FileOutputStream fos = context.openFileOutput(filename, Context.MODE_PRIVATE);
            BufferedWriter out = new BufferedWriter(new OutputStreamWriter(fos));
            Gson gson = new Gson();
            gson.toJson(this.contents, out);
            out.flush();
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            throw new RuntimeException();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            throw new RuntimeException();
        }
    }



    public void clear() {
        this.contents.clear();
    }


    public int size() {
        return contents.size();
    }


    public void add(Subscription sub) {
        contents.add(sub);
    }


    public void remove(int index) {
        contents.remove(index);
    }


    public Subscription get(int index) {
        return contents.get(index);
    }


    /**
     * @param index Where to overwrite Subscription
     * @param sub Subscription to overwrite with
     */
    public void set(int index, Subscription sub) {
        this.contents.set(index, sub);
    }


    /**
     * @return Sum of costs of Subscriptions, formatted: X.yy
     */
    public String sumString() {
        double s = 0;
        for (int i = 0; i < contents.size(); i++) {
            s += contents.get(i).getCharge();
        }

        NumberFormat fmt = NumberFormat.getInstance();
        fmt.setMinimumFractionDigits(2);
        fmt.setMaximumFractionDigits(2);
        return fmt.format(s);
    }


    /**
     * Bind the SubscriptionList to a ListView
     *
     * @param context calling activity's context
     * @param resource ID of xml layout of list element
     * @param v ID of ListView in activity
     */
    public void bindListView(Context context, int resource, ListView v) {
        this.adapter = new ArrayAdapter<Subscription>(context, resource, contents);
        v.setAdapter(adapter);
    }


    /**
     * Refresh the ListView's display
     */
    public void refreshDisplay() {
        adapter.notifyDataSetChanged();
    }


}

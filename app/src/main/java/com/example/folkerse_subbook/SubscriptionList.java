package com.example.folkerse_subbook;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
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
import java.io.FileOutputStream;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

/**
 * Created by cf on 2018-02-04.
 */

public class SubscriptionList {
    public ArrayList<Subscription> contents;
    private ArrayAdapter<Subscription> adapter;


    public SubscriptionList(){
        this.contents = new ArrayList<Subscription>();
    }


    public SubscriptionList(Context context, int resource, ListView v){
        super();
        this.setup(context, resource, v);
    }


    //taken from lab
    public void load(Context context, String filename) {

        try {
            FileInputStream fis = context.openFileInput(filename);
            BufferedReader in = new BufferedReader(new InputStreamReader(fis));

            //ADDED
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
    public void save(Context context, String filename) {
        try {
            //CHANGED AND ADDED
            FileOutputStream fos = context.openFileOutput(filename,
                    Context.MODE_PRIVATE);
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



    public void clear(){
        this.contents.clear();
    }


    public int size(){
        return contents.size();
    }


    public void add(Subscription sub){
        contents.add(sub);
    }


    public void remove(int index){
        contents.remove(index);
    }


    public Subscription get(int index){
        return contents.get(index);
    }


    public String sumString(){
        double s = 0;
        for(int i = 0; i < contents.size(); i++){
            s += contents.get(i).getCharge();
        }

        NumberFormat fmt = NumberFormat.getInstance();
        fmt.setMinimumFractionDigits(2);
        fmt.setMaximumIntegerDigits(2);
        return fmt.format(s);
    }


    public void setup(Context context, int resource, ListView v){
        this.adapter = new ArrayAdapter<Subscription>(context, resource, contents);
        v.setAdapter(adapter);
    }


    public void refreshDisplay(){
        adapter.notifyDataSetChanged();
    }


}

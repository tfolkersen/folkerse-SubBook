package com.example.folkerse_subbook;

import android.content.Context;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

/**
 * Created by cf on 2018-02-04.
 */

public class SubscriptionList {
    private ArrayList<Subscription> contents;
    private ArrayAdapter<Subscription> adapter;




    public SubscriptionList(){
        this.contents = new ArrayList<Subscription>();
    }


    public SubscriptionList(Context context, int resource, ListView v){
        super();
        this.setup(context, resource, v);
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


    public double sum(){
        double s = 0;
        for(int i = 0; i < contents.size(); i++){
            s += contents.get(i).getCharge();
        }

        return s;
    }

    public void setup(Context context, int resource, ListView v){
        this.adapter = new ArrayAdapter<Subscription>(context, resource, contents);
        v.setAdapter(adapter);
    }



    public void refreshDisplay(){
        adapter.notifyDataSetChanged();
    }




}

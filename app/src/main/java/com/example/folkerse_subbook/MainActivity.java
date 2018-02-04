package com.example.folkerse_subbook;

import android.content.Intent;
import android.os.Parcel;
import android.os.Parcelable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import java.util.Date;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ListView subDisplay;
    private Button buttonNew;
    private ArrayAdapter<Subscription> subAdapter;
    private ArrayList<Subscription> subList;
    private ArrayList<String> test;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Testing
        test = new ArrayList<String>();
        test.add("asd");
        test.add("ndsfoijsdofij");

        buttonNew = findViewById(R.id.buttonNew);
        subDisplay = findViewById(R.id.subDisplay);
        subList = new ArrayList<Subscription>();
        subAdapter = new ArrayAdapter<Subscription>(this, R.layout.list_element, subList);
        subDisplay.setAdapter(subAdapter);

        //ADD LISTENERS
        buttonNew.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                viewItem(1);
            }

        });

        //Done with listeners

        //Add some testing data
        try {
            subList.add(new Subscription("Name xd", new Date(), 20.2, "asd"));
            subList.add(new Subscription("lmao", new Date(), 0.5, "comment here"));
        }
        catch (Exception E){
            Log.i("EXCEPTION xd", E.toString());
        }
        subAdapter.notifyDataSetChanged();


    }

    protected void viewItem(int index){
        Intent intent = new Intent(this, ViewItem.class);
        intent.putExtra("ind", index);
        intent.putExtra("arr", subList);
        startActivity(intent);
    }

    @Override
    protected void onStart(){
        super.onStart();
        //Load everything
        //Refresh data
    }

    private void saveData(){
        //Save all the data
    }

    private void loadData(){
        //Load the data and refresh
    }


}

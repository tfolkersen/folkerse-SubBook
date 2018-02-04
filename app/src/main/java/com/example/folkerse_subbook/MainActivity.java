package com.example.folkerse_subbook;

import android.content.Intent;
import android.os.Parcel;
import android.os.Parcelable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import java.util.Date;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ListView subDisplay;
    private Button buttonNew;
    private ArrayAdapter<Subscription> subAdapter;
    public static ArrayList<Subscription> subList;

    public MainActivity(){
        super();
        Log.i("CREATED","xd");
        subList = new ArrayList<Subscription>();

        //Add some testing data
        try {
            subList.add(new Subscription("Name xd", new Date(), 20.2, "asd"));
            subList.add(new Subscription("lmao", new Date(), 0.5, "comment here"));
        }
        catch (Exception E){
            Log.i("EXCEPTION xd", E.toString());
        }
    }//end constructor


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        buttonNew = findViewById(R.id.buttonNew);
        subDisplay = findViewById(R.id.subDisplay);
        subAdapter = new ArrayAdapter<Subscription>(this, R.layout.list_element, subList);
        subDisplay.setAdapter(subAdapter);

        //ADD LISTENERS
        buttonNew.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
            }

        });

        subDisplay.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Log.i("CLICKED", ""+position);
                viewItem(position);
            }
        });

        //Done with listeners

        subAdapter.notifyDataSetChanged();
    }


    protected void viewItem(int index){
        Intent intent = new Intent(this, ViewItem.class);
        intent.putExtra("sub", subList.get(index));
        intent.putExtra("index", index);
        startActivityForResult(intent, IntentCodes.VIEW_ITEM);
    }


    @Override
    protected void onStart(){
        super.onStart();
        //Load everything
        //Refresh data
        subAdapter.notifyDataSetChanged();
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent intent){
        switch(resultCode){
            case IntentCodes.DELETE_ITEM:
                subList.remove((int) intent.getSerializableExtra("index"));
                subAdapter.notifyDataSetChanged();
                break;
        }
    }


    private void saveData(){
        //Save all the data
    }


    private void loadData(){
        //Load the data and refresh
    }


}

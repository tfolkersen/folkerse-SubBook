package com.example.folkerse_subbook;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Parcel;
import android.os.Parcelable;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.Date;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    public ListView subDisplay;
    private Button buttonNew;
    private TextView costView;
    public static SubscriptionList subList;
    private final String filename = "com.example.folkerse_suiuhiuhbbook.test.sav";

    public MainActivity(){
        super();
        Log.i("CREATED","xd");
        subList = new SubscriptionList();
    }//end constructor



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        buttonNew = findViewById(R.id.buttonNew);
        subDisplay = findViewById(R.id.subDisplay);
        costView = findViewById(R.id.costView);


        subList.setup(this, R.layout.list_element, subDisplay);
        //ADD LISTENERS
        buttonNew.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                try{
                    subList.add(new Subscription("Name xd", new Date(), 20.2, "asd"));
                    subList.add(new Subscription("lmao", new Date(), 0.5, "comment here"));
                }
                catch(Exception e){
                    Log.i("rip","sdfsdfs");
                }
                subList.refreshDisplay();
                costView.setText("Total: $" + subList.sumString());
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
        load();
        subList.refreshDisplay();
        costView.setText("Total: $" + subList.sumString());

    }


    /*
  Taken from
  https://stackoverflow.com/questions/14981233/android-arraylist-of-custom-objects-save-to-sharedpreferences-serializable
   */
    public void save(){
        String key = "Key";
        ArrayList<Subscription> ModelArrayList=new ArrayList();

        SharedPreferences shref;
        SharedPreferences.Editor editor;
        shref = this.getSharedPreferences("asd", Context.MODE_PRIVATE);

        Gson gson = new Gson();
        String json = gson.toJson(subList.contents);

        editor = shref.edit();
        editor.remove(key).commit();
        editor.putString(key, json);
        editor.commit();

    }

    /*
    Taken from
    https://stackoverflow.com/questions/14981233/android-arraylist-of-custom-objects-save-to-sharedpreferences-serializable
     */
    public void load(){
        SharedPreferences shref;
        SharedPreferences.Editor editor;
        shref = this.getSharedPreferences("asd", Context.MODE_PRIVATE);

        Gson gson = new Gson();

        String response = shref.getString("asd" , "");
        subList.contents = gson.fromJson(response,
                new TypeToken<ArrayList<Subscription>>(){}.getType());

    }

    protected void viewItem(int index){
        Intent intent = new Intent(this, ViewItem.class);
        intent.putExtra("sub", subList.get(index));
        intent.putExtra("index", index);
        startActivityForResult(intent, IntentCodes.VIEW_ITEM);
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent intent){
        switch(resultCode){
            case IntentCodes.DELETE_ITEM:
                subList.remove((int) intent.getSerializableExtra("index"));
                subList.refreshDisplay();
                costView.setText("Total: $" + subList.sumString());
                break;
        }
    }

    @Override
    protected void onDestroy(){
        super.onDestroy();
    }


}

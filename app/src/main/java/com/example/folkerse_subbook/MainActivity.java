package com.example.folkerse_subbook;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;


public class MainActivity extends AppCompatActivity {

    //Views of the activity
    public ListView subDisplay;
    private Button buttonNew;
    private TextView costView;
    private SubscriptionList subList;
    private final String filename = "subList_contents.sav";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Get all views
        buttonNew = findViewById(R.id.buttonNew);
        subDisplay = findViewById(R.id.subDisplay);
        costView = findViewById(R.id.costView);

        //Init subList
        subList = new SubscriptionList();
        subList.load(this, filename);
        subList.setup(this, R.layout.list_element, subDisplay);

        //ADD LISTENERS
        buttonNew.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                makeNew();
            }
        });

        subDisplay.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Log.i("CLICKED", ""+position);
                viewItem(position);
            }
        });

        //Done setting up
        refreshDisplay();
    }


    protected void viewItem(int index){
        Intent intent = new Intent(this, ViewItem.class);
        intent.putExtra("sub", subList.get(index));
        intent.putExtra("index", index);
        intent.putExtra("requestCode", IntentCodes.VIEW_ITEM);
        startActivityForResult(intent, IntentCodes.VIEW_ITEM);
    }


    protected void makeNew(){
        Intent intent = new Intent(this, EditItem.class);
        intent.putExtra("requestCode", IntentCodes.NEW_ITEM);
        startActivityForResult(intent, IntentCodes.NEW_ITEM);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent intent){
        Subscription sub;

        switch(resultCode){
            case IntentCodes.DELETE_ITEM:
                subList.remove((int) intent.getSerializableExtra("index"));
                subList.save(this, filename);
                refreshDisplay();
                break;

            case IntentCodes.NEW_ITEM:
                sub = (Subscription) intent.getSerializableExtra("sub");
                subList.add(sub);
                subList.save(this, filename);
                refreshDisplay();
                break;

            case IntentCodes.EDIT_ITEM:
                int index = (int) intent.getSerializableExtra("index");
                sub = (Subscription) intent.getSerializableExtra("sub");
                subList.set(index, sub);
                subList.save(this, filename);
                refreshDisplay();
                break;
        }
    }

    protected void refreshDisplay(){
        subList.refreshDisplay();
        costView.setText("Total: $" + subList.sumString());
    }


}

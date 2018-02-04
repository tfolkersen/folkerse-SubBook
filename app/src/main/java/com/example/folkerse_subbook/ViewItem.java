package com.example.folkerse_subbook;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

public class ViewItem extends AppCompatActivity {

    private Button buttonEdit;
    private Button buttonDelete;
    private TextView nameView;
    private TextView secondView;
    private TextView commentView;
    private Intent intent;
    private Subscription subscription;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_item);


        intent = getIntent();

        subscription = (Subscription) intent.getSerializableExtra("sub");

        nameView  = findViewById(R.id.nameView);
        secondView = findViewById(R.id.secondView);
        commentView = findViewById(R.id.commentView);
        buttonDelete = findViewById(R.id.buttonDelete);
        buttonEdit = findViewById(R.id.buttonEdit);

        //Set up the view
        refreshView();

        //Add button listeners
        buttonDelete.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                deleteCurrent();
            }
        });

        buttonEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editItem();
            }
        });

    }

    protected void editItem(){
        Intent intent = new Intent(this, EditItem.class);
        intent.putExtra("sub", subscription);
        startActivityForResult(intent, IntentCodes.EDIT_ITEM);

    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent intent){
        switch(resultCode){
            case IntentCodes.EDITED_ITEM:
                break;
        }
    }


    protected void refreshView(){
        nameView.setText(subscription.getName());
        secondView.setText(subscription.getDateString() + "\n" + "$" + subscription.getChargeString());
        commentView.setText(subscription.getComment());
    }

    protected void deleteCurrent(){
        Intent returnIntent = new Intent();
        returnIntent.putExtra("index", intent.getSerializableExtra("index"));
        setResult(IntentCodes.DELETE_ITEM, returnIntent);
        finish();
    }

}

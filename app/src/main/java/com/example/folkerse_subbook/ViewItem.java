package com.example.folkerse_subbook;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import java.util.ArrayList;

public class ViewItem extends AppCompatActivity {

    private TextView nameView;
    private TextView secondView;
    private TextView commentView;
    private Intent intent;
    private ArrayList<Subscription> subList;
    private int index;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_item);

        intent = getIntent();

        subList = (ArrayList<Subscription>) intent.getSerializableExtra("arr");
        index = (int) intent.getSerializableExtra("ind");

        nameView  = findViewById(R.id.nameView);
        secondView = findViewById(R.id.secondView);
        commentView = findViewById(R.id.commentView);

        //Set up the view
        refreshView();

    }

    protected void refreshView(){
        Subscription sub = subList.get(index);
        nameView.setText(sub.getName());
        secondView.setText(sub.getDateString() + "\n" + "$" + sub.getCharge());
        commentView.setText(sub.getComment());
    }
}

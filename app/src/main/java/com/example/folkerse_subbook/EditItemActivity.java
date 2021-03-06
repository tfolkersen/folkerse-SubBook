//EditItemActivity

package com.example.folkerse_subbook;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Date;

public class EditItemActivity extends AppCompatActivity {
    //Views for the activity
    private DatePicker datePicker;
    private EditText nameEdit;
    private EditText valueEdit;
    private EditText commentEdit;
    private TextView dateView;
    private Button buttonEditDate;
    private Button buttonDateConfirm;
    private TextView errorView;
    private Button buttonSave;
    private Button buttonCancel;

    //Values of the Subscription being edited
    private String name;
    private double value;
    private Date date;
    private String comment;
    private Intent intent;
    private int returnCode;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_item);

        //Save some things
        intent = getIntent();
        returnCode = (int) intent.getSerializableExtra("requestCode");

        date = new Date();

        //Get all views
        datePicker = findViewById(R.id.datePicker);
        nameEdit = findViewById(R.id.nameEdit);
        valueEdit = findViewById(R.id.valueEdit);
        commentEdit = findViewById(R.id.commentEdit);
        buttonEditDate = findViewById(R.id.buttonEditDate);
        dateView = findViewById(R.id.dateView);
        buttonDateConfirm = findViewById(R.id.buttonDateConfirm);
        errorView = findViewById(R.id.errorView);
        buttonSave = findViewById(R.id.buttonSave);
        buttonCancel = findViewById(R.id.buttonCancel);

        //Set initial values if editing an existing item
        if (returnCode == IntentCodes.EDIT_ITEM) {
            Subscription sub = (Subscription) intent.getSerializableExtra("sub");
            date = sub.getDate();
            value = sub.getCharge();
            name = sub.getName();
            comment = sub.getComment();

            valueEdit.setText(Double.toString(value));
            commentEdit.setText(comment);
            nameEdit.setText(name);
        }

        //Set up views
        refreshDateView(dateView, date);
        datePicker.setVisibility(View.GONE);

        //Add button listeners
        buttonEditDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDatePicker();
            }
        });

        buttonDateConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                confirmDate();
            }
        });

        buttonSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveChanges();
            }
        });

        buttonCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cancel();
            }
        });

    }


    /**
     * Put the date into the dateView
     * @param view
     * @param d
     */
    protected void refreshDateView(TextView view, Date d) {
        SimpleDateFormat fmt = new SimpleDateFormat("yyyy MMMM d");
        view.setText(fmt.format(d));
    }


    /**
     * Cancel editing current Subscription, return to ViewItemActivity
     */
    protected void cancel() {
        Intent returnIntent = new Intent();
        setResult(IntentCodes.CANCELLED, returnIntent);
        finish();
    }


    /**
     * Send Subscription back to MainActivity if valid, else show error
     */
    protected void saveChanges() {
        Subscription sub;
        try {
            name = nameEdit.getText().toString();
            comment = commentEdit.getText().toString();
            value = Double.parseDouble(valueEdit.getText().toString().replaceAll(",",""));
            sub = new Subscription(name, date, value, comment);
        } catch (NumberFormatException e) {
            errorView.setText("Enter a Number");
            Log.e("Number Format Exception", e.toString());
            return;
        } catch (Exception e) {
            errorView.setText(e.toString());
            return;
        }

        //If the Subscription is valid, return it
        Intent returnIntent = new Intent();
        returnIntent.putExtra("sub", sub);

        if (returnCode == IntentCodes.EDIT_ITEM) {
            int index = (int) intent.getSerializableExtra("index");
            returnIntent.putExtra("index", index);
        }


        setResult(returnCode, returnIntent);
        finish();
    }


    /**
     * Hide everything but the date picker
     */
    protected void showDatePicker() {
        //Hide everything
        commentEdit.setVisibility(View.GONE);
        dateView.setVisibility(View.GONE);
        buttonEditDate.setVisibility(View.GONE);
        nameEdit.setVisibility(View.GONE);
        valueEdit.setVisibility(View.GONE);
        buttonCancel.setVisibility(View.GONE);
        buttonSave.setVisibility(View.GONE);

        //Show the date picker and confirm button
        datePicker.updateDate(date.getYear()+1900, date.getMonth(), date.getDate());
        buttonDateConfirm.setVisibility(View.VISIBLE);
        datePicker.setVisibility(View.VISIBLE);
    }


    /**
     * Get chosen date, hide date picker
     */
    protected void confirmDate() {
        //Get chosen date
        date = new Date(datePicker.getYear()-1900, datePicker.getMonth(), datePicker.getDayOfMonth());

        //Show main views
        commentEdit.setVisibility(View.VISIBLE);
        dateView.setVisibility(View.VISIBLE);
        buttonEditDate.setVisibility(View.VISIBLE);
        nameEdit.setVisibility(View.VISIBLE);
        valueEdit.setVisibility(View.VISIBLE);
        buttonSave.setVisibility(View.VISIBLE);
        buttonCancel.setVisibility(View.VISIBLE);

        //Hide date selection views
        buttonDateConfirm.setVisibility(View.GONE);
        datePicker.setVisibility(View.GONE);

        refreshDateView(dateView, date);
    }

}

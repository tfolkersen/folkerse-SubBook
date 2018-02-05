//ViewItemActivity

package com.example.folkerse_subbook;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

/**
 * Activity that shows the full subscription info, can open edit activity or go back to Main
 *
 * @author folkerse
 * @version 1.0
 */
public class ViewItemActivity extends AppCompatActivity {
    //Views for the activity
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

        //Save some objects
        intent = getIntent();
        subscription = (Subscription) intent.getSerializableExtra("sub");

        //Get views
        nameView  = findViewById(R.id.nameView);
        secondView = findViewById(R.id.secondView);
        commentView = findViewById(R.id.commentView);
        buttonDelete = findViewById(R.id.buttonDelete);
        buttonEdit = findViewById(R.id.buttonEdit);


        //Set up the screen
        refreshScreen();

        //Add button listeners
        buttonDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
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

    /**
     * Open the Edit activity with this Subscription
     */
    protected void editItem() {
        int index = (int) intent.getSerializableExtra("index");

        Intent intent = new Intent(this, EditItemActivity.class);
        intent.putExtra("sub", subscription);
        intent.putExtra("requestCode", IntentCodes.EDIT_ITEM);
        intent.putExtra("index", index);
        startActivityForResult(intent, IntentCodes.EDIT_ITEM);
    }


    /**
     * Pass edited Subscription from Edit activity back to MainActivity
     *
     * @param requestCode
     * @param resultCode
     * @param intent returning intent
     */
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent intent) {
        switch (resultCode) {
            case IntentCodes.EDIT_ITEM:
                setResult(IntentCodes.EDIT_ITEM, intent);
                finish();
                break;
        }
    }


    /**
     * Set views to hold data from the given Subscription
     */
    protected void refreshScreen() {
        nameView.setText(subscription.getName());
        secondView.setText(subscription.getDateString() + "\n" + "$" + subscription.getChargeString());
        commentView.setText(subscription.getComment());
    }


    /**
     * Tell MainActivity to delete the current Subscription
     */
    protected void deleteCurrent() {
        Intent returnIntent = new Intent();
        returnIntent.putExtra("index", intent.getSerializableExtra("index"));
        setResult(IntentCodes.DELETE_ITEM, returnIntent);
        finish();
    }

}

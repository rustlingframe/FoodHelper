package edu.miracosta.cs134.aespinoza.foodhelper;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

/**
 * HelpActivity made to display helpful information to the user.
 * @Author Alfredo Hernandez Jr
 */
public class HelpActivity extends AppCompatActivity {

    /**
     * Is called when the activity first starts.
     * @param savedInstanceState Used for instantiating Parcelable and Serializable objects.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_help);
    }

    /**
     * Returns to the main activity after allowing the user to read about the app
     * @param v current view.
     */
    public void goBack(View v)
    {
        Intent intent = new Intent(this, SettingsActivity.class) ;
        startActivity(intent) ;
        finish() ;
    }
}

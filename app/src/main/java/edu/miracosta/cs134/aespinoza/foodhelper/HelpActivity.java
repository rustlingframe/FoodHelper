package edu.miracosta.cs134.aespinoza.foodhelper;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

/**
 * helpActivity made to display helpful information to the user
 */
public class HelpActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_help);
    }

    /**
     * Return to the main activity after reading about the app
     * @param v current view.
     */
    public void goBack(View v)
    {
        Intent intent = new Intent(this, SettingsActivity.class) ;
        startActivity(intent) ;
        finish() ;
    }
}

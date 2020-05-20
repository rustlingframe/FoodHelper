package edu.miracosta.cs134.aespinoza.foodhelper;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ListView;

import com.google.android.gms.maps.GoogleMap;

import java.util.List;

/**
 * MainActivity, the first real screen for the app.
 * Project - Final Project CS134
 * @Author firstName lastName & Alfredo Hernandez
 */
public class MainActivity extends AppCompatActivity {

    //Load a Google Map into our mapsFragment
    private GoogleMap map;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    /**
     * Takes the user to the helpActivity.
     * @param v current view
     */
    public void goToHelp(View v)
    {

    }
}

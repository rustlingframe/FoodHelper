package edu.miracosta.cs134.aespinoza.foodhelper;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import edu.miracosta.cs134.aespinoza.foodhelper.model.DBHelper;
import edu.miracosta.cs134.aespinoza.foodhelper.model.FoodResource;
import edu.miracosta.cs134.aespinoza.foodhelper.model.JSONLoader;
/**
 * SplashActivity displays a loading screen before the app starts, also loads in the JSON file.
 * @Author Alvaro Espinoza Merida
 * CS134 Final Project
 */
public class SplashActivity extends AppCompatActivity {

    private ArrayList<FoodResource> mAllFoodResourcesList;

    /**
     * Starts when the activity is first called. Loads in the JSON file and delays the start of MainActivity.
     * @param savedInstanceState current context
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        mAllFoodResourcesList = JSONLoader.loadJSONFromHTTP();

        //Wait four seconds then go to the main activity
        //TimerTask waits a specific amount of time before performing a task

        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                //Create an intent to go to the main activity
                Intent intent = new Intent(SplashActivity.this, MainActivity.class);
                intent.putParcelableArrayListExtra("AllFoodResourcesList", mAllFoodResourcesList);
                startActivity(intent);
                //lets close the splash activity
                finish();
            }

        };
        //Lets define a timer for when the task should occur
        Timer timer = new Timer();
        timer.schedule(task, 4000);
    }
}

package edu.miracosta.cs134.aespinoza.foodhelper;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import java.util.Timer;
import java.util.TimerTask;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        //Wait four seconds then go to the main activity
        //TimerTask waits a specfic amount of time before performing a task

        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                //Create an intent to go to the main activity
                Intent intent = new Intent(SplashActivity.this, MainActivity.class);
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

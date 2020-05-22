package edu.miracosta.cs134.aespinoza.foodhelper;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class LogoutActivity extends AppCompatActivity {

    // Frame Animation
    AnimationDrawable frameAnim ; // frameAnim == null
    private ImageView porkyImageView ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_logout);

        porkyImageView = findViewById(R.id.porkieImageView) ;

        if(frameAnim == null)
        {
            porkyImageView.setBackgroundResource(R.drawable.frame_anim) ;
            frameAnim = (AnimationDrawable) porkyImageView.getBackground() ;
        }
        // If the animation is running (and we click button), stop the animation
        if(frameAnim.isRunning())
        {
            frameAnim.stop() ;
        }
        else
        {
            frameAnim.start() ;
        }
    }

    public void logoutButton(View v)
    {
        finish() ;
        Intent intent = new Intent(this, MainActivity.class) ;
        startActivity(intent) ;
    }
}

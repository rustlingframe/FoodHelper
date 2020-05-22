package edu.miracosta.cs134.aespinoza.foodhelper;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
/**
 * LogoutActivity used to log the user out of the app.
 * @Author Alfredo Hernandez Jr
 * CS134 Final Project
 */
public class LogoutActivity extends AppCompatActivity {

    // Frame Animation
    AnimationDrawable frameAnim ; // frameAnim == null
    private ImageView porkyImageView ;

    private FirebaseAuth auth ;
    private FirebaseUser user ;

    /**
     * Called upon when activity first starts, creates an animation of porky pig waving goodbye.
     * @param savedInstanceState Used for instantiating Parcelable and Serializable objects.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_logout);

        porkyImageView = findViewById(R.id.porkyImageView) ;

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

        auth = FirebaseAuth.getInstance() ;
        user = auth.getCurrentUser() ;
    }

    /**
     * Called when the user presses the logoutButton, sends the user back to MainActivity.
     * @param v current view
     */
    public void logoutButton(View v)
    {
        auth.signOut();
        finish() ;
        Intent intent = new Intent(this, MainActivity.class) ;
        Toast.makeText(LogoutActivity.this, "That's all folks!",
                Toast.LENGTH_SHORT).show();

        startActivity(intent) ;
    }
}

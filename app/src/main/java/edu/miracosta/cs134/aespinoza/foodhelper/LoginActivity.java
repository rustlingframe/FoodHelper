package edu.miracosta.cs134.aespinoza.foodhelper;


import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

/**
 * LoginActivity used to login the user into the app.
 * @Author Alfredo Hernandez Jr
 * CS134 Final Project
 */
public class LoginActivity extends AppCompatActivity {

    private static final String TAG = "Login";

    private EditText mEmailEditText;
    private EditText mPasswordEditText;

    private FirebaseAuth mAuth;

    /**
     * Called upon when activity first starts.
     * @param savedInstanceState Used for instantiating Parcelable and Serializable objects.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        // Views
        mEmailEditText = findViewById(R.id.loginEmailEditText);
        mPasswordEditText = findViewById(R.id.loginPasswordEditText);


        mAuth = FirebaseAuth.getInstance();

        FirebaseUser user = mAuth.getCurrentUser();
        if (user != null) {
            goToMain() ;
        }
    }

    /**
     * After logging into the app this method takes the user back to the main activity
     * of the app.
     */
    public void goToMain()
    {
        Intent intent = new Intent(this, MainActivity.class) ;
        finish();
        startActivity(intent) ;
    }


    /**
     * Checks whether or not valid input has been entered for both the email and password fields.
     * @return true or false
     */
    private boolean isValidInput()
    {
        if(mEmailEditText.getText().toString().equals("") ||
                mPasswordEditText.getText().toString().equals(""))
        {
            return false ;
        }
        return true ;
    }

    /**
     * Is called after the loginButton is pressed.
     * @param email first parameter for a user to sign in
     * @param password secret code only the user can enter
     */
    private void signIn(String email, String password)
    {
        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d(TAG, "signInWithEmail:success");
                            FirebaseUser user = mAuth.getCurrentUser();
                            Toast.makeText(LoginActivity.this, "Welcome back!",
                                    Toast.LENGTH_SHORT).show();
                            goToMain();
                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w(TAG, "signInWithEmail:failure", task.getException());
                            Toast.makeText(LoginActivity.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }

    /**
     * Is called when the user presses and calls signIn to attempt to login the user.
     * @param v current context
     */
    public void handleLoginButton(View v)
    {
        if(v.getId() == R.id.loginButton)
        {
            signIn(mEmailEditText.getText().toString(),
                    mPasswordEditText.getText().toString());
        }
    }
}

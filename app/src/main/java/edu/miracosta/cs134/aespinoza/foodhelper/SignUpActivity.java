package edu.miracosta.cs134.aespinoza.foodhelper;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

/**
 * SignUpActivity used to register an account from the user for the app.
 * @Author Alfredo Hernandez Jr
 * CS134 Final Project
 */
public class SignUpActivity extends AppCompatActivity {

    private static final String TAG = "SignUp";

    private EditText mEmailEditText;
    private EditText mPasswordEditText;

    private FirebaseAuth mAuth;

    /**
     * Called when the activity first starts, takes in the user's input to attempt to sign them up.
     * @param savedInstanceState current context
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        // Views
        mEmailEditText = findViewById(R.id.signUpEmailEditText);
        mPasswordEditText = findViewById(R.id.signUpPasswordEditText);

        mAuth = FirebaseAuth.getInstance();

        FirebaseUser user = mAuth.getCurrentUser();
        if (user != null) {
            goToMain();
        }
    }

    /**
     * Takes the user back to the MainActivity.
     */
    public void goToMain() {
        Intent intent = new Intent(this, MainActivity.class);
        finish();
        startActivity(intent);
    }

    /**
     * Checks both the email and password fields to see if valid input has been entered.
     * @return true or false
     */
    private boolean isValidInput() {
        if (mEmailEditText.getText().toString().equals("") ||
                mPasswordEditText.getText().toString().equals("")) {
            return false;
        }
        return true;
    }

    /**
     * Attempts to create an account for the user based on their input.
     * @param email user's email
     * @param password user's password
     */
    private void createAccount(String email, String password) {
        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d(TAG, "createUserWithEmail:success");
                            FirebaseUser user = mAuth.getCurrentUser();
                            goToMain();
                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w(TAG, "createUserWithEmail:failure", task.getException());
                            Toast.makeText(SignUpActivity.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }

    /**
     * Is called when the sign uo button is pressed, calls the createAccount using the input from the user.
     * @param v current view
     */
    public void handleSignUp(View v) {
        if (v.getId() == R.id.signUpButton) {
            createAccount(mEmailEditText.getText().toString(),
                    mPasswordEditText.getText().toString());
        }
    }
}
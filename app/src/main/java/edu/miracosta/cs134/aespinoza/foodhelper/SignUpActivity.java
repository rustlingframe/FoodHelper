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

public class SignUpActivity extends AppCompatActivity {

    private static final String TAG = "TreasuredLogin";

    private EditText mEmailEditText;
    private EditText mPasswordEditText;

    //TODO (1): Add Firebase member variables (auth and user)
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        // Views
        mEmailEditText = findViewById(R.id.emailEditText);
        mPasswordEditText = findViewById(R.id.passwordEditText);


        //TODO (2): Initialize Firebase authentication
        // Initialize Firebase Auth
        mAuth = FirebaseAuth.getInstance();

        // TODO (3): Get the current user.  If not null (already signed in), go directly to TreasureActivity
        FirebaseUser user = mAuth.getCurrentUser();
        if (user != null) {
            goToTreasure();
        }
    }

    // TODO (4): Create a private void goToTreasure() method that finishes this activity
    // TODO (4): then creates a new Intent to the TreasureActivity.class and starts the intent.
    public void goToTreasure() {
        Intent intent = new Intent(this, MainActivity.class);
        finish();
        startActivity(intent);
    }


    // TODO (5): Create a private boolean isValidInput() method that checks to see whether
    // TODO (5): the email address or password is empty.  Return false if either is empty, true otherwise.
    private boolean isValidInput() {
        if (mEmailEditText.getText().toString().equals("") ||
                mPasswordEditText.getText().toString().equals("")) {
            return false;
        }
        return true;
    }

    // TODO (6): Create a private void createAccount(String email, String password) method
    // TODO (6): that checks for valid input, then uses Firebase authentication to create the user with email and password.
    private void createAccount(String email, String password) {
        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d(TAG, "createUserWithEmail:success");
                            FirebaseUser user = mAuth.getCurrentUser();
                            goToTreasure();
                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w(TAG, "createUserWithEmail:failure", task.getException());
                            Toast.makeText(SignUpActivity.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }

    // TODO (7): Create a private void signIn(String email, String password) method
    // TODO (7): that checks for valid input, then uses Firebase authentication to sign in user with email and password entered.
    private void signIn(String email, String password) {
        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d(TAG, "signInWithEmail:success");
                            FirebaseUser user = mAuth.getCurrentUser();
                            goToTreasure();
                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w(TAG, "signInWithEmail:failure", task.getException());
                            Toast.makeText(SignUpActivity.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }

    // TODO (8): Create a public void handleLoginButtons(View v) that checks the id of the button clicked.
    // TODO (8): If the button is createAccountButton, call the createAccount() method, else if it's signInButton, call the signIn() method.
    public void handleLoginButtons(View v) {
        if (v.getId() == R.id.signUpButton) {
            createAccount(mEmailEditText.getText().toString(),
                    mPasswordEditText.getText().toString());
            Toast.makeText(SignUpActivity.this, "Thanks for signing up!",
                    Toast.LENGTH_SHORT).show();
        }
    }
}
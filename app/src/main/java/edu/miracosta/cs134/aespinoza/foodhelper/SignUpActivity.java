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

    private static final String TAG = "SignUp";

    private EditText mEmailEditText;
    private EditText mPasswordEditText;

    private FirebaseAuth mAuth;

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

    public void goToMain() {
        Intent intent = new Intent(this, MainActivity.class);
        finish();
        startActivity(intent);
    }


    private boolean isValidInput() {
        if (mEmailEditText.getText().toString().equals("") ||
                mPasswordEditText.getText().toString().equals("")) {
            return false;
        }
        return true;
    }

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

    public void handleSignUp(View v) {
        if (v.getId() == R.id.signUpButton) {
            createAccount(mEmailEditText.getText().toString(),
                    mPasswordEditText.getText().toString());
        }
    }
}
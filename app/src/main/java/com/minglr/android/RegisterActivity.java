package com.minglr.android;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class RegisterActivity extends AppCompatActivity {

    /*
    TODO:
        confirm password error
     */

    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;

    private DatabaseReference mDatabase;

    private EditText emailField;
    private EditText firstnameField;
    private EditText lastnameField;
    private EditText birthdayField;
    private EditText cityField;
    private EditText stateField;
    private EditText passwordField;
    private EditText confirmPasswordField;

    private String TAG;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        emailField = (EditText) findViewById(R.id.registerEmail);
        firstnameField = (EditText) findViewById(R.id.registerFirstName);
        lastnameField = (EditText) findViewById(R.id.registerLastName);
        birthdayField = (EditText) findViewById(R.id.registerBirthday);
        cityField = (EditText) findViewById(R.id.registerCity);
        stateField = (EditText) findViewById(R.id.registerState);
        passwordField = (EditText) findViewById(R.id.registerPassword);
        confirmPasswordField = (EditText) findViewById(R.id.registerConfirmPassword);

        TAG = "anthony";

        // Initialize firebase authentication and methods for log in/out
        mAuth = FirebaseAuth.getInstance();
        mAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if (user != null) {
                    // User is signed in
                    Log.d(TAG, "onAuthStateChanged:signed_in:" + user.getUid());
                    Toast.makeText(RegisterActivity.this, "Signed in",
                            Toast.LENGTH_SHORT).show();
                } else {
                    // User is signed out
                    Log.d(TAG, "onAuthStateChanged:signed_out");
                    Toast.makeText(RegisterActivity.this, "Signed out",
                            Toast.LENGTH_SHORT).show();
                }
                // ...
            }
        };

        // Init database
        mDatabase = FirebaseDatabase.getInstance().getReference();
    }

    // Register button clicked
    public void registerClicked(View view) {
        final String email = emailField.getText().toString();
        final String password = passwordField.getText().toString();
        final String firstname = firstnameField.getText().toString();
        final String lastname = lastnameField.getText().toString();
        final String birthday = birthdayField.getText().toString();
        final String city = cityField.getText().toString();
        final String state = stateField.getText().toString();
        Log.d(TAG, email + " " + password);

        // Create new account
        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        Log.d(TAG, "createUserWithEmail:onComplete:" + task.isSuccessful());

                        // If sign in fails, display a message to the user. If sign in succeeds
                        // the auth state listener will be notified and logic to handle the
                        // signed in user can be handled in the listener.
                        if (!task.isSuccessful()) {
                            Toast.makeText(RegisterActivity.this, "Register failed",
                                    Toast.LENGTH_SHORT).show();
                        } else {
                            // Write user to DB
                            writeNewUser(email, firstname, lastname, birthday, city, state);
                        }

                    }
                });

        Intent intent = new Intent(RegisterActivity.this, ViewActivity.class);
        RegisterActivity.this.startActivity(intent);
    }

    // Write the users info to the database
    private void writeNewUser(String email, String firstname, String lastname, String birthday,
                              String city, String state) {
        User user = new User(email, firstname, lastname, birthday, city, state);


        FirebaseUser loggedInUser = FirebaseAuth.getInstance().getCurrentUser();
        String userId = loggedInUser.getUid();
//        if (loggedInUser != null) {
//            userId = loggedInUser.getUid();
//        } else {
//            // No user is signed in
//        }

        mDatabase.child("users").child(userId).setValue(user);
    }

    @Override
    public void onStart() {
        super.onStart();
        mAuth.addAuthStateListener(mAuthListener);
    }

    @Override
    public void onStop() {
        super.onStop();
        if (mAuthListener != null) {
            mAuth.removeAuthStateListener(mAuthListener);
        }
    }
}

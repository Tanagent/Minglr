package com.minglr.android;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class HomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if(user != null) {
            Intent intent = new Intent(HomeActivity.this, MainActivity.class);
            startActivity(intent);
        }

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
    }


    // Login button clicked
    public void loginClicked(View view) {
        Intent myIntent = new Intent(HomeActivity.this, LoginActivity.class);
        HomeActivity.this.startActivity(myIntent);
    }

    // Register button clicked
    public void registerClicked(View view) {
        Intent myIntent = new Intent(HomeActivity.this, RegisterActivity.class);
        HomeActivity.this.startActivity(myIntent);
    }
}
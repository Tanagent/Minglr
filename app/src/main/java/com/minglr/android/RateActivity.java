package com.minglr.android;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class RateActivity extends AppCompatActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.rate_your_date);
    }

    public void confirmOnClick(View view) {
        Intent intent = new Intent(RateActivity.this, MainActivity.class);
        RateActivity.this.startActivity(intent);
    }
}

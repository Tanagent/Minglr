package com.minglr.android;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;

import com.google.firebase.database.DatabaseReference;

public class SetActivity extends AppCompatActivity {
    private EditText locationField;
    private EditText dateField;
    private EditText timeField;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.set_up_date);
    }

    public void cancelOnClick(View view) {
        finish();
    }

    public void confirmOnClick(View view) {
        locationField = (EditText) findViewById(R.id.textLocation);
        dateField = (EditText) findViewById(R.id.textDate);
        timeField = (EditText) findViewById(R.id.textTime);

        String location = locationField.getText().toString();
        String date = dateField.getText().toString();
        String time = timeField.getText().toString();

        writeNewDate(location, date, time);

        Intent intent = new Intent(SetActivity.this, MainActivity.class);
        SetActivity.this.startActivity(intent);
    }

        // Write the users info to the database
    private void writeNewDate(String location, String date, String time) {
        DbInfo info = new DbInfo();

        Date dateObj = new Date(location, date, time, R.drawable.allison, info.userId);

        info.mDatabase = info.mDatabase.child("dates");

        DatabaseReference newDateRef = info.mDatabase.push();
        newDateRef.setValue(dateObj);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_Log_Out) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}

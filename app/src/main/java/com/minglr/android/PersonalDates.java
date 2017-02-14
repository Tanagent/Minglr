package com.minglr.android;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;

/**
 * Created by Brian on 2/12/17.
 */

public class PersonalDates extends AppCompatActivity {
    private ListView listView;

    private PDateAdapter dateAdapter;
    private Button addButton;
    DateList dateList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setContentView(R.layout.personal_dates);

        listView = (ListView) findViewById(R.id.listView);
        dateList = new DateList();
        dateAdapter = new PDateAdapter(this, R.layout.personal_dates_item, dateList.getDateList());
        listView.setAdapter(dateAdapter);
    }
}

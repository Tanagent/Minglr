package com.minglr.android;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;

public class MainActivity extends AppCompatActivity {

    private ListView listView;

    private DateAdapter dateAdapter;
    private Button addButton;
    DateList dateList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setContentView(R.layout.activity_main);

        listView = (ListView) findViewById(R.id.listView);
        dateList = new DateList();
        dateAdapter = new DateAdapter(this, R.layout.friend_list_item, dateList.getDateList());
        listView.setAdapter(dateAdapter);

        ChildEventListener childEventListener = new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String previousChildName) {
                Log.d("anthony", "onChildAdded:" + dataSnapshot.getKey());
                Date date = dataSnapshot.getValue(Date.class);
                Log.i("Location", date.getLocation());
                date = new Date(date.getLocation(),
                        date.getDate() + " | " + date.getTime(),
                        date.getTime(),
                        R.drawable.allison,
                        date.getUserID());


                dateAdapter.add(date);
                dateAdapter.notifyDataSetChanged();
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String previousChildName) {}

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {}

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String previousChildName) {}

            @Override
            public void onCancelled(DatabaseError databaseError) {}
        };
        DbInfo info = new DbInfo();
        info.mDatabase.child("dates").addChildEventListener(childEventListener);





        addButton = (Button) findViewById(R.id.addNewDate);
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(MainActivity.this, NewDateActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        //  Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        Intent intent;
        switch (item.getItemId()) {
            case R.id.action_Log_Out:
                FirebaseAuth.getInstance().signOut();
                intent = new Intent(MainActivity.this, HomeActivity.class);
                startActivity(intent);
                break;
            case R.id.edit_profile:
                intent = new Intent(MainActivity.this,ProfileEditActivity.class);
                startActivity(intent);
                break;
        }

        return super.onOptionsItemSelected(item);
    }
}

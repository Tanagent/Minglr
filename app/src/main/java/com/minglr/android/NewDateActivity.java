package com.minglr.android;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.location.places.Place;
import com.google.android.gms.location.places.Places;
import com.google.android.gms.location.places.ui.PlaceAutocompleteFragment;
import com.google.android.gms.location.places.ui.PlaceSelectionListener;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;

public class NewDateActivity extends FragmentActivity
        implements GoogleApiClient.OnConnectionFailedListener {

    private GoogleApiClient mGoogleApiClient;

    private String location;
    private EditText dateField;
    private EditText timeField;

    private String TAG;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_date);

        TAG = "newDate";

        //locationField = (Fragment) findViewById(R.id.newDateLocation);

        PlaceAutocompleteFragment autocompleteFragment = (PlaceAutocompleteFragment)
                getFragmentManager().findFragmentById(R.id.newDateLocation);

        autocompleteFragment.setOnPlaceSelectedListener(new PlaceSelectionListener() {
            @Override
            public void onPlaceSelected(Place place) {
                // TODO: Get info about the selected place.
                if (place.getName().toString().split(" ")[0]
                        .equals(place.getAddress().toString().split(",")[0].trim().split(" ")[0])) {
                    location = place.getAddress().toString();
                } else {
                    location = place.getName() + ", " + place.getAddress().toString();
                }
            }

            @Override
            public void onError(Status status) {
                // TODO: Handle the error.
                Log.i(TAG, "An error occurred: " + status);
            }
        });

        dateField = (EditText) findViewById(R.id.newDateDate);
        timeField = (EditText) findViewById(R.id.newDateTime);

        mGoogleApiClient = new GoogleApiClient
                .Builder(this)
                .addApi(Places.GEO_DATA_API)
                .addApi(Places.PLACE_DETECTION_API)
                .enableAutoManage(this, this)
                .build();

    }

    public void cancelOnClick(View view) {
        finish();
    }

    public void confirmOnClick(View view) {
        String date = dateField.getText().toString();
        String time = timeField.getText().toString();

        Log.d("newdate", location);
        writeNewDate(location, date, time);


        Intent intent = new Intent(NewDateActivity.this, MainActivity.class);
        NewDateActivity.this.startActivity(intent);
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
    public void onConnectionFailed(ConnectionResult result) {
        // An unresolvable error has occurred and a connection to Google APIs
        // could not be established. Display an error message, or handle
        // the failure silently

        // ...
    }
}
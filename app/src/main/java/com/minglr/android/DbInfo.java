package com.minglr.android;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

/**
 * Created by anthony on 2/11/17.
 */

public class DbInfo {

    public DatabaseReference mDatabase;
    public String userId;
    private String TAG;

    public DbInfo() {
        mDatabase = FirebaseDatabase.getInstance().getReference();
        String TAG = "anthony";

        FirebaseUser loggedInUser = FirebaseAuth.getInstance().getCurrentUser();
        userId = loggedInUser.getUid();
    }
}

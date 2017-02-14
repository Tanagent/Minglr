package com.minglr.android;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.Image;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.ByteArrayOutputStream;
import java.util.HashMap;
import java.util.Map;

public class ProfileEditActivity extends AppCompatActivity {

    private static final int CAMERA_REQUEST = 1888;
    private ImageView profilePic;
    private EditText firstnameField;
    private EditText lastnameField;
    private TextView birthdayField;
    private EditText cityField;
    private EditText stateField;
    private EditText descriptionField;
    private FirebaseStorage storage;
    private StorageReference storageRef;
    private StorageReference profileImgRef;
    private String TAG;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);

        storage = FirebaseStorage.getInstance();
        DbInfo info = new DbInfo();

        // Create a storage reference from our app
        storageRef = storage.getReferenceFromUrl("gs://blinddateapp-1dcff.appspot.com");
        profileImgRef = storageRef.child(info.userId + ".jpg");

        profilePic = (ImageView) findViewById(R.id.imageView);

        // Get image from storage and put it as profile image
        final long ONE_MEGABYTE = 1024 * 1024;
        profileImgRef.getBytes(ONE_MEGABYTE).addOnSuccessListener(new OnSuccessListener<byte[]>() {
            @Override
            public void onSuccess(byte[] bitmapBytes) {
                // Data for "images/island.jpg" is returns, use this as needed
                Log.d(TAG, "Got image: ");
                profilePic.setImageBitmap(
                        BitmapFactory.decodeByteArray(bitmapBytes , 0, bitmapBytes.length)
                );
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception exception) {
                // Handle any errors
            }
        });

        firstnameField = (EditText) findViewById(R.id.editFirstName);
        lastnameField = (EditText) findViewById(R.id.editLastName);
        birthdayField = (TextView) findViewById(R.id.tvBirthday);
        cityField = (EditText) findViewById(R.id.editCity);
        stateField = (EditText) findViewById(R.id.editState);
        descriptionField = (EditText) findViewById(R.id.editDescription);

        setFieldText();

        TAG = "profileedit";

        // Get the user info from database after logging in
        info.mDatabase.child("users").child(info.userId).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // The users data
                User user = dataSnapshot.getValue(User.class);
                Log.d(TAG, "Value is: " + user.email);

            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Log.w(TAG, "Failed to read value.", error.toException());
            }
        });

    }

    public void cancelOnClick(View view) {
        finish();
    }

    public void saveOnClick(View view){
        updateUser();


        Intent intent = new Intent(ProfileEditActivity.this, MainActivity.class);
        ProfileEditActivity.this.startActivity(intent);
    }

    // Image clicked to edit profile pic
    public void imageClicked(View view) {
        Intent cameraIntent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(cameraIntent, CAMERA_REQUEST);
    }

    // After the camera capture
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == CAMERA_REQUEST && resultCode == Activity.RESULT_OK) {
            Bitmap photo = (Bitmap) data.getExtras().get("data");

            // Send to storage
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            photo.compress(Bitmap.CompressFormat.JPEG, 100, baos);
            byte[] photoData = baos.toByteArray();

            UploadTask uploadTask = profileImgRef.putBytes(photoData);
            uploadTask.addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception exception) {
                    // Handle unsuccessful uploads
                }
            }).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                    // taskSnapshot.getMetadata() contains file metadata such as size, content-type, and download URL.
                    Uri downloadUrl = taskSnapshot.getDownloadUrl();
                }
            });

            profilePic.setImageBitmap(photo);
        }
    }

    private void updateUser() {
        DbInfo info = new DbInfo();

        Map<String, Object> userValues = new HashMap<>();
        userValues.put("firstname", "firstname");
        userValues.put("lastname", "lastname");
        userValues.put("city", "city");
        userValues.put("state", "state");
        userValues.put("description", "description");

        Map<String, Object> childUpdates = new HashMap<>();
        childUpdates.put("/users/" + info.userId, userValues);

        //info.mDatabase.updateChildren(childUpdates);
    }

    private void setFieldText() {
        // Get the users info from database after logging in
        DbInfo info = new DbInfo();
        info.mDatabase.child("users").child(info.userId).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // The users data
                User user = dataSnapshot.getValue(User.class);

                firstnameField.setText(user.firstname);
                lastnameField.setText(user.lastname);
                birthdayField.setText(user.birthday);
                cityField.setText(user.city);
                stateField.setText(user.state);
                descriptionField.setText(user.description);
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Log.w(TAG, "Failed to read value.", error.toException());
            }
        });
    }

}
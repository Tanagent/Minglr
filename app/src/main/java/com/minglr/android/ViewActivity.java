package com.minglr.android;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class ViewActivity extends AppCompatActivity {
    TextView textName;
    ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.profile_view);

        textName = (TextView) findViewById(R.id.tvProfileName);
        imageView = (ImageView) findViewById(R.id.profile_view_pic);
        imageView.setImageResource(getIntent().getIntExtra("photoID",0));
    }

    public void backOnClick(View view) {
        finish();
    }

    public void approveOnClick(View view) {
        Intent intent = new Intent(ViewActivity.this, PersonalDates.class);
        ViewActivity.this.startActivity(intent);
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

    public void logOutOnClick(View view) {
        Intent intent = new Intent(ViewActivity.this, RateActivity.class);
        ViewActivity.this.startActivity(intent);
    }
}

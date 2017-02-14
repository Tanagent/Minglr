package com.minglr.android;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by Brian on 2/11/17.
 */

public class DateHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    Date date;
    TextView locationText;
    TextView timeAndDateText;
    ImageView imageView;
    Button addButton;
    Context context;

    public DateHolder(Context context, View itemView) {
        super(itemView);
        this.locationText = (TextView) itemView.findViewById(R.id.location_name);
        this.timeAndDateText = (TextView) itemView.findViewById(R.id.time_and_date);
        this.imageView = (ImageView) itemView.findViewById(R.id.profile_image_view);

        itemView.setOnClickListener(this);
    }

    public void bindDate(Date date) {
        this.date = date;
        this.locationText.setText(date.getLocation());
        this.timeAndDateText.setText(date.getTime());
        this.imageView.setImageResource(date.getProfilePhotoId());
    }

    @Override
    public void onClick(View v) {
        if (this.date != null) {
            Toast.makeText(this.context, "what", Toast.LENGTH_SHORT ).show();
        }
    }
}

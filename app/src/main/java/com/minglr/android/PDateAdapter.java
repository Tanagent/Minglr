package com.minglr.android;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Brian on 2/12/17.
 */

public class PDateAdapter extends ArrayAdapter<Date> {
    private Context context;
    private List<Date> dateList;

    public PDateAdapter(Context context, int resource, List<Date> objects) {
        super(context, resource, objects);
        this.context = context;
        this.dateList = objects;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(
                Context.LAYOUT_INFLATER_SERVICE);
        View view = layoutInflater.inflate(R.layout.personal_dates_item, parent, false);

        final Date date = dateList.get(position);

        TextView nameText = (TextView) view.findViewById(R.id.date_name);
        nameText.setText("FIRST NAME LAST NAME");

        TextView locationText = (TextView) view.findViewById(R.id.location_name);
        locationText.setText(date.getLocation());

        TextView dateText = (TextView) view.findViewById(R.id.date);
        dateText.setText(date.getDate());

        TextView timeText = (TextView) view.findViewById(R.id.time);
        timeText.setText(date.getTime());

        ImageView imageView = (ImageView) view.findViewById(R.id.profile_image_view);
        imageView.setImageResource(date.getProfilePhotoId());

        return view;
    }
}

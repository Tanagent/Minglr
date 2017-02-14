package com.minglr.android;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;

import java.util.List;


public class DateAdapter extends ArrayAdapter<Date> {

    private Context context;
    private List<Date> dateList;

    public DateAdapter(Context context, int resource, List<Date> objects) {
        super(context, resource, objects);
        this.context = context;
        this.dateList = objects;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {

        LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(
                Context.LAYOUT_INFLATER_SERVICE);
        View view = layoutInflater.inflate(R.layout.friend_list_item, parent, false);

        final Date date = dateList.get(position);

        TextView locationText = (TextView) view.findViewById(R.id.location_name);
        locationText.setText(date.getLocation());

        TextView timeAndDateText = (TextView) view.findViewById(R.id.time_and_date);
        timeAndDateText.setText(date.getTime() + "");

        ImageView imageView = (ImageView) view.findViewById(R.id.profile_image_view);
        imageView.setImageResource(date.getProfilePhotoId());

        Button addButton = (Button) view.findViewById(R.id.view_prof_btn);
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), ViewActivity.class);
                intent.putExtra("photoID", date.getProfilePhotoId());
                intent.putExtra("location", date.getLocation());
                v.getContext().startActivity(intent);
            }
        });


        if(date.getUserID() == null)
            Log.i("USERID", "5");

        return view;
    }
}

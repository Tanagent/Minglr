package com.minglr.android;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

/**
 * Created by Brian on 2/11/17.
 */

public class DateAdapterRecycler extends RecyclerView.Adapter<DateHolder> {
    private final List<Date> dates;
    private Context context;
    private int itemResource;

    public DateAdapterRecycler(Context context, int itemResource, List<Date> bakeries, List<Date> dates) {

        // 1. Initialize our adapter
        this.dates = dates;
        this.context = context;
        this.itemResource = itemResource;
    }



    // 2. Override the onCreateViewHolder method
    @Override
    public DateHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        // 3. Inflate the view and return the new ViewHolder
        View view = LayoutInflater.from(parent.getContext())
                .inflate(this.itemResource, parent, false);
        return new DateHolder(this.context, view);
    }

    @Override
    public void onBindViewHolder(DateHolder holder, int position) {
        // 5. Use position to access the correct Bakery object
        Date date = this.dates.get(position);

        // 6. Bind the bakery object to the holder
        holder.bindDate(date);
    }

    @Override
    public int getItemCount() {
        return this.dates.size();
    }


}

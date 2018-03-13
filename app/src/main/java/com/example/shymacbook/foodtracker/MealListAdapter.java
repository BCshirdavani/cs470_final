package com.example.shymacbook.foodtracker;

import android.content.Context;
import android.database.Cursor;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by shymacbook on 3/13/18.
 */

public class MealListAdapter extends RecyclerView.Adapter<MealListAdapter.MealViewHolder> {
    private Cursor mCursor;
    private Context mContext;

    @Override
    public MealViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(MealViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public class MealViewHolder extends RecyclerView.ViewHolder {
        public MealViewHolder(View itemView) {
            super(itemView);
        }
    }
}

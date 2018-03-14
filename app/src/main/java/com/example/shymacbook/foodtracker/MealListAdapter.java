package com.example.shymacbook.foodtracker;

import android.content.Context;
import android.database.Cursor;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.shymacbook.foodtracker.data.MealListContract;

/**
 * Created by shymacbook on 3/13/18.
 */

public class MealListAdapter extends RecyclerView.Adapter<MealListAdapter.MealViewHolder> {
    private Cursor mCursor;
    private Context mContext;

    // constructor for Adapter
    public MealListAdapter(Context context, Cursor cursor) {
        this.mContext = context;
        this.mCursor = cursor;
    }

    @Override
    public MealViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // Get the RecyclerView item layout
        LayoutInflater inflater = LayoutInflater.from(mContext);
        View view = inflater.inflate(R.layout.meal_list_item, parent, false);
        return new MealViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MealViewHolder holder, int position) {
        Log.d("function ex", "onBindViewHolder: executing from MealListAdapter");
        // Move the mCursor to the position of the item to be displayed
        if (!mCursor.moveToPosition(position))
            return; // bail if returned null

        // Update the view holder with the information needed to display
        String title = mCursor.getString(mCursor.getColumnIndex(MealListContract.MealListEntry.COLUMN_MEAL_TITLE));
        String notes = mCursor.getString(mCursor.getColumnIndex(MealListContract.MealListEntry.COLUMN_MEAL_NOTES));
        long id = mCursor.getLong(mCursor.getColumnIndex(MealListContract.MealListEntry._ID));

        // Display the meal name <------------------------------------------------- LEFT OFF HERE *****************
        holder.titleTextView.setText(title);
        // Display the meal notes
        holder.notesTextView.setText(String.valueOf(notes));
        // use this to ID each row element, so we can delete with swipe.
        holder.itemView.setTag(id);
    }

    @Override
    public int getItemCount() {
        Log.d("function ex", "getItemCount = " + mCursor.getCount());
        return mCursor.getCount();

    }

    public void swapCursor(Cursor newCursor) {
        Log.d("function ex", "swapCursor: executing from MealListAdapter");
        // COMPLETED (16) Inside, check if the current cursor is not null, and close it if so
        // Always close the previous mCursor first
        if (mCursor != null) mCursor.close();
        // COMPLETED (17) Update the local mCursor to be equal to  newCursor
        mCursor = newCursor;
        Log.d("function ex", "swapCursor: mCursor = newCursor");
        // COMPLETED (18) Check if the newCursor is not null, and call this.notifyDataSetChanged() if so
        if (newCursor != null) {
            // Force the RecyclerView to refresh
            this.notifyDataSetChanged();
        }
    }


    public class MealViewHolder extends RecyclerView.ViewHolder {
        // Will display the Meal Name
        TextView titleTextView;
        // Will display the Meal Notes
        TextView notesTextView;

        public MealViewHolder(View itemView) {
            super(itemView);
            titleTextView = (TextView) itemView.findViewById(R.id.mealTitle);
            notesTextView = (TextView) itemView.findViewById(R.id.mealSubTitle);
        }
    }
}

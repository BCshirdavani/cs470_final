package com.example.shymacbook.foodtracker;

import android.content.Context;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.Image;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.shymacbook.foodtracker.data.MealListContract;

import java.util.Arrays;

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
        return new MealViewHolder(view, this);
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
        // TODO: FIX THIS HERE: bitMapBytes appear to be incomplete?
        byte[] bitmapBytes = mCursor.getBlob(mCursor.getColumnIndex(MealListContract.MealListEntry.COLUMN_MEAL_NOTES));
        Log.d("CHECK IMAGE", "onBindViewHolder: MealListAdapter row 50: bitmapBytes = " + bitmapBytes);
        Log.d("CHECK IMAGE", "onBindViewHolder: MealListAdapter row 51: arrays.tostring(bitmapBytes) = " + Arrays.toString(bitmapBytes));
        String byteTEST = "";
        for (int i = 0; i < bitmapBytes.length; i++){
            byteTEST += bitmapBytes[i];
        }
        Log.d("CHECK IMAGE", "onBindViewHolder: for loop byteTEST print = " + byteTEST);
        long id = mCursor.getLong(mCursor.getColumnIndex(MealListContract.MealListEntry._ID));
        holder.titleTextView.setText(title);
        // Display the meal notes
        holder.notesTextView.setText(String.valueOf(notes));
        // TODO: rather than string, just use BLOB and byte[] the whole time?
//        holder.picView.setImageBitmap(dbBitmapUtility.getImage(bitmapBytes));
        Bitmap bitmap = BitmapFactory.decodeByteArray(bitmapBytes, 0, bitmapBytes.length);
        holder.picView.setImageBitmap(bitmap);
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


    public class MealViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        // Will display the Meal Name
        public final TextView titleTextView;
        // Will display the Meal Notes
        TextView notesTextView;
        // will show the picture of meal
        ImageView picView;
        // give ID to this card, so SQL ID can assign to a tag of the card
//        CardView card;
        final MealListAdapter mAdapter;

        public MealViewHolder(View itemView, MealListAdapter adapter) {
            super(itemView);
            titleTextView = (TextView) itemView.findViewById(R.id.mealTitle);
            notesTextView = (TextView) itemView.findViewById(R.id.mealSubTitle);
            picView = (ImageView) itemView.findViewById(R.id.mealImageView);
//            card = (CardView) itemView.findViewById(R.id.meal_cardView);
            this.mAdapter = adapter;
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            // TODO: give action for onClick in Meal List, add to FAB temp data
            // TODO: add data to table sql
            //      make sql select statement to find that cardID tag, which is the primary key ID,
            //      use that querry, and add this data to the next temporary table
            //          ...or just put it in a temp string[] array, then use those parameters
            //          to populate the final table, no need for temp table?
            long cardID;
            cardID = (long) MealViewHolder.this.itemView.getTag();
            int mPosition = getLayoutPosition();
            Toast.makeText(mContext, "meal clicked @ pos: " + mPosition + ", @ id: " + cardID, Toast.LENGTH_SHORT).show();
            Log.d("onClick", "onClick: position = " + mPosition);
            cardID = (long) MealViewHolder.this.itemView.getTag();
            Log.d("onClick", "onClick: meal clicked @ card ID: " + cardID);
        }


    }
}

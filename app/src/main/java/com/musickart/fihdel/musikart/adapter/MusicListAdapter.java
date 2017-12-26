package com.musickart.fihdel.musikart.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.musickart.fihdel.musikart.R;
import com.musickart.fihdel.musikart.model.Album;

import java.util.List;

/**
 * Created by julio on 12/26/17.
 */

// Create the basic adapter extending from RecyclerView.Adapter
// Note that we specify the custom ViewHolder which gives us access to our views
public class MusicListAdapter extends
        RecyclerView.Adapter<MusicListAdapter.ViewHolder>  {

    /***** Creating OnItemClickListener *****/

    // Define listener member variable
    private OnItemClickListener listener;
    // Define the listener interface
    public interface OnItemClickListener {
        void onItemClick(View itemView, int position);
    }
    // Define the method that allows the parent activity or fragment to define the listener
    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }

    // Provide a direct reference to each of the views within a data item
    // Used to cache the views within the item layout for fast access
    public class ViewHolder extends RecyclerView.ViewHolder {
        // Your holder should contain a member variable
        // for any view that will be set as you render a row
        public TextView nameTextView;
        public TextView tvDuration;

        // We also create a constructor that accepts the entire item row
        // and does the view lookups to find each subview
        public ViewHolder(final View itemView) {
            // Stores the itemView in a public final member variable that can be used
            // to access the context from any ViewHolder instance.
            super(itemView);

            nameTextView = itemView.findViewById(R.id.contact_name);
            tvDuration = itemView.findViewById(R.id.tvDuration);
            // Setup the click listener
                itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // Triggers click upwards to the adapter on click
                    if (listener != null) {
                        int position = getAdapterPosition();
                        if (position != RecyclerView.NO_POSITION) {
                            listener.onItemClick(itemView, position);
                        }
                    }
                }
            });
           // itemView.setOnClickListener(this);
        }

    }

    private List<Album> mAlbums;
    // Store the context for easy access
    private Context mContext;

    // Pass in the contact array into the constructor
    public MusicListAdapter(Context context, List<Album> albums) {
        mAlbums = albums;
        mContext = context;
    }

    // Easy access to the context object in the recyclerview
    private Context getContext() {
        return mContext;
    }

    @Override
    public MusicListAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        // Inflate the custom layout
        View contactView = inflater.inflate(R.layout.item_music_by_album, parent, false);

        // Return a new holder instance
        ViewHolder viewHolder = new ViewHolder(contactView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(MusicListAdapter.ViewHolder viewHolder, int position) {
        // Get the data model based on position
        Album album = mAlbums.get(position);

        // Set item views based on your views and data model
        TextView textView = viewHolder.nameTextView;
        textView.setText(album.getName());
        TextView tvDuration = viewHolder.tvDuration;
        // tvDuration.setText(album.isOnline() ? "Achter" : "Ecouter");
        // tvDuration.setEnabled(album.isOnline());
    }
    // Returns the total count of items in the list
    @Override
    public int getItemCount() {
        return mAlbums.size();
    }
}
package com.musickart.fihdel.musikart.adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.musickart.fihdel.musikart.R;
import com.musickart.fihdel.musikart.activity.MainActivity;
import com.musickart.fihdel.musikart.fragment.PlayerFragment;
import com.musickart.fihdel.musikart.model.Album;
import com.musickart.fihdel.musikart.model.Music;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import jp.wasabeef.glide.transformations.BlurTransformation;

import static com.bumptech.glide.request.RequestOptions.bitmapTransform;

/**
 * Created by julio on 12/21/17.
 */

public class MusicAdapter extends
        RecyclerView.Adapter<MusicAdapter.ViewHolder>  {

    /***** Creating OnItemClickListener *****/

    // Define listener member variable
    private OnItemClickListener listener;

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }

    // Define the listener interface
    public interface OnItemClickListener {
        void onItemClick(View itemView, int position);
    }
    // Define the method that allows the parent activity or fragment to define the listener
   /* public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }*/

    // Provide a direct reference to each of the views within a data item
    // Used to cache the views within the item layout for fast access
    public class ViewHolder extends RecyclerView.ViewHolder {
        // Your holder should contain a member variable
        // for any view that will be set as you render a row
        ImageView imageMusic ;
        ImageView backGr ;
        TextView TitleAlbum ;
        TextView TitleMusic ;
        Button playBtn ;

        // We also create a constructor that accepts the entire item row
        // and does the view lookups to find each subview

        public ViewHolder(final View itemView) {
            // Stores the itemView in a public final member variable that can be used
            // to access the context from any ViewHolder instance.
            super(itemView);

             imageMusic = itemView.findViewById(R.id.imageMusic);
             backGr =  itemView.findViewById(R.id.backGr);

            // clear out image from convert view
            imageMusic.setImageResource(0);
            backGr.setImageResource(0);

            TitleAlbum =  itemView.findViewById(R.id.groupe_music);
            TitleMusic =  itemView.findViewById(R.id.titre_music);
            playBtn =  itemView.findViewById(R.id.achter_btn);


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
        }

    }

    private List<Music> mMusics;
    // Store the context for easy access
    private Context mContext;

    // Pass in the contact array into the constructor
    public MusicAdapter(Context context, List<Music> musics) {
        mMusics = musics;
        mContext = context;
    }

    // Easy access to the context object in the recyclerview
    private Context getContext() {
        return mContext;
    }

    @Override
    public MusicAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        // Inflate the custom layout
        View contactView = inflater.inflate(R.layout.item_music, parent, false);

        // Return a new holder instance
        ViewHolder viewHolder = new ViewHolder(contactView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(MusicAdapter.ViewHolder viewHolder, int position) {
        // Get the data model based on position
        Music music = mMusics.get(position);
        ImageView image_Music = viewHolder.imageMusic ;
        ImageView back_Gr =  viewHolder.backGr ;
        TextView Title_Album  =  viewHolder.TitleAlbum ;
        TextView Title_Music =  viewHolder.TitleMusic  ;
        Button play_Btn =  viewHolder.playBtn ;

        // Set item views based on your views and data model

        Title_Album.setText(music.getAlbum());

        Title_Music.setText(music.getTitle());

        // playBtn.setText();
        Glide.with(getContext()).load(R.drawable.amonik)
                .apply(bitmapTransform(new BlurTransformation(25)))
                .into((back_Gr));

        // populate data
        Picasso.with(getContext()).load(music.getImagesMusic()).placeholder(R.drawable.amonik).into(image_Music);
        Picasso.with(getContext()).load(music.getImagesMusic()).placeholder(R.drawable.amonik).into(back_Gr);
        // return the view

    }
    // Returns the total count of items in the list
    @Override
    public int getItemCount() {
        return mMusics.size();
    }
}

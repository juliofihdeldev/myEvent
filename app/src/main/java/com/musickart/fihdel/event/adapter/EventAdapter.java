package com.musickart.fihdel.event.adapter;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.widget.RecyclerView;
import android.telecom.Call;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.musickart.fihdel.event.R;
import com.musickart.fihdel.event.activity.CommentActivity;
import com.musickart.fihdel.event.activity.Details;
import com.musickart.fihdel.event.model.Event;
import com.squareup.picasso.Picasso;

import org.w3c.dom.Comment;

import java.util.List;

import jp.wasabeef.glide.transformations.BlurTransformation;

import static com.bumptech.glide.request.RequestOptions.bitmapTransform;

/**
 * Created by julio on 12/21/17.
 */

public class EventAdapter extends
        RecyclerView.Adapter<EventAdapter.ViewHolder>  {

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
    /*
        public void setOnItemClickListener(OnItemClickListener listener) {
            this.listener = listener;
        }
    */

    // Provide a direct reference to each of the views within a data item
    // Used to cache the views within the item layout for fast access

    public class ViewHolder extends RecyclerView.ViewHolder {
        // Your holder should contain a member variable
        // for any view that will be set as you render a row
        ImageView imageMusic ,imgLike,imgComments ;
        ImageView backGr ;
        TextView TitleAlbum ;
        TextView userName ,groupe_music;
        Button playBtn ;

        // We also create a constructor that accepts the entire item row
        // and does the view lookups to find each subview

        public ViewHolder(final View itemView) {
            // Stores the itemView in a public final member variable that can be used
            // to access the context from any ViewHolder instance.
            super(itemView);

             imageMusic = itemView.findViewById(R.id.imageMusic);
             imgLike = itemView.findViewById(R.id.imgLike);
             imgComments = itemView.findViewById(R.id.imgComments);
             backGr =  itemView.findViewById(R.id.backGr);

            // clear out image from convert view
            imageMusic.setImageResource(0);
            backGr.setImageResource(0);

            TitleAlbum =  itemView.findViewById(R.id.groupe_music);
            userName =  itemView.findViewById(R.id.userName);
            groupe_music =  itemView.findViewById(R.id.groupe_music);
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

    private List<Event> mEvents;
    // Store the context for easy access
    private Context mContext;

    // Pass in the contact array into the constructor
    public EventAdapter(Context context, List<Event> events) {
        mEvents = events;
        mContext = context;
    }

    // Easy access to the context object in the recyclerview
    private Context getContext() {
        return mContext;
    }

    @Override
    public EventAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        // Inflate the custom layout
        View contactView = inflater.inflate(R.layout.item_event, parent, false);

        // Return a new holder instance
        ViewHolder viewHolder = new ViewHolder(contactView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(EventAdapter.ViewHolder viewHolder, int position) {
        // Get the data model based on position
        Event event = mEvents.get(position);
        ImageView image_Music = viewHolder.imageMusic ;
        ImageView imgLike = viewHolder.imgLike ;
        ImageView imgComments = viewHolder.imgComments ;
        ImageView back_Gr =  viewHolder.backGr ;
        TextView Title_Album  =  viewHolder.TitleAlbum ;
        TextView Title_Music =  viewHolder.userName  ;
        TextView groupe_music =  viewHolder.groupe_music  ;
        Button play_Btn =  viewHolder.playBtn ;

        // Set item views based on your views and data model

        groupe_music.setText(event.getLien());

        Title_Music.setText(event.getTitle());

        imgLike.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getContext()," Do a query to increment 1 Like " ,Toast.LENGTH_LONG).show();
               //  Intent i = new Intent(getContext(), Details.class);
               // v.getContext().startActivity(new Intent(v.getContext(),Details.class));

            }
        });

        imgComments.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Event event = mEvents.get(position);
                // Toast.makeText(getContext()," Comment the event "+event.getTitle() ,Toast.LENGTH_LONG).show();
                v.getContext().startActivity(new Intent(v.getContext(),CommentActivity.class));

                // Save it in a shared preferences
                SharedPreferences mSettings = getContext().getSharedPreferences("myComments", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = mSettings.edit();

                String id_user = event.getIdUser();
                String id_event = event.getId();
                editor.putString("id_user", id_user);
                editor.putString("id_event",id_event);
                editor.apply();
            }
        });


        //groupe_music.setText(event.getAlbum());

        // playBtn.setText();
        /*
        Glide.with(getContext()).load(event.getImagesMusic())
                .apply(bitmapTransform(new BlurTransformation(5)))
                .into((back_Gr));
        */
        // populate data
        Picasso.with(getContext()).load(event.getImagesMusic()).placeholder(R.drawable.logoevent).into(image_Music);
        Picasso.with(getContext()).load(event.getImagesMusic()).placeholder(R.drawable.logoevent).into(back_Gr);
        // return the view

    }
    // Returns the total count of items in the list
    @Override
    public int getItemCount() {
        return mEvents.size();
    }
    public interface IMethodCaller{
        void myMethode(Event event);
    }
}

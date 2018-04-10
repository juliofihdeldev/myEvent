package com.td.fintech.rebo.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.rebo.fintech.R;
import com.td.fintech.rebo.model.Plat;
import com.td.fintech.rebo.model.Resto;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by julio on 12/21/17.
 */

public class RestoAdapter extends
        RecyclerView.Adapter<RestoAdapter.ViewHolder>  {

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
        // For any view that will be set as you render a row
        ImageView imageMusic ;
        TextView userName ;


        // We also create a constructor that accepts the entire item row
        // And does the view lookups to find each subview

        public ViewHolder(final View itemView) {
            // Stores the itemView in a public final member variable that can be used
            // to access the context from any ViewHolder instance.
            super(itemView);

             imageMusic = itemView.findViewById(R.id.imagePlat);
            userName = itemView.findViewById(R.id.namePlat);

            // clear out image from convert view
            imageMusic.setImageResource(0);

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

    private List<Resto> mResto;
    // Store the context for easy access
    private Context mContext;

    // Pass in the contact array into the constructor
    public RestoAdapter(Context context, List<Resto> plats) {
        mResto = plats;
        mContext = context;
    }

    // Easy access to the context object in the recyclerview
    private Context getContext() {
        return mContext;
    }

    @Override
    public RestoAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        // Inflate the custom layout
        View contactView = inflater.inflate(R.layout.item_resto, parent, false);

        // Return a new holder instance
        ViewHolder viewHolder = new ViewHolder(contactView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(RestoAdapter.ViewHolder viewHolder, int position) {
        // Get the data model based on position
        Resto plat = mResto.get(position);
        ImageView image_Music = viewHolder.imageMusic ;
        TextView Title_Music =  viewHolder.userName  ;
        Title_Music.setText(plat.getNom_resto());
        // populate data
        Picasso.with(getContext()).load(plat.getImages_resto()).placeholder(R.drawable.lo).into(image_Music);
        // return the view

    }
    // Returns the total count of items in the list
    @Override
    public int getItemCount() {
        return mResto.size();
    }
    public interface IMethodCaller{
        void myMethode(Plat plat);
    }
}

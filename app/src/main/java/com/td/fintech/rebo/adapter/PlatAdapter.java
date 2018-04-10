package com.td.fintech.rebo.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.rebo.fintech.R;
import com.td.fintech.rebo.model.Plat;

import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by julio on 12/21/17.
 */

public class PlatAdapter extends
        RecyclerView.Adapter<PlatAdapter.ViewHolder>  {

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
        ImageView imagePlat ,imgLike,imgComments , imgAddPanier;
        ImageView backGr ;
        TextView namePlat, pricePlat;


        // We also create a constructor that accepts the entire item row
        // and does the view lookups to find each subview

        public ViewHolder(final View itemView) {
            // Stores the itemView in a public final member variable that can be used
            // to access the context from any ViewHolder instance.
            super(itemView);

            namePlat = itemView.findViewById(R.id.namePlat);
            pricePlat = itemView.findViewById(R.id.tvPrice);
            imagePlat = itemView.findViewById(R.id.imagePlat);
            imgAddPanier = itemView.findViewById(R.id.tvAddPanier);
            imgLike = itemView.findViewById(R.id.imgLike);
            imgComments = itemView.findViewById(R.id.imgComments);
            backGr =  itemView.findViewById(R.id.backGr);
            // clear out image from convert view
            imagePlat.setImageResource(0);
            backGr.setImageResource(0);


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

    private List<Plat> mPlats;
    // Store the context for easy access
    private Context mContext;

    // Pass in the contact array into the constructor
    public PlatAdapter(Context context, List<Plat> plats) {
        mPlats = plats;
        mContext = context;
    }

    // Easy access to the context object in the recyclerview
    private Context getContext() {
        return mContext;
    }

    @Override
    public PlatAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        // Inflate the custom layout
        View contactView = inflater.inflate(R.layout.item_plat, parent, false);

        // Return a new holder instance
        ViewHolder viewHolder = new ViewHolder(contactView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(PlatAdapter.ViewHolder viewHolder, int position) {
        // Get the data model based on position
        Plat plat = mPlats.get(position);
        ImageView imageplat = viewHolder.imagePlat ;
        TextView pricePlat = viewHolder.pricePlat ;
        ImageView imgLike = viewHolder.imgLike ;
        ImageView imgComments = viewHolder.imgComments ;
        ImageView back_Gr =  viewHolder.backGr ;
        TextView namePlat =  viewHolder.namePlat  ;

        // Set item views based on your views and data model

        namePlat.setText(plat.getName_plat());
        pricePlat.setText(plat.getPrix_plat());

        imgLike.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getContext()," Do a query to increment 1 Like " ,Toast.LENGTH_LONG).show();
               // Intent i = new Intent(getContext(), Details.class);
               // v.getContext().startActivity(new Intent(v.getContext(),Details.class));

            }
        });
        /*
            imgComments.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    Plat plat = mPlats.get(position);
                    // Toast.makeText(getContext()," Comment the plat "+plat.getTitle() ,Toast.LENGTH_LONG).show();
                    v.getContext().startActivity(new Intent(v.getContext(),CommentActivity.class));

                    // Save it in a shared preferences
                    SharedPreferences mSettings = getContext().getSharedPreferences("myComments", Context.MODE_PRIVATE);
                    SharedPreferences.Editor editor = mSettings.edit();

                    String id_event = plat.getId();
                    editor.putString("id_user", id_user);
                    editor.putString("id_event",id_event);
                    editor.apply();
                }
            });
        */


        //groupe_music.setText(plat.getAlbum());

        // playBtn.setText();
        /*
        Glide.with(getContext()).load(plat.getImagesMusic())
                .apply(bitmapTransform(new BlurTransformation(5)))
                .into((back_Gr));
        */
        // populate data
        Picasso.with(getContext()).load(plat.getImgPlat()).placeholder(R.drawable.lo).into(imageplat);
        Picasso.with(getContext()).load(plat.getImgPlat()).placeholder(R.drawable.lo).into(back_Gr);
        // return the view

    }
    // Returns the total count of items in the list
    @Override
    public int getItemCount() {
        return mPlats.size();
    }
    public interface IMethodCaller{
        void myMethode(Plat plat);
    }
}

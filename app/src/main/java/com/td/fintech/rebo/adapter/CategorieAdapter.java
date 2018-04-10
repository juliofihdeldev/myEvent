package com.td.fintech.rebo.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.rebo.fintech.R;
import com.squareup.picasso.Picasso;
import com.td.fintech.rebo.model.Categorie;
import com.td.fintech.rebo.model.Plat;

import java.util.List;

/**
 * Created by julio on 12/21/17.
 */

public class CategorieAdapter extends
        RecyclerView.Adapter<CategorieAdapter.ViewHolder>  {

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
        ImageView imageCat;
        TextView nameCategorie;


        // We also create a constructor that accepts the entire item row
        // and does the view lookups to find each subview

        public ViewHolder(final View itemView) {
            // Stores the itemView in a public final member variable that can be used
            // to access the context from any ViewHolder instance.
            super(itemView);

            imageCat = itemView.findViewById(R.id.imageCat);
            nameCategorie = itemView.findViewById(R.id.nameCat);
            // clear out image from convert view
            imageCat.setImageResource(0);
            /*
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
            */
        }

    }

    private List<Categorie> mCats;
    // Store the context for easy access
    private Context mContext;

    // Pass in the contact array into the constructor
    public CategorieAdapter(Context context, List<Categorie> categories) {
        mCats = categories;
        mContext = context;
    }

    // Easy access to the context object in the recyclerview
    private Context getContext() {
        return mContext;
    }

    @Override
    public CategorieAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        // Inflate the custom layout
        View contactView = inflater.inflate(R.layout.item_categorie, parent, false);

        // Return a new holder instance
        ViewHolder viewHolder = new ViewHolder(contactView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(CategorieAdapter.ViewHolder viewHolder, int position) {
        // Get the data model based on position
        Categorie categorie = mCats.get(position);
        ImageView imageCat = viewHolder.imageCat ;
        TextView nameCategorie = viewHolder.nameCategorie ;

        // Set item views based on your views and data model
        nameCategorie.setText(categorie.getNameCategorie());

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
        Picasso.with(getContext()).load(categorie.getImageCategorie()).placeholder(R.drawable.lo).into(imageCat);

        // return the view

    }
    // Returns the total count of items in the list
    @Override
    public int getItemCount() {
        return mCats.size();
    }
    public interface IMethodCaller{
        void myMethode(Categorie categorie);
    }
}

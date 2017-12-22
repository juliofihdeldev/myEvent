package com.musickart.fihdel.musikart.adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
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
import com.musickart.fihdel.musikart.model.Music;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import jp.wasabeef.glide.transformations.BlurTransformation;

import static com.bumptech.glide.request.RequestOptions.bitmapTransform;

/**
 * Created by julio on 12/21/17.
 */


    public class MusicAdapter extends ArrayAdapter<Music> {

        public MusicAdapter(Context context, ArrayList<Music> musique) {
            super(context, android.R.layout.simple_list_item_1, musique);
        }

        // Model=> view
        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            // Get the data item for this position
            Music musique = getItem(position);
            // Check if an existing view is being reused, otherwise inflate the view
            if (convertView == null) {

                LayoutInflater inflater = LayoutInflater.from(getContext());
                convertView= inflater.inflate(R.layout.item_music,parent,false);
            }

            //find the image view
            ImageView imageMusic = (ImageView) convertView.findViewById(R.id.imageMusic);
            ImageView backGr = (ImageView) convertView.findViewById(R.id.backGr);

            // clear out image from convert view
            imageMusic.setImageResource(0);
            backGr.setImageResource(0);

            TextView TitleAlbum = (TextView) convertView.findViewById(R.id.groupe_music);
            TextView TitleMusic = (TextView) convertView.findViewById(R.id.titre_music);
            Button playBtn =  convertView.findViewById(R.id.achter_btn);

            TitleAlbum.setText(musique.getAlbum());
            TitleMusic.setText(musique.getTitle());

            // playBtn.setText();
            Glide.with(getContext()).load(R.drawable.amonik)
                    .apply(bitmapTransform(new BlurTransformation(25)))
                    .into((ImageView) convertView.findViewById(R.id.backGr));

            // populate data
            Picasso.with(getContext()).load(musique.getImagesMusic()).placeholder(R.drawable.amonik).into(imageMusic);
            Picasso.with(getContext()).load(musique.getImagesMusic()).placeholder(R.drawable.amonik).into(backGr);
            // return the view
            return convertView;

        }
    }
/*
*
* Blurry.with(context)
  .radius(10)
  .sampling(8)
  .color(Color.argb(66, 255, 255, 0))
  .async()
  .animate(500)
  .onto(rootView);

  */
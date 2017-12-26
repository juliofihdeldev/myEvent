package com.musickart.fihdel.musikart.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.musickart.fihdel.musikart.R;

import jp.wasabeef.glide.transformations.BlurTransformation;

import static com.bumptech.glide.request.RequestOptions.bitmapTransform;

public class ProfilUsers extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profil_users);

        Glide.with(getApplicationContext()).load(R.drawable.phoo)
                .apply(bitmapTransform(new BlurTransformation(20)))
                .into((ImageView) findViewById(R.id.backGr));
    }
}

package com.musickart.fihdel.musikart.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.Toolbar;

import com.bumptech.glide.Glide;
import com.musickart.fihdel.musikart.R;

import jp.wasabeef.glide.transformations.BlurTransformation;

import static com.bumptech.glide.request.RequestOptions.bitmapTransform;


/**
 * Created by julio on 12/25/17.
 */

public class LogActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.log_file);
        // ImageView backGr = findViewById(R.id.backGr);

        // playBtn.setText();
        Glide.with(getApplicationContext()).load(R.drawable.amonik)
                .apply(bitmapTransform(new BlurTransformation(30)))
                .into((ImageView) findViewById(R.id.backGr));

    }

}

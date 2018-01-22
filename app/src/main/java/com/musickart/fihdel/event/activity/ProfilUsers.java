package com.musickart.fihdel.event.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.musickart.fihdel.event.R;
import com.musickart.fihdel.event.model.Event;

import jp.wasabeef.glide.transformations.BlurTransformation;

import static com.bumptech.glide.request.RequestOptions.bitmapTransform;

public class ProfilUsers extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profil_users);
        /*
            Glide.with(getApplicationContext()).load(R.drawable.phoo)
            .apply(bitmapTransform(new BlurTransformation(20)))
            .into((ImageView) findViewById(R.id.backGr));
        */
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Profil");
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == android.R.id.home) {
            finish();
            return true;
        }
        /*
            if (id == R.id.share) {
                Intent shareIntent = new Intent(Intent.ACTION_SEND);
                shareIntent.setType("text/plain");
                shareIntent.putExtra(Intent.EXTRA_TEXT," https://play.google.com/store/apps/details?id=jfsl.jobetrouve " +job.getTitre()+""+job.getDetails()  );
                startActivity(Intent.createChooser(shareIntent, "job.etrouve.com "));
                return true;
            }
        */
        return super.onOptionsItemSelected(item);
    }


}

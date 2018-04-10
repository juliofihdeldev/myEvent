package com.td.fintech.rebo.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import com.rebo.fintech.R;
import com.td.fintech.rebo.model.Plat;
import com.squareup.picasso.Picasso;

public class Details extends AppCompatActivity {
    public Plat plat;
    ImageView imageDetatils;
    Button btnbuy;
    TextView btnPrice, tvDescription;
    String prixPlat ,ID_plat, description,images, title;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_details);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        plat = (Plat) getIntent().getSerializableExtra("plat");
        getSupportActionBar().setTitle(plat.getName_plat());

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        imageDetatils = findViewById(R.id.imageDetatils);
        btnPrice = findViewById(R.id.btnPrice);
        tvDescription = findViewById(R.id.tvDescription);

        // Add value to
        prixPlat = plat.getPrix_plat();
        ID_plat = plat.getId();
        description = plat.getDescription_plat();
        images = plat.getImgPlat();
        title = plat.getName_plat();

        btnbuy = findViewById(R.id.btnbuy);
        Picasso.with(getApplicationContext()).load(plat.getImgPlat()).placeholder(R.drawable.lo).
                into(imageDetatils);
        btnPrice.setText(prixPlat + " HTG ");

        tvDescription.setText(description);
        // btnbuy.setText("$" + prixEvent+ "US");
        btnbuy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), Panier_Activity.class);
                i.putExtra("prixEvent",prixPlat);
                i.putExtra("ID_event",ID_plat);
                i.putExtra("description",description);
                i.putExtra("images",images);
                i.putExtra("title",title);
                startActivity(i);
            }
        });

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

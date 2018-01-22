package com.musickart.fihdel.event.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.musickart.fihdel.event.R;
import com.musickart.fihdel.event.model.Event;
import com.squareup.picasso.Picasso;

import org.w3c.dom.Text;

public class Details extends AppCompatActivity {
    public Event event;
    ImageView imageDetatils;
    Button btnbuy;
    TextView btnPrice;
    String prixEvent ,ID_event,ID_user;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_details);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        event = (Event) getIntent().getSerializableExtra("event");
        getSupportActionBar().setTitle(event.getTitle());

        Toast.makeText(getApplicationContext(),event.getTitle(), Toast.LENGTH_LONG).show();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        imageDetatils = findViewById(R.id.imageDetatils);
        btnPrice = findViewById(R.id.btnPrice);

        // Add value to
        prixEvent = event.getPrix_event();
        ID_event = event.getId();
        ID_user = event.getIdUser();

        btnbuy = findViewById(R.id.btnbuy);
        Picasso.with(getApplicationContext()).load(event.getImagesMusic()).placeholder(R.drawable.logoevent).
                into(imageDetatils);
        btnPrice.setText("$" + prixEvent+ "US");
        // btnbuy.setText("$" + prixEvent+ "US");
        btnbuy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext()  , PaymentTicketEvent.class);
                i.putExtra("prixEvent",prixEvent);
                i.putExtra("ID_event",ID_event);
                i.putExtra("ID_user",ID_user);
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
        }*/

        return super.onOptionsItemSelected(item);
    }

}

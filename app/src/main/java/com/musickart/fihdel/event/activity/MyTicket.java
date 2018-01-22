package com.musickart.fihdel.event.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.RelativeLayout;

import com.musickart.fihdel.event.R;

public class MyTicket extends AppCompatActivity {
    RelativeLayout idShowMyTicket;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_ticket);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("My Ticket");
        idShowMyTicket = findViewById(R.id.idShowMyTicket);
        idShowMyTicket.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent valideTicket = new Intent(getApplicationContext(), UserValideTicket.class);
                startActivity(valideTicket);
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

package com.td.fintech.rebo.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.rebo.fintech.R;


public class Commande_Activity extends AppCompatActivity {
    RelativeLayout idShowMyTicket;
    Button btnSendToCard;
    TextView tvPrice, tvDescription, tvTitle;
    ImageView imagesPanier;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_commande);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Mes commandes");
        idShowMyTicket = findViewById(R.id.idShowMyTicket);
        imagesPanier = findViewById(R.id.imagesPanier);

        btnSendToCard = findViewById(R.id.btnSendToCard);
        tvDescription = findViewById(R.id.tvDescription);
        tvPrice = findViewById(R.id.tvPrice);
        tvTitle = findViewById(R.id.tvTitle);
        /*
            String title = getIntent().getStringExtra("title");
            String prixEvent = getIntent().getStringExtra("prixEvent");
            String description = getIntent().getStringExtra("description");
            String images = getIntent().getStringExtra("images");

            Picasso.with(getApplicationContext()).load(images).placeholder(R.drawable.lo).
                    into(imagesPanier);
            tvPrice.setText(prixEvent + " HTG ");
            tvTitle.setText(title);
            tvDescription.setText(description);

            btnSendToCard.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent goToPayment = new Intent(getApplicationContext(), PaymentPanier.class);
                    goToPayment.putExtra("titre", title);
                    goToPayment.putExtra("prixEvent", prixEvent);
                    goToPayment.putExtra("description", description);
                    startActivity(goToPayment);
                }
            });
        */
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == android.R.id.home) {
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

}

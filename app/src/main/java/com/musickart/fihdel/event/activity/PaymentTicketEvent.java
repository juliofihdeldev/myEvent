package com.musickart.fihdel.event.activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.RequestParams;
import com.loopj.android.http.TextHttpResponseHandler;
import com.musickart.fihdel.event.R;
import com.stripe.exception.StripeException;
import com.stripe.model.Charge;
import com.stripe.net.RequestOptions;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import cz.msebera.android.httpclient.Header;

public class PaymentTicketEvent extends AppCompatActivity {

    ProgressBar progressLoadCompany , abovePayment;
    EditText tvCarteNumber, tvMonth, tvYear, tvCvc;
    Button btnMonthYear, btnpayment , btnpayment2;
    TextView tvPrix;
    //GESTION DE PAIEMENT STRIPE
    ProgressDialog progress;
    public String name;

    public String  idTransaction ,id_event,id_user,prix;

    public Date paiementDate;
    public DateFormat dateFormat ;
    int prixEvent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment_ticket_event);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Buy Ticket");
        // Setup price
        tvPrix = findViewById(R.id.tvPrice);
        // Recuperer les donnees de la liste
        id_event = getIntent().getStringExtra("ID_event");
        id_user = getIntent().getStringExtra("ID_user");
        prix = getIntent().getStringExtra("prixEvent");

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        btnpayment = findViewById(R.id.btnpayment);
        btnpayment2 = findViewById(R.id.btnpayment2);
        prixEvent = Integer.parseInt(prix);
        tvPrix.setText( "$"+ prixEvent +  "USD ");
        btnpayment.setText( " Payer " + prixEvent +  " $ USD ");

        btnpayment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showLoading();
                doPayment();
            }
        });
        btnpayment2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }
    public void showLoading(){
        progress = ProgressDialog.show(this, "Patietez","Paiement en cours...",false,false);
    }
    public void dimissLoading(){
        progress.dismiss();
    }

    public void doPayment (){
        // create id for billet
        Long tsLong = System.currentTimeMillis()/1000;

        String ts = tsLong.toString();
        idTransaction = "ME-"+ts;
        dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        paiementDate = new Date();

        tvCarteNumber = (EditText) findViewById(R.id.tvCarteNumber);

        // edtEntreprise = (EditText) findViewById(R.id.edtEntreprise);
        tvMonth = (EditText) findViewById(R.id.tvMonth);
        tvYear = (EditText) findViewById(R.id.tvYear);
        tvCvc = (EditText) findViewById(R.id.tvCvc);

      //  btnpayment.setVisibility(View.GONE);
        final String carteNumber = "4242424242424242" ; //tvCarteNumber.getText().toString(); //"4242424242424242"
        final String mois = tvMonth.getText().toString();
        final String annee = tvYear.getText().toString();
        final String cvc = tvCvc.getText().toString();
        final int amount = prixEvent * 100;
        final String ladate = "" + mois + annee + "";

        String nullChamps = "";

        if (carteNumber.equals(nullChamps) || mois.equals(nullChamps) || cvc.equals(nullChamps)) {
            Toast.makeText(getApplicationContext(), "Un ou Plusieurs champs sont vides", Toast.LENGTH_SHORT).show();
            //btnpayment.setVisibility(View.VISIBLE);
            dimissLoading();

        } else {

            RequestOptions requestOptions = (new RequestOptions.RequestOptionsBuilder()).setApiKey("sk_test_MPz9HYMyiXwhbz4nWpYIUsKc").build();
            Map<String, Object> chargeMap = new HashMap<String, Object>();
            chargeMap.put("currency", "usd");
            chargeMap.put("amount", amount);
            Map<String, Object> cardMap = new HashMap<String, Object>();
            cardMap.put("number", carteNumber);
            cardMap.put("exp_month", mois);
            cardMap.put("exp_year", annee);
            chargeMap.put("card", cardMap);
            try {
                Charge charge = Charge.create(chargeMap, requestOptions);
                if (charge.getPaid()){
                    dimissLoading();
                    Toast.makeText(getApplicationContext(),"Le payment s'est bien passé",Toast.LENGTH_LONG).show();
                    /*
                    * *
                    *     Pass data to my php code
                    * *
                    */
                    AsyncHttpClient client = new AsyncHttpClient();
                    RequestParams params = new RequestParams();
                    params.put("id_user_event", id_event);
                    params.put("id_user_ticket", id_user);
                    params.put("dateValidation", "la date ");
                    client.get("http://www.cristalhotelhaiti.com/apievent/myTicket.php", params, new TextHttpResponseHandler() {
                        @Override
                        public void onSuccess(int statusCode, Header[] headers, String res) {
                            // called when response HTTP status is "200 OK"
                            Toast.makeText(getApplicationContext(),res,Toast.LENGTH_LONG).show();
                        }
                        @Override
                        public void onFailure(int statusCode, Header[] headers, String res, Throwable t) {
                            // called when response HTTP status is "4XX" (eg. 401, 403, 404)
                        }
                    }
                    );
                }else {
                    Toast.makeText(getApplicationContext(),charge.getFailureMessage(),Toast.LENGTH_LONG).show();
                    dimissLoading();
                }
                dimissLoading();
                Intent i = new Intent(getApplicationContext(), MyTicket.class);
                startActivity(i);
                finish();
                //  btnpayment.setVisibility(View.VISIBLE);

            } catch (StripeException e) {
                e.printStackTrace();
                Toast.makeText(getApplication()," Paiement interrompu vérifie la carte"+e.getMessage(), Toast.LENGTH_LONG).show();
            }
        }
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

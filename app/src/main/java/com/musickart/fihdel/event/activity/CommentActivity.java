package com.musickart.fihdel.event.activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;
import com.musickart.fihdel.event.R;
import com.musickart.fihdel.event.model.Event;
import com.squareup.picasso.Picasso;

import org.json.JSONObject;

import cz.msebera.android.httpclient.Header;

public class CommentActivity extends AppCompatActivity {
    EditText edtComment;
    ImageView imgSend;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comment);
        Toolbar toolbar =  findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Comments");
        edtComment = findViewById(R.id.edtComment);
        imgSend = findViewById(R.id.imgSend);

        SharedPreferences mComments = getApplicationContext().getSharedPreferences("myComments", Context.MODE_PRIVATE);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        String id_user = mComments.getString("id_user", "missing");
        String id_event = mComments.getString("id_event", "missing");

        Toast.makeText(getApplicationContext(),id_event +" Separated" +id_user,Toast.LENGTH_LONG).show();

        imgSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String text_comment = edtComment.getText().toString();

                String url = "http://localhost/dashboard/cristal/apievent/comment.php";
                AsyncHttpClient client = new AsyncHttpClient();
                RequestParams params = new RequestParams();
                params.put("text_comment", text_comment);
                params.put("id_user", id_user);
                params.put("event_comment", id_event);
                client.get(url, params, new JsonHttpResponseHandler() {
                    @Override
                    public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                        // Root JSON in response is an dictionary i.e { "data : [ ... ] }
                        // Handle resulting parsed JSON response here
                        // reload data in this adapter
                    }

                    @Override
                    public void onFailure(int statusCode, Header[] headers, String res, Throwable t) {
                        // called when response HTTP status is "4XX" (eg. 401, 403, 404)
                    }
                });

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

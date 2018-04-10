package com.td.fintech.rebo.fragment;

import android.content.Intent;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.rebo.fintech.R;
import com.td.fintech.rebo.activity.Details;
import com.td.fintech.rebo.adapter.RestoAdapter;
import com.td.fintech.rebo.model.Resto;

import org.json.JSONArray;

import java.util.ArrayList;

import cz.msebera.android.httpclient.Header;

import static com.td.fintech.rebo.util.Connect.BASE_URL;

public class ListResto extends AppCompatActivity {
    ProgressBar idProgress;
    private SwipeRefreshLayout swipeContainer;
    ArrayList<Resto> restoArrayList;
    RestoAdapter adapter;
    RecyclerView rvItems;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_real_resto);

        Toolbar toolbar =  findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Restorant fritay ");

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
       // idProgress = findViewById(R.id.idProgress);
        swipeContainer = (SwipeRefreshLayout) findViewById(R.id.swipeContainer);
        rvItems = (RecyclerView) findViewById(R.id.list_item);

        swipeContainer.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                // Your code to refresh the list here.
                // Make sure you call swipeContainer.setRefreshing(false)
                // once the network request has completed successfully.
                fetchTimelineAsync(0);
            }
        });
        // Configure the refreshing colors
        swipeContainer.setColorSchemeResources(android.R.color.holo_blue_bright,
                android.R.color.holo_green_light,
                android.R.color.holo_orange_light,
                android.R.color.holo_red_light);

        laodFood();
    }

    public void laodFood(){

        String url = BASE_URL+"/resto.php";
        AsyncHttpClient client = new AsyncHttpClient();

        client.get(url, new JsonHttpResponseHandler(){
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONArray response) {
                super.onSuccess(statusCode, headers, response);
                Log.d("DEBUG", response.toString());
                JSONArray arrayMusic = response;
                restoArrayList = Resto.fromJSONArray(arrayMusic);
                adapter = new RestoAdapter(getApplicationContext(), restoArrayList);
                // Attach the adapter to the recyclerview to populate items
                rvItems.setAdapter(adapter);
                rvItems.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
                swipeContainer.setRefreshing(false);
               //
                // idProgress.setVisibility(View.GONE);

                adapter.setOnItemClickListener(new RestoAdapter.OnItemClickListener() {
                    @Override
                    public void onItemClick(View itemView, int position) {
                        Resto resto = restoArrayList.get(position);
                        Intent go = new Intent(getApplicationContext(), Details.class);
                        go.putExtra("resto", resto);
                        startActivity(go);
                    }
                });
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONArray errorResponse) {
                super.onFailure(statusCode, headers, throwable, errorResponse);
                Toast.makeText(getApplicationContext(), ""+errorResponse.toString(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void fetchTimelineAsync(int page) {
        // Send the network request to fetch the updated data
        // `client` here is an instance of Android Async HTTP
        // getHomeTimeline is an example endpoint.
        laodFood();
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


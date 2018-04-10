package com.td.fintech.rebo.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.widget.DrawerLayout;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
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
import com.td.fintech.rebo.adapter.CategorieAdapter;
import com.td.fintech.rebo.adapter.PlatAdapter;
import com.td.fintech.rebo.model.Categorie;
import com.td.fintech.rebo.model.Plat;

import org.json.JSONArray;

import java.util.ArrayList;

import cz.msebera.android.httpclient.Header;

import static com.td.fintech.rebo.util.Connect.BASE_URL;

public class ListCategorie extends AppCompatActivity {
    private SwipeRefreshLayout swipeContainer;

    ProgressBar idProgress;

    ArrayList<Categorie> categoriArrayList;
    CategorieAdapter adapter;
    RecyclerView rvItems;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_categorie);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Categorie");
        toolbar.setTitle("Categorie");
        rvItems = findViewById(R.id.list_item);
        idProgress = findViewById(R.id.idProgress);
        swipeContainer = (SwipeRefreshLayout) findViewById(R.id.swipeContainer);

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
        laodCAtegorie();
    }

    public void laodCAtegorie(){

        String url = BASE_URL + "categorie.php";
        // String url = "http://cristalhotelhaiti.com/rebo/api/plat.php";
        AsyncHttpClient client = new AsyncHttpClient();

        client.get(url, new JsonHttpResponseHandler(){
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONArray response) {
                super.onSuccess(statusCode, headers, response);
                Log.d("DEBUG", response.toString());
                //Toast.makeText(MainActivity.this, ""+response.toString(), Toast.LENGTH_SHORT).show();
                JSONArray arrayCat = response;
                categoriArrayList = Categorie.fromJSONArray(arrayCat);
                adapter = new CategorieAdapter(getApplicationContext(), categoriArrayList);
                // Attach the adapter to the recyclerview to populate items
                rvItems.setAdapter(adapter);
                rvItems.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
                swipeContainer.setRefreshing(false);

                idProgress.setVisibility(View.GONE);
                adapter.setOnItemClickListener(new CategorieAdapter.OnItemClickListener() {
                    @Override
                    public void onItemClick(View view, int position) {
                        Categorie categorie = categoriArrayList.get(position);
                        Intent go = new Intent(getApplicationContext(), Details.class);
                        go.putExtra("plat", categorie);
                        startActivity(go);
                        // Toast.makeText(getApplicationContext(), plat.getTitle() + " was clicked!", Toast.LENGTH_SHORT).show();
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
        laodCAtegorie();
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

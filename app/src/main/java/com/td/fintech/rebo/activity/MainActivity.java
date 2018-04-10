package com.td.fintech.rebo.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.rebo.fintech.R;
import com.td.fintech.rebo.adapter.PlatAdapter;
import com.td.fintech.rebo.fragment.ListResto;
import com.td.fintech.rebo.fragment.PlayerFragment;
import com.td.fintech.rebo.model.Plat;
import com.td.fintech.rebo.util.Connect;

import org.json.JSONArray;

import java.util.ArrayList;

import cz.msebera.android.httpclient.Header;

import static com.td.fintech.rebo.util.Connect.BASE_URL;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    private SwipeRefreshLayout swipeContainer;

    ProgressBar idProgress;

    ArrayList<Plat> platArrayList;
    PlatAdapter adapter;
    RecyclerView rvItems;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setTitle("Rebo SA");
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

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        laodFood();

        /*
            StaggeredGridLayoutManager gridLayoutManager =
                new StaggeredGridLayoutManager(2    , StaggeredGridLayoutManager.VERTICAL);
            // Attach the layout manager to the recycler view
            rvItems.setLayoutManager(gridLayoutManager);
        */

    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();
        if (id == R.id.commandes) {
            Intent commande = new Intent(MainActivity.this, Commande_Activity.class);
            startActivity(commande);
        } else if (id == R.id.compte) {
           // Intent log = new Intent(MainActivity.this, LogActivity.class);
            Intent log = new Intent(MainActivity.this, ProfilUsers.class);
            startActivity(log);
        } else if (id == R.id.categorie) {
            Intent categorie = new Intent(MainActivity.this, ListCategorie.class);
            startActivity(categorie);
        } else if (id == R.id.compose) {
            Intent resto = new Intent(MainActivity.this, ComposeMenu.class);
            startActivity(resto);
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    public void laodFood(){

        String url = BASE_URL + "plat.php";
        //String url = "http://cristalhotelhaiti.com/rebo/api/plat.php";
        AsyncHttpClient client = new AsyncHttpClient();

        client.get(url, new JsonHttpResponseHandler(){
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONArray response) {
                super.onSuccess(statusCode, headers, response);
                Log.d("DEBUG", response.toString());
                //Toast.makeText(MainActivity.this, ""+response.toString(), Toast.LENGTH_SHORT).show();
                JSONArray arrayMusic = response;
                platArrayList = Plat.fromJSONArray(arrayMusic);
                adapter = new PlatAdapter(getApplicationContext(), platArrayList);
                // Attach the adapter to the recyclerview to populate items
                rvItems.setAdapter(adapter);
                rvItems.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
                swipeContainer.setRefreshing(false);

                idProgress.setVisibility(View.GONE);
                adapter.setOnItemClickListener(new PlatAdapter.OnItemClickListener() {
                    @Override
                    public void onItemClick(View view, int position) {
                        Plat plat = platArrayList.get(position);
                        Intent go = new Intent(getApplicationContext(), Details.class);
                        go.putExtra("plat", plat);
                        startActivity(go);
                      //  Toast.makeText(getApplicationContext(), plat.getTitle() + " was clicked!", Toast.LENGTH_SHORT).show();
                    }
                });

            }

            @Override
            public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONArray errorResponse) {
                super.onFailure(statusCode, headers, throwable, errorResponse);
                Toast.makeText(MainActivity.this, ""+errorResponse.toString(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void fetchTimelineAsync(int page) {
        // Send the network request to fetch the updated data
        // `client` here is an instance of Android Async HTTP
        // getHomeTimeline is an example endpoint.
        laodFood();
    }

}

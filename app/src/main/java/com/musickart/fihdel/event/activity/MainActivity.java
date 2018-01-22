package com.musickart.fihdel.event.activity;

import android.content.Intent;
import android.os.Bundle;
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
import android.widget.Toast;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.musickart.fihdel.event.R;
import com.musickart.fihdel.event.adapter.EventAdapter;
import com.musickart.fihdel.event.fragment.EventFragment;
import com.musickart.fihdel.event.fragment.PlayerFragment;
import com.musickart.fihdel.event.model.Event;

import org.json.JSONArray;

import java.util.ArrayList;

import cz.msebera.android.httpclient.Header;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    ArrayList<Event> eventArrayList;
    EventAdapter adapter;
    RecyclerView rvItems;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        rvItems = findViewById(R.id.list_item);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        laodMusic();

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
        if (id == R.id.newAlbum) {
            Intent album = new Intent(MainActivity.this, MyTicket.class);
            startActivity(album);
        } else if (id == R.id.compte) {
           // Intent log = new Intent(MainActivity.this, LogActivity.class);
            Intent log = new Intent(MainActivity.this, ProfilUsers.class);
            startActivity(log);
        } else if (id == R.id.one) {

        } else if (id == R.id.two) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    private void showPlayer() {
        Intent player = new Intent(MainActivity.this, PlayerFragment.class);
        player.putExtra("id_music",1);
        startActivity(player);
    }


    public void laodMusic(){
        //String url = "http://musikart.org/mobile/searchJson.php";
        String url = "http://www.cristalhotelhaiti.com/apievent/event.php";
        AsyncHttpClient client = new AsyncHttpClient();

        client.get(url, new JsonHttpResponseHandler(){
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONArray response) {
                super.onSuccess(statusCode, headers, response);
                Log.d("DEBUG", response.toString());
                //Toast.makeText(MainActivity.this, ""+response.toString(), Toast.LENGTH_SHORT).show();
                JSONArray arrayMusic = response;
                eventArrayList = Event.fromJSONArray(arrayMusic);
                adapter = new EventAdapter(getApplicationContext(), eventArrayList);
                // Attach the adapter to the recyclerview to populate items
                rvItems.setAdapter(adapter);
                rvItems.setLayoutManager(new LinearLayoutManager(getApplicationContext()));

                adapter.setOnItemClickListener(new EventAdapter.OnItemClickListener() {
                    @Override
                    public void onItemClick(View view, int position) {
                        Event event = eventArrayList.get(position);
                        Intent go = new Intent(getApplicationContext(), Details.class);
                        go.putExtra("event", event);
                        startActivity(go);
                      //  Toast.makeText(getApplicationContext(), event.getTitle() + " was clicked!", Toast.LENGTH_SHORT).show();
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

}

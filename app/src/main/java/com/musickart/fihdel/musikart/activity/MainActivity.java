package com.musickart.fihdel.musikart.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.FragmentManager;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.ListView;

import com.musickart.fihdel.musikart.R;
import com.musickart.fihdel.musikart.adapter.MusicAdapter;
import com.musickart.fihdel.musikart.fragment.PlayerFragment;
import com.musickart.fihdel.musikart.model.Music;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    ArrayList<Music> musicArrayList;
    MusicAdapter musicAdapter;
    ListView lvItems;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showPlayer();
               /*
                    Snackbar.make(view, "Show the musique player in a fragment ", Snackbar.LENGTH_LONG)
                     .setAction("Action", null).show();
                */
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        lvItems = findViewById(R.id.list_item);
        musicArrayList = new ArrayList<>();
        musicAdapter = new MusicAdapter(getApplicationContext(), musicArrayList);
        laodMusic();
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

        if (id == R.id.nav_camera) {

        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

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
        lvItems.setAdapter(musicAdapter);

        Music music = new Music();
        music.album= "Harmonic";
        music.title= "Amour d'enfance";
        music.ImagesMusic= "R.id.amonik";


        Music music2 = new Music();
        music2.album= "Djakout No !";
        music2.title= "Amour d'enfance enfance ";
        music2.ImagesMusic= "R.id.enfance";

        musicAdapter.add(music);
        musicAdapter.add(music2);

        musicAdapter.notifyDataSetChanged();

        lvItems.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent player = new Intent(MainActivity.this, PlayerFragment.class);
                player.putExtra("id_music",1);
                startActivity(player);

            }
        });


     /*
        String url = "http://ayibopost.com/wp-json/posts?filter[posts_per_page]=9";
        AsyncHttpClient client = new AsyncHttpClient();

        client.get(url, new JsonHttpResponseHandler(){
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONArray response) {
                super.onSuccess(statusCode, headers, response);
                //Log.d("DEBUG", toString());
                JSONArray articleJsonPosts = null;

                articleJsonPosts = response;
                article.addAll( Article.fromJSONArray(articleJsonPosts));

                articleAdapter.notifyDataSetChanged();
                progressBar3.setVisibility(View.GONE);
                Log.d("DEBUG", articleJsonPosts.toString() );

            }

            @Override
            public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONArray errorResponse) {
                super.onFailure(statusCode, headers, throwable, errorResponse);
            }
        });
        */

    }

}

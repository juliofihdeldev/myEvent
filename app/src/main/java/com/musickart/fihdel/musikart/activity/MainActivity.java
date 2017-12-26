package com.musickart.fihdel.musikart.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
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
import android.widget.Toast;

import com.musickart.fihdel.musikart.R;
import com.musickart.fihdel.musikart.adapter.AlbumAdapter;
import com.musickart.fihdel.musikart.adapter.MusicAdapter;
import com.musickart.fihdel.musikart.fragment.AlbumFragment;
import com.musickart.fihdel.musikart.fragment.ListMysicByAlbum;
import com.musickart.fihdel.musikart.fragment.PlayerFragment;
import com.musickart.fihdel.musikart.model.Album;
import com.musickart.fihdel.musikart.model.Music;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    ArrayList<Music> musicArrayList;
    MusicAdapter adapter;
    RecyclerView rvItems;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        rvItems = findViewById(R.id.list_item);
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

        musicArrayList = Music.createMusicList(13);
        adapter = new MusicAdapter(this, musicArrayList);
        // Attach the adapter to the recyclerview to populate items
        rvItems.setAdapter(adapter);
        rvItems.setLayoutManager(new LinearLayoutManager(this));
        Music m = new Music();

        /*
            StaggeredGridLayoutManager gridLayoutManager =
                new StaggeredGridLayoutManager(2    , StaggeredGridLayoutManager.VERTICAL);
            // Attach the layout manager to the recycler view
            rvItems.setLayoutManager(gridLayoutManager);
        */

        adapter.setOnItemClickListener(new MusicAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Music music = musicArrayList.get(position);
                Intent go = new Intent(getApplicationContext(), ListMysicByAlbum.class);
                go.putExtra("album", music);
                startActivity(go);
                Toast.makeText(getApplicationContext(), music.getTitle() + " was clicked!", Toast.LENGTH_SHORT).show();
            }
        });

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

        } else if (id == R.id.newAlbum) {
            Intent album = new Intent(MainActivity.this, AlbumFragment.class);
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

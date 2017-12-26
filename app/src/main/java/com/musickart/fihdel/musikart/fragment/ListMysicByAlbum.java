package com.musickart.fihdel.musikart.fragment;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Toast;

import com.musickart.fihdel.musikart.R;
import com.musickart.fihdel.musikart.adapter.AlbumAdapter;
import com.musickart.fihdel.musikart.adapter.MusicListAdapter;
import com.musickart.fihdel.musikart.model.Album;

import java.util.ArrayList;

public class ListMysicByAlbum extends AppCompatActivity {

    ArrayList<Album> albums;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_mysic_by_album);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        final RecyclerView rvContacts = (RecyclerView) findViewById(R.id.rvContacts);
        toolbar.setTitle("Album de ");
        // Initialize contacts
        albums = Album.createContactsList(13);
        // Create adapter passing in the sample user data
        MusicListAdapter adapter = new MusicListAdapter(this, albums);
        // Attach the adapter to the recyclerview to populate items
        rvContacts.setAdapter(adapter);

        // Set layout manager to position the items
        rvContacts.setLayoutManager(new LinearLayoutManager(this));
        /*
        StaggeredGridLayoutManager gridLayoutManager =
                new StaggeredGridLayoutManager(2    , StaggeredGridLayoutManager.VERTICAL);
        // Attach the layout manager to the recycler view
        rvContacts.setLayoutManager(gridLayoutManager);
        */

        adapter.setOnItemClickListener(new MusicListAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Album album = albums.get(position);
                Intent go = new Intent(getApplicationContext(), PlayerFragment.class);
                go.putExtra("album", album);
                startActivity(go);
                Toast.makeText(getApplicationContext(), album.getName() + " was clicked!", Toast.LENGTH_SHORT).show();
            }
        });
    }
}

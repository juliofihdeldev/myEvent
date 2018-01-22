package com.musickart.fihdel.event.fragment;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Toast;


import com.musickart.fihdel.event.R;
import com.musickart.fihdel.event.model.Album;
import com.musickart.fihdel.event.adapter.AlbumAdapter;

import java.util.ArrayList;

public class EventFragment extends AppCompatActivity {

    ArrayList<Album> albums;
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_album);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        final RecyclerView rvContacts = (RecyclerView) findViewById(R.id.rvContacts);
        toolbar.setTitle("Nouveaux album");
        // Initialize contacts
        albums = Album.createContactsList(20);
        // Create adapter passing in the sample user data
        AlbumAdapter adapter = new AlbumAdapter(this, albums);
        // Attach the adapter to the recyclerview to populate items
        rvContacts.setAdapter(adapter);

        // Set layout manager to position the items
        // rvContacts.setLayoutManager(new LinearLayoutManager(this));

        StaggeredGridLayoutManager gridLayoutManager =
                new StaggeredGridLayoutManager(2    , StaggeredGridLayoutManager.VERTICAL);
        // Attach the layout manager to the recycler view
        rvContacts.setLayoutManager(gridLayoutManager);

        adapter.setOnItemClickListener(new AlbumAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Album album = albums.get(position);
                Intent go = new Intent(getApplicationContext(), ListMysicByAlbum.class);
                go.putExtra("album", album);
                startActivity(go);
                Toast.makeText(getApplicationContext(), album.getName() + " was clicked!", Toast.LENGTH_SHORT).show();
            }
        });

    }

}

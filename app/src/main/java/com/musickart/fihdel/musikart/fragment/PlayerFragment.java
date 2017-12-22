package com.musickart.fihdel.musikart.fragment;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;
import android.widget.Toolbar;

import com.musickart.fihdel.musikart.R;

import java.io.IOException;

public class PlayerFragment extends AppCompatActivity {
    ImageView playBtn;
    MediaPlayer mediaPlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_player);
        Toolbar toolbar = findViewById(R.id.toolbar);
        playBtn= findViewById(R.id.playBtn);
/*        avanceBtn= findViewById(R.id.avanceBtn);*/

        mediaPlayer = MediaPlayer.create(PlayerFragment.this, R.raw.kalash_laisse_moi_te_sauver);
        mediaPlayer.start();

        playBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               if(mediaPlayer.isPlaying()){
                   mediaPlayer.pause();
                   playBtn.setImageResource(R.drawable.ic_action_pause);
                   Toast.makeText(getApplicationContext(),  "la position est" + mediaPlayer.getCurrentPosition()/1000, Toast.LENGTH_LONG).show();
               }else {
                   mediaPlayer.start();
                   playBtn.setImageResource(R.drawable.ic_action_play);
               }

            }
        });

       /* avanceBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mediaPlayer.seekTo(19*1000);
            }
        });*/

    }


/*
    MediaPlayer mediaPlayer = MediaPlayer.create(this, R.raw.sample_audio);
    mediaPlayer.start();              // start the audio from last location
    mediaPlayer.pause();              // pause audio
    mediaPlayer.reset();              // reset audio to beginning of track
    mediaPlayer.isPlaying();          // returns true/false indicating the song is playing
    mediaPlayer.seekTo(100);           // move song to that particular millisecond
    mediaPlayer.getCurrentDuration(); // current position of song in milliseconds
    mediaPlayer.getDuration();
*/

/*
*
* String url = "https://dl.dropboxusercontent.com/u/10281242/sample_audio.mp3";
    final MediaPlayer mediaPlayer = new MediaPlayer();

// Set type to streaming
    mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
// Listen for if the audio file can't be prepared
    mediaPlayer.setOnErrorListener(new OnErrorListener() {
        @Override
        public boolean onError(MediaPlayer mp, int what, int extra) {
            // ... react appropriately ...
            // The MediaPlayer has moved to the Error state, must be reset!
            return false;
        }
    });

// Attach to when audio file is prepared for playing
mediaPlayer.setOnPreparedListener(new OnPreparedListener() {
	@Override
	public void onPrepared(MediaPlayer mp) {
		mediaPlayer.start();
	}
});
    // Set the data source to the remote URL
    mediaPlayer.setDataSource(url);
    // Trigger an async preparation which will file listener when completed
    mediaPlayer.prepareAsync();
*/

}
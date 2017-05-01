package trainedge.enharmonic;

import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import java.io.IOException;

/**
 * Created by Anushri on 01-05-2017.
 */

public class SongPlayerActivity extends AppCompatActivity {

    private MediaPlayer mediaPlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_position);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Intent recIntent = getIntent();

        mediaPlayer = new MediaPlayer();

        int position=recIntent.getIntExtra("trainedge.enharmonic.position",0);
        String path = recIntent.getExtras().getString("trainedge.enharmonic.path");
        Uri songUri= recIntent.getExtras().getParcelable("trainedge.enharmonic.uri");
        try {
            handleSongPlay();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private void handleSongPlay() throws IOException {

        mediaPlayer.start();
        mediaPlayer.prepare();

    }
}

package trainedge.enharmonic;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;

import java.util.List;

public class MusicActivity extends AppCompatActivity implements PrepareMusicRetrieverTask.MusicRetrieverPreparedListener {

    private MusicRetriever mRetriever;
    private List<MusicRetriever.Item> songList;
    private RecyclerView musicrecycler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_music);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        musicrecycler = (RecyclerView) findViewById(R.id.musicrecycler);

        mRetriever = new MusicRetriever(getContentResolver());
        (new PrepareMusicRetrieverTask(mRetriever, this)).execute();

        LinearLayoutManager Manager = new LinearLayoutManager(this);
        musicrecycler.setLayoutManager(Manager);
    }

    @Override
    public void onMusicRetrieverPrepared() {
        songList = mRetriever.mItems;

        //pass Recycler View Adapter
        SongsAdapter adapter = new SongsAdapter(songList);
        musicrecycler.setAdapter(adapter);


        //recycler holder
        // set onclick listner
        // and analyser
    }
}

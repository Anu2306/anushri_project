package trainedge.enharmonic;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

/**
 * Created by Anushri on 01-05-2017.
 */

public class SongsAdapter extends RecyclerView.Adapter<SongHolder> {

    private List<MusicRetriever.Item> songlist;

    SongsAdapter(List<MusicRetriever.Item> songlist) {
        this.songlist = songlist;
    }

    @Override
    public SongHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View row = ((LayoutInflater) parent.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE)).inflate(R.layout.simple_song_list, parent, false);
        return new SongHolder(row);
    }

    @Override
    public void onBindViewHolder(final SongHolder holder, int position) {
        final MusicRetriever.Item musicitem = songlist.get(holder.getAdapterPosition());
        holder.tvsongs.setText(musicitem.getTitle());
        holder.tvartist.setText(musicitem.getArtist());
        holder.tvduration.setText(String.valueOf(musicitem.getDuration()));
        // holder.rlayout.setOnClickListener();
        holder.rlayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(holder.rlayout.getContext(), MusicAnalyser.class);
                i.putExtra("trainedge.beattiles.path", musicitem.getPath());
                i.putExtra("trainedge.beattiles.uri", musicitem.getURI());
                holder.rlayout.getContext().startActivity(i);
            }
        });
        holder.rlayout.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                Intent i = new Intent(holder.rlayout.getContext(), SongPlayerActivity.class);
                i.putExtra("trainedge.beattiles.position", holder.getAdapterPosition());
                i.putExtra("trainedge.beattiles.path", musicitem.getPath());
                i.putExtra("trainedge.beattiles.uri", musicitem.getURI());
                holder.rlayout.getContext().startActivity(i);
                return true;
            }
        });
    }

    @Override
    public int getItemCount() {
        return songlist.size();
    }
}

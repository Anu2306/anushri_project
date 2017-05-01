package trainedge.enharmonic;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

/**
 * Created by Anushri on 01-05-2017.
 */

public class SongHolder extends RecyclerView.ViewHolder {

    TextView tvsongs;
    TextView tvartist;
    TextView tvduration;
    View rlayout;
    public SongHolder(View itemView) {
        super(itemView);
        tvsongs=(TextView) itemView.findViewById(R.id.tvsongs);
        tvartist=(TextView) itemView.findViewById(R.id.tvartist);
        tvduration=(TextView) itemView.findViewById(R.id.tvduration);
        rlayout=itemView.findViewById(R.id.rlcontainer);
    }
}

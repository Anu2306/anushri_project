package trainedge.enharmonic;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

/**
 * Created by Anushri on 07-04-2017.
 */

public class CommentHolder extends RecyclerView.ViewHolder {

    TextView tvCommentUser;
    TextView tvCommentMsg;

    public CommentHolder(View itemView) {
        super(itemView);
        tvCommentMsg= (TextView) itemView.findViewById(R.id.tvCommentMsg);
        tvCommentUser= (TextView) itemView.findViewById(R.id.tvCommentUser);
    }
}

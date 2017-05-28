package trainedge.enharmonic;

import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import java.util.List;

/**
 * Created by Anushri on 28-05-2017.
 */

public class ScoreAdapter extends RecyclerView.Adapter<ScoreHolder> {

    //we also need a constructor for this example
    List<ScoreModel> commentList;
    //alt insert to add constructor


    public ScoreAdapter(List<ScoreModel> commentList) {
        this.commentList = commentList;
    }

    @Override
    public ScoreHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return null;
    }

    /*@Override
        public ScoreHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View row = ((LayoutInflater) parent.getContext()
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE))
                    .inflate(R.layout.gamescore, parent, false);
            return new ScoreHolder(row);
        }
    */
    @Override
    public void onBindViewHolder(ScoreHolder holder, int position) {
        //databinding
        ScoreModel model = commentList.get(position);
        holder.tvCommentUser.setText(model.username);
        holder.tvCommentMsg.setText(model.msg);
    }

    @Override
    public int getItemCount() {
        return commentList.size();
    }
}

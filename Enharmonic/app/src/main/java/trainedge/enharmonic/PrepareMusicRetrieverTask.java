package trainedge.enharmonic;

import android.os.AsyncTask;

/**
 * Created by Anushri on 01-05-2017.
 */

public class PrepareMusicRetrieverTask extends AsyncTask<Void,Void,Void> {

    MusicRetriever mRetriever;
    MusicRetrieverPreparedListener mListener;
    public PrepareMusicRetrieverTask(MusicRetriever retriever,
                                         MusicRetrieverPreparedListener listener) {
        mRetriever = retriever;
        mListener = listener;
    }
    @Override
    protected Void doInBackground(Void... arg0) {
        mRetriever.prepare();
        return null;
    }
    @Override
    protected void onPostExecute(Void result) {
            mListener.onMusicRetrieverPrepared();
        }
    public interface MusicRetrieverPreparedListener {
        public void onMusicRetrieverPrepared();
    }
}

package trainedge.enharmonic;

import com.google.firebase.database.DataSnapshot;

/**
 * Created by Anushri on 28-05-2017.
 */

public class ScoreModel {

    String username;
    String msg;

    public ScoreModel(DataSnapshot dataSnapshot) {
        this.username = dataSnapshot.child("score").getValue(String.class);
        this.msg = dataSnapshot.child("user").getValue(String.class);
    }
}

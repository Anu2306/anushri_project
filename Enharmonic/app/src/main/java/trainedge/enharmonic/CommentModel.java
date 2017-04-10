package trainedge.enharmonic;

import com.google.firebase.database.DataSnapshot;

/**
 * Created by Anushri on 07-04-2017.
 */

class CommentModel {

    String username;
    String msg;

    public CommentModel(DataSnapshot dataSnapshot) {
        this.username = dataSnapshot.child("user").getValue(String.class);
        this.msg = dataSnapshot.child("msg").getValue(String.class);
    }
}

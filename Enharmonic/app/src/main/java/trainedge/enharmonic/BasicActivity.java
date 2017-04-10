package trainedge.enharmonic;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.view.animation.OvershootInterpolator;
import android.widget.Toast;

import com.google.android.gms.appinvite.AppInvite;
import com.google.android.gms.appinvite.AppInviteInvitation;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import jp.wasabeef.recyclerview.animators.SlideInUpAnimator;

public class BasicActivity extends AppCompatActivity implements GoogleApiClient.OnConnectionFailedListener{

    public static final int REQUEST_INVITE =232;
    private GoogleApiClient mGoogleApiClient;

    public static final String TAG = "BasicActivity";

    private FirebaseDatabase dbInstance;
    private DatabaseReference dbRef;
    List<CommentModel> commentList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_basic);

        //get the data and covert into java obj
        dbInstance = FirebaseDatabase.getInstance();
        dbRef = dbInstance.getReference("comments");
        //creating blank list in memory
        commentList = new ArrayList<>();

        //recyclerview obj

        final RecyclerView rvComments = (RecyclerView) findViewById(R.id.rvComments);
        LinearLayoutManager manager = new LinearLayoutManager(this);

        //passing layout manager in recyclerview
        rvComments.setLayoutManager(manager);

        final CommentAdapter adapter = new CommentAdapter(commentList);
        rvComments.setAdapter(adapter);

        SlideInUpAnimator animator = new SlideInUpAnimator(new OvershootInterpolator(1f));

        rvComments.setItemAnimator(animator);
        rvComments.getItemAnimator().setAddDuration(1000);
        //setup listener
        //using anonymous class

        dbRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                //data is in dataSnapshot obj
                int position = 0;
                commentList.clear();
                if (dataSnapshot.hasChildren()) { //tab
                    for (DataSnapshot snapshot : dataSnapshot.getChildren()) { // datasnapshot.getChildren().iter
                        commentList.add(new CommentModel(snapshot));
                        adapter.notifyItemInserted(position);
                        position++;
                    }
                    Toast.makeText(BasicActivity.this, "data loaded", Toast.LENGTH_SHORT).show();

                } else {
                    Toast.makeText(BasicActivity.this, "No comments", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Toast.makeText(BasicActivity.this, databaseError.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

        mGoogleApiClient = new GoogleApiClient.Builder(this)
                .enableAutoManage(this, this)
                .addApi(Auth.GOOGLE_SIGN_IN_API)
                .addApi(AppInvite.API)
                .build();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_basic, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            Intent goToNextActivity = new Intent(getApplicationContext(), SettingsActivity.class);
            startActivity(goToNextActivity);

            return true;
        }

        if (id == R.id.action_feedback) {
            Intent goToNextActivity = new Intent(getApplicationContext(), FeedbackActivity.class);
            startActivity(goToNextActivity);

            return true;
        }

        if (id == R.id.action_invite) {
            sendInvitation();

            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
        Log.d(TAG, "onConnectionFailed:" + connectionResult);
    }

    private void sendInvitation() {
        Intent intent = new AppInviteInvitation.IntentBuilder("Connect Now")
                .setMessage("Install our App to to get amazing life")
                .setCallToActionText("Install App")
                .build();
        startActivityForResult(intent, REQUEST_INVITE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);


        if (requestCode == REQUEST_INVITE) {
            if (resultCode == RESULT_OK) {
                // Check how many invitations were sent and log.
                String[] ids = AppInviteInvitation.getInvitationIds(resultCode, data);
                Log.d(TAG, "Invitations sent: " + ids.length);
            } else {
                // Sending failed or it was canceled, show failure message to the user
                Log.d(TAG, "Failed to send invitation.");
            }
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        if (mGoogleApiClient!=null) {
            mGoogleApiClient.connect();
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        if (mGoogleApiClient!=null && mGoogleApiClient.isConnected()) {
            mGoogleApiClient.disconnect();
        }
    }
}

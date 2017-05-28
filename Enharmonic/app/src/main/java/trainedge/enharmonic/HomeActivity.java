package trainedge.enharmonic;

import android.content.ContentResolver;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.google.android.gms.appinvite.AppInvite;
import com.google.android.gms.appinvite.AppInviteInvitation;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;

public class HomeActivity extends AppCompatActivity implements GoogleApiClient.OnConnectionFailedListener,View.OnClickListener {

    //public static final String TAG = "MusicRetriever";
    private Button ngamebtn;
    private ContentResolver mContentResolver;
    private ContentResolver cr;
    private Button highscorebtn;


    public static final int REQUEST_INVITE =232;
    private GoogleApiClient mGoogleApiClient;

    public static final String TAG = "BasicActivity";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        ngamebtn = (Button) findViewById(R.id.ngamebtn);
        ngamebtn.setOnClickListener(this);

        highscorebtn = (Button) findViewById(R.id.highscorebtn);
        highscorebtn.setOnClickListener(this);


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

        if (id == R.id.action_login) {
            Intent goToNextActivity = new Intent(getApplicationContext(), LoginActivity.class);
            startActivity(goToNextActivity);
        }

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

        if (id == R.id.action_about_us) {
            Intent goToNextActivity = new Intent(getApplicationContext(), AboutUsActivity.class);
            startActivity(goToNextActivity);

            return true;
        }

        return super.onOptionsItemSelected(item);
    }
    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
        Log.d(TAG, "onConnectionFailed:" + connectionResult);
    }

    public void sendInvitation() {
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

    @Override
    public void onClick(View v) {

        if (v.getId() == R.id.ngamebtn)
        {

            Intent musicintent=new Intent(HomeActivity.this,MusicActivity.class);
            startActivity(musicintent);

        }
        if(v.getId()==R.id.highscorebtn)
        {
            Intent scoreintent=new Intent(HomeActivity.this,ScoreActivity.class);
            startActivity(scoreintent);
        }

    }
}

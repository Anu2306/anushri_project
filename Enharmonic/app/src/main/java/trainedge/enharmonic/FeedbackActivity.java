package trainedge.enharmonic;

import android.os.Bundle;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import org.w3c.dom.Text;

import java.util.HashMap;

public class FeedbackActivity extends AppCompatActivity implements View.OnClickListener {


    private EditText etName;
    private EditText etComment;
    private Button btnSend;
    private FirebaseDatabase db;
    private DatabaseReference commentRef;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feedback);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        /*FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        */
        //<code>
        etName = (EditText) findViewById(R.id.etName);
        etComment = (EditText) findViewById(R.id.etComment);
        btnSend = (Button) findViewById(R.id.btnSend);

        db = FirebaseDatabase.getInstance();
        commentRef = db.getReference("Comments");

        btnSend.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        String name=etName.getText().toString();
        String comment=etComment.getText().toString();

        if(name.isEmpty()){
            etName.setError("Fill your Name");
            return;
        }

        if(comment.isEmpty()){
            etComment.setError("Add Comments Here");
            return;
        }

        //Firebase Upload

        HashMap<String,String> map=new HashMap<>();
        map.put("msg",comment);
        map.put("user",name);

        commentRef.push().setValue(map);
        //you may add onCompletionListener too
        //update ui

        etName.setText("");
        etComment.setText("");
    }
}

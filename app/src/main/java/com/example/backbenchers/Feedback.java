package com.example.backbenchers;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

public class Feedback extends AppCompatActivity
{
    DatabaseReference reference;
    FirebaseDatabase rootNode;
    EditText feedBacktext;
    Button feedBackSendButton;
    String name,username,email,feedback;
    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);//for full screen
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_feedback);
        //database reference
        rootNode = FirebaseDatabase.getInstance();
        reference = rootNode.getReference("feedbacks");//database reference
        //hooks
        feedBacktext = findViewById(R.id.editTextTextMultiLine2);
        feedBackSendButton = findViewById(R.id.button3);
        progressBar = findViewById(R.id.progressBarfeedback);
        progressBar.setVisibility(View.GONE);

        feedBackSendButton.setOnClickListener(new View.OnClickListener() //when button clicked
        {
            @Override
            public void onClick(View view) {
                SendFeedBack();
            }
        });
    }

    private void SendFeedBack()
    {
        progressBar.setVisibility(View.VISIBLE);
        //getting data from session
        SessionManager sessionManager = new SessionManager(this,SessionManager.SESSION_USERSESSION);//get data from db
        HashMap<String,String> userDetails =  sessionManager.getUserDetailFromSession();
        name = userDetails.get(SessionManager.KEY_NAME);
        username = userDetails.get(SessionManager.KEY_USERNAME);
        email = userDetails.get(SessionManager.KEY_EMAIL);
        feedback = feedBacktext.getText().toString();//get the user feedback

        //send feedback to the database using helper class
        UserFeedbackHelperClass userFeedbackHelperClass = new UserFeedbackHelperClass(name,username,email,feedback);
        reference.child(username).setValue(userFeedbackHelperClass);
        // if done then show toast message
        Intent intent = new Intent(getApplicationContext(),Dashboard.class);//send user to the dashboard.
        startActivity(intent);
        Toast.makeText(getApplicationContext(), "Thankyou for your feedback", Toast.LENGTH_LONG).show();
        finish();

    }
}
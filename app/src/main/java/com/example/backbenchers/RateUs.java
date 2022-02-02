package com.example.backbenchers;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

public class RateUs extends AppCompatActivity {

    RatingBar ratingBar;
    TextView textView;
    Button button;
    DatabaseReference reference;
    FirebaseDatabase rootNode;
    String rating,name,email;
    float rateValue;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_rate_us);

        rootNode  = FirebaseDatabase.getInstance();
        reference = rootNode.getReference("ratings");
        //hooks
        ratingBar = findViewById(R.id.ratingbar);
        textView = findViewById(R.id.rate_text);
        button = findViewById(R.id.submit_rateus);

        ratingBar.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float v, boolean b) {
                rateValue = ratingBar.getRating();

                if(rateValue<=1&&rateValue>0){
                    textView.setText(" Bad "+rateValue);
                }
                else if(rateValue<=2&&rateValue>1)
                {
                    textView.setText(" Ok "+rateValue);
                }
                else if(rateValue<=3&&rateValue>2)
                {
                    textView.setText(" Good "+rateValue);
                }
                else if(rateValue<=4&&rateValue>3)
                {
                    textView.setText(" Very Good "+rateValue);
                }
                else if(rateValue<+5&&rateValue>4)
                {
                    textView.setText(" Excellent "+rateValue);
                }
            }
        });

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SendRating();
            }
        });
    }

    private void SendRating() {

         rating = textView.getText().toString().trim();
         SessionManager sessionManager = new SessionManager(this,SessionManager.SESSION_USERSESSION);//get data from db
         HashMap<String,String> userDetails =  sessionManager.getUserDetailFromSession();
         email = userDetails.get(SessionManager.KEY_USERNAME);
         name = userDetails.get(SessionManager.KEY_NAME);

         UserRatingHelperClass userRatingHelperClass = new UserRatingHelperClass(rating);
         reference.child(email).setValue(userRatingHelperClass);
         Toast.makeText(getApplicationContext(), "Thank you "+name+" For Rate Us", Toast.LENGTH_LONG).show();
    }
}
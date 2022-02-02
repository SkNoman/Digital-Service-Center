package com.example.backbenchers;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageButton;
import android.widget.Toast;

public class AdminPanel extends AppCompatActivity {

    ImageButton Users,Feedbacks,Ratings,Orders,Profiles;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);//for full screen
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_admin_panel);

        Users = findViewById(R.id.imageButton3);
        Feedbacks = findViewById(R.id.imageButton4);
        Ratings = findViewById(R.id.imageButton);
        Orders = findViewById(R.id.imageButton5);
        Profiles = findViewById(R.id.imageButton2);
        Profiles.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(),"Sorry You can't edit admin credentials\nContact with your super admins.",Toast.LENGTH_LONG).show();
            }
        });

        Feedbacks.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), com.example.backbenchers.UserFeedbacks.class);
                startActivity(intent);
            }
        });

        Ratings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(), "Ratings only visible for supper admins", Toast.LENGTH_SHORT).show();
               // Intent intent = new Intent(getApplicationContext(), com.example.backbenchers.UserRatings.class);
               // startActivity(intent);
            }
        });

        Orders.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), com.example.backbenchers.UserOrders.class);
                startActivity(intent);
            }
        });

        Users.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), com.example.backbenchers.Users.class);
                startActivity(intent);
            }
        });


    }
}
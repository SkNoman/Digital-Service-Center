package com.example.backbenchers;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;

public class Automation extends AppCompatActivity {

    Button S1,S2,S3;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);//for full screen
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_automation);


        S1 = findViewById(R.id.s1);
        S2 = findViewById(R.id.s2);
        S3 = findViewById(R.id.s3);
        String ST1="Home Appliences";
        String ST2="Kithen Safety";
        String ST3="Automate Door";
        String PT1="1000/d";
        String PT2="1000/d";
        String PT3="1000/d";
        String MS="Automation Services";

        S1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),OrderCook.class);
                intent.putExtra("stname",ST1);
                intent.putExtra("price",PT1);
                intent.putExtra("servicename",MS);
                finish();
                startActivity(intent);

            }
        });
        S2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),OrderCook.class);
                intent.putExtra("stname",ST2);
                intent.putExtra("price",PT2);
                intent.putExtra("servicename",MS);
                finish();
                startActivity(intent);
            }
        });
        S3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),OrderCook.class);
                intent.putExtra("stname",ST3);
                intent.putExtra("price",PT3);
                intent.putExtra("servicename",MS);
                finish();
                startActivity(intent);
            }
        });

    }
}
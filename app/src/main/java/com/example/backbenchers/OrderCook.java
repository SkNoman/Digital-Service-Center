 package com.example.backbenchers;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import java.util.Random;
import java.util.HashMap;

public class OrderCook extends AppCompatActivity {
    FirebaseDatabase rootNode;
    DatabaseReference reference;
    TextView Ousername,Ophone,Oaddress,Oservice_type,OService_id,OPrice,Oservicename;
    EditText Omessage;
    Button PlaceOrder;
    ProgressBar progressBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_order_cook);

        rootNode = FirebaseDatabase.getInstance();
        reference = rootNode.getReference("orders");//database reference

        OPrice = findViewById(R.id.textView14);
        Ousername = findViewById(R.id.textView16);
        Ophone = findViewById(R.id.textView13);
        Oaddress = findViewById(R.id.textView6);
        Oservice_type = findViewById(R.id.textView12);
        OService_id = findViewById(R.id.textView3);
        Omessage = findViewById(R.id.editText2);
        Oservicename = findViewById(R.id.textView15);
        PlaceOrder = findViewById(R.id.button);
        progressBar = findViewById(R.id.progressBar4);
        progressBar.setVisibility(View.GONE);

        SessionManager sessionManager = new SessionManager(this,SessionManager.SESSION_USERSESSION);//get data from db
        HashMap<String,String> userDetails =  sessionManager.getUserDetailFromSession();
        String dusernname = userDetails.get(SessionManager.KEY_USERNAME);//get specific data
        String dphone = userDetails.get(SessionManager.KEY_PHONE);//get specific data
        String daddress = userDetails.get(SessionManager.KEY_CITY);//get specific data
        Ousername.setText(dusernname);
        Ophone.setText(dphone);
        Oaddress.setText(daddress);

        Intent intent = getIntent();
        String ST = intent.getStringExtra("stname");
        String PT = intent.getStringExtra("price");
        String MS = intent.getStringExtra("servicename");
        Oservice_type.setText(ST);
        OPrice.setText("$"+PT);
        Oservicename.setText(MS);

        //Generating Random Number For Service Id:
        int min = 1;
        int max = 1000;
        //Generate random int value from 1 to 1000
        //System.out.println("Random value in int from "+min+" to "+max+ ":");
        int random_int = (int)Math.floor(Math.random()*(max-min+1)+min);
        //System.out.println(random_int);
        OService_id.setText(""+random_int);

        PlaceOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                progressBar.setVisibility(View.VISIBLE);
                //Toast.makeText(getApplicationContext(),"Order placing",Toast.LENGTH_SHORT).show();
                Order();
            }
        });
    }


    private Boolean validateMessage()
    {
        String val = Omessage.getText().toString();
        if(val.isEmpty())
        {
            Omessage.setError("Field cannot be empty");

            return false;
        }
        else if (val.length()>=101)
        {
            Omessage.setError("Message is too long");
            progressBar.setVisibility(View.GONE);
            return false;
        }
        else {
            Omessage.setError(null);
            //Omessage.setErrorEnabled(false);//to remove error sign
            return true;
        }
    }

    private void Order()
    {

        if(!validateMessage()){
            return;
        }//if any error found then return to validate function aggain.
        String username = Ousername.getText().toString().trim();
        String phone = Ophone.getText().toString().trim();
        String city = Oaddress.getText().toString().trim();
        String service_type = Oservice_type.getText().toString().trim();
        String service_id = OService_id.getText().toString().trim();
        String message =Omessage.getText().toString().trim();

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O)
        {
            NotificationChannel channel = new NotificationChannel("n","n",NotificationManager.IMPORTANCE_DEFAULT);
            NotificationManager manager = getSystemService(NotificationManager.class);
            manager.createNotificationChannel(channel);
        }
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this,"n")
                .setContentTitle("Your order is placed")
                .setSmallIcon(R.drawable.ic_baseline_notifications_24)
                .setAutoCancel(true)
                .setContentText("Thank You");
        NotificationManagerCompat managerCompat = NotificationManagerCompat.from(this);
        managerCompat.notify(999,builder.build());



        OrderHelperClass orderHelperClass = new OrderHelperClass(username,phone,city,service_type,service_id,message);
        reference.child(service_id).setValue(orderHelperClass);
        Toast.makeText(getApplicationContext(),"Your Order Is Placed\n We Will Call Or Notify You",Toast.LENGTH_LONG).show();

        Intent intent = new Intent(getApplicationContext(),Order_Conformation.class);
        //progressBar.setVisibility(View.GONE);
        finish();
        startActivity(intent);


    }
}
package com.example.backbenchers;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationManagerCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class UserOrderDetails extends AppCompatActivity {

    TextView Nodata;
    Button delete;
    RecyclerView recyclerView;
    ArrayList<OrderHelperClass>mlist;
    AdapterClass adapterClass;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);//for full screen
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_user_order_details);

        //hooks

        recyclerView = findViewById(R.id.rid);

        delete = findViewById(R.id.deletebutton);
        Intent intent = getIntent();
        String Username = intent.getStringExtra("Username");

        SessionManager sessionManager = new SessionManager(this,SessionManager.SESSION_USERSESSION);
        HashMap<String,String> userDetails =  sessionManager.getUserDetailFromSession();
        String name = userDetails.get(SessionManager.KEY_USERNAME);
        
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference().child("orders");

        recyclerView.setAdapter(adapterClass);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        mlist=new ArrayList<>();
        adapterClass =  new AdapterClass(this,mlist);
        recyclerView.setAdapter(adapterClass);


        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot)
            {
                    //Nodata.setText("TOTAL ORDER LISTS");
                    for (DataSnapshot snapshot : dataSnapshot.getChildren())
                    {
                        OrderHelperClass orderHelperClass = snapshot.getValue(OrderHelperClass.class);
                        String text = orderHelperClass.getUsername();
                        String order_id = orderHelperClass.getService_id();
                        if (text.equals(Username))
                        {
                            //User.setText(servicetyp);
                            mlist.add(orderHelperClass);

                            delete.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {

                                    DatabaseReference delete = FirebaseDatabase.getInstance().getReference("orders").child(order_id);
                                    delete.removeValue();
                                    Toast.makeText(getApplicationContext(),"Histroy Clear",Toast.LENGTH_SHORT).show();
                                    mlist.clear();

                                    //Intent intent = new Intent(getApplicationContext(),DeleteAllOrderHistroy.class);
                                    //intent.putExtra("Username",Username);
                                    //startActivity(intent);
                                }
                            });



                           // reference.removeValue();

                        }


                    }

                    adapterClass.notifyDataSetChanged();



            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
              Toast.makeText(getApplicationContext(),"Error",Toast.LENGTH_SHORT).show();
            }
        });

    }
}
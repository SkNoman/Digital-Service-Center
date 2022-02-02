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

public class Users extends AppCompatActivity {

    TextView Nodata;
    RecyclerView recyclerView;
    ArrayList<UserHelperClass>mlist;
    AdapterClass2 adapterClass2;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);//for full screen
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_users);

        //hooks

        recyclerView = findViewById(R.id.rid);

        Intent intent = getIntent();
        String Username = intent.getStringExtra("Username");

        SessionManager sessionManager = new SessionManager(this,SessionManager.SESSION_USERSESSION);
        HashMap<String,String> userDetails =  sessionManager.getUserDetailFromSession();
        String name = userDetails.get(SessionManager.KEY_USERNAME);

        DatabaseReference reference = FirebaseDatabase.getInstance().getReference().child("users");

        recyclerView.setAdapter(adapterClass2);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        mlist=new ArrayList<>();
        //adapterClass2 =  new AdapterClass(this,mlist);
        adapterClass2 = new AdapterClass2(this,mlist);
        recyclerView.setAdapter(adapterClass2);


        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot)
            {
                //Nodata.setText("TOTAL ORDER LISTS");
                for (DataSnapshot snapshot : dataSnapshot.getChildren())
                {
                    UserHelperClass userHelperClass = snapshot.getValue(UserHelperClass.class);
                    String text = userHelperClass.getUsername();
                   // String order_id = userHelperClass.getService_id();

                        //User.setText(servicetyp);
                        mlist.add(userHelperClass);

                }

                adapterClass2.notifyDataSetChanged();



            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(getApplicationContext(),"Error",Toast.LENGTH_SHORT).show();
            }
        });

    }
}
package com.example.backbenchers;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.SharedPreferences;
import android.icu.text.CaseMap;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.widget.Toolbar;


import com.google.android.material.navigation.NavigationView;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.HashMap;

public class Dashboard extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    RecyclerView featuredRecyler;
    RecyclerView.Adapter adapter;

    DatabaseReference reference;

    DrawerLayout drawerLayout;
    NavigationView navigationView;

    Toolbar toolbar;
    ImageButton cook,tailor,homeautomation,people,shift,ac,easy,doctor,laundry,paint,construction,carrental;
    ImageButton Call,Location,House,Office,Hospital,Factory;
    TextView header_name;
    String session_username;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);//for full screen
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_dashboard);



        SessionManager sessionManager = new SessionManager(this,SessionManager.SESSION_USERSESSION); // user session
        HashMap<String,String> userDetails =  sessionManager.getUserDetailFromSession();
        String name = userDetails.get(SessionManager.KEY_NAME);
        session_username = userDetails.get(SessionManager.KEY_USERNAME);

        setTitle("Welcome "+name);

        //Hooks

        header_name = findViewById(R.id.user_name_header);
       // header_name.setText(name);


        Hospital = findViewById(R.id.hospital);
        Hospital.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(),"WE HAVE SERVICES FOR HOSPITALS/CLINICS",Toast.LENGTH_SHORT).show();
            }
        });

        Office = findViewById(R.id.office);
        Office.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(),"WE HAVE SERVICES FOR OFFICE",Toast.LENGTH_SHORT).show();
            }
        });

        House = findViewById(R.id.house);
        House.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(),"WE HAVE SERVICES FOR YOUR HOME",Toast.LENGTH_SHORT).show();
            }
        });

        Factory = findViewById(R.id.factory);
        Factory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(),"WE HAVE SERVICES FOR FACTORY",Toast.LENGTH_SHORT).show();
            }
        });

        Call = findViewById(R.id.call_btn);
        Call.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(Intent.ACTION_DIAL);
                intent.setData(Uri.parse("tel:01302762781"));
                startActivity(intent);
            }
        });

        Location = findViewById(R.id.navibate_btn);
        Location.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_VIEW);
                //intent.setData(Uri.parse("geo:19.076,72,8777"));
                intent.setData(Uri.parse("geo:23.754974320217094, 90.37639954919783"));
                startActivity(intent);
            }
        });

       // showuser = findViewById(R.id.user_name_header);
       // showuser.setText(name);


        carrental = findViewById(R.id.car_btn);
        carrental.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openCarRental();
            }
        });
        construction = findViewById(R.id.construction_btn);
        construction.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openConstruction();
            }
        });
        cook = findViewById(R.id.cook_btn);
        cook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                opencook();
                //finish();
            }
        });
        easy = findViewById(R.id.easy_btn);
        easy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openclean();
            }
        });
        tailor = findViewById(R.id.tailor_btn);
        tailor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                opentailor();
            }
        });
        homeautomation = findViewById(R.id.automation_btn);
        homeautomation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openautomation();
            }
        });
        people = findViewById(R.id.people_btn);
        people.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openpeople();
            }
        });
        
        shift = findViewById(R.id.shift_btn);
        shift.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openshift();
            }
        });
        ac = findViewById(R.id.ac_btn);
        ac.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                openac();
            }
        });
        laundry = findViewById(R.id.laundery_btn);
        laundry.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openlaundry();
            }
        });
        doctor = findViewById(R.id.doctor_btn);
        doctor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                opendoctor();
            }
        });
        paint = findViewById(R.id.paint_btn);
        paint.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openpaint();
            }
        });

        featuredRecyler = findViewById(R.id.featured_recylerview);
        drawerLayout=findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.nav_view);
        toolbar=findViewById(R.id.toolbarid);


        //toolbar
        setSupportActionBar(toolbar);
        navigationView.bringToFront();
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this,drawerLayout,toolbar,R.string.navigation_drawer_open,R.string.navigation_drawer_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();


        //navigation
        navigationView.setNavigationItemSelectedListener(this);
        navigationView.setCheckedItem(R.id.nav_home);

        featuredRecyler();





    }





    private void openCarRental() {
        Intent intent = new Intent(getApplicationContext(),CarRental.class);
        startActivity(intent);
    }
    private void openConstruction() {
        Intent intent = new Intent(getApplicationContext(),Construction.class);
        startActivity(intent);
    }
    private void openpaint() {
        Intent intent = new Intent(getApplicationContext(),Paint.class);
        startActivity(intent);
    }

    private void openshift() {
        Intent intent = new Intent(getApplicationContext(),Shift.class);
        startActivity(intent);
    }

    private void opentailor() {
        Intent intent = new Intent(getApplicationContext(),Tailor.class);
        startActivity(intent);
    }

    private void openautomation() {
        Intent intent = new Intent(getApplicationContext(),Automation.class);
        startActivity(intent);
    }

    private void openac() {
        Intent intent = new Intent(getApplicationContext(),Aircondition.class);
        startActivity(intent);
    }

    private void openpeople() {
        Intent intent = new Intent(getApplicationContext(),Manpower.class);
        startActivity(intent);
    }

    private void openlaundry() {
        Intent intent = new Intent(getApplicationContext(),Laundry.class);
        startActivity(intent);
    }

    private void openclean() {
        Intent intent = new Intent(getApplicationContext(),Clean.class);
        startActivity(intent);
    }

    private void opendoctor() {
        Intent intent = new Intent(getApplicationContext(),Medical.class);
        startActivity(intent);

    }

    private void opencook() {
        Intent intent = new Intent(getApplicationContext(),Cook.class);
        startActivity(intent);
    }


    @Override
    public void onBackPressed() {

        if (drawerLayout.isDrawerOpen(GravityCompat.START))
        {
            drawerLayout.closeDrawer(GravityCompat.START);
        }
        else
        {
            super.onBackPressed();
        }

    }

    private void featuredRecyler()
    {
        featuredRecyler.setHasFixedSize(true);
        featuredRecyler.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false));
        ArrayList<FeaturedHelperClass> featuredLocations = new ArrayList<>();

        featuredLocations.add(new FeaturedHelperClass(R.drawable.smarthome,"Home Automation","We can automate your home"));// manual addition of recyler card view
        featuredLocations.add(new FeaturedHelperClass(R.drawable.plumber1,"Plumber","We always serve you skilled person"));
        featuredLocations.add(new FeaturedHelperClass(R.drawable.acmechanic,"Ac Mechanic","Our skilled mechanic can deal with any problem with your Ac"));

        adapter = new FeaturedAdapter(featuredLocations);
        featuredRecyler.setAdapter(adapter);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId())
        {
            case R.id.nav_logout:
                logout();
                finish();
                break;


            case R.id.nav_profile:

                Intent intent1 = new Intent(getApplicationContext(),Profile.class);
                startActivity(intent1);
                break;


            case R.id.nav_login:
                Intent intent3 = new Intent(getApplicationContext(),Login.class);
                startActivity(intent3);
                finish();
                break;

            case R.id.nav_call:
                Intent intent4= new Intent(Intent.ACTION_DIAL);
                intent4.setData(Uri.parse("tel:01867577624"));
                startActivity(intent4);
                break;

            case  R.id.nav_message:
                Intent intent5 = new Intent(getApplicationContext(),Feedback.class);
                startActivity(intent5);
                break;
            case  R.id.nav_rateus:
                Intent intent = new Intent(getApplicationContext(),RateUs.class);
                startActivity(intent);
                break;
            case  R.id.nav_share:
                Toast.makeText(Dashboard.this,"Not implemented yet", Toast.LENGTH_LONG).show();
                break;
        }
        return true;
    }//for click able navigation item

    private void logout() {

        FirebaseDatabase.getInstance().getReference("users").child(session_username).child("device_token").removeValue();
        SessionManager sessionManager = new SessionManager(this,SessionManager.SESSION_REMEMBERME);
        sessionManager.logoutUserSession();
        Intent intent = new Intent(getApplicationContext(),Login.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK|Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
    }


}
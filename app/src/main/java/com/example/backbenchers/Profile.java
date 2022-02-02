package com.example.backbenchers;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

public class Profile extends AppCompatActivity
{

    FirebaseDatabase firebaseDatabase;
    DatabaseReference reference;
    EditText Name,Username,Email,Phone,City,Password;
    Button Update_Data,Delete_Data,Total_Service;
    String dname,dusername,demail,dphone,daddress,dpassword;
    AlertDialog.Builder builder;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);//for full screen
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_profile);

        reference = FirebaseDatabase.getInstance().getReference("users");
        //hooks
        Name = findViewById(R.id.user_name);
        Username = findViewById(R.id.user_username);
        Email = findViewById(R.id.user_email);
        Phone = findViewById(R.id.user_phone);
        City = findViewById(R.id.user_city);
        Password = findViewById(R.id.user_password);
        Update_Data = findViewById(R.id.update);
        Delete_Data = findViewById(R.id.profile_delete);
        Total_Service = findViewById(R.id.total_service);



        //Get user data
        SessionManager sessionManager = new SessionManager(this,SessionManager.SESSION_USERSESSION);//get data from db
        HashMap<String,String> userDetails =  sessionManager.getUserDetailFromSession();
         dname = userDetails.get(SessionManager.KEY_NAME);//get specific data
         dusername = userDetails.get(SessionManager.KEY_USERNAME);//get specific data
         dphone = userDetails.get(SessionManager.KEY_PHONE);//get specific data
         daddress = userDetails.get(SessionManager.KEY_CITY);//get specific data
         demail = userDetails.get(SessionManager.KEY_EMAIL);//get specific data
         dpassword = userDetails.get(SessionManager.KEY_PASSWORD);


        Name.setText(dname);
        Username.setText(dusername);
        Email.setText(demail);
        Phone.setText(daddress);
        City.setText(dphone);
        Password.setText(dpassword);

        Total_Service.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                // Toast.makeText(getApplicationContext(),"Not Implemented Yet",Toast.LENGTH_SHORT).show();
                // OrderDetails();
                Intent intent = new Intent(getApplicationContext(),UserOrderDetails.class);
                intent.putExtra("Username",dusername);
                startActivity(intent);

            }
        });

        builder = new AlertDialog.Builder(this);



    }

    //validation
    private Boolean validateName()
    {
        String val = Name.getText().toString();
        if(val.isEmpty())
        {
            Name.setError("Field cannot be empty");

            return false;
        }
        else {
            Name.setError(null);
             //Name.setError(false);//to remove error sign
            return false;
        }
    }
    private Boolean validateUsername()
    {
        String val = Username.getText().toString();
        String noWhiteSpace= "\\A\\w{4,20}\\z";
        if(val.isEmpty())
        {
            Username.setError("Field cannot be empty");
            return false;
        }
        else if (val.length()>=15)
        {
            Username.setError("Username is too long");

            return false;
        }
        else if(!val.matches(noWhiteSpace))
        {
            Username.setError("White spaces are not allowed");

            return false;
        }
        else {
            Username.setError(null);
           // regfullname.setErrorEnabled(false);//to remove error sign
            return true;
        }
    }

    private Boolean validateEmail()
    {
        String val = Email.getText().toString();
        String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";

        if (val.isEmpty()) {
            Email.setError("Field cannot be empty");

            return false;
        } else if (!val.matches(emailPattern)) {
            Email.setError("Invalid email address");

            return false;
        } else {
            Email.setError(null);
            //regemail.setErrorEnabled(false);
            return true;
        }
    }

    private Boolean validatePhone()
    {
        String val = Phone.getText().toString();
        if(val.isEmpty())
        {
            Phone.setError("Field cannot be empty");

            return false;
        }
        else {
            Phone.setError(null);
            //regphone.setErrorEnabled(false);//to remove error sign
            return true;
        }
    }

    private Boolean validateCity()
    {
        String val = City.getText().toString();
        if(val.isEmpty())
        {
            City.setError("Field cannot be empty");

            return false;
        }
        else {
            City.setError(null);
           // regcity.setErrorEnabled(false);//to remove error sign
            return true;
        }
    }

    private Boolean validatePassword()
    {
        String val = Password.getText().toString();

        String passwordVal = "^" +
                //"(?=.*[0-9])" +         //at least 1 digit
                //"(?=.*[a-z])" +         //at least 1 lower case letter
                //"(?=.*[A-Z])" +         //at least 1 upper case letter
                "(?=.*[a-zA-Z])" +      //any letter
                "(?=.*[@#$%^&+=])" +    //at least 1 special character
                "(?=\\S+$)" +           //no white spaces
                ".{4,}" +               //at least 4 characters
                "$";

        if(val.length()>8)
        {
            Password.setError("Not more than 8 character");
            return false;
        }
        if (val.isEmpty()) {
            Password.setError("Field cannot be empty");

            return false;
        } else if (!val.matches(passwordVal)) {
            Password.setError("Password is too weak");

            return false;
        } else {
            Password.setError(null);
            //regpassword.setErrorEnabled(false);
            return true;
        }
    }



    //done validation


    private boolean isPhoneUpdated()
    {
        if (!dphone.equals(Phone.getText().toString()))
        {
            //reference.child(dusername).child("phone").setValue(Phone.getText().toString());
            dphone = Phone.getText().toString();
            return true;
        }
        else
        {
            return  false;
        }
    }

    private boolean isUsernameChanged()
    {
        if (!dusername.equals(Username.getText().toString()))
        {
            //reference.child(dusername).child("username").setValue(Username.getText().toString());
            //dusername = Username.getText().toString();
            return true;
        }
        else
        {
            return  false;
        }
    }

    private boolean isPasswordChanged()
    {
        if (!dpassword.equals(Password.getText().toString()))
        {
           // reference.child(dusername).child("pas").setValue(Password.getText().toString());
          // dpassword = Password.getText().toString();
            return true;
        }
        else
        {
            return  false;
        }
    }

    private boolean isCityChanged()
    {
        if (!daddress.equals(City.getText().toString()))
        {
           // reference.child(dusername).child("city").setValue(City.getText().toString());
            //daddress = City.getText().toString();
            return true;
        }
        else
        {
            return  false;
        }
    }

    private boolean isNameChanged()
    {
        if (!dname.equals(Name.getText().toString()))
        {
            //reference.child(dusername).child("name").setValue(Name.getText().toString());
            //dname = Name.getText().toString();
            return true;
        }
        else
        {
            return  false;
        }
    }
    private boolean isEmailChanged()
    {
        if (!dname.equals(Email.getText().toString()))
        {
            //reference.child(dusername).child("email").setValue(Email.getText().toString());
            //demail = Email.getText().toString();
            return true;
        }
        else
        {
            return  false;
        }
    }

    public void update(View view)
    {


        /*if(!validateName() | !validatePassword() | !validatePhone() | !validateEmail() | !validateUsername() | !validateCity())
        {
            return;
        }
        else
        { */




       if(isNameChanged() || isCityChanged() || isPasswordChanged() || isUsernameChanged() || isPhoneUpdated() || isEmailChanged()  )
        {

            String valn = Name.getText().toString();
            if(valn.isEmpty())
            {
                Name.setError("Field cannot be empty");
            }
            else {
                Name.setError(null);
                reference.child(dusername).child("name").setValue(Name.getText().toString());
               // Toast.makeText(this,"Updated",Toast.LENGTH_SHORT).show();
            }
            //username done
            String valu = Username.getText().toString();
            String noWhiteSpace= "\\A\\w{4,20}\\z";
            if(valu.isEmpty())
            {
                Username.setError("Field cannot be empty");
            }
            else if (valu.length()>=15)
            {
                Username.setError("Username is too long");

            }
            else if(!valu.matches(noWhiteSpace))
            {
                Username.setError("White spaces are not allowed");


            }
            else {
                Username.setError(null);
                reference.child(dusername).child("username").setValue(Username.getText().toString());
                //Toast.makeText(this,"Updated",Toast.LENGTH_SHORT).show();

            }
            //phone done
            String valph = Phone.getText().toString();
            if(valph.isEmpty())
            {
                Phone.setError("Field cannot be empty");

            }
            else {
                Phone.setError(null);
                reference.child(dusername).child("phone").setValue(Phone.getText().toString());
            }
            //city done
            String valc = City.getText().toString();
            if(valc.isEmpty())
            {
                City.setError("Field cannot be empty");


            }
            else {
                City.setError(null);
                reference.child(dusername).child("city").setValue(City.getText().toString());
                //Toast.makeText(this,"Updated to see changes you have to login again.",Toast.LENGTH_SHORT).show();

            }

            //password done
            String valp = Password.getText().toString();

            String passwordVal = "^" +
                    //"(?=.*[0-9])" +         //at least 1 digit
                    //"(?=.*[a-z])" +         //at least 1 lower case letter
                    //"(?=.*[A-Z])" +         //at least 1 upper case letter
                    "(?=.*[a-zA-Z])" +      //any letter
                    "(?=.*[@#$%^&+=])" +    //at least 1 special character
                    "(?=\\S+$)" +           //no white spaces
                    ".{4,}" +               //at least 4 characters
                    "$";

            if(valp.length()>8)
            {
                Password.setError("Not more than 8 character");

            }
            if (valp.isEmpty()) {
                Password.setError("Field cannot be empty");


            } else if (!valp.matches(passwordVal)) {
                Password.setError("Password is too weak");


            } else {
                Password.setError(null);
               // Toast.makeText(this,"Updated",Toast.LENGTH_SHORT).show();

            }

            //email done
            String val = Email.getText().toString();
            String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";

            if (val.isEmpty()) {
                Email.setError("Field cannot be empty");


            } else if (!val.matches(emailPattern)) {
                Email.setError("Invalid email address");


            } else {
                Email.setError(null);
                reference.child(dusername).child("email").setValue(Email.getText().toString());
               // Toast.makeText(this,"Updated",Toast.LENGTH_SHORT).show();
            }

            Toast.makeText(this,"Updated",Toast.LENGTH_SHORT).show();
            Toast.makeText(this,"Login again to see changes",Toast.LENGTH_SHORT).show();
          //  SessionManager sessionManager = new SessionManager(Profile.this,SessionManager.SESSION_USERSESSION);
           // sessionManager.createLoginSession(dname,dusername,demail,daddress,dphone,dpassword);



        }

        else
        {
            Toast.makeText(this,"Data is same, cannot be updated",Toast.LENGTH_SHORT).show();
        }


    }


    public void delete(View view)
    {
//Toast.makeText(getApplicationContext(),"Under Development",Toast.LENGTH_SHORT).show();
        builder.setMessage("Are you sure") .setTitle("Alert");

        //Setting message manually and performing action on button click
        builder.setMessage("Do you want to delete your account?")
                .setCancelable(false)
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {

                        reference.child(dusername).removeValue();


                        Toast.makeText(getApplicationContext(),"Your Account Is Deleted",
                                Toast.LENGTH_SHORT).show();
                        logout();
                    }
                })
                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        //  Action for 'NO' Button
                        dialog.cancel();
                        Toast.makeText(getApplicationContext(),"Thank You!",
                                Toast.LENGTH_SHORT).show();
                    }
                });
        //Creating dialog box
        AlertDialog alert = builder.create();
        //Setting the title manually
        alert.setTitle("AlertDialog");
        alert.show();


    }

    private void logout() {

        SessionManager sessionManager = new SessionManager(this,SessionManager.SESSION_REMEMBERME);
        sessionManager.logoutUserSession();
        Intent intent = new Intent(getApplicationContext(),Login.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK|Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
    }


}
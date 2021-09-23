package com.example.mdnahidulislam.demoproject;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class signup extends AppCompatActivity implements View.OnClickListener{
private EditText name,username,email,password;
    private Button signup;
    UserDetails userDetails;
    DbHelper dbhelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        signup=(Button)findViewById(R.id.signupid);
        name=(EditText)findViewById(R.id.nameid);
        email=(EditText)findViewById(R.id.emailid) ;
        username=(EditText)findViewById(R.id.usernameid);
        password=(EditText)findViewById(R.id.passwordid);
userDetails=new UserDetails();
dbhelper=new DbHelper(this);
        signup.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

        String name1=name.getText().toString();
        String username1=username.getText().toString();
        String email1=email.getText().toString();
        String password1=password.getText().toString();
        userDetails.setName(name1);
        userDetails.setUsername(username1);
        userDetails.setEmail(email1);
        userDetails.setPassword(password1);

        if(name1.equals("")||username1.equals("")||email1.equals("")||password1.equals(""))
        {
            Toast.makeText(getApplicationContext(),"Fill up all info",Toast.LENGTH_LONG).show();
        }
        else{

            long rowid=dbhelper.insertData(userDetails);

            if(rowid>0)
        {
            Toast.makeText(getApplicationContext(),"Row "+rowid+" is inserted succesfully",Toast.LENGTH_LONG).show();
        }
        else
        {
            Toast.makeText(getApplicationContext(),"Row insertion failed",Toast.LENGTH_LONG).show();
        }
        }
    }

    }


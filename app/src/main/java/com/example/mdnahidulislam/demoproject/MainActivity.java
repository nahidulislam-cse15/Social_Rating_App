package com.example.mdnahidulislam.demoproject;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private Button signin,signup;
    private EditText username,password;
DbHelper dbhelper;
LogUser loguser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        dbhelper=new DbHelper(this);
       SQLiteDatabase db=dbhelper.getWritableDatabase();
       signin=(Button)findViewById(R.id.signinid);
        signup=(Button)findViewById(R.id.signupid);
        username=(EditText)findViewById(R.id.usernameid);
        password=(EditText)findViewById(R.id.passwordid);
UserDetails userDetails=new UserDetails();
        signin.setOnClickListener(this);
        signup.setOnClickListener(this);
        loguser=new LogUser();


    }

    @Override


    public void onClick(View v) {
        String username1=username.getText().toString();
        String password1=password.getText().toString();
if(v.getId()==R.id.signinid)
{
    //UserDetails userDetails=new UserDetails();
    if(username1.equals("")||password1.equals("")) {
        Toast.makeText(getApplicationContext(), " Give USERNAME & PASSWORD", Toast.LENGTH_LONG).show();
    }

    Boolean result=dbhelper.findmatch(username1,password1);
    if(result==true)
    {
        String loguser1=username.getText().toString();
        dbhelper.deletedata();

    loguser.setLoguser(loguser1);

       long rowid2=dbhelper.insertData2(loguser);


    Intent inten=new Intent(MainActivity.this,Home.class);
        startActivity(inten);
    }
  else
    {
        Toast.makeText(getApplicationContext(),"USERNAME & PASSWORD NOT MATCH",Toast.LENGTH_LONG).show();
    }



}
else if(v.getId()==R.id.signupid)
{
    Intent intent=new Intent(MainActivity.this,signup.class);
    startActivity(intent);
}


    }

}
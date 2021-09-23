package com.example.mdnahidulislam.demoproject;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class RatingFriends extends AppCompatActivity implements View.OnClickListener {
    private TextView text;
    private EditText smile,anger,beauty,nature;
    private Button rating,cancel;
    String value;
 RatingDetails ratingDetails;
    DbHelper dbhelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rating_friends);
        text=(TextView)findViewById(R.id.textid);
        smile=(EditText)findViewById(R.id.smileid);
        anger=(EditText)findViewById(R.id.angerid);
        beauty=(EditText)findViewById(R.id.beautyid);
        nature=(EditText)findViewById(R.id.natureid);
        rating=(Button)findViewById(R.id.ratingid);
        cancel=(Button)findViewById(R.id.cancelbuttonid);
        ratingDetails=new RatingDetails();
        dbhelper=new DbHelper(this);
rating.setOnClickListener(this);
cancel.setOnClickListener(this);
        Bundle bundle=getIntent().getExtras();
        if(bundle!=null)
        {
            String tex=bundle.getString("tag");
            text.setText(tex);


        }
    }

    @Override
    public void onClick(View v) {
        if(v.getId()==R.id.ratingid)
        {
            value=text.getText().toString();

            String smile1=smile.getText().toString();
            String anger1=anger.getText().toString();
            String beauty1=beauty.getText().toString();
            String nature1=nature.getText().toString();
           ratingDetails.setSmile(smile1);
            ratingDetails.setAnger(anger1);
            ratingDetails.setBeauty(beauty1);
            ratingDetails.setNature(nature1);

            if(smile1.equals("")||anger1.equals("")||beauty1.equals("")||nature1.equals(""))
            {
                Toast.makeText(getApplicationContext(),"Fill up all info",Toast.LENGTH_LONG).show();
            }
            else{

                long rowid=dbhelper.insertData5(ratingDetails,value);

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
        if(v.getId()==R.id.cancelbuttonid)
        {
            Intent intent=new Intent(RatingFriends.this,Home.class);
            startActivity(intent);
        }
}}

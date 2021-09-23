package com.example.mdnahidulislam.demoproject;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class Find_Friends extends AppCompatActivity {
    private ListView listView;
    private DbHelper dbHelper;
    String value;
    LogUser logUser1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_find__friends);
        listView = (ListView) findViewById(R.id.listviweid);
        dbHelper = new DbHelper(this);
        logUser1=new LogUser();
        loaddata();
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.send, menu);

        return super.onCreateOptionsMenu(menu);
    }

    public void loaddata() {
        ArrayList<String> frnds = new ArrayList<>();
        Cursor cursor = dbHelper.showfrnd();
        if (cursor.getCount() == 0)

        {
            Toast.makeText(getApplicationContext(), "error", Toast.LENGTH_SHORT).show();
        } else {
            while (cursor.moveToNext())

            {
                frnds.add(cursor.getString(1));
               // frnds.add(cursor.getString(3));

            }
        }
        String frndarray[] = new String[frnds.size()];

        frndarray = frnds.toArray(frndarray);
        final ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, R.layout.list_item, R.id.textviewid, frnds);
        listView.setAdapter(adapter);

        final String[] finalFrndarray = frndarray;
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                value = finalFrndarray[position];
                Toast.makeText(Find_Friends.this, value, Toast.LENGTH_SHORT).show();
            }
        });
    }

    public boolean onOptionsItemSelected(MenuItem item) {
     if (item.getItemId() == R.id.sendid) {
         if(value!=null) {
             ArrayList<String> loguser = new ArrayList<>();
             Cursor cursor = dbHelper.getloggeduser();
             if (cursor.getCount() == 0)

             {
                 Toast.makeText(getApplicationContext(), "No user", Toast.LENGTH_SHORT).show();
             } else {
                 while (cursor.moveToNext())

                 {
                     loguser.add(cursor.getString(1));

                 }
             }
             String ar[] = new String[loguser.size()];

             ar = loguser.toArray(ar);
             String fr1 = ar[0];
             logUser1.setLoguser(fr1);

             long rowid3 = dbHelper.insertData3(logUser1, value);

             Toast.makeText(Find_Friends.this, "Send request succesfull", Toast.LENGTH_SHORT).show();

         }
     else
         {
             Toast.makeText(Find_Friends.this,"Select A Friend",Toast.LENGTH_SHORT).show();
         }}
        if(item.getItemId()==R.id.home1id)
        {
            Intent intent=new Intent(Find_Friends.this,Home.class);
            startActivity(intent);


        }
        return super.onOptionsItemSelected(item);
    }}
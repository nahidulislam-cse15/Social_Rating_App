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
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class Friends extends AppCompatActivity {
    private ListView listView;

    private DbHelper dbHelper;
    String value;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_friends);


        listView = (ListView) findViewById(R.id.listviweid2);
        dbHelper = new DbHelper(this);
        loaddata();
    }
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.rate, menu);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
       if(item.getItemId()==R.id.rateid)
       {
          if(value!=null) {
              Intent intent = new Intent(Friends.this, RatingFriends.class);
              intent.putExtra("tag", value);
              startActivity(intent);
          }else
          {
              Toast.makeText(Friends.this,"Select A Friend",Toast.LENGTH_SHORT).show();
          }

       }
        if(item.getItemId()==R.id.homeid)
        {
            Intent intent=new Intent(Friends.this,Home.class);
            //intent.putExtra("tag",value);
            startActivity(intent);


        }
        if(item.getItemId()==R.id.unfrndid)
        {
           dbHelper.deletedatafrnd1(value);
            dbHelper.deletedatafrnd2(value);
        }


        return super.onOptionsItemSelected(item);
    }

    public void loaddata()
    {
        ArrayList<String> frnds = new ArrayList<>();
        Cursor cursor = dbHelper.showfrnd2();
        if (cursor.getCount() == 0)

        {
            Toast.makeText(getApplicationContext(), "NO FRIENDS YET", Toast.LENGTH_SHORT).show();
        } else {
            while (cursor.moveToNext())

            {
                frnds.add(cursor.getString(2));

            }
        }
        String frndarray[] = new String[frnds.size()];

        frndarray = frnds.toArray(frndarray);
        final ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, R.layout.list_item,R.id.textviewid, frnds);
        listView.setAdapter(adapter);

        final String[] finalFrndarray = frndarray;
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                value = finalFrndarray[position];
                Toast.makeText(Friends.this, value, Toast.LENGTH_SHORT).show();
            }
        });
    }
    }


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

public class RequestedFrnd extends AppCompatActivity {
    private ListView listView1;
    private DbHelper dbHelper;
    String value;
    LogUser logUser1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_requested_frnd);
        listView1 = (ListView) findViewById(R.id.listviweid1);
        dbHelper = new DbHelper(this);
        logUser1=new LogUser();
        loaddata1();
    }
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater=getMenuInflater();
        inflater.inflate(R.menu.confirm,menu);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId()==R.id.acceptid)
        {
            ArrayList<String> loguser= new ArrayList<>();
            Cursor cursor = dbHelper.getloggeduser();
            if (cursor.getCount() == 0)

            {
                Toast.makeText(getApplicationContext(), "No user", Toast.LENGTH_SHORT).show();
            }
            else {
                while (cursor.moveToNext())

                {
                    loguser.add(cursor.getString(1));

                }
            }
            String ar[] = new String[loguser.size()];

            ar = loguser.toArray(ar);
            String fr1=ar[0];
            logUser1.setLoguser(fr1);
if(value!=null){
            long rowid4=dbHelper.insertData4(logUser1,value);
            if(rowid4!=-1)
            {
                Toast.makeText(RequestedFrnd.this, "Accepted ", Toast.LENGTH_SHORT).show();

            }

        else
            {
                Toast.makeText(RequestedFrnd.this, "Error", Toast.LENGTH_SHORT).show();
            }
           dbHelper.deletedatafrnd(value);

        }else
{
                 Toast.makeText(RequestedFrnd.this, "Select A friend", Toast.LENGTH_SHORT).show();
}
        }
        if(item.getItemId()==R.id.cancelid)
        {
    if(value!=null)
    {            dbHelper.deletedatafrnd(value);

        Toast.makeText(RequestedFrnd.this, "CANCEL REQUES succesfully", Toast.LENGTH_SHORT).show();
    }else
    {
        Toast.makeText(RequestedFrnd.this, "Select A friend", Toast.LENGTH_SHORT).show();
    }

        }
        if(item.getItemId()==R.id.nav_home)
        { Intent intent=new Intent(RequestedFrnd.this,Home.class);
            startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }

    public void loaddata1()
    {
        ArrayList<String> frnd= new ArrayList<>();
        Cursor cursor = dbHelper.showfrnd1();
        if (cursor.getCount() == 0)

        {
            Toast.makeText(getApplicationContext(), "No Friends Request", Toast.LENGTH_SHORT).show();
        } else {
            while (cursor.moveToNext())

            {
                frnd.add(cursor.getString(1));

            }
        }
        final ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, R.layout.list_item, R.id.textviewid, frnd);
        listView1.setAdapter(adapter);
        String frndarray[] = new String[frnd.size()];

        frndarray = frnd.toArray(frndarray);
        final String[] finalFrndarray = frndarray;
        listView1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                value =finalFrndarray[position]; ;
                Toast.makeText(RequestedFrnd.this, value, Toast.LENGTH_SHORT).show();
            }
        });
    }
}

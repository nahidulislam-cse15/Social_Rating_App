package com.example.mdnahidulislam.demoproject;

import android.content.Intent;
import android.database.Cursor;
import android.media.Rating;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Currency;
import java.util.List;

public class Home extends AppCompatActivity {
DbHelper dbHelper;
ArrayList<Post> postlist;
ListView listView;
Post post;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        dbHelper=new DbHelper(this);
        listView=(ListView)findViewById(R.id.listview3);
        postlist=new ArrayList<>();
        Cursor cursor=dbHelper.getpost();
        if(cursor.getCount()==0)
        {
            Toast.makeText(getApplicationContext(), "No One Rate you", Toast.LENGTH_SHORT).show();
        }
        else
        {
            while (cursor.moveToNext())
            {
                post=new Post(cursor.getString(2),cursor.getString(3),cursor.getString(4),cursor.getString(5));
            postlist.add(post);
            }

            CustomAdapter customAdapter=new CustomAdapter(this,R.layout.sample_view,postlist);
            listView.setAdapter(customAdapter);
        }

    }
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater=getMenuInflater();
        inflater.inflate(R.menu.navgation_menu,menu);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId()==R.id.nav_frnd)
        {
            Intent intent=new Intent(Home.this,Friends.class);
            startActivity(intent);

        }
        if(item.getItemId()==R.id.nav_ffrnd)
        {
            Intent intent=new Intent(Home.this,Find_Friends.class);
            startActivity(intent);

        }
        if(item.getItemId()==R.id.nav_set)
        {
            Intent intent=new Intent(Home.this,Settings.class);
            startActivity(intent);

        }
        if(item.getItemId()==R.id.nav_msg)
        {
            Intent intent=new Intent(Home.this,Message.class);
            startActivity(intent);

        }
        if(item.getItemId()==R.id.nav_req)
        {
            Intent intent=new Intent(Home.this,RequestedFrnd.class);
            startActivity(intent);
        }
        if(item.getItemId()==R.id.nav_logout)
        {
            Intent intent=new Intent(Home.this,MainActivity.class);
            startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }
}

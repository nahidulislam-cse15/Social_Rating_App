package com.example.mdnahidulislam.demoproject;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class CustomAdapter extends ArrayAdapter<Post> {
    private LayoutInflater inflater;
    private ArrayList<Post> posts;
private Context context;
private int resourceid;

    public CustomAdapter(Context context,int textViewResourceId, ArrayList<Post> posts) {
        super(context,textViewResourceId,posts);
        this.posts = posts;
        inflater=(LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        resourceid=textViewResourceId;
    }


    @NonNull
    @Override
    public View getView(int position, View convertView,ViewGroup parent) {
        convertView= inflater.inflate(R.layout.sample_view,parent,false);
       Post post=posts.get(position);
        if(post!=null) {


            TextView smile = (TextView) convertView.findViewById(R.id.smileid);
            TextView anger = (TextView) convertView.findViewById(R.id.angerid);
            TextView beauty = (TextView) convertView.findViewById(R.id.beautyid);
            TextView nature = (TextView) convertView.findViewById(R.id.natureid);

        if(smile!=null){
            smile.setText(post.getSmile());
        }
        if(anger!=null){
            anger.setText(post.getAnger());
        }
        if(beauty!=null){
            beauty.setText(post.getBeauty());
        }
        if(nature!=null){
            nature.setText(post.getNature());
        }
        }

        return convertView;

}}

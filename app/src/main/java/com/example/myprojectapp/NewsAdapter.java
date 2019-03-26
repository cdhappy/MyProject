package com.example.myprojectapp;

import android.content.Context;
import android.media.Image;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;


public class NewsAdapter extends ArrayAdapter<News> {
    private int resourceId;
    public NewsAdapter(Context context, int textViewResourceId, List<News> objects){
        super(context,textViewResourceId,objects);
        resourceId=textViewResourceId;
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent){
        News news=getItem(position);           //获取当前项的实例
        View view= LayoutInflater.from(getContext()).inflate(resourceId,parent,false);

        ImageView newsImage=(ImageView)view.findViewById(R.id.news_image);
        TextView newsTitle=(TextView) view.findViewById(R.id.news_title);
        newsImage.setImageResource(news.getImageId());
        newsTitle.setText(news.getTitle());
        return view;
    }
}

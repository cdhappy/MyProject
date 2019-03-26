package com.example.myprojectapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.annotation.SuppressLint;
import android.view.View;
import android.content.Intent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class Notice extends AppCompatActivity {
    //private String[] data = {"1","2","3","4","5"};
    private List<News> newsList = new ArrayList<>();
    private NewsAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.notice);

        //initNews();
        adapter = new NewsAdapter(Notice.this, R.layout.news_item, newsList);
        ListView listview = (ListView) findViewById(R.id.list_view);
        listview.setAdapter(adapter);
        initNews();

        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterview, View view, int i, long l)
            {
                if(i == 0)
                {
                    Intent intent = new Intent(Notice.this , ReadNews.class);
                    startActivity(intent);
                }
                if(i == 1)
                {
                    Intent intent = new Intent(Notice.this , ReadNews1.class);
                    startActivity(intent);
                }
            }
        });

    }


    private void initNews() {
        for (int i = 0; i < 1; i++) {
            News news1 = new News("College Entrance Examination Reform", R.drawable.news1);
            newsList.add(news1);
            News news2 = new News("The University of Cambridge acknowledges China's National College Entrance Examination results", R.drawable.news3);
            newsList.add(news2);
            News news3 = new News("Encyclopedia of College Entrance Examination: What is the Filing Ratio?", R.drawable.main);
            newsList.add(news3);
            News news4 = new News("Encyclopedia of College Entrance Examination: What is Parallel Volunteer ",R.drawable.bg);
            newsList.add(news4);
            News news5 = new News("What are 985, 211 and double-top Universities?", R.drawable.main2);
            newsList.add(news5);
            News news6 = new News("The Key Points of Customs Declaration in 2019", R.drawable.news2);
            newsList.add(news6);
            News news7 = new News("Explanation of Professional Grade Differentiation", R.drawable.news4);
            newsList.add(news7);
            adapter.notifyDataSetChanged();
        }
    }
}


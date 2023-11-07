package com.example.weatherapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.Arrays;

public class ListActivity extends AppCompatActivity {
    ListView listView;
    String[] cities = {"Capital", "Rawson", "Rivadavia", "Chimbas", "Pocito"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        listView = findViewById(R.id.listView);
        ArrayList<String> cityList = new ArrayList<>(Arrays.asList(cities));
        MyAdapter adapter = new MyAdapter(this, cityList);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String cityName = (String) parent.getItemAtPosition(position);
                Intent intent = new Intent(ListActivity.this, DetailActivity.class);
                intent.putExtra("cityName", cityName);
                startActivity(intent);
            }
        });
    }
}

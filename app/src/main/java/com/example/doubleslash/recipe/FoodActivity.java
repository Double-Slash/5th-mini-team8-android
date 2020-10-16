package com.example.fooda;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class FoodActivity extends AppCompatActivity {

    RecyclerView recyclerView;

    String s1[], s2[], s3[], s4[], s5[], s6[];
    int images[] = {R.drawable.food1, R.drawable.food2, R.drawable.food3};

    // private RecyclerView recyclerView;
    // private MyAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food);
        //  init();
        recyclerView = findViewById(R.id.recyclerview);

        s1 = getResources().getStringArray(R.array.food);
        s2 = getResources().getStringArray(R.array.kcal);
        s3 = getResources().getStringArray(R.array.ng);
        s4 = getResources().getStringArray(R.array.ng1);
        s5 = getResources().getStringArray(R.array.nmg);
        s6 = getResources().getStringArray(R.array.ing);

        MyAdapter myAdapter = new MyAdapter(this, s1, s2, s3, s4, s5, s6, images);
        recyclerView.setAdapter(myAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }
}
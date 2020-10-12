package com.example.doubleslash.refrigerator;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.example.doubleslash.R;

public class AddIngredientActivity extends AppCompatActivity {

    private static final String Tag = "AddIngredientActivity";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_ingredient);

        Button search_button = findViewById(R.id.search_button);
        Button ingredient_addBt = findViewById(R.id.ingredient_addBt);
        Button slidebtn = findViewById(R.id.slidebtn);
        ConstraintLayout ref_container = findViewById(R.id.ref_container);

        search_button.setOnClickListener(view -> {
            //서버통신으로 String 보내고 리스트 받아오기

        });
        ingredient_addBt.setOnClickListener(view -> {

        });

        slidebtn.setOnClickListener(view -> {
            if(ref_container.getVisibility() == View.GONE){
                Log.e(Tag,"1");
                ref_container.setVisibility(View.VISIBLE);
            }
            else{
                Log.e(Tag,"2");
                ref_container.setVisibility(View.GONE);
            }
        });
    }

}
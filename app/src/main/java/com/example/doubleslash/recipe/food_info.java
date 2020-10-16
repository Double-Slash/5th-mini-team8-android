package com.example.fooda;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class food_info extends AppCompatActivity {

    ImageView mainImageView;
    TextView title, description,description1, description2, description3, ing;

    String data1, data2, data3, data4, data5, data6;
    int myImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food_info);

        mainImageView = findViewById(R.id.mainImageView);
        title = findViewById(R.id.title);
        description = findViewById(R.id.description);
        description1 = findViewById(R.id.description1);
        description2 = findViewById(R.id.description2);
        description3 = findViewById(R.id.description3);
        ing = findViewById(R.id.ing);

        getData();
        setData();
    }


    private void getData(){
        if(getIntent().hasExtra("myImage") && getIntent().hasExtra("data1") &&
        getIntent().hasExtra("data2")&& getIntent().hasExtra("data3")&& getIntent().hasExtra("data4")&& getIntent().hasExtra("data5")&& getIntent().hasExtra("data6")){
            data1 =getIntent().getStringExtra("data1");
            data2 =getIntent().getStringExtra("data2");
            data2 =getIntent().getStringExtra("data3");
            data2 =getIntent().getStringExtra("data4");
            data2 =getIntent().getStringExtra("data5");
            data2 =getIntent().getStringExtra("data6");
            myImage = getIntent().getIntExtra("myImage",1);
        }else {
            Toast.makeText(this, "no data.", Toast.LENGTH_LONG).show();
        }
    }

    private void setData(){
        title.setText(data1);
        description.setText(data2);
        description1.setText(data3);
        description2.setText(data4);
        description3.setText(data5);
        ing.setText(data6);
        mainImageView.setImageResource(myImage);
    }

    public void onButtonClick(View view) {
        Intent intent = new Intent(this, food_recipe.class);
        startActivity(intent);
    }
}
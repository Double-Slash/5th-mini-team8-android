package com.example.doubleslash;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.example.doubleslash.ui.login.LoginActivity;

public class StartActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);


        Button startBtn = (Button) findViewById(R.id.startBtn);
        Button signInBtn = (Button) findViewById(R.id.signInBtn);


        //시작하기
        startBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //냉장고관리 페이지 연결
                finish();
            }
        });

        //로그인 페이지 가기
        signInBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                startActivity(intent);
                StartActivity.this.finish();
            }
        });

    }
}


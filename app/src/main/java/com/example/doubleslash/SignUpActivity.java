package com.example.doubleslash;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class SignUpActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        final Button signUp = findViewById(R.id.signUp_completed);
        final EditText usernameEditText = findViewById(R.id.username);
        final EditText emailEditText = findViewById(R.id.email);
        final EditText idEditText = findViewById(R.id.ID);
        final EditText passwordEditText = findViewById(R.id.password);
        final EditText password2EditText = findViewById(R.id.password2);


        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), StartActivity.class);
                startActivity(intent);

                //다시 StartActivity로 돌아갔을 때 "로그인하기" 안뜨게

                finish();
            }
        });




    }

}

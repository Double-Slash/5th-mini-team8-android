package com.example.doubleslash;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.media.Image;
import android.os.Bundle;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.doubleslash.ui.login.LoginActivity;

public class StartActivity extends AppCompatActivity {

    private CustomDialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);

        Button startBtn = (Button) findViewById(R.id.startBtn);
        Button signInBtn = (Button) findViewById(R.id.signInBtn);
        TextView textView = findViewById(R.id.textView);
        String content = textView.getText().toString();
        SpannableString spannableString = new SpannableString(content);
        String word = "뭐먹지";
        int start = content.indexOf(word);
        int end = start + word.length();
        spannableString.setSpan(new ForegroundColorSpan(Color.parseColor("#ff4f48")), start, end, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        textView.setText(spannableString);


        //시작하기
        startBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    dialog = new CustomDialog(StartActivity.this, cancelListener);
                    dialog.createDialog(dialog);
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


    private View.OnClickListener cancelListener = new View.OnClickListener() {
        public void onClick(View v) {
            dialog.dismiss();
        }
    };


}


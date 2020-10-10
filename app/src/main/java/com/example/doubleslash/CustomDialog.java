package com.example.doubleslash;

import android.app.Dialog;
import android.content.Context;
import android.content.res.Resources;
import android.os.Build;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageButton;

import androidx.annotation.RequiresApi;

import java.util.Objects;

public class CustomDialog extends Dialog {

    private ImageButton deleteBtn;
    private View.OnClickListener cancelListener;


    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.custom_dialog);

        deleteBtn= (ImageButton)findViewById(R.id.deleteBtn);

        //다이얼로그 밖의 화면은 흐리게 만들어줌
        WindowManager.LayoutParams layoutParams = new WindowManager.LayoutParams();

        layoutParams.flags = WindowManager.LayoutParams.FLAG_DIM_BEHIND;
        layoutParams.dimAmount = 0.4f;

        //dp로 변경하기
        //Objects.requireNonNull(getWindow()).setAttributes(layoutParams);

        final int width = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,300, getContext().getResources().getDisplayMetrics());
        final int height = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,100, getContext().getResources().getDisplayMetrics());
        layoutParams.width = width;
        layoutParams.height = height;



        //클릭 리스너 셋팅 (클릭버튼이 동작하도록 만들어줌.)
        deleteBtn.setOnClickListener(cancelListener);

    }


    //생성자 생성
    public CustomDialog(Context context, View.OnClickListener cancelListener) {
        super(context);
        this.cancelListener = cancelListener;
    }

}
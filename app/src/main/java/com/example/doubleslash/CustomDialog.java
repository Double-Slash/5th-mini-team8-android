package com.example.doubleslash;

import android.app.Dialog;
import android.content.Context;
import android.content.res.Resources;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageButton;

public class CustomDialog extends Dialog {

    private ImageButton deleteBtn;
    private View.OnClickListener cancelListener;


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
        layoutParams.width = 762;
        layoutParams.height = 265;
        getWindow().setAttributes(layoutParams);





        //클릭 리스너 셋팅 (클릭버튼이 동작하도록 만들어줌.)
        deleteBtn.setOnClickListener(cancelListener);

    }


    //생성자 생성
    public CustomDialog(Context context, View.OnClickListener cancelListener) {
        super(context);
        this.cancelListener = cancelListener;
    }

}
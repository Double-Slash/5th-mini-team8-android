package com.example.doubleslash;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageButton;

import androidx.annotation.RequiresApi;

public class CustomDialog extends Dialog {

    private ImageButton deleteBtn;
    private View.OnClickListener cancelListener;
    private int width;
    private int height;

    public CustomDialog(Context context, View.OnClickListener cancelListener) {
        super(context);
        this.cancelListener = cancelListener;
        setContentView(R.layout.custom_dialog);
        deleteBtn= (ImageButton)findViewById(R.id.deleteBtn);
        deleteBtn.setOnClickListener(cancelListener);
    }

    public void createDialog(CustomDialog dialog)
    {
        width = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 260, getContext().getResources().getDisplayMetrics());
        height = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,100, getContext().getResources().getDisplayMetrics());

        WindowManager.LayoutParams layoutParams = new WindowManager.LayoutParams();
        Window window = dialog.getWindow();
        window.addFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
        window.setDimAmount(0.4f);

        layoutParams.copyFrom(window.getAttributes());
        layoutParams.width = width;
        layoutParams.height = height;
        window.setAttributes(layoutParams);

        dialog.setCanceledOnTouchOutside(true);
        dialog.setCancelable(true);
        dialog.show();
    }



    /*
    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.custom_dialog);

        //클릭 리스너 셋팅 (클릭버튼이 동작하도록 만들어줌.)
        deleteBtn= (ImageButton)findViewById(R.id.deleteBtn);
        deleteBtn.setOnClickListener(cancelListener);


        //다이얼로그 밖의 화면은 흐리게 만들어줌
        WindowManager.LayoutParams layoutParams = new WindowManager.LayoutParams();

        layoutParams.flags = WindowManager.LayoutParams.FLAG_DIM_BEHIND;
        layoutParams.dimAmount = 0.4f;

        width = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 254, getContext().getResources().getDisplayMetrics());
        height = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,85, getContext().getResources().getDisplayMetrics());

        layoutParams.width = width;
        layoutParams.height = height;

   }

     */



    //생성자 생성


}
package com.example.administrator.yaome;

import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.PopupWindow;

import com.jungly.gridpasswordview.GridPasswordView;

public class ApplyTiXianActivity extends AppCompatActivity {

    private Button next_bt;

    private PopupWindow popupWindow;

    private GridPasswordView passwordView;

    private ImageView close;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_apply_ti_xian);

        next_bt=(Button)this.findViewById(R.id.next_bt);





        initView();

    }

    private void initView(){
        next_bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                initPopupWindow();
            }
        });
    }

    private void initPopupWindow(){


        View popupWindowView=getLayoutInflater().inflate(R.layout.password_layout,null);
        //窗口的大小
        //设置后窗口组件无法获取焦点 比如无法输入文字
        //popupWindow=new PopupWindow(popupWindowView,600,300);
        popupWindow=new PopupWindow(popupWindowView, ActionBar.LayoutParams.WRAP_CONTENT,ActionBar.LayoutParams.WRAP_CONTENT,true);

        backgroundAlpha(0.5f);
        //获取屏幕中间焦点
//        WindowManager windowManager = (WindowManager) getSystemService(Context.WINDOW_SERVICE);
//        int xPos = windowManager.getDefaultDisplay().getWidth() / 2 - popupWindow.getWidth() / 2;
//        int yPos = windowManager.getDefaultDisplay().getHeight() / 2 -popupWindow.getHeight() / 2;
        //显示在中间偏上  偏移  X 、Y
        popupWindow.showAtLocation(getLayoutInflater().inflate(R.layout.activity_apply_ti_xian,null),Gravity.CENTER_HORIZONTAL,0,-100);

        ColorDrawable colorDrawable=new ColorDrawable(0xffffffff);
        popupWindow.setBackgroundDrawable(colorDrawable);
        //不允许点击外部  点击外部不消失
        popupWindow.setOutsideTouchable(false);
        popupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                backgroundAlpha(1.0f);
            }
        });

        close=(ImageView)popupWindowView.findViewById(R.id.close);
        passwordView=(GridPasswordView)popupWindowView.findViewById(R.id.passwordView);

        close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                popupWindow.dismiss();
            }
        });
    }


    //改变背景颜色
    private void backgroundAlpha(float bgAlpha){
        WindowManager.LayoutParams lp=getWindow().getAttributes();
        lp.alpha=bgAlpha;
        getWindow().setAttributes(lp);
    }
}

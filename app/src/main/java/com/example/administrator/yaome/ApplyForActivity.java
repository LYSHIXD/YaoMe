package com.example.administrator.yaome;

import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.support.v7.app.ActionBar;
import   android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class ApplyForActivity extends AppCompatActivity {

    private Spinner spinner_remote;
    private Spinner spinner_high;
    //选择金额区域
    private LinearLayout apply_count;
    //选择时间区域
    private LinearLayout apply_time;

    private PopupWindow popupWindow;
    //万元滚动器
    private PickerView tenThousand_pv;
    //千元滚动器
    private PickerView thousand_pv;
    //月份滚动器
    private PickerView time_pv;
    //滚动器上确定取消按钮
    private Button cancle_time,cancle_count,enter_time,enter_count;
    //弹窗中的是与否按钮
    private Button agree_bt,disagree_bt;
    //显示金额和月份
    private TextView count,time;
    //选择是否高利息区域
    private LinearLayout high_ll;
    //选择是否异地贷款区域
    private LinearLayout remote_ll;
    //高利息与异地贷款显示
    private TextView high_tv,remote_tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_apply_for);

        apply_count=(LinearLayout)this.findViewById(R.id.apply_count);
        apply_time=(LinearLayout)this.findViewById(R.id.apply_time);

        high_ll=(LinearLayout)this.findViewById(R.id.high_ll);
        remote_ll=(LinearLayout)this.findViewById(R.id.remote_ll);

        count=(TextView)this.findViewById(R.id.count);
        time=(TextView)this.findViewById(R.id.time);

        high_tv=(TextView)this.findViewById(R.id.high_tv);
        remote_tv=(TextView)this.findViewById(R.id.remote_tv);


        initView();



    }

    private void initView(){
        //点击金额区域弹出滚动器
        apply_count.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                initPopupWindowCount();
            }
        });
        //点击月份区域弹出滚动器
        apply_time.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                initPopuWindowTime();
            }
        });
        //点击高利息区域弹出窗口
        high_ll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                initPopuWindowsHigh();
            }
        });
        //点击异地贷款区域弹出窗口
        remote_ll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                initPopuWindowsRemote();
            }
        });

    }

    //设置万元和千元
    private String tenThouand=new String();
    private String thousand=new String();
    //设置月份
    private String monthCount=new String();
    //金额窗口
    private void initPopupWindowCount(){
        //加载布局
        View popupWindowView=this.getLayoutInflater().inflate(R.layout.popupwindow_buttom_count_check,null);
        //内容、宽度、高度
        popupWindow=new PopupWindow(popupWindowView, ActionBar.LayoutParams.MATCH_PARENT, ActionBar.LayoutParams.WRAP_CONTENT,true);

        //加载动画
        //popupWindow.setAnimationStyle();

        //菜单背景
        ColorDrawable color=new ColorDrawable(0xffffffff);
        popupWindow.setBackgroundDrawable(color);

        //显示位置
        popupWindow.showAtLocation(getLayoutInflater().inflate(R.layout.activity_apply_for,null), Gravity.BOTTOM|Gravity.CENTER_HORIZONTAL,0,0);

        backgroundAlpha(0.5f);

        //关闭窗口时恢复主界面透明度
        popupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                backgroundAlpha(1f);
            }
        });

        //确定与取消按钮
        cancle_count=(Button)popupWindowView.findViewById(R.id.cancle_count);
        enter_count=(Button)popupWindowView.findViewById(R.id.enter_count);

        cancle_count.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popupWindow.dismiss();
            }
        });
        enter_count.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                count.setText(tenThouand+"."+thousand);
                popupWindow.dismiss();
            }
        });


        //滚动选择器
        tenThousand_pv=(PickerView)popupWindowView.findViewById(R.id.tenThousand_pv);
        thousand_pv=(PickerView)popupWindowView.findViewById(R.id.thousand_pv);

        List<String> date1=new ArrayList<String>();
        List<String> date2=new ArrayList<String>();

        for(int i=1;i<10;i++){
            date1.add(""+i);
        }
        for(int i=1;i<10;i++){
            date2.add(""+i);
        }
        //设置数据、监听
        thousand_pv.setSelected(1);
        tenThousand_pv.setSelected(1);
        thousand_pv.setData(date1);
        thousand_pv.setOnSelectListener(new PickerView.onSelectListener() {
            @Override
            public void onSelect(String text) {
                thousand=text;
            }
        });
        tenThousand_pv.setData(date2);
        tenThousand_pv.setOnSelectListener(new PickerView.onSelectListener() {
            @Override
            public void onSelect(String text) {
                tenThouand=text;

            }
        });


    }
    //时间窗口
    private void initPopuWindowTime(){
        View popupWindowView=this.getLayoutInflater().inflate(R.layout.popupwindow_buttom_time_check,null);
        popupWindow=new PopupWindow(popupWindowView, ActionBar.LayoutParams.MATCH_PARENT, ActionBar.LayoutParams.WRAP_CONTENT,true);
        //菜单背景
        ColorDrawable color=new ColorDrawable(0xffffffff);
        popupWindow.setBackgroundDrawable(color);

        //显示位置
        popupWindow.showAtLocation(getLayoutInflater().inflate(R.layout.activity_apply_for,null), Gravity.BOTTOM|Gravity.CENTER_HORIZONTAL,0,0);

        backgroundAlpha(0.5f);

        //关闭窗口时恢复主界面透明度
        popupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                backgroundAlpha(1f);
            }
        });

        //确定与取消按钮
        cancle_time=(Button)popupWindowView.findViewById(R.id.cancle_time);
        enter_time=(Button)popupWindowView.findViewById(R.id.enter_time);
        cancle_time.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popupWindow.dismiss();
            }
        });
        enter_time.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                time.setText(monthCount);
                popupWindow.dismiss();
            }
        });

        //滚动选择器
        time_pv=(PickerView)popupWindowView.findViewById(R.id.time_pv);
        final List<String> month=new ArrayList<String>();
        for(int i=1;i<13;i++){
           month.add(""+i);

        }
        time_pv.setData(month);
        time_pv.setOnSelectListener(new PickerView.onSelectListener() {
            @Override
            public void onSelect(String text) {
                monthCount=text;
            }
        });

    }
    //高利息窗口
    private void initPopuWindowsHigh(){
        View popupWindowView=this.getLayoutInflater().inflate(R.layout.popupwindow_remote_high,null);
        popupWindow=new PopupWindow(popupWindowView, ActionBar.LayoutParams.MATCH_PARENT, ActionBar.LayoutParams.WRAP_CONTENT,true);
        //菜单背景
        ColorDrawable color=new ColorDrawable(0xffffffff);
        popupWindow.setBackgroundDrawable(color);

        //显示位置
        popupWindow.showAtLocation(getLayoutInflater().inflate(R.layout.activity_apply_for,null), Gravity.BOTTOM|Gravity.CENTER_HORIZONTAL,0,0);

        backgroundAlpha(0.5f);

        //关闭窗口时恢复主界面透明度
        popupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                backgroundAlpha(1f);
            }
        });

        //是与否按钮
        agree_bt=(Button)popupWindowView.findViewById(R.id.agree_bt);
        disagree_bt=(Button)popupWindowView.findViewById(R.id.disagree_bt);
        agree_bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                high_tv.setText("是");
                popupWindow.dismiss();
            }
        });
        disagree_bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                high_tv.setText("否");
                popupWindow.dismiss();
            }
        });
    }
    //异地贷款窗口
    private void initPopuWindowsRemote(){
        View popupWindowView=this.getLayoutInflater().inflate(R.layout.popupwindow_remote_high,null);
        popupWindow=new PopupWindow(popupWindowView, ActionBar.LayoutParams.MATCH_PARENT, ActionBar.LayoutParams.WRAP_CONTENT,true);
        //菜单背景
        ColorDrawable color=new ColorDrawable(0xffffffff);
        popupWindow.setBackgroundDrawable(color);

        //显示位置
        popupWindow.showAtLocation(getLayoutInflater().inflate(R.layout.activity_apply_for,null), Gravity.BOTTOM|Gravity.CENTER_HORIZONTAL,0,0);

        backgroundAlpha(0.5f);

        //关闭窗口时恢复主界面透明度
        popupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                backgroundAlpha(1f);
            }
        });

        //是与否按钮
        agree_bt=(Button)popupWindowView.findViewById(R.id.agree_bt);
        disagree_bt=(Button)popupWindowView.findViewById(R.id.disagree_bt);
        agree_bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                remote_tv.setText("是");
                popupWindow.dismiss();
            }
        });
        disagree_bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                remote_tv.setText("否");
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







//    private void spinnerView(){
//        //添加数据（是否)
//        String[] mitem = getResources().getStringArray(R.array.yes_no);
//        //初始化adapter 选择自定义样式
//        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this, R.layout.check_spinner_layout, mitem);
//        arrayAdapter.setDropDownViewResource(R.layout.spinnerlayout);
//
//        spinner_remote.setAdapter(arrayAdapter);
//        spinner_high.setAdapter(arrayAdapter);
//
//        //选择监听
//        spinner_remote.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
//            @Override
//            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
//
//            }
//
//            @Override
//            public void onNothingSelected(AdapterView<?> parent) {
//
//            }
//        });
//        spinner_high.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
//            @Override
//            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
//
//            }
//
//            @Override
//            public void onNothingSelected(AdapterView<?> parent) {
//
//            }
//        });
//
//    }












}

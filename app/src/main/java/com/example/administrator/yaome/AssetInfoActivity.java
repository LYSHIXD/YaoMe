package com.example.administrator.yaome;

import android.app.Activity;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class AssetInfoActivity extends AppCompatActivity implements View.OnClickListener{

    //点击区域  使用用途、社保、公积金、信用情况
    private RelativeLayout use_rl,social_security_rl,fund_rl,credit_rl;
    //显示文字  ...同上
    private TextView use_tv,social_security_tv,fund_tv,credit_tv;

    private PopupWindow popupWindow;
    //使用用途弹窗的按钮 新车、二手车、新房首付、新房、二手房、过桥、装修、教育、旅游、股票
    private Button new_car_bt,old_car_bt,new_house_first_pay_bt,new_house_bt,old_house_bt,guo_qiao_bt,zhuang_xiu_bt,education_bt,travel_bt,stock_bt;
    //信用情况弹窗按钮 有信用卡、无信用卡、少于三次逾期，大于三次逾期
    private Button have_creditCard_bt,no_credit_bt,less_three_bt,more_three_bt;

    private Button agree_bt,disagree_bt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_asset_info);

        use_rl=(RelativeLayout)this.findViewById(R.id.use_rl);
        social_security_rl=(RelativeLayout)this.findViewById(R.id.social_security_rl);
        credit_rl=(RelativeLayout)this.findViewById(R.id.credit_rl);
        fund_rl=(RelativeLayout)this.findViewById(R.id.fund_rl);

        use_tv=(TextView)this.findViewById(R.id.use_tv);
        social_security_tv=(TextView)this.findViewById(R.id.social_security_tv);
        credit_tv=(TextView)this.findViewById(R.id.credit_tv);
        fund_tv=(TextView)this.findViewById(R.id.fund_tv);


        initView();

    }


    private void initView(){
        use_rl.setOnClickListener(this);
        social_security_rl.setOnClickListener(this);
        fund_rl.setOnClickListener(this);
        credit_rl.setOnClickListener(this);
    }

    //显示哪个弹窗的标记
    //使用用途弹窗
    private void initPopupWindowUse(){

        View popupWindowView=this.getLayoutInflater().inflate(R.layout.popupwindow_asset_use,null);
        popupWindow=new PopupWindow(popupWindowView, ActionBar.LayoutParams.MATCH_PARENT,ActionBar.LayoutParams.WRAP_CONTENT,true);

        ColorDrawable mColor=new ColorDrawable(0xffffffff);
        popupWindow.setBackgroundDrawable(mColor);
        //显示位置
        popupWindow.showAtLocation(getLayoutInflater().inflate(R.layout.activity_asset_info,null), Gravity.BOTTOM|Gravity.CENTER_HORIZONTAL,0,0);

        backgroundAlpha(0.5f);

        popupWindow.setOutsideTouchable(false);

        popupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                backgroundAlpha(1.0f);
            }
        });

        new_car_bt=(Button)popupWindowView.findViewById(R.id.new_car_bt);
        old_car_bt=(Button)popupWindowView.findViewById(R.id.old_car_bt);
        new_house_bt=(Button)popupWindowView.findViewById(R.id.new_house_bt);
        new_house_first_pay_bt=(Button)popupWindowView.findViewById(R.id.new_house_first_pay_bt);
        old_house_bt=(Button)popupWindowView.findViewById(R.id.old_house_bt);
        guo_qiao_bt=(Button)popupWindowView.findViewById(R.id.guo_qiao_bt);
        zhuang_xiu_bt=(Button)popupWindowView.findViewById(R.id.zhuang_xiu_bt);
        education_bt=(Button)popupWindowView.findViewById(R.id.education_bt);
        travel_bt=(Button)popupWindowView.findViewById(R.id.travel_bt);
        stock_bt=(Button)popupWindowView.findViewById(R.id.stock_bt);

        new_car_bt.setOnClickListener(this);
        old_car_bt.setOnClickListener(this);
        new_house_bt.setOnClickListener(this);
        new_house_first_pay_bt.setOnClickListener(this);
        old_house_bt.setOnClickListener(this);
        guo_qiao_bt.setOnClickListener(this);
        zhuang_xiu_bt.setOnClickListener(this);
        education_bt.setOnClickListener(this);
        travel_bt.setOnClickListener(this);
        stock_bt.setOnClickListener(this);
    }
    //信用情况弹窗
    private void initPopupWindowCredit(){
        View popupWindowView=this.getLayoutInflater().inflate(R.layout.popupwindow_asset_credit,null);
        popupWindow=new PopupWindow(popupWindowView, ActionBar.LayoutParams.MATCH_PARENT,ActionBar.LayoutParams.WRAP_CONTENT,true);

        ColorDrawable mColor=new ColorDrawable(0xffffffff);
        popupWindow.setBackgroundDrawable(mColor);
        //显示位置
        popupWindow.showAtLocation(getLayoutInflater().inflate(R.layout.activity_asset_info,null), Gravity.BOTTOM|Gravity.CENTER_HORIZONTAL,0,0);

        backgroundAlpha(0.5f);

        popupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                backgroundAlpha(1.0f);
            }
        });

        have_creditCard_bt=(Button)popupWindowView.findViewById(R.id.have_creditCard_bt);
        no_credit_bt=(Button)popupWindowView.findViewById(R.id.no_credit_bt);
        less_three_bt=(Button)popupWindowView.findViewById(R.id.less_three);
        more_three_bt=(Button)popupWindowView.findViewById(R.id.more_three);

        have_creditCard_bt.setOnClickListener(this);
        no_credit_bt.setOnClickListener(this);
        less_three_bt.setOnClickListener(this);
        more_three_bt.setOnClickListener(this);

    }
    //是否有公积金 社保弹窗
    private void initPopupWindowYesOrNo(){
        View popupWindowView=this.getLayoutInflater().inflate(R.layout.popupwindow_remote_high,null);
        popupWindow=new PopupWindow(popupWindowView, ActionBar.LayoutParams.MATCH_PARENT,ActionBar.LayoutParams.WRAP_CONTENT,true);

        ColorDrawable mColor=new ColorDrawable(0xffffffff);
        popupWindow.setBackgroundDrawable(mColor);
        //显示位置
        popupWindow.showAtLocation(getLayoutInflater().inflate(R.layout.activity_asset_info,null), Gravity.BOTTOM|Gravity.CENTER_HORIZONTAL,0,0);

        backgroundAlpha(0.5f);

        popupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                backgroundAlpha(1.0f);
            }
        });

        agree_bt=(Button)popupWindowView.findViewById(R.id.agree_bt);
        disagree_bt=(Button)popupWindowView.findViewById(R.id.disagree_bt);

        agree_bt.setOnClickListener(this);
        disagree_bt.setOnClickListener(this);
    }

    private String str="";
    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.use_rl:
                initPopupWindowUse();
                break;
            case R.id.social_security_rl:
                str="shebao";
                initPopupWindowYesOrNo();
                break;
            case R.id.fund_rl:
                str="gongjijin";
                initPopupWindowYesOrNo();
                break;
            case R.id.credit_rl:
                initPopupWindowCredit();
                break;
            case R.id.new_car_bt:
                use_tv.setText("新车购买贷款");
                popupWindow.dismiss();
                break;
            case R.id.old_car_bt:
                use_tv.setText("二手车购买贷款");
                popupWindow.dismiss();
                break;
            case R.id.new_house_bt:
                use_tv.setText("新房购房贷款");
                popupWindow.dismiss();
                break;
            case R.id.new_house_first_pay_bt:
                use_tv.setText("新房首付贷款");
                popupWindow.dismiss();
                break;
            case R.id.old_house_bt:
                use_tv.setText("二手车房购房贷款");
                popupWindow.dismiss();
                break;
            case R.id.guo_qiao_bt:
                use_tv.setText("过桥短期贷款");
                popupWindow.dismiss();
                break;
            case R.id.zhuang_xiu_bt:
                use_tv.setText("装修贷款");
                popupWindow.dismiss();
                break;
            case R.id.education_bt:
                use_tv.setText("教育培训贷款");
                popupWindow.dismiss();
                break;
            case R.id.travel_bt:
                use_tv.setText("旅游贷款");
                popupWindow.dismiss();
                break;
            case R.id.stock_bt:
                use_tv.setText("股票配资贷款");
                popupWindow.dismiss();
                break;
            case R.id.have_creditCard_bt:
                credit_tv.setText("有信用卡或贷款，无逾期");
                popupWindow.dismiss();
                break;
            case R.id.no_credit_bt:
                credit_tv.setText("无信用卡或贷款");
                popupWindow.dismiss();
                break;
            case R.id.less_three:
                credit_tv.setText("一年内逾期少于3次且小于90天");
                popupWindow.dismiss();
                break;
            case R.id.more_three:
                credit_tv.setText("一年内逾期超过3次且超过90天");
                popupWindow.dismiss();
                break;
            case R.id.agree_bt:
                if(str.equals("shebao")){
                    social_security_tv.setText("有社保");
                }else{
                    fund_tv.setText("有公积金");}
                popupWindow.dismiss();
                break;
            case R.id.disagree_bt:
                if(str.equals("shebao")){
                    social_security_tv.setText("无社保");
                }else{
                    fund_tv.setText("无公积金");}
                popupWindow.dismiss();
                break;
        }
    }


    private void backgroundAlpha(float bgAlpha){
        WindowManager.LayoutParams lp=getWindow().getAttributes();
        lp.alpha=bgAlpha;
        getWindow().setAttributes(lp);
    }












}

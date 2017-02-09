package com.example.administrator.yaome;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    //点击区域 个人贷款、企业贷款、房产贷款、车辆贷款
    private LinearLayout personal_ll,company_ll,house_ll,car_ll;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        personal_ll=(LinearLayout)this.findViewById(R.id.personal_ll);
        house_ll=(LinearLayout)this.findViewById(R.id.house_ll);
        company_ll=(LinearLayout)this.findViewById(R.id.company_ll);
        car_ll=(LinearLayout)this.findViewById(R.id.car_ll);

        personal_ll.setOnClickListener(this);
        company_ll.setOnClickListener(this);
        house_ll.setOnClickListener(this);
        car_ll.setOnClickListener(this);
    }
    //哪个activity跳转的标记
    private String whichActivity="which";

    //个人贷款、企业贷款、房产贷款、车辆贷款的tag分别为 0、1、2、3
    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.personal_ll:
                intentView(0);
                break;
            case R.id.company_ll:
                intentView(1);
                break;
            case R.id.house_ll:
                intentView(2);
                break;
            case R.id.car_ll:
                intentView(3);
                break;
        }
    }

    //跳转到贷款申请界面
    private void intentView(int tag){
        Intent intent=new Intent(this,ApplyForActivity.class);
        intent.putExtra(whichActivity,tag);
        startActivity(intent);
    }



}

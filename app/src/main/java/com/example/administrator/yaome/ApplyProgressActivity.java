package com.example.administrator.yaome;

import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

public class ApplyProgressActivity extends AppCompatActivity implements View.OnClickListener{


    //按钮点击区域，用于改变背景
    private LinearLayout all_ll,audit_ll,finish_ll;
    //按钮 全部、审核中、已完成
    private Button all_bt,audit_bt,finish_bt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_apply_progress);

        all_ll=(LinearLayout)this.findViewById(R.id.all_ll);
        audit_ll=(LinearLayout)this.findViewById(R.id.audit_ll);
        finish_ll=(LinearLayout)this.findViewById(R.id.finish_ll);

        all_bt=(Button)this.findViewById(R.id.all_bt);
        audit_bt=(Button)this.findViewById(R.id.audit_bt);
        finish_bt=(Button)this.findViewById(R.id.finish_bt);

        all_bt.setOnClickListener(this);
        audit_bt.setOnClickListener(this);
        finish_bt.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.all_bt:
                all_ll.setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.yellowText)));
                all_bt.setTextColor(getResources().getColor(R.color.yellowText));
                audit_ll.setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.white)));
                audit_bt.setTextColor(getResources().getColor(R.color.blackText));
                finish_ll.setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.white)));
                finish_bt.setTextColor(getResources().getColor(R.color.blackText));
                break;
            case R.id.audit_bt:
                all_ll.setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.white)));
                all_bt.setTextColor(getResources().getColor(R.color.blackText));
                audit_ll.setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.yellowText)));
                audit_bt.setTextColor(getResources().getColor(R.color.yellowText));
                finish_ll.setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.white)));
                finish_bt.setTextColor(getResources().getColor(R.color.blackText));
                break;
            case R.id.finish_bt:
                all_ll.setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.white)));
                all_bt.setTextColor(getResources().getColor(R.color.blackText));
                audit_ll.setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.white)));
                audit_bt.setTextColor(getResources().getColor(R.color.blackText));
                finish_ll.setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.yellowText)));
                finish_bt.setTextColor(getResources().getColor(R.color.yellowText));
                break;
        }
    }
}

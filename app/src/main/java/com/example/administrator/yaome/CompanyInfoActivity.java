package com.example.administrator.yaome;

import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.administrator.yaome.adapter.PopupwindowAdapter;

public class CompanyInfoActivity extends AppCompatActivity implements View.OnClickListener{
    //下一步按钮
    private Button next_btn;
    private ImageView back_iv;
    //企业全称
    private EditText company_name_et;
    //企业地址
    private EditText company_location_et;
    //企业注册资本
    private EditText company_money_et;
    //注册时间
    private EditText company_register_et;
    //法人姓名
    private EditText company_legal_person_et;
    //手机号码
    private EditText phoneNum_et;
    //短信验证码
    private EditText massageNum_et;
    //身份证号码
    private EditText idNum_et;
    //企业现状
    private RelativeLayout company_now_rl;
    private TextView company_now_tv;
    //企业资料
    private RelativeLayout company_file_rl;
    //身份证照片
    private RelativeLayout idCard_photo_rl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_company_info);


        next_btn=(Button)this.findViewById(R.id.next_btn);
        back_iv=(ImageView) this.findViewById(R.id.back_iv);

        company_name_et=(EditText)this.findViewById(R.id.company_name_et);
        company_location_et=(EditText)this.findViewById(R.id.company_location_et);
        company_money_et=(EditText)this.findViewById(R.id.company_money_et);
        company_register_et=(EditText)this.findViewById(R.id.company_register_et);
        company_legal_person_et=(EditText)this.findViewById(R.id.company_legal_person_et);
        phoneNum_et=(EditText)this.findViewById(R.id.phoneNum_et);
        massageNum_et=(EditText)this.findViewById(R.id.massageNum_et);
        idNum_et=(EditText)this.findViewById(R.id.idNum_et);

        company_now_rl=(RelativeLayout) this.findViewById(R.id.company_now_rl);
        company_file_rl=(RelativeLayout) this.findViewById(R.id.company_file_rl);
        idCard_photo_rl=(RelativeLayout) this.findViewById(R.id.idCard_photo_rl);

        company_now_tv=(TextView) this.findViewById(R.id.company_now_tv);


        next_btn.setOnClickListener(this);
        back_iv.setOnClickListener(this);
        company_now_rl.setOnClickListener(this);
        company_file_rl.setOnClickListener(this);
        idCard_photo_rl.setOnClickListener(this);
    }

    private Intent intent;
    private PopupWindow popupWindow;

    private String [] strings;
    private ListView listView;

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.next_btn:
                intent=new Intent(CompanyInfoActivity.this,CompanyAssetInfoActivity.class);
                startActivity(intent);
                break;
            case R.id.company_now_rl:
                initPopupwindow();
                break;
            case R.id.company_file_rl:
                intent=new Intent(CompanyInfoActivity.this,CompanyPhotoActivity.class);
                startActivity(intent);
                break;
            case R.id.idCard_photo_rl:
                intent=new Intent(CompanyInfoActivity.this,IdCardInfoActivity.class);
                startActivity(intent);
                break;
            case R.id.back_iv:
                finish();
                break;
        }
    }

    private void initPopupwindow(){

        final View popupWindowView=getLayoutInflater().inflate(R.layout.popupwindow_listview,null);
        popupWindow=new PopupWindow(popupWindowView, ActionBar.LayoutParams.MATCH_PARENT,ActionBar.LayoutParams.WRAP_CONTENT);

        ColorDrawable colorDrawable=new ColorDrawable(getResources().getColor(R.color.gray));
        popupWindow.setBackgroundDrawable(colorDrawable);

        popupWindow.showAtLocation(getLayoutInflater().inflate(R.layout.activity_company_info,null), Gravity.BOTTOM|Gravity.CENTER_HORIZONTAL,0,0);

        backgroundAlpha(0.5f);

        popupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                backgroundAlpha(1.0f);
            }
        });

        listView=(ListView)popupWindowView.findViewById(R.id.listview);
        strings=getResources().getStringArray(R.array.companyNow);

        PopupwindowAdapter adapter=new PopupwindowAdapter(this,strings);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                company_now_tv.setText(strings[i]);

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

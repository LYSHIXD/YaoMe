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

public class PersonalInfoActivity extends AppCompatActivity {
    //返回按钮
    private ImageView back_iv;
    //真实姓名
    private EditText name;
    //身份证号码
    private EditText idNum;
    //真实年龄
    private EditText age;
    //手机号码
    private EditText phoneNum;
    //短信验证码
    private EditText massageNum;
    //是否上传
    private TextView upLoad;
    //职业角色区域
    private RelativeLayout job_rl;
    //身份证上传区域
    private RelativeLayout upload_rl;
    //职业角色
    private TextView job_tv;
    //下一步、上班族、自由职业、按钮
    private Button next_btn;

    private PopupWindow popupWindow;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personal_base_info);

        back_iv=(ImageView)this.findViewById(R.id.back_iv);
        name=(EditText)this.findViewById(R.id.name_et);
        idNum=(EditText)this.findViewById(R.id.idNum_et);
        age=(EditText)this.findViewById(R.id.age_et);
        phoneNum=(EditText)this.findViewById(R.id.phoneNum_et);
        massageNum=(EditText)this.findViewById(R.id.massageNum_et);
        upLoad=(TextView) this.findViewById(R.id.upload_tv);
        job_rl=(RelativeLayout) this.findViewById(R.id.job_rl);
        upload_rl=(RelativeLayout) this.findViewById(R.id.upload_rl);
        next_btn=(Button)this.findViewById(R.id.next_bt);
        job_tv=(TextView)this.findViewById(R.id.job_tv);

        initView();
    }

    private void initView(){
        //点击职业角色区域弹出职业选择按钮
        job_rl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                initPopupWindow();
            }
        });
        //跳转身份证上传界面
        upload_rl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //跳转到身份证上传界面
                Intent intent=new Intent(PersonalInfoActivity.this,IdCardInfoActivity.class);
                startActivity(intent);
            }
        });
        //返回按钮
        back_iv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        //下一步跳转
        next_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intentView();
            }
        });

    }

    //跳转信息
    private void intentView(){
        Intent lastIntent=getIntent();
        int tag=lastIntent.getIntExtra("which2",4);

        //个人贷款、企业贷款、房产贷款、车辆贷款的tag分别为 0、1、2、3
        //通过tag 0、2、3 来选择跳转对应的activity
        Intent intent;
        switch (tag){
            case 0:
                //跳转到个人资产信息
                intent=new Intent(this,PersonalAssetInfoActivity.class);
                startActivity(intent);
                break;
            case 2:
                //跳转到房产信息
                intent=new Intent(this,HouseInfoActivity.class);
                startActivity(intent);
                break;
            case 3:
                //跳转到车辆信息
                intent=new Intent(this,CarInfoActivity.class);
                startActivity(intent);
                break;
        }
    }
    private ListView listView;
    private String [] strings;

    //职业选择弹窗
    private void initPopupWindow(){
        final View popupWindowView=this.getLayoutInflater().inflate(R.layout.popupwindow_listview,null);
        popupWindow=new PopupWindow(popupWindowView, ActionBar.LayoutParams.MATCH_PARENT, ActionBar.LayoutParams.WRAP_CONTENT,true);
        //菜单背景
        ColorDrawable color=new ColorDrawable(0xffffffff);
        popupWindow.setBackgroundDrawable(color);

        //显示位置
        popupWindow.showAtLocation(getLayoutInflater().inflate(R.layout.activity_personal_base_info,null), Gravity.BOTTOM|Gravity.CENTER_HORIZONTAL,0,0);

        backgroundAlpha(0.5f);

        //关闭窗口时恢复主界面透明度
        popupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                backgroundAlpha(1f);
            }
        });

        listView=(ListView)popupWindowView.findViewById(R.id.listview);
        strings=getResources().getStringArray(R.array.personalJob);
        PopupwindowAdapter adapter=new PopupwindowAdapter(this,strings);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                job_tv.setText(strings[i]);

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










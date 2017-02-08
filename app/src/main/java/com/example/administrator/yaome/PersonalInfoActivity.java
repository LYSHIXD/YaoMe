package com.example.administrator.yaome;

import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class PersonalInfoActivity extends AppCompatActivity {
    //返回按钮
    private ImageView back;
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
    //职业角色
    private TextView job_tv;
    //下一步、上班族、自由职业、按钮
    private Button next,work_bt,freedom_bt;

    private PopupWindow popupWindow;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personal_info);

        back=(ImageView)this.findViewById(R.id.back_iv);
        name=(EditText)this.findViewById(R.id.name_et);
        idNum=(EditText)this.findViewById(R.id.idNum_et);
        age=(EditText)this.findViewById(R.id.age_et);
        phoneNum=(EditText)this.findViewById(R.id.phoneNum_et);
        massageNum=(EditText)this.findViewById(R.id.massageNum_et);
        upLoad=(TextView) this.findViewById(R.id.upload_tv);
        job_rl=(RelativeLayout) this.findViewById(R.id.job_rl);
        next=(Button)this.findViewById(R.id.next_bt);
        work_bt=(Button)this.findViewById(R.id.work_bt);
        freedom_bt=(Button)this.findViewById(R.id.freedom_bt);
        job_tv=(TextView)this.findViewById(R.id.job_tv);

        initView();
    }

    private void initView(){
        job_rl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                initPopupWindow();
            }
        });
    }

    private void initPopupWindow(){
        View popupWindowView=this.getLayoutInflater().inflate(R.layout.popupwindow_job,null);
        popupWindow=new PopupWindow(popupWindowView, ActionBar.LayoutParams.MATCH_PARENT, ActionBar.LayoutParams.WRAP_CONTENT,true);
        //菜单背景
        ColorDrawable color=new ColorDrawable(0xffffffff);
        popupWindow.setBackgroundDrawable(color);

        //显示位置
        popupWindow.showAtLocation(getLayoutInflater().inflate(R.layout.activity_personal_info,null), Gravity.BOTTOM|Gravity.CENTER_HORIZONTAL,0,0);

        backgroundAlpha(0.5f);

        //关闭窗口时恢复主界面透明度
        popupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                backgroundAlpha(1f);
            }
        });

        //是与否按钮
        work_bt=(Button)popupWindowView.findViewById(R.id.work_bt);
        freedom_bt=(Button)popupWindowView.findViewById(R.id.freedom_bt);
        work_bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                job_tv.setText("上班族");
                popupWindow.dismiss();
            }
        });
        freedom_bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                job_tv.setText("自由职业");
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










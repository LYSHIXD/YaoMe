package com.example.administrator.yaome;

import android.icu.text.DisplayContext;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class FindPswActivity extends AppCompatActivity {

    //两个密码的显示图标
    private ImageView newPsw_iv,newAgainPsw_iv;
    //验证码
    private EditText verificationgCode;
    //新密码
    private EditText newPsw_et,newAgainPsw_et;
    //手机号
    private TextView phoneNum;
    //保存按钮
    private Button save_bt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_find_psw);

        newPsw_iv=(ImageView)this.findViewById(R.id.new_psw_iv);
        newAgainPsw_iv=(ImageView)this.findViewById(R.id.new_again_psw_iv);

        verificationgCode=(EditText)this.findViewById(R.id.verification_code);

        newPsw_et=(EditText)this.findViewById(R.id.new_psw_et);
        newAgainPsw_et=(EditText)this.findViewById(R.id.new_again_psw_et);

        phoneNum=(TextView)this.findViewById(R.id.phoneNum_tv);
        save_bt=(Button)this.findViewById(R.id.save_bt);

        showPsw();

        save();

    }
    //设置开始不显示密码
    private boolean isShowPsw_new=false;
    private boolean isShowPsw_again=false;
    //显示与隐藏密码
    private void showPsw(){
        //新密码的显示与赢藏
        newPsw_iv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(isShowPsw_new){
                    isShowPsw_new=false;
                    newPsw_iv.setImageResource(R.mipmap.ic_password_hover);
                    //以明文显示
                    newPsw_et.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                }else{
                    isShowPsw_new=true;
                    newPsw_iv.setImageResource(R.mipmap.ic_password);
                    //以密码显示
                    newPsw_et.setTransformationMethod(PasswordTransformationMethod.getInstance());
                }
            }
        });
        //再次输入密码的显示与影藏
        newAgainPsw_iv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(isShowPsw_again){
                    isShowPsw_again=false;
                    newAgainPsw_iv.setImageResource(R.mipmap.ic_password_hover);
                    //以明文显示
                    newAgainPsw_et.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                }else{
                    isShowPsw_again=true;
                    newAgainPsw_iv.setImageResource(R.mipmap.ic_password);
                    //以密码显示
                    newAgainPsw_et.setTransformationMethod(PasswordTransformationMethod.getInstance());
                }
            }
        });




    }

    //判断两次输入密码是否相同
    private boolean isEqually(){
        if(newPsw_et.getText().toString().equals(newAgainPsw_et.getText().toString())){
            return true;
        }else{
            return false;
        }

    }

    //点击保存按钮
    private void save(){
        save_bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(isEqually()){
                    Toast.makeText(FindPswActivity.this, "两次输入相同", Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(FindPswActivity.this, "两次输入不相同，请重新输入", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }























}

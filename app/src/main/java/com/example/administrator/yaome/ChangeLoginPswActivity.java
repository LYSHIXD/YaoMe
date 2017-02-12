package com.example.administrator.yaome;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

public class ChangeLoginPswActivity extends AppCompatActivity {
    //返回按钮
    private ImageView back_iv;
    //旧密码
    private EditText oldPsw_et;
    //新密码
    private EditText newPsw_et;
    //再次输入新密码
    private EditText newAgainPsw_et;
    //新密码显示图标
    private ImageView newPsw_iv;
    //再次输入新密码显示图标
    private ImageView newAgainPsw_iv;

    private Button save_btn;

    //设置开始不显示密码
    private boolean isShowPsw_new=false;
    private boolean isShowPsw_again=false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_login_psw);

        back_iv=(ImageView)this.findViewById(R.id.back_iv);
        newPsw_iv=(ImageView)this.findViewById(R.id.new_psw_iv);
        newAgainPsw_iv=(ImageView)this.findViewById(R.id.new_again_psw_iv);
        oldPsw_et=(EditText) this.findViewById(R.id.oldPsw_et);
        newPsw_et=(EditText) this.findViewById(R.id.new_psw_et);
        newAgainPsw_et=(EditText) this.findViewById(R.id.new_again_psw_et);

        save_btn=(Button) this.findViewById(R.id.save_btn);

        initView();
        save();
        showPsw();
    }

    private void initView(){
        back_iv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });


    }

    //点击保存按钮
    private void save() {
        save_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isEqually()) {
                    Toast.makeText(ChangeLoginPswActivity.this, "两次输入相同", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(ChangeLoginPswActivity.this, "两次输入不相同，请重新输入", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

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
                //设置焦点在最后
                newPsw_et.setSelection(newPsw_et.getText().length());
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

                //设置焦点在最后
                newAgainPsw_et.setSelection(newAgainPsw_et.getText().length());
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




















}

package com.example.administrator.yaome;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

public class SettingsActivity extends AppCompatActivity implements View.OnClickListener{
    //返回按钮
    private ImageView back_iv;
    //当前版本
    private TextView version_tv;
    //版本更新
    private RelativeLayout versionUpdate_rl;
    //服务协议
    private RelativeLayout service_contract_rl;
    //清除缓存
    private RelativeLayout clean_rl;
    //修改登录密码
    private RelativeLayout changeLoginPsw_rl;
    //修改交易密码
    private RelativeLayout changeDealPsw_rl;
    //退出登录
    private Button exitLogin_btn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        back_iv=(ImageView)this.findViewById(R.id.back_iv);
        version_tv=(TextView) this.findViewById(R.id.version_tv);
        versionUpdate_rl=(RelativeLayout) this.findViewById(R.id.versionUpdate_rl);
        service_contract_rl=(RelativeLayout) this.findViewById(R.id.service_contract_rl);
        clean_rl=(RelativeLayout) this.findViewById(R.id.clean_rl);
        changeLoginPsw_rl=(RelativeLayout) this.findViewById(R.id.changeLoginPsw_rl);
        changeDealPsw_rl=(RelativeLayout) this.findViewById(R.id.changeDealPsw_rl);

        exitLogin_btn=(Button) this.findViewById(R.id.exitLogin_btn);


        back_iv.setOnClickListener(this);
        versionUpdate_rl.setOnClickListener(this);
        service_contract_rl.setOnClickListener(this);
        clean_rl.setOnClickListener(this);
        changeLoginPsw_rl.setOnClickListener(this);
        changeDealPsw_rl.setOnClickListener(this);
        exitLogin_btn.setOnClickListener(this);





    }

    private void initView(){

    }

    private Intent intent;

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.back_iv:
                finish();
                break;
            case R.id.versionUpdate_rl:
                Toast.makeText(this, "当前已是最新版本！", Toast.LENGTH_SHORT).show();
                break;
            case R.id.service_contract_rl:

                break;
            case R.id.clean_rl:

                break;
            case R.id.changeLoginPsw_rl:
                intent=new Intent(this,ChangeLoginPswActivity.class);
                startActivity(intent);
                break;
            case R.id.changeDealPsw_rl:
                intent=new Intent(this,ChangeDealPswActivity.class);
                startActivity(intent);
                break;
            case R.id.exitLogin_btn:

                break;
        }




    }
}

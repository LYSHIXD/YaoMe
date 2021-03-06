package com.example.administrator.yaome;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class CompanyPhotoActivity extends AppCompatActivity implements View.OnClickListener{

    //返回按钮
    private ImageView back_iv;
    //保存按钮
    private Button save_btn;
    //企业执照
    private ImageView company_files_iv;
    //办公地
    private ImageView company_office_iv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_company_photo);

        back_iv=(ImageView)this.findViewById(R.id.back_iv);
        save_btn=(Button) this.findViewById(R.id.save_btn);
        company_files_iv=(ImageView)this.findViewById(R.id.company_files_iv);
        company_office_iv=(ImageView)this.findViewById(R.id.company_office_iv);

        back_iv.setOnClickListener(this);
        save_btn.setOnClickListener(this);
        company_files_iv.setOnClickListener(this);
        company_office_iv.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.back_iv:
                finish();
                break;
            case R.id.save_btn:

                break;
            case R.id.company_files_iv:

                break;
            case R.id.company_office_iv:

                break;


        }
    }
}

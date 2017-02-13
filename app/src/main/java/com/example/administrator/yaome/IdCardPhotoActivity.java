package com.example.administrator.yaome;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class IdCardPhotoActivity extends AppCompatActivity implements View.OnClickListener{

    //返回按钮
    private ImageView back_iv;
    //保存按钮
    private Button save_btn;
    //身份证正面
    private ImageView IdCard_front_iv;
    //身份证背面
    private ImageView IdCard_contrary_iv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_id_card_photo);

        back_iv=(ImageView)this.findViewById(R.id.back_iv);
        save_btn=(Button) this.findViewById(R.id.save_btn);
        IdCard_front_iv=(ImageView)this.findViewById(R.id.IdCard_front_iv);
        IdCard_contrary_iv=(ImageView)this.findViewById(R.id.IdCard_contrary_iv);

        back_iv.setOnClickListener(this);
        save_btn.setOnClickListener(this);
        IdCard_front_iv.setOnClickListener(this);
        IdCard_contrary_iv.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.back_iv:
                finish();
                break;
            case R.id.save_btn:

                break;
            case R.id.IdCard_front_iv:

                break;
            case R.id.IdCard_contrary_iv:

                break;


        }
    }
}

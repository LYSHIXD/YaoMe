package com.example.administrator.yaome;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class HousePhotoActivity extends AppCompatActivity implements View.OnClickListener{

    //返回按钮
    private ImageView back_iv;
    //保存按钮
    private Button save_btn;
    //房屋外观
    private ImageView house_appearance_iv;
    //房屋内饰
    private ImageView house_interior_iv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_house_photo);

        back_iv=(ImageView)this.findViewById(R.id.back_iv);
        save_btn=(Button) this.findViewById(R.id.save_btn);
        house_appearance_iv=(ImageView)this.findViewById(R.id.house_appearance_iv);
        house_interior_iv=(ImageView)this.findViewById(R.id.house_interior_iv);

        back_iv.setOnClickListener(this);
        save_btn.setOnClickListener(this);
        house_appearance_iv.setOnClickListener(this);
        house_interior_iv.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.back_iv:
                finish();
                break;
            case R.id.save_btn:

                break;
            case R.id.house_appearance_iv:

                break;
            case R.id.house_interior_iv:

                break;


        }
    }
}

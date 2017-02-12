package com.example.administrator.yaome;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;


import com.example.administrator.yaome.adapter.HeadPagerAdapter;
import com.example.administrator.yaome.util.ImageChange;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    //点击区域 个人贷款、企业贷款、房产贷款、车辆贷款
    private LinearLayout personal_ll,company_ll,house_ll,car_ll;

    private ViewPager vp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        personal_ll=(LinearLayout)this.findViewById(R.id.personal_ll);
        house_ll=(LinearLayout)this.findViewById(R.id.house_ll);
        company_ll=(LinearLayout)this.findViewById(R.id.company_ll);
        car_ll=(LinearLayout)this.findViewById(R.id.car_ll);

        dot_iv1=(ImageView)this.findViewById(R.id.dot_iv1);
        dot_iv2=(ImageView)this.findViewById(R.id.dot_iv2);
        dot_iv3=(ImageView)this.findViewById(R.id.dot_iv3);

        vp=(ViewPager)this.findViewById(R.id.vp);

        personal_ll.setOnClickListener(this);
        company_ll.setOnClickListener(this);
        house_ll.setOnClickListener(this);
        car_ll.setOnClickListener(this);

        initViewPager();

    }

    private List<ImageView> imageViewList;
    private HeadPagerAdapter adapter;

    private ImageView img1;
    private ImageView img2;
    private ImageView img3;

    private ImageView dot_iv1;
    private ImageView dot_iv2;
    private ImageView dot_iv3;

    private void initViewPager(){

        imageViewList=new ArrayList<ImageView>();

        Bitmap bitmap1= BitmapFactory.decodeResource(super.getResources(),R.mipmap.im1);
        Bitmap bitmap2= BitmapFactory.decodeResource(super.getResources(),R.mipmap.im2);
        Bitmap bitmap3= BitmapFactory.decodeResource(super.getResources(),R.mipmap.im3);

        img1=new ImageView(this);
        img2=new ImageView(this);
        img3=new ImageView(this);

        img1.setImageBitmap(bitmap1);
        img2.setImageBitmap(bitmap2);
        img3.setImageBitmap(bitmap3);

        img1.setScaleType(ImageView.ScaleType.FIT_XY);
        img2.setScaleType(ImageView.ScaleType.FIT_XY);
        img3.setScaleType(ImageView.ScaleType.FIT_XY);

        imageViewList.add(img1);
        imageViewList.add(img2);
        imageViewList.add(img3);

        adapter=new HeadPagerAdapter(this,imageViewList);
        vp.setAdapter(adapter);

        vp.setCurrentItem(0);

        ImageChange.Change(vp,dot_iv1,dot_iv2,dot_iv3,4000);


    }



    //哪个activity跳转的标记
    private String whichActivity="which";

    //个人贷款、企业贷款、房产贷款、车辆贷款的tag分别为 0、1、2、3
    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.personal_ll:
                intentView(0);
                break;
            case R.id.company_ll:
                intentView(1);
                break;
            case R.id.house_ll:
                intentView(2);
                break;
            case R.id.car_ll:
                intentView(3);
                break;
        }
    }

    //跳转到贷款申请界面
    private void intentView(int tag){
        Intent intent=new Intent(this,ApplyForActivity.class);
        intent.putExtra(whichActivity,tag);
        startActivity(intent);
    }



}

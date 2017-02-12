package com.example.administrator.yaome;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;


import com.example.administrator.yaome.adapter.HeadPagerAdapter;
import com.example.administrator.yaome.util.ImageChange;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    //点击区域
    //个人贷款
    private LinearLayout personal_ll;
    //企业贷款
    private LinearLayout company_ll;
    //房产贷款
    private LinearLayout house_ll;
    //车辆贷款
    private LinearLayout car_ll;
    //头部图片滚动vp
    private ViewPager vp;
    //侧边栏
    private DrawerLayout drawer;
    //侧边栏开关图片
    private ImageView openAndcloseDrawer;

    //侧边栏部分
    //头像图片
    private ImageView drawer_headImg_iv;
    //登录账号-手机号码
    private TextView drawer_phoneNUm_tv;
    //钱包
    private RelativeLayout drawer_wallet_rl;
    //申请进度
    private RelativeLayout drawer_apply_progress_rl;
    //设置
    private RelativeLayout drawer_settings_rl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        drawer=(DrawerLayout)this.findViewById(R.id.drawer);
        openAndcloseDrawer=(ImageView)this.findViewById(R.id.openAndcloseDrawer_iv);


        personal_ll=(LinearLayout)this.findViewById(R.id.personal_ll);
        house_ll=(LinearLayout)this.findViewById(R.id.house_ll);
        company_ll=(LinearLayout)this.findViewById(R.id.company_ll);
        car_ll=(LinearLayout)this.findViewById(R.id.car_ll);

        dot_iv1=(ImageView)this.findViewById(R.id.dot_iv1);
        dot_iv2=(ImageView)this.findViewById(R.id.dot_iv2);
        dot_iv3=(ImageView)this.findViewById(R.id.dot_iv3);

        drawer_headImg_iv=(ImageView)this.findViewById(R.id.drawer_headImg_iv);
        drawer_phoneNUm_tv=(TextView) this.findViewById(R.id.drawer_phoneNUm_tv);
        drawer_wallet_rl=(RelativeLayout) this.findViewById(R.id.drawer_wallet_rl);
        drawer_apply_progress_rl=(RelativeLayout) this.findViewById(R.id.drawer_apply_progress_rl);
        drawer_settings_rl=(RelativeLayout) this.findViewById(R.id.drawer_settings_rl);

        vp=(ViewPager)this.findViewById(R.id.vp);

        personal_ll.setOnClickListener(this);
        company_ll.setOnClickListener(this);
        house_ll.setOnClickListener(this);
        car_ll.setOnClickListener(this);
        openAndcloseDrawer.setOnClickListener(this);

        drawer_wallet_rl.setOnClickListener(this);
        drawer_apply_progress_rl.setOnClickListener(this);
        drawer_settings_rl.setOnClickListener(this);

        initViewPager();


    }


    private List<ImageView> imageViewList;
    private HeadPagerAdapter adapter;

    //假数据
    private ImageView img1;
    private ImageView img2;
    private ImageView img3;

    //vp上的三个圆点
    private ImageView dot_iv1;
    private ImageView dot_iv2;
    private ImageView dot_iv3;

    //头部vp的图片切换
    private void initViewPager(){

        imageViewList=new ArrayList<ImageView>();

        //三条假数据
        Bitmap bitmap1= BitmapFactory.decodeResource(super.getResources(),R.mipmap.im1);
        Bitmap bitmap2= BitmapFactory.decodeResource(super.getResources(),R.mipmap.im2);
        Bitmap bitmap3= BitmapFactory.decodeResource(super.getResources(),R.mipmap.im3);

        //初始化
        img1=new ImageView(this);
        img2=new ImageView(this);
        img3=new ImageView(this);

        //把图片资源文件转为ImageView
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

        ImageChange.Change(this,vp,dot_iv1,dot_iv2,dot_iv3,4000);


    }



    //哪个activity跳转的标记
    private String whichActivity="which";

    //设置drawer默认关闭
    private boolean open=false;

    private Intent intent;

    //个人贷款、企业贷款、房产贷款、车辆贷款的tag分别为 0、1、2、3
    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.personal_ll:
                intentView(0);                               //个人贷款跳转
                break;
            case R.id.company_ll:
                intentView(1);                               //企业贷款跳转
                break;
            case R.id.house_ll:
                intentView(2);                                //房产贷款跳转
                break;
            case R.id.car_ll:
                intentView(3);                                //车辆贷款跳转
                break;
            case R.id.openAndcloseDrawer_iv:
                drawer.openDrawer(Gravity.LEFT);               //打开左边的侧边栏
                break;
            case R.id.drawer_wallet_rl:
                intent=new Intent(this,WalletActivity.class);   //跳转到钱包
                startActivity(intent);
                break;
            case R.id.drawer_apply_progress_rl:
                intent=new Intent(this,ApplyProgressActivity.class);  //跳转到申请进度
                startActivity(intent);
                break;
            case R.id.drawer_settings_rl:
                intent=new Intent(this,SettingsActivity.class);     //跳转到设置
                startActivity(intent);
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

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

public class CarInfoActivity extends AppCompatActivity implements View.OnClickListener{

    //返回按钮
    private ImageView back_iv;
    //下一步按钮
    private Button next_btn;
    //车辆品牌
    private EditText car_brand_et;
    //购买时间
    private RelativeLayout car_buy_time_rl;
    //公里数
    private EditText car_kilometre_et;
    //车辆所有人
    private EditText car_possessor_et;
    //车辆用途
    private RelativeLayout car_use_rl;
    private TextView car_use_tv;
    //抵押方式
    private RelativeLayout car_pledge_rl;
    private TextView car_pledge_tv;
    //车辆照片
    private RelativeLayout car_photo_rl;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_car_info);

        back_iv=(ImageView)this.findViewById(R.id.back_iv);
        next_btn=(Button) this.findViewById(R.id.next_btn);

        car_brand_et=(EditText) this.findViewById(R.id.car_brand_et);
        car_brand_et=(EditText) this.findViewById(R.id.car_brand_et);
        car_kilometre_et=(EditText) this.findViewById(R.id.car_kilometre_et);
        car_possessor_et=(EditText) this.findViewById(R.id.car_possessor_et);

        car_buy_time_rl=(RelativeLayout) this.findViewById(R.id.car_buy_time_rl);
        car_use_rl=(RelativeLayout) this.findViewById(R.id.car_use_rl);
        car_pledge_rl=(RelativeLayout) this.findViewById(R.id.car_pledge_rl);
        car_photo_rl=(RelativeLayout) this.findViewById(R.id.car_photo_rl);

        car_use_tv=(TextView) this.findViewById(R.id.car_use_tv);
        car_pledge_tv=(TextView) this.findViewById(R.id.car_pledge_tv);





        back_iv.setOnClickListener(this);
        next_btn.setOnClickListener(this);
        car_buy_time_rl.setOnClickListener(this);
        car_use_rl.setOnClickListener(this);
        car_pledge_rl.setOnClickListener(this);
        car_photo_rl.setOnClickListener(this);




    }
    private PopupWindow popupWindow;
    private ListView listView;
    private String[] strings;
    private String tag;

    private void initPopupwindow(){
        View popupwindowView=getLayoutInflater().inflate(R.layout.popupwindow_listview,null);
        popupWindow=new PopupWindow(popupwindowView, ActionBar.LayoutParams.MATCH_PARENT,ActionBar.LayoutParams.WRAP_CONTENT,true);

        //背景颜色
        ColorDrawable colorDrawable=new ColorDrawable(getResources().getColor(R.color.gray));
        popupWindow.setBackgroundDrawable(colorDrawable);

        //显示位置
        popupWindow.showAtLocation(getLayoutInflater().inflate(R.layout.activity_car_info,null),Gravity.BOTTOM|Gravity.CENTER_HORIZONTAL,0,0);

        backgroundAlpha(0.5f);
        popupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                backgroundAlpha(1.0f);
            }
        });

        listView=(ListView)popupwindowView.findViewById(R.id.listview);

        if( tag == "use" ){

            strings=getResources().getStringArray(R.array.carUse);

        }else if( tag == "peldge" ){

            strings=getResources().getStringArray(R.array.carPledge);
        }

        PopupwindowAdapter adapter=new PopupwindowAdapter(this,strings);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                if( tag == "use" ){

                    car_use_tv.setText(strings[i]);

                }else if( tag == "pledge" ){

                    car_pledge_tv.setText(strings[i]);
                }

                popupWindow.dismiss();
            }
        });

    }

    //改变背景颜色
    private void backgroundAlpha(float alpha){
        WindowManager.LayoutParams lp=getWindow().getAttributes();
        lp.alpha=alpha;
        getWindow().setAttributes(lp);
    }

    @Override
    public void onClick(View view) {

        Intent intent;

        switch (view.getId()){
            case R.id.back_iv:
                finish();
                break;
            case R.id.next_btn:
                intent=new Intent(this,SubmitActivity.class);
                startActivity(intent);
                break;
            case R.id.car_buy_time_rl:

                break;
            case R.id.car_use_rl:
                tag="use";
                initPopupwindow();
                break;
            case R.id.car_pledge_rl:
                tag="pledge";
                initPopupwindow();
                break;
            case R.id.car_photo_rl:
                intent=new Intent(this,CarPhotoActivity.class);
                startActivity(intent);
                break;
        }

    }
}

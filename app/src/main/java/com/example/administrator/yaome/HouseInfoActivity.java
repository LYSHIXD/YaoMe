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

public class HouseInfoActivity extends AppCompatActivity implements View.OnClickListener{

    //返回按钮
    private ImageView back_iv;
    //下一步按钮
    private Button next_btn;
    //房屋大小
    private EditText house_size_et;
    //房屋建筑地
    private EditText house_location_et;
    //入住时间
    private RelativeLayout house_come_rl;
    //房屋所有人
    private EditText house_brand_et;
    //房屋用途
    private RelativeLayout house_use_rl;
    private TextView house_use_tv;
    ///房屋照片
    private RelativeLayout house_photo_rl;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_house_info);

        back_iv=(ImageView)this.findViewById(R.id.back_iv);
        next_btn=(Button) this.findViewById(R.id.next_btn);

        house_size_et=(EditText) this.findViewById(R.id.house_size_et);
        house_location_et=(EditText) this.findViewById(R.id.house_location_et);
        house_brand_et=(EditText) this.findViewById(R.id.house_brand_et);

        house_use_tv=(TextView) this.findViewById(R.id.house_use_tv);

        house_come_rl=(RelativeLayout) this.findViewById(R.id.house_come_rl);
        house_use_rl=(RelativeLayout) this.findViewById(R.id.house_use_rl);
        house_photo_rl=(RelativeLayout) this.findViewById(R.id.house_photo_rl);


        back_iv.setOnClickListener(this);
        next_btn.setOnClickListener(this);
        house_come_rl.setOnClickListener(this);
        house_use_rl.setOnClickListener(this);
        house_photo_rl.setOnClickListener(this);




    }

    private PopupWindow popupWindow;
    private String [] strings;
    private ListView listView;

    private void initPopupwindow(){
        View popupwindowView=getLayoutInflater().inflate(R.layout.popupwindow_listview,null);
        popupWindow=new PopupWindow(popupwindowView, ActionBar.LayoutParams.MATCH_PARENT,ActionBar.LayoutParams.WRAP_CONTENT,true);

        //背景颜色
        ColorDrawable colorDrawable=new ColorDrawable(getResources().getColor(R.color.gray));
        popupWindow.setBackgroundDrawable(colorDrawable);

        //显示位置
        popupWindow.showAtLocation(getLayoutInflater().inflate(R.layout.activity_car_info,null), Gravity.BOTTOM|Gravity.CENTER_HORIZONTAL,0,0);

        backgroundAlpha(0.5f);
        popupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                backgroundAlpha(1.0f);
            }
        });

        listView=(ListView)popupwindowView.findViewById(R.id.listview);

        strings=getResources().getStringArray(R.array.houseUse);


        PopupwindowAdapter adapter=new PopupwindowAdapter(this,strings);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                house_use_tv.setText(strings[i]);

                popupWindow.dismiss();
            }
        });
    }

    //设置背景颜色
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
            case R.id.house_come_rl:

                break;
            case R.id.car_use_rl:
                initPopupwindow();
                break;
            case R.id.car_photo_rl:
                intent=new Intent(this,HousePhotoActivity.class);
                startActivity(intent);
                break;
        }
    }
}

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
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.administrator.yaome.adapter.PopupwindowAdapter;

import java.util.ArrayList;
import java.util.List;

public class CompanyAssetInfoActivity extends AppCompatActivity implements View.OnClickListener{
    //返回按钮
    private ImageView back_iv;
    //下一步按钮
    private Button next_btn;
    //企业类型
    private RelativeLayout company_type_rl;
    private TextView company_type_tv;
    //企业规模
    private RelativeLayout company_size_rl;
    private TextView company_size_tv;
    //企业总价值
    private EditText company_value_et;
    //企业年收入
    private EditText company_income_et;
    //企业债务信息
    private RelativeLayout company_debt_rl;
    private TextView company_debt_tv;
    //企业经营时长
    private RelativeLayout company_duration_rl;
    private TextView company_duration_tv;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_company_asset_info);


        back_iv=(ImageView)this.findViewById(R.id.back_iv);
        next_btn=(Button)this.findViewById(R.id.next_btn);

        company_type_tv=(TextView)this.findViewById(R.id.company_type_tv);
        company_size_tv=(TextView)this.findViewById(R.id.company_size_tv);
        company_value_et=(EditText) this.findViewById(R.id.company_value_et);
        company_debt_tv=(TextView)this.findViewById(R.id.company_debt_tv);
        company_income_et=(EditText) this.findViewById(R.id.company_income_et);
        company_duration_tv=(TextView)this.findViewById(R.id.company_duration_tv);

        company_type_rl=(RelativeLayout)this.findViewById(R.id.company_type_rl);
        company_size_rl=(RelativeLayout)this.findViewById(R.id.company_size_rl);
        company_debt_rl=(RelativeLayout)this.findViewById(R.id.company_debt_rl);
        company_duration_rl=(RelativeLayout)this.findViewById(R.id.company_duration_rl);


        company_type_rl.setOnClickListener(this);
        company_size_rl.setOnClickListener(this);
        company_debt_rl.setOnClickListener(this);
        company_duration_rl.setOnClickListener(this);

        back_iv.setOnClickListener(this);
        next_btn.setOnClickListener(this);


        initView();


    }


    private void initView(){

    }

    //点击的标记
    private String tag;

    @Override
    public void onClick(View view) {

        switch (view.getId()){
            case R.id.company_type_rl:
                tag="type";
                initPopupWindow();
                break;
            case R.id.company_size_rl:
                tag="size";
                initPopupWindow();
                break;
            case R.id.company_debt_rl:
                tag="debt";
                initPopupWindow();
                break;
            case R.id.company_duration_rl:
                tag="duration";
                initPopupWindow();
                break;
            case R.id.back_iv:
                finish();
                break;
            case R.id.next_btn:
                Intent intent=new Intent(this,SubmitActivity.class);
                startActivity(intent);
                break;

        }

    }

    private PopupWindow popupWindow;
    private ListView listView;

    //添加 string—array中的数据
    private String [] str;

    //企业类型弹窗
    private void initPopupWindow(){
        View typeView=getLayoutInflater().inflate(R.layout.popupwindow_listview,null);
        popupWindow=new PopupWindow(typeView, ActionBar.LayoutParams.MATCH_PARENT,ActionBar.LayoutParams.WRAP_CONTENT,true);
        //设置背景
        ColorDrawable colorDrawable=new ColorDrawable(getResources().getColor(R.color.gray));
        popupWindow.setBackgroundDrawable(colorDrawable);
        //显示位置
        popupWindow.showAtLocation(getLayoutInflater().inflate(R.layout.activity_company_asset_info,null), Gravity.BOTTOM|Gravity.CENTER_HORIZONTAL,0,0);

        backgroundAlpha(0.5f);

        popupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                backgroundAlpha(1.0f);
            }
        });

        listView=(ListView)typeView.findViewById(R.id.listview);

        //选择加载不同的数据
        if( tag == "type" ){
            str=getResources().getStringArray(R.array.companyType);
        }else if( tag == "size" ){
            str=getResources().getStringArray(R.array.companySize);
        }else if( tag == "debt" ){
            str=getResources().getStringArray(R.array.companyDebt);
        }else if( tag == "duration" ){
            str=getResources().getStringArray(R.array.companyDiration);
        }

        //自定义的 String[]  的adapter
        PopupwindowAdapter adapter=new PopupwindowAdapter(this,str);

        listView.setAdapter(adapter);

        //设置数据
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                switch (tag){
                    case "type":
                        company_type_tv.setText(str[i]);
                        break;
                    case "size":
                        company_size_tv.setText(str[i]);
                        break;
                    case "debt":
                        company_debt_tv.setText(str[i]);
                        break;
                    case "duration":
                        company_duration_tv.setText(str[i]);
                        break;
                }

                popupWindow.dismiss();
            }
        });
    }

    //改变背景颜色
    private void backgroundAlpha(float bgAlpha){
        WindowManager.LayoutParams lp=getWindow().getAttributes();
        lp.alpha=bgAlpha;
        getWindow().setAttributes(lp);
    }

}

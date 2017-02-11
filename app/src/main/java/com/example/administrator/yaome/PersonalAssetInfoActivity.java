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
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.administrator.yaome.adapter.PopupwindowAdapter;

public class PersonalAssetInfoActivity extends AppCompatActivity implements View.OnClickListener{

    //点击区域  使用用途、社保、公积金、信用情况
    private RelativeLayout use_rl,social_security_rl,fund_rl,credit_rl;
    //显示文字  ...同上
    private TextView use_tv,social_security_tv,fund_tv,credit_tv;

    private PopupWindow popupWindow;
    //下一步按钮
    private Button next_btn;
    //返回按钮
    private ImageView back_iv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personal_asset_info);

        use_rl=(RelativeLayout)this.findViewById(R.id.use_rl);
        social_security_rl=(RelativeLayout)this.findViewById(R.id.social_security_rl);
        credit_rl=(RelativeLayout)this.findViewById(R.id.credit_rl);
        fund_rl=(RelativeLayout)this.findViewById(R.id.fund_rl);

        next_btn=(Button)this.findViewById(R.id.next_btn);
        back_iv=(ImageView)this.findViewById(R.id.back_iv);

        use_tv=(TextView)this.findViewById(R.id.use_tv);
        social_security_tv=(TextView)this.findViewById(R.id.social_security_tv);
        credit_tv=(TextView)this.findViewById(R.id.credit_tv);
        fund_tv=(TextView)this.findViewById(R.id.fund_tv);


        initView();

    }


    private void initView(){
        use_rl.setOnClickListener(this);
        social_security_rl.setOnClickListener(this);
        fund_rl.setOnClickListener(this);
        credit_rl.setOnClickListener(this);
        next_btn.setOnClickListener(this);
        back_iv.setOnClickListener(this);
    }

    private ListView listView;
    private String[] strings;



    private void initPopupWindow(){

        final View popupWindowView=this.getLayoutInflater().inflate(R.layout.popupwindow_listview,null);
        popupWindow=new PopupWindow(popupWindowView, ActionBar.LayoutParams.MATCH_PARENT,ActionBar.LayoutParams.WRAP_CONTENT,true);

        ColorDrawable mColor=new ColorDrawable(0xffffffff);
        popupWindow.setBackgroundDrawable(mColor);
        //显示位置
        popupWindow.showAtLocation(getLayoutInflater().inflate(R.layout.activity_personal_asset_info,null), Gravity.BOTTOM|Gravity.CENTER_HORIZONTAL,0,0);

        backgroundAlpha(0.5f);

        listView=(ListView)popupWindowView.findViewById(R.id.listview);
        if( tag == "use"){
            strings=getResources().getStringArray(R.array.moneyUsage);
        }else if( tag == "shebao"|| tag == "fund"){
            strings=getResources().getStringArray(R.array.yes_no);
        }else if( tag == "credit"){
            strings=getResources().getStringArray(R.array.creditInfo);
        }

        PopupwindowAdapter adapter=new PopupwindowAdapter(this,strings);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                switch (tag){
                    case "use":
                        use_tv.setText(strings[i]);
                        break;
                    case "shebao":
                        social_security_tv.setText(strings[i]);
                        break;
                    case "fund":
                        fund_tv.setText(strings[i]);
                        break;
                    case "credit":
                        credit_tv.setText(strings[i]);
                        break;
                }

                popupWindow.dismiss();
            }
        });

        popupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                backgroundAlpha(1.0f);
            }
        });

    }

    private String tag;
    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.use_rl:
                tag="use";
                initPopupWindow();
                break;
            case R.id.social_security_rl:
                tag="shebao";
                initPopupWindow();
                break;
            case R.id.fund_rl:
                tag = "fund";
                initPopupWindow();
                break;
            case R.id.credit_rl:
                tag="credit";
                initPopupWindow();
                break;
            case R.id.next_btn:
                Intent intent=new Intent(this,SubmitActivity.class);
                startActivity(intent);
                break;
            case R.id.back_iv:
                finish();
                break;
        }
    }

    //背景颜色
    private void backgroundAlpha(float bgAlpha){
        WindowManager.LayoutParams lp=getWindow().getAttributes();
        lp.alpha=bgAlpha;
        getWindow().setAttributes(lp);
    }












}

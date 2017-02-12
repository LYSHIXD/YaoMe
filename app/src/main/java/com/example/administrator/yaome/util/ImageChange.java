package com.example.administrator.yaome.util;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.support.v4.view.ViewPager;
import android.widget.ImageView;

import com.example.administrator.yaome.R;


/**
 * Created by LY on 2016/3/15.
 */
public class ImageChange {

    public static void Change(final ViewPager vp, final ImageView iv1, final ImageView iv2,final ImageView iv3, final long time){



        vp.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                if (position == 0) {

                    iv1.setImageResource(R.mipmap.icon_u);
                    iv2.setImageResource(R.mipmap.icon_u_u);
                    iv3.setImageResource(R.mipmap.icon_u_u);

                } else if (position == 1) {

                    iv1.setImageResource(R.mipmap.icon_u_u);
                    iv2.setImageResource(R.mipmap.icon_u);
                    iv3.setImageResource(R.mipmap.icon_u_u);

                } else if (position == 2) {

                    iv1.setImageResource(R.mipmap.icon_u_u);
                    iv2.setImageResource(R.mipmap.icon_u_u);
                    iv3.setImageResource(R.mipmap.icon_u);

                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        final Handler handler=new Handler(){
            @Override
            public void handleMessage(Message msg) {
                switch (msg.what){
                    case 0:
                        vp.setCurrentItem(0);
                        break;
                    case 1:
                        vp.setCurrentItem(1);
                        break;
                    case 2:
                        vp.setCurrentItem(2);
                        break;
                }
            }
        };
        new Thread(new Runnable() {
            @Override
            public void run() {
                for(int i=2;i>0;i++){
                    try {
                        Thread.sleep(time);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                    //设置循环显示
                    if(vp.getCurrentItem() == 0){handler.sendEmptyMessage(1);}
                    else if(vp.getCurrentItem() == 1){handler.sendEmptyMessage(2);}
                    else if(vp.getCurrentItem() == 2){handler.sendEmptyMessage(0);}
                }

            }
        }).start();
    }
    
}

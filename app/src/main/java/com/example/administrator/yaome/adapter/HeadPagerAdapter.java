package com.example.administrator.yaome.adapter;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;

import java.util.List;

/**
 * Created by Administrator on 2017/2/11.
 */

public class HeadPagerAdapter extends PagerAdapter {

    private Context context;
    private List<ImageView> imageViewList;

    public HeadPagerAdapter(Context context,List<ImageView> imageViewList){
        this.context=context;
        this.imageViewList=imageViewList;
    }

    @Override
    public int getCount() {
        return imageViewList.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view==object;
    }


    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {

        ImageView img=imageViewList.get(position);

        container.addView(img);

        return img;
    }
}

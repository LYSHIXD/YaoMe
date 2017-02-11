package com.example.administrator.yaome.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.administrator.yaome.R;

/**
 * Created by Administrator on 2017/2/10.
 */

public class PopupwindowAdapter extends BaseAdapter {

    private String [] strings;
    private Context context;
    private TextView textView;

    public PopupwindowAdapter(Context context, String [] strings){
        this.strings=strings;
        this.context=context;
    }
    @Override
    public int getCount() {
        return strings.length;
    }

    @Override
    public Object getItem(int i) {
        return strings[i];
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        String str=strings[i];


        view= LayoutInflater.from(context).inflate(R.layout.popupwindow_adapter_view,null);

        textView=(TextView)view.findViewById(R.id.popuwindow_tv);

        textView.setText(str);


        return view;
    }

}

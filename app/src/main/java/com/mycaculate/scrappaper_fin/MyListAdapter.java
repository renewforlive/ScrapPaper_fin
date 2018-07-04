package com.mycaculate.scrappaper_fin;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

public class MyListAdapter extends BaseAdapter {
    Context context;
    LayoutInflater inflater;
    TextView showDate,showText;
    LinearLayout color_layout;
    //資料庫的數據
    MySQLAdapter mySQLAdapter;
    String[] scrap_date;
    String[] scrap_text;
    String[] scrap_ipt;


    public MyListAdapter(Context context,String[] scrap_date,String[] scrap_text,String[] scrap_ipt) {
        this.context = context;
        this.scrap_date = scrap_date;
        this.scrap_text = scrap_text;
        this.scrap_ipt = scrap_ipt;
    }

    @Override
    public int getCount() {
        return 0;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        //第一步，將layout灌入context
        inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.scrap_item,null);
        //第二步，取得layout中的資料
        showDate = view.findViewById(R.id.showDate);
        showText = view.findViewById(R.id.showText);
        color_layout = view.findViewById(R.id.color_layout);
        //第三步，獲取資料庫的資料
        mySQLAdapter = new MySQLAdapter(context);
        showDate.setText(scrap_date[position]);
        showText.setText(scrap_text[position]);
        switch (scrap_ipt[position]){
            case "yellow":
                color_layout.setBackgroundResource(R.mipmap.scrap_yellow);
                break;
            case "green":
                color_layout.setBackgroundResource(R.mipmap.scrap_green);
                break;
            case "blue":
                color_layout.setBackgroundResource(R.mipmap.scrap_blue);
                break;
            case "brown":
                color_layout.setBackgroundResource(R.mipmap.scrap_brown);
                break;
            case "red":
                color_layout.setBackgroundResource(R.mipmap.scrap_red);
                break;
        }

        return view;
    }
}

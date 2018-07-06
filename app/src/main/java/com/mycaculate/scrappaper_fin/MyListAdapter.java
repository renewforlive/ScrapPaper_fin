package com.mycaculate.scrappaper_fin;

import android.content.Context;
import android.database.Cursor;
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
    ArrayList<Scrap> arrayList;

    public MyListAdapter(Context context, ArrayList<Scrap> arrayList) {
        this.context = context;
        this.arrayList = arrayList;
    }

    @Override
    public int getCount() {
        return arrayList.size();
    }

    @Override
    public Object getItem(int position) {
        return arrayList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return arrayList.indexOf(getItem(position));
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        //第一步，取得位置
        Scrap scrap  = (Scrap)getItem(position);
        //第二步，將layout灌入context
        inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.scrap_item,null);
        //第二步，取得layout中的資料
        showDate = view.findViewById(R.id.showDate);
        showText = view.findViewById(R.id.showText);
        color_layout = view.findViewById(R.id.color_layout);
        //第三步，獲取資料庫的資料
        showDate.setText(scrap.getScrap_date());
        showText.setText(scrap.getScrap_text());
        if (scrap.getScrap_ipt().equals("yellow")){
            color_layout.setBackgroundResource(R.mipmap.scrap_yellow);
        }
        else if(scrap.getScrap_ipt().equals("green")){
            color_layout.setBackgroundResource(R.mipmap.scrap_green);
        }
        else if(scrap.getScrap_ipt().equals("blue")){
            color_layout.setBackgroundResource(R.mipmap.scrap_blue);
        }
        else if(scrap.getScrap_ipt().equals("brown")){
            color_layout.setBackgroundResource(R.mipmap.scrap_brown);
        }
        else if(scrap.getScrap_ipt().equals("red")){
            color_layout.setBackgroundResource(R.mipmap.scrap_red);
        }
        return view;
    }
}

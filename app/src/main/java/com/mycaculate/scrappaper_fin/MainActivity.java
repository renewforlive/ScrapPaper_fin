package com.mycaculate.scrappaper_fin;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    ListView listView;
    TextView no_scrap;
    Intent intent;
    private MyListAdapter myListAdapter;
    MySQLAdapter mySQLAdapter;
    String[] scrap_date, scrap_text, scrap_ipt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();
        intent = new Intent();

        mySQLAdapter = new MySQLAdapter(this);
        //如果資料庫無資料時
        if (mySQLAdapter.list_scraps().getCount() == 0){
            listView.setVisibility(View.INVISIBLE);
            no_scrap.setVisibility(View.VISIBLE);
        }else{
            listView.setVisibility(View.VISIBLE);
            no_scrap.setVisibility(View.INVISIBLE);
        }
        displayList();
    }
    public void initView(){
        listView = findViewById(R.id.scrapList);
        no_scrap = findViewById(R.id.no_list);
    }
    public void displayList(){
        Cursor cursor = mySQLAdapter.list_scraps();
        String[] columns = {
                mySQLAdapter.SCRAP_DATE,
                mySQLAdapter.TEXT,
                mySQLAdapter.IPT
        };
        int[] to = {
                R.id.showDate,
                R.id.showText,
                R.id.color_layout
        };


        myListAdapter = new MyListAdapter(this, cursor,columns,to);

        listView.setAdapter(myListAdapter);
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.addevent,menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.addEvent:
                intent = new Intent();
                intent.putExtra("type","add");
                intent.setClass(this, EditScrap.class);
                startActivity(intent);
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}

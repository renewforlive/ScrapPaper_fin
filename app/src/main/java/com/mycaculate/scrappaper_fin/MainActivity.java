package com.mycaculate.scrappaper_fin;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    ListView listView;
    TextView no_scrap;
    Intent intent;
    MyListAdapter myListAdapter;
    MySQLAdapter mySQLAdapter;
    ArrayList<Scrap> arrayList;
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
        arrayList = new ArrayList<Scrap>();
    }
    public void displayList(){
        Cursor cursor = mySQLAdapter.list_scraps();
        do{
            int index = cursor.getInt(0);
            String date = cursor.getString(1);
            String text = cursor.getString(2);
            String ipt = cursor.getString(3);
            Scrap scrap = new Scrap(index, date, text, ipt);
            arrayList.add(scrap);
        }
        while (cursor.moveToNext());
        myListAdapter = new MyListAdapter(this,arrayList);

        listView.setAdapter(myListAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Scrap item_cur = (Scrap) listView.getItemAtPosition(position);
                final int item_id = item_cur.getId();

                intent = new Intent();
                intent.putExtra("type","edit");
                intent.putExtra("item_id",item_id);
                intent.setClass(MainActivity.this, EditScrap.class );
                startActivity(intent);
            }
        });
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

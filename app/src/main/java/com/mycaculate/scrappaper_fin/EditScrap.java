package com.mycaculate.scrappaper_fin;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.TimeZone;

public class EditScrap extends AppCompatActivity implements View.OnClickListener{
    TextView showTitle, alertDate, alertTime;
    EditText edtText;
    Button btn_alertDate,btn_alertTime, btn_ok,btn_back;
    MySQLAdapter mySQLAdapter;
    Intent intent;
    Bundle bundle;
    String scrap_date,scrap_text,scrap_ipt;
    int index;
    Spinner scrap_spinner;
    DatePickerDialog datePickerDialog;
    TimePickerDialog timePickerDialog;
    GregorianCalendar calendar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.edit_scrap_paper);
        //初始元件
        initView();
        //初始按鈕事件
        initClick();
        //資料庫物件
        mySQLAdapter = new MySQLAdapter(this);
        //接收其他Activity的資料
        intent = new Intent();
        bundle = this.getIntent().getExtras();
        if(bundle.getString("type").equals("edit")){
            showTitle.setText("修改便條");
            Cursor cursor = mySQLAdapter.queryByID(bundle.getInt("item_id"));
            index = cursor.getInt(0);
            edtText.setText(cursor.getString(2));
        }
        //設定時間
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy年MM月dd日HH:mm:ss");
        formatter.setTimeZone(TimeZone.getTimeZone("Asia/Taipei"));
        Date curDate = new Date(System.currentTimeMillis());
        scrap_date = formatter.format(curDate);

        //設定顏色
        scrap_spinner = findViewById(R.id.color_spinner);
        ArrayAdapter<CharSequence> nAdapter = ArrayAdapter.createFromResource(
                this, R.array.ipt_array, android.R.layout.simple_spinner_item );
        nAdapter.setDropDownViewResource(
                android.R.layout.simple_spinner_dropdown_item);
        scrap_spinner.setAdapter(nAdapter);
        scrap_spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                switch (position){
                    case 0:
                        scrap_ipt = "yellow";
                        break;
                    case 1:
                        scrap_ipt = "green";
                        break;
                    case 2:
                        scrap_ipt = "blue";
                        break;
                    case 3:
                        scrap_ipt = "brown";
                        break;
                    case 4:
                        scrap_ipt = "red";
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }
    public void initView(){
        showTitle = findViewById(R.id.showTitle);
        edtText = findViewById(R.id.edtText);
        btn_alertDate = findViewById(R.id.btn_alertDate);
        btn_alertTime = findViewById(R.id.btn_alertTime);
        btn_ok = findViewById(R.id.btn_ok);
        btn_back = findViewById(R.id.btn_back);
        alertDate = findViewById(R.id.alertDate);
        alertTime = findViewById(R.id.alertTime);

    }
    public void initClick(){
        btn_ok.setOnClickListener(this);
        btn_back.setOnClickListener(this);
        btn_alertDate.setOnClickListener(this);
        btn_alertTime.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_ok:
                scrap_text = edtText.getText().toString();
                if (bundle.getString("type").equals("add")){
                    try{
                        mySQLAdapter.add_scraps(scrap_date,scrap_text,scrap_ipt);
                    }catch (Exception e){
                        e.printStackTrace();
                    }finally {
                        intent.setClass(EditScrap.this,MainActivity.class);
                        startActivity(intent);
                        finish();
                    }
                }else{
                    try{
                        mySQLAdapter.update_scraps(index,scrap_date,scrap_text,scrap_ipt);
                    }catch (Exception e){
                        e.printStackTrace();
                    }finally {
                        intent.setClass(EditScrap.this,MainActivity.class);
                        startActivity(intent);
                        finish();
                    }
                }
                break;
            case R.id.btn_back:
                intent.setClass(EditScrap.this,MainActivity.class);
                startActivity(intent);
                finish();
                break;
            case R.id.btn_alertDate:
                calendar = new GregorianCalendar();
                datePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        month = month+1;
                        alertDate.setText(year + "/" + month + "/" + dayOfMonth);
                    }
                },calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH));
                datePickerDialog.show();
                break;
            case R.id.btn_alertTime:
                calendar = new GregorianCalendar();
                timePickerDialog = new TimePickerDialog(this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                        alertTime.setText(hourOfDay + ":" + minute);
                    }
                },calendar.get(Calendar.HOUR_OF_DAY),calendar.get(Calendar.MINUTE),false);
                timePickerDialog.show();
                break;
        }
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.delete_event,menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.deleteEvent:
                if (bundle.getString("type").equals("add")){
                    Toast.makeText(this,"請先新建便條",Toast.LENGTH_SHORT).show();
                }
                else{
                    try{
                        mySQLAdapter.delet_scraps(index);
                    }catch (Exception e){
                        e.printStackTrace();
                    }finally {
                        intent.setClass(EditScrap.this,MainActivity.class);
                        startActivity(intent);
                        finish();
                    }
                }
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}

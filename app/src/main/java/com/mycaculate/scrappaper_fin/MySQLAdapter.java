package com.mycaculate.scrappaper_fin;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.widget.Toast;

public class MySQLAdapter {
    public static final String KEY_ID = "_id";
    public static final String SCRAP_DATE = "date";
    public static final String TEXT = "text";
    public static final String IPT = "ipt";

    private MySQLHelper mySQLHelper;
    private SQLiteDatabase mdb;
    private final Context mCtx;
    private static final String TABLE_NAME = "scrap_table";
    private Intent i;
    private ContentValues values;

    public MySQLAdapter(Context mCtx) {
        this.mCtx = mCtx;
        open();
    }
    public void open(){
        mySQLHelper = new MySQLHelper(mCtx);
        mdb = mySQLHelper.getWritableDatabase();

    }
    public void close(){
        if (mySQLHelper != null){
            mySQLHelper.close();
        }
    }
    public Cursor list_scraps(){
        Cursor cursor = mdb.query(TABLE_NAME, new String[]{KEY_ID,SCRAP_DATE,TEXT,IPT},null,null,null,null,null);
        if (cursor != null){
            cursor.moveToFirst();
        }
        return cursor;
    }
    public long add_scraps(String date, String text, String ipt){
        try{
            values = new ContentValues();
            values.put(SCRAP_DATE,date);
            values.put(TEXT,text);
            values.put(IPT,ipt);

        }catch (Exception e){
            e.printStackTrace();
        }finally {
            Toast.makeText(mCtx,"建立成功",Toast.LENGTH_SHORT).show();
        }
        return mdb.insert(TABLE_NAME,null,values);
    }
    public long update_scraps(int id,String date, String text,String ipt){
        try{
            values = new ContentValues();
            values.put(SCRAP_DATE, date);
            values.put(TEXT, text);
            values.put(IPT,ipt);

        }catch (Exception e){
            e.printStackTrace();
        }finally {
            Toast.makeText(mCtx,"更新成功!", Toast.LENGTH_SHORT).show();
        }
        return mdb.update(TABLE_NAME,values,"_id=" + id,null);
    }
    public boolean delet_scraps(int id){
        String[] args = {Integer.toString(id)};
        mdb.delete(TABLE_NAME, "_id= ?",args);
        return true;
    }
    public Cursor queryByID(int item_id){
        Cursor mCursor = null;
        mCursor = mdb.query(true, TABLE_NAME, new String[] {KEY_ID, SCRAP_DATE, TEXT, IPT},
                KEY_ID + " = " + item_id, null, null, null, null, null);

        if (mCursor != null) {
            mCursor.moveToFirst();
        }

        return mCursor;
    }
}

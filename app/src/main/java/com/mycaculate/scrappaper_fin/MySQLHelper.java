package com.mycaculate.scrappaper_fin;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class MySQLHelper extends SQLiteOpenHelper {
    public static final String KEY_ID = "_id";
    public static final String SCRAP_DATE = "date";
    public static final String TEXT = "text";
    public static final String IPT = "ipt";

    private static final String DATABASE_NAME = "SCRAP_DB";
    private static final String TABLE_NAME = "scrap";
    private static final int DATABASE_VERSION = 1;

    public MySQLHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        final  String Database_Create = "CREATE TABLE IF NOT EXISTS " + TABLE_NAME + " ( " + KEY_ID +" integer PRIMARY KEY autoincrement, "
                + SCRAP_DATE + ", " + TEXT + ", "+ IPT + " );";
        db.execSQL(Database_Create);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME );
        onCreate(db);
    }
}

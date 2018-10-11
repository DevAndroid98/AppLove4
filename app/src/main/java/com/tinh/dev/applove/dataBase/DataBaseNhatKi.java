package com.tinh.dev.applove.dataBase;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DataBaseNhatKi {

    private static final String DB_NAME="NhatKi";
    private static final String TB_TABLE="Data";
    private static final int DB_VERSION=1;
    private SQLiteDatabase dataBase;

    public DataBaseNhatKi(Context context){
        OpenHelper openHelper=new OpenHelper(context);
        dataBase=openHelper.getWritableDatabase();

    }


    public class OpenHelper extends SQLiteOpenHelper{
        public OpenHelper(Context context) {
            super(context, DB_NAME, null, DB_VERSION);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
              String taobang="CREATE TABLE IF NOT EXISTS Data(_id INTEGER PRIMARY KEY AUTOINCREMENT,Date NVARCHAR,Text NVARCHAR,uri NVARCHAR)";
              db.execSQL(taobang);
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
                           db.execSQL("DROP TABLE IF EXISTS Data");
                           onCreate(db);
        }
    }

    public void insert(String date,String text,String uri){
        ContentValues values=new ContentValues();
        values.put("Date",date);
        values.put("Text",text);
        values.put("uri",uri);
        dataBase.insert(TB_TABLE,null,values); }

        public Cursor getData(){

        return dataBase.query(TB_TABLE,null,
                null,null,null,null,null);
        }
        public void delete(int id){
        dataBase.delete(TB_TABLE,"_id="+id,null);

    }


}

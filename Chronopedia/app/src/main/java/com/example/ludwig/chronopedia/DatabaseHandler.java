package com.example.ludwig.chronopedia;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Handler;
import android.provider.ContactsContract;
import android.util.Log;

import java.util.ArrayList;

import Models.Alarm;

/**
 * Created by Freddie and Helena on 2017-10-30.
 */

public class DatabaseHandler extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "alarm.db";
    private static final String TABLE_NAME = "alarm";
    private static final String COLUMN_ID = "id";
    private static final String COLUMN_DAY = "day";
    private static final String COLUMN_HOUR = "hour";
    private static final String COLUMN_MIN = "min";


    SQLiteDatabase db;

    private static final String TABLE_CREATE = "create table alarm (id integer primary key not null , " +
            "day integer not null , hour integer not null , min integer not null);";

    public DatabaseHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(TABLE_CREATE);
        this.db = db;
    }

    public void insertAlarm(Alarm a)
    {
        db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        String query = "select * from alarm";
        Cursor cursor = db.rawQuery(query, null);
        int count = cursor.getCount();

        values.put(COLUMN_ID, count);
        values.put(COLUMN_DAY, a.getDay());
        values.put(COLUMN_HOUR, a.getHour());
        values.put(COLUMN_MIN, a.getMin());

        db.insert(TABLE_NAME, null, values);
        db.close();
    }

    public Alarm getAlarm(int idToFind)
    {
        db = this.getReadableDatabase();
        String query = "select id, day, hour, min from "+TABLE_NAME;
        Cursor cursor = db.rawQuery(query, null);
        String id;
        Alarm alarm = new Alarm();
//        alarm.setTitle("Alarm not set");
        if (cursor.moveToFirst())
        {
            do {
                id = cursor.getString(0);

                if (id.equals(String.valueOf(idToFind)))
                {
                    alarm.setDate(cursor.getInt(1), cursor.getInt(2), cursor.getInt(3));
                    break;
                }
            }
            while(cursor.moveToNext());
        }
        return alarm;
    }

    public Alarm[] getAllElements() {

        Alarm[] alarmList = new Alarm[(int)getAlarmRows()];
        int count = 0;

        // Select All Query
        String selectQuery = "SELECT  * FROM " + TABLE_NAME;

        db = this.getReadableDatabase();
        try {

            Cursor cursor = db.rawQuery(selectQuery, null);
            try {
                if (cursor.moveToFirst()) {
                    do {
                        Alarm alarmObj = new Alarm();
                        alarmObj.setDate(cursor.getInt(1), cursor.getInt(2), cursor.getInt(3));
                        alarmList[count] = alarmObj;
                        count++;
                    } while (cursor.moveToNext());
                }

            } finally {
                try { cursor.close(); } catch (Exception ignore) {}
            }

        } finally {
            try { db.close(); } catch (Exception ignore) {}
        }
        return alarmList;
    }

    public int numberOfAlarms(){
        Alarm[] alarmList = new Alarm[(int)getAlarmRows()];
        int count = 0;

        // Select All Query
        String selectQuery = "SELECT  * FROM " + TABLE_NAME;

        db = this.getReadableDatabase();
        try {

            Cursor cursor = db.rawQuery(selectQuery, null);
            try {
                if (cursor.moveToFirst()) {
                    do {
                        Alarm alarmObj = new Alarm();
                        alarmObj.setDate(cursor.getInt(1), cursor.getInt(2), cursor.getInt(3));
                        alarmList[count] = alarmObj;
                        count++;
                    } while (cursor.moveToNext());
                }

            } finally {
                try { cursor.close(); } catch (Exception ignore) {}
            }

        } finally {
            try { db.close(); } catch (Exception ignore) {}
        }
        return  count;
    }

    public void deleteEntry(int id) {
        db = getWritableDatabase();
        db.delete(DatabaseHandler.TABLE_NAME, COLUMN_ID+"="+id, null);
    }

    public void deleteAll()
    {
        db = getWritableDatabase();
        db.delete(DatabaseHandler.TABLE_NAME, null, null);
    }

    public long getAlarmRows() {
        db = getReadableDatabase();
        long cnt  = DatabaseUtils.queryNumEntries(db, TABLE_NAME);
//        db.close();
        return cnt;
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String query = "DROP TABLE IF EXISTS " + TABLE_NAME;
        db.execSQL(query);
        this.onCreate(db);
    }
}

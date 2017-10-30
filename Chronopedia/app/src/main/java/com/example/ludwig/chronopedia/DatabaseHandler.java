package com.example.ludwig.chronopedia;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Handler;
import android.provider.ContactsContract;

import Models.Alarm;

/**
 * Created by Freddie and Helena on 2017-10-30.
 */

public class DatabaseHandler extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "alarms.db";
    private static final String TABLE_NAME = "alarms";
    private static final String COLUMN_ID = "id";
    private static final String COLUMN_TITLE = "title";
    private static final String COLUMN_DAY = "day";
    private static final String COLUMN_HOUR = "hour";
    private static final String COLUMN_MIN = "min";

    SQLiteDatabase db;

    private static final String TABLE_CREATE = "create table alarms (id integer primary key not null , " +
            "title text not null , day integer not null , hour integer not null , min integer not null);";

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

        String query = "select * from alarms";
        Cursor cursor = db.rawQuery(query, null);
        int count = cursor.getCount();

        values.put(COLUMN_ID, count);
        values.put(COLUMN_TITLE, a.getTitle());
        values.put(COLUMN_DAY, a.getDay());
        values.put(COLUMN_HOUR, a.getHour());
        values.put(COLUMN_MIN, a.getMin());

        db.insert(TABLE_NAME, null, values);
        db.close();
    }

    public Alarm getAlarm(int idToFind)
    {
        db = this.getReadableDatabase();
        String query = "select id, title, day, hour, min from "+TABLE_NAME;
        Cursor cursor = db.rawQuery(query, null);
        String id;
        Alarm alarm = new Alarm();
        alarm.setTitle("Alarm not set");
        if (cursor.moveToFirst())
        {
            do {
                id = cursor.getString(0);

                if (id.equals(String.valueOf(idToFind)))
                {
                    alarm.setTitle(cursor.getString(1));
                    alarm.setDay(cursor.getInt(2));
                    alarm.setHour(cursor.getInt(3));
                    alarm.setMin(cursor.getInt(4));
                    break;
                }
            }
            while(cursor.moveToNext());
        }
        return alarm;
    }

    public void deleteAll()
    {
        db = getWritableDatabase(); // helper is object extends SQLiteOpenHelper
        db.delete(DatabaseHandler.TABLE_NAME, null, null);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String query = "DROP TABLE IF EXISTS " + TABLE_NAME;
        db.execSQL(query);
        this.onCreate(db);
    }
}

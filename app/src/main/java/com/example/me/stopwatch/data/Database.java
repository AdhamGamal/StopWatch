package com.example.me.stopwatch.data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class Database extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "StopWatch",
            TABLE_Name = "Records_Table",
            KEY_ID = "id",
            KEY_hh = "hh",
            KEY_mm = "mm",
            KEY_ss = "ss",
            KEY_ms = "ms",
            KEY_title = "title",
            KEY_subtitle = "subtitle",
            KEY_measure = "measure";

    public Database(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE " + TABLE_Name + "(" + KEY_ID + " INTEGER PRIMARY KEY AUTO INCREMENT," +
                KEY_title + " TEXT," +
                KEY_subtitle + " TEXT," +
                KEY_hh + " INTEGER," +
                KEY_mm + " INTEGER," +
                KEY_ss + " INTEGER," +
                KEY_ms + " INTEGER," +
                KEY_measure + " TEXT" +
                ")");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int old_version, int new_version) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_Name);
        onCreate(db);
    }

    public void InsertRecord(Record record) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(KEY_title, record.getTitle());
        values.put(KEY_subtitle, record.getSubTitle());
        values.put(KEY_hh, record.getHh());
        values.put(KEY_mm, record.getMm());
        values.put(KEY_ss, record.getSs());
        values.put(KEY_ms, record.getMs());
        values.put(KEY_measure, record.getMeasure());

        db.insert(TABLE_Name, KEY_title+","+KEY_subtitle+","+KEY_hh+KEY_mm+","+KEY_ss+","+KEY_ms+","+KEY_measure, values);
        db.close();

    }

    public void DeleteRecord(Record record) {
        SQLiteDatabase db = getWritableDatabase();
        db.delete(TABLE_Name, KEY_ID + "=?", new String[]{String.valueOf(record.getId())});
        db.close();
    }

    public List<Record> getAllRecords() {

        List<Record> contactList = new ArrayList<Record>();
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_Name, null);
        Record record;
        if (cursor.moveToFirst()) {
            do {
                record = new Record(cursor.getInt(0), cursor.getString(1), cursor.getString(2),
                        cursor.getInt(3), cursor.getInt(4), cursor.getInt(5),
                        cursor.getInt(6), cursor.getString(7));

                contactList.add(record);
            }
            while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return contactList;
    }
}


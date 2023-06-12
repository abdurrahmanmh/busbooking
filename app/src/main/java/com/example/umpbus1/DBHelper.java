package com.example.umpbus1;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


public class DBHelper extends SQLiteOpenHelper {
    public DBHelper(Context context) {
        super(context, "report.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase DB) {
        DB.execSQL("create Table Reports (id INTEGER primary key autoincrement, report TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase DB, int oldVersion, int newVersion) {
        DB.execSQL("drop Table if exists Reports");
    }

    public boolean insertuserdata(String report) {
        SQLiteDatabase DB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("report", report);

        long result = DB.insert("Reports", null, contentValues);

        if (result == -1) {
            return false;
        } else {
            return true;
        }
    }

    public boolean updateuserdata(int id, String report) {
        SQLiteDatabase DB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("id", id);
        contentValues.put("report", report);
        Cursor cursor = DB.rawQuery("Select * from Reports where id = ?", new String[]{String.valueOf(id)});
        if (cursor.getCount() > 0) {


            long result = DB.update("Reports", contentValues, "id=?", new String[]{String.valueOf(id)});

            if (result == -1) {
                return false;
            } else {
                return true;
            }
        } else {
            return false;
        }
    }


    public boolean deleteuserdata(int id) {
        SQLiteDatabase DB = this.getWritableDatabase();
        Cursor cursor = DB.rawQuery("Select * from Reports where id = ?", new String[]{String.valueOf(id)});
        if (cursor.getCount() > 0) {


            long result = DB.delete("Reports", "id=?", new String[]{String.valueOf(id)});

            if (result == -1) {
                return false;
            } else {
                return true;
            }
        } else {
            return false;
        }
    }

    public Cursor getdata() {
        SQLiteDatabase DB = this.getWritableDatabase();
        Cursor cursor = DB.rawQuery("Select * from Reports ", null);
        return cursor;
    }

}

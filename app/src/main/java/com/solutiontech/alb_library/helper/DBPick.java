package com.solutiontech.alb_library.helper;

import static com.solutiontech.alb_library.helper.DBmain.TABLENAME;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.solutiontech.alb_library.models.PickModel;

import java.util.ArrayList;
import java.util.List;

public class DBPick extends SQLiteOpenHelper {
    public static final String DB_NAME = "person.db";
    public static final String TABLE_NAME = "person";
    public static final int VER = 1;



    public DBPick(@Nullable Context context) {
        super(context, DB_NAME, null, VER);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query = "create table " + TABLE_NAME + "(id integer primary key, name text, father text,phone text, adress text, bookname text, date text)";
        db.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String query = "drop table if exists " + TABLE_NAME + "";
        db.execSQL(query);
    }

}

package com.va.kotlintaste.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.va.kotlintaste.constant.DBConstant;

/**
 * Created by Junmeng.Chen on 2017/9/14.
 */

public class TasteDBHelper extends SQLiteOpenHelper {

    public static final String CREATE_TABLE_PERSON = "create table " + DBConstant.TABLE_PERSON +
            "(id integer primary key autoincrement not null," +
            DBConstant.KEY_ID + " integer," +
            DBConstant.KEY_NAME + " varchar(64)," +
            DBConstant.KEY_WHERE + " varchar(64)"
            + ")";

    public TasteDBHelper(Context context) {
        super(context, DBConstant.DB_NAME, null, DBConstant.DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE_PERSON);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}

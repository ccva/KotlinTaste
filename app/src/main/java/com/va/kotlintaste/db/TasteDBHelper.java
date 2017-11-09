package com.va.kotlintaste.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.va.kotlintaste.constant.DbConstant;

/**
 * Created by Junmeng.Chen on 2017/9/14.
 */

public class TasteDBHelper extends SQLiteOpenHelper {

    public static final String CREATE_TABLE_PERSON = "create table " + DbConstant.TABLE_PERSON +
            "(id integer primary key autoincrement not null," +
            DbConstant.KEY_ID + " integer," +
            DbConstant.KEY_NAME + " varchar(64)," +
            DbConstant.KEY_WHERE + " varchar(64)"
            + ")";

    public TasteDBHelper(Context context) {
        super(context, DbConstant.DB_NAME, null, DbConstant.DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE_PERSON);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}

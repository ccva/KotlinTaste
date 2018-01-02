package com.va.kotlintaste.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.va.kotlintaste.constant.DbConstant;

/**
 * Created by Junmeng.Chen on 2017/9/14.
 */

public class TasteDBHelper extends SQLiteOpenHelper {

    public static final String CREATE_TABLE_PERSON = "create table " + DbConstant.Companion.getTABLE_PERSON() +
            "(id integer primary key autoincrement not null," +
            DbConstant.Companion.getKEY_ID() + " integer," +
            DbConstant.Companion.getKEY_NAME() + " varchar(64)," +
            DbConstant.Companion.getKEY_WHERE() + " varchar(64)"
            + ")";

    public TasteDBHelper(Context context) {
        super(context, DbConstant.Companion.getDB_NAME(), null, DbConstant.Companion.getDB_VERSION());
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE_PERSON);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}

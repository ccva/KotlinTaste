package com.va.kotlintaste.contentprovider;

import android.content.ContentProvider;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.SystemClock;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;

import com.va.kotlintaste.constant.DbConstant;
import com.va.kotlintaste.db.TasteDBHelper;
import com.va.kotlintaste.util.Utils;

/**
 *
 * @author Junmeng.Chen
 * @date 2017/9/14
 */

public class PersonProvider extends ContentProvider {

    public static final int PERSON = 100;

    private static UriMatcher uriMatcher;

    static {
        uriMatcher = new UriMatcher(UriMatcher.NO_MATCH);
        uriMatcher.addURI(DbConstant.AUTHORITY_PERSON, DbConstant.PATH_PERSON, PERSON);
    }

    private TasteDBHelper tasteDBHelper;

    private ContentResolver contentResolver;

    @Override
    public boolean onCreate() {
        initProvider();
        contentResolver = getContext().getContentResolver();
        return true;
    }

    private void initProvider() {
        tasteDBHelper = new TasteDBHelper(getContext());

    }

    @Nullable
    @Override
    public String getType(@NonNull Uri uri) {
        return null;
    }

    @Nullable
    @Override
    public Cursor query(@NonNull Uri uri, @Nullable String[] projection, @Nullable String selection,
                        @Nullable String[] selectionArgs, @Nullable String sortOrder) {

        Cursor cursor = null;
        int match = uriMatcher.match(uri);
        switch (match) {
            case PERSON:
                Log.i("cjm", "query provider 1  " + SystemClock.currentThreadTimeMillis());
                SQLiteDatabase database = tasteDBHelper.getReadableDatabase();
                Log.i("cjm", "query provider a2  " + SystemClock.currentThreadTimeMillis());
                cursor = database.query(DbConstant.TABLE_PERSON, projection, selection, selectionArgs, null, null, sortOrder);
                Log.i("cjm", "query provider 3  " + SystemClock.currentThreadTimeMillis());
                cursor.setNotificationUri(contentResolver, uri);
                Log.i("cjm", "query process is " + Utils.getProcessName(getContext(), android.os.Process.myPid()));
                break;
        }
        return cursor;
    }

    @Nullable
    @Override
    public Uri insert(@NonNull Uri uri, @Nullable ContentValues values) {
        long insert = -1;
        int match = uriMatcher.match(uri);
        switch (match) {
            case PERSON:
                SQLiteDatabase database = tasteDBHelper.getWritableDatabase();
                values.put(DbConstant.KEY_WHERE, Utils.getProcessName(getContext(), android.os.Process.myPid()));
                insert = database.insert(DbConstant.TABLE_PERSON, null, values);
                if (insert > 0) {
                    contentResolver.notifyChange(uri, null);
                }
                Log.i("cjm", "insert process is " + Utils.getProcessName(getContext(), android.os.Process.myPid()));
                break;
        }
        return null;
    }

    @Override
    public int delete(@NonNull Uri uri, @Nullable String selection, @Nullable String[] selectionArgs) {
        int delete = -1;
        int match = uriMatcher.match(uri);
        switch (match) {
            case PERSON:
                SQLiteDatabase db = tasteDBHelper.getWritableDatabase();
                delete = db.delete(DbConstant.TABLE_PERSON, selection, selectionArgs);
                if (delete > 0) {
                    contentResolver.notifyChange(uri, null);
                }
                Log.i("cjm", "delete process is " + Utils.getProcessName(getContext(), android.os.Process.myPid()));
                break;
        }
        return delete;
    }

    @Override
    public int update(@NonNull Uri uri, @Nullable ContentValues values, @Nullable String selection, @Nullable String[] selectionArgs) {
        int update = 0;
        int match = uriMatcher.match(uri);
        switch (match) {
            case PERSON:
                SQLiteDatabase db = tasteDBHelper.getWritableDatabase();
                update = db.update(DbConstant.TABLE_PERSON, values, selection, selectionArgs);
                if (update > 0) {
                    contentResolver.notifyChange(uri, null);
                }
                Log.i("cjm", "update process is " + Utils.getProcessName(getContext(), android.os.Process.myPid()));
                break;
        }
        contentResolver.notifyChange(uri, null);
        return update;
    }
}

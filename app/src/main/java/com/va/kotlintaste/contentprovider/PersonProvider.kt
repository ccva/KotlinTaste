package com.va.kotlintaste.contentprovider

import android.content.ContentProvider
import android.content.ContentResolver
import android.content.ContentValues
import android.content.UriMatcher
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.net.Uri
import android.os.SystemClock
import android.util.Log

import com.va.kotlintaste.constant.DbConstant
import com.va.kotlintaste.db.TasteDBHelper
import com.va.kotlintaste.util.Utils

/**
 *
 * @author Junmeng.Chen
 * @date 2017/9/14
 */

class PersonProvider : ContentProvider() {

    private var tasteDBHelper: TasteDBHelper? = null

    private var contentResolver: ContentResolver? = null

    override fun onCreate(): Boolean {
        initProvider()
        contentResolver = context!!.contentResolver
        return true
    }

    private fun initProvider() {
        tasteDBHelper = TasteDBHelper(context)

    }

    override fun getType(uri: Uri): String? {
        return null
    }

    override fun query(uri: Uri, projection: Array<String>?, selection: String?,
                       selectionArgs: Array<String>?, sortOrder: String?): Cursor? {

        var cursor: Cursor? = null
        val match = uriMatcher!!.match(uri)
        when (match) {
            PERSON -> {
                val database = tasteDBHelper!!.readableDatabase
                cursor = database.query(DbConstant.TABLE_PERSON, projection, selection, selectionArgs, null, null, sortOrder)
                cursor!!.setNotificationUri(contentResolver, uri)
            }
        }
        return cursor
    }

    override fun insert(uri: Uri, values: ContentValues?): Uri? {
        var insert: Long = -1
        val match = uriMatcher!!.match(uri)
        when (match) {
            PERSON -> {
                val database = tasteDBHelper!!.writableDatabase
                values!!.put(DbConstant.KEY_WHERE, Utils.getProcessName(context, android.os.Process.myPid()))
                insert = database.insert(DbConstant.TABLE_PERSON, null, values)
                if (insert > 0) {
                    contentResolver!!.notifyChange(uri, null)
                }
            }
        }
        return null
    }

    override fun delete(uri: Uri, selection: String?, selectionArgs: Array<String>?): Int {
        var delete = -1
        val match = uriMatcher!!.match(uri)
        when (match) {
            PERSON -> {
                val db = tasteDBHelper!!.writableDatabase
                delete = db.delete(DbConstant.TABLE_PERSON, selection, selectionArgs)
                if (delete > 0) {
                    contentResolver!!.notifyChange(uri, null)
                }
            }
        }
        return delete
    }

    override fun update(uri: Uri, values: ContentValues?, selection: String?, selectionArgs: Array<String>?): Int {
        var update = 0
        val match = uriMatcher!!.match(uri)
        when (match) {
            PERSON -> {
                val db = tasteDBHelper!!.writableDatabase
                update = db.update(DbConstant.TABLE_PERSON, values, selection, selectionArgs)
            }
        }
        contentResolver!!.notifyChange(uri, null)
        return update
    }

    companion object {

        val PERSON = 100

        private var uriMatcher: UriMatcher? = null

        init {
            uriMatcher = UriMatcher(UriMatcher.NO_MATCH)
            uriMatcher!!.addURI(DbConstant.AUTHORITY_PERSON, DbConstant.PATH_PERSON, PERSON)
        }
    }
}

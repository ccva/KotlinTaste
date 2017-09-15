package com.va.kotlintaste.constant;

/**
 * Created by Junmeng.Chen on 2017/9/14.
 */

public final class DBConstant {

    public static final String PERSON_URI_STRING = "content://" + DBConstant.AUTHORITY_PERSON + "/" + DBConstant.PATH_PERSON;

    public static final String AUTHORITY_PERSON = "com.va.kotlintaste";

    public static final String PATH_PERSON = "person";

    public static final int DB_VERSION = 1;

    public static final String DB_NAME = "taste_db.db";

    public static final String TABLE_PERSON = "person";

    public static final String KEY_ID = "_id";

    public static final String KEY_NAME = "_name";

    public static final String KEY_WHERE = "_where";

}

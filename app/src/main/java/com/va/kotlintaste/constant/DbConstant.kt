package com.va.kotlintaste.constant

/**
 *
 * @author Junmeng.Chen
 * @date 2017/9/14
 */

class DbConstant {

    companion object {

        val PERSON_URI_STRING = "content://" + DbConstant.AUTHORITY_PERSON + "/" + DbConstant.PATH_PERSON

        val AUTHORITY_PERSON = "com.va.kotlintaste"

        val PATH_PERSON = "person"

        val DB_VERSION = 1

        val DB_NAME = "taste_db.db"

        val TABLE_PERSON = "person"

        val KEY_ID = "_id"

        val KEY_NAME = "_name"

        val KEY_WHERE = "_where"
    }

}

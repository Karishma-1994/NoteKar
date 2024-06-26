package com.example.notekar

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper



class DateBaseHelper(context: Context) : SQLiteOpenHelper(context, dbName, null, dbVersion) {

        companion object {
            val dbName = "MyDB"
            val dbVersion = 1
            val TABLE_NAME = "noteTable"
            val ID = "id"
            val TITLE = "title"
            val CONTENT = "content"

        }
        override fun onCreate(db: SQLiteDatabase?) {
            TODO("Not yet implemented")
            val CREATE_TABLE = "CREATE TABLE $TABLE_NAME($ID INTERGER PRIMARY KEY,$TITLE TEXT, $CONTENT TEXT );"
            db?.execSQL(CREATE_TABLE)
        }

        override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
            TODO("Not yet implemented")
            val DROP_TABLE = "DROP TABLE IF EXISTS $TABLE_NAME"
            db?.execSQL(DROP_TABLE)
            onCreate(db)
        }
    }



package com.example.notekar

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.widget.Toast


class DateBaseHelper(
    private val context: Context
) : SQLiteOpenHelper(context, dbName, null, dbVersion) {

    companion object {
        const val dbName = "MyDB"
        const val dbVersion = 1
        const val TABLE_NAME = "noteTable"
        const val ID = "id"
        const val TITLE = "title"
        const val CONTENT = "content"
    }

    override fun onCreate(db: SQLiteDatabase?) {
        val CREATE_TABLE =
            "CREATE TABLE $TABLE_NAME($ID INTEGER PRIMARY KEY AUTOINCREMENT, $TITLE TEXT, $CONTENT TEXT );"
        db?.execSQL(CREATE_TABLE)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        val DROP_TABLE = "DROP TABLE IF EXISTS $TABLE_NAME"
        db?.execSQL(DROP_TABLE)
        onCreate(db)
    }

    fun addNote(title: String?, content: String?) {
        val db = writableDatabase
        val cv = ContentValues()
        cv.put(TITLE, title)
        cv.put(CONTENT, content)
        val result = db.insert(TABLE_NAME, null, cv)
        if (result == -1L) {
            Toast.makeText(context, "Failed", Toast.LENGTH_SHORT).show()
        } else {
            Toast.makeText(context, "Added Successfully", Toast.LENGTH_SHORT).show()
        }
        db.close()
    }

    fun getAll(): List<NoteModel> {
        val returnList: MutableList<NoteModel> = ArrayList()
        val queryString = "SELECT * FROM $TABLE_NAME"
        val db = readableDatabase
        val cursor = db.rawQuery(queryString, null)
        if (cursor.moveToFirst()) {
            do {
                val noteID = cursor.getInt(0)
                val title = cursor.getString(1)
                val content = cursor.getString(2)
                val newNote = NoteModel(noteID, title, content)
                returnList.add(newNote)
            } while (cursor.moveToNext())
        }
        cursor.close()
        db.close()
        return returnList
    }
}



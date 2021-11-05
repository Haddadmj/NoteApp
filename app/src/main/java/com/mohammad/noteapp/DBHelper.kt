package com.mohammad.noteapp

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DBHelper(
    context: Context,
) : SQLiteOpenHelper(context, "NoteDB.db", null, 1) {
    override fun onCreate(db: SQLiteDatabase?) {
        db?.execSQL("CREATE TABLE Notes(ID INTEGER PRIMARY KEY AUTOINCREMENT,Text TEXT)")
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        TODO("Not yet implemented")
    }

    fun insert(note:Note){
        val cv = ContentValues()
        cv.put("text",note.text)
        this.writableDatabase.insert("Notes",null,cv)
    }
}
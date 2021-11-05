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

    fun insert(note: Note) {
        val cv = ContentValues()
        cv.put("Text", note.text)
        this.writableDatabase.insert("Notes", null, cv)
    }

    fun getNotes(): ArrayList<Note> {
        val cursor = this.readableDatabase.query("Notes", null, null, null, null, null, "id")

        cursor.moveToFirst()
        val tempList = arrayListOf<Note>()
        while (!cursor.isAfterLast) {
            val id = cursor.getInt(cursor.getColumnIndex("ID"))
            val text = cursor.getString(cursor.getColumnIndex("Text"))
            tempList.add(Note(id, text))
            cursor.moveToNext()
        }

        cursor.close()

        return tempList
    }

    fun updateNote(note: Note) {
        val cv = ContentValues()
        cv.put("Text", note.text)
        this.writableDatabase.update("Notes", cv, "ID=${note.id}", null)
    }

    fun deleteNote(id: Int) {
        this.writableDatabase.delete("Notes", "ID=$id",null)
    }
}
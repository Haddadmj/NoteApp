package com.mohammad.noteapp

import androidx.room.*

@Dao
interface NoteDao {
    @Query("SELECT * FROM Notes ORDER BY ID")
    fun getNotes() : List<Note>

    @Insert
    fun insertNote(note:Note)

    @Update
    fun updateNote(note:Note)

    @Delete
    fun deleteNote(note: Note)
}
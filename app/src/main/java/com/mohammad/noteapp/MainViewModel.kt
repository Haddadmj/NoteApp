package com.mohammad.noteapp

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData

class MainViewModel(application: Application) : AndroidViewModel(application) {
    private val noteDao = NoteDatabase.getInstance(application).NoteDao()
    private val notes: LiveData<List<Note>> = noteDao.getNotes()

    fun getNotes(): LiveData<List<Note>> = notes

    fun addNote(note: Note) = noteDao.insertNote(note)

    fun updateNote(note: Note) = noteDao.updateNote(note)


    fun deleteNote(note: Note) = noteDao.deleteNote(note)

}
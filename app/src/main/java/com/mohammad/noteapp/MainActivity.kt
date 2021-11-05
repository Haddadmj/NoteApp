package com.mohammad.noteapp

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {

    private lateinit var mainRecyclerView: RecyclerView
    private lateinit var etNote: EditText
    private lateinit var addButton: Button

    private lateinit var noteDao: NoteDao

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mainRecyclerView = findViewById(R.id.rvMain)
        etNote = findViewById(R.id.etNote)
        addButton = findViewById(R.id.addBtn)
        noteDao = NoteDatabase.getInstance(this).NoteDao()

        updateRV(noteDao.getNotes())

        addButton.setOnClickListener {
            noteDao.insertNote(Note(0, etNote.text.toString()))
            Toast.makeText(applicationContext, "Note Added", Toast.LENGTH_SHORT).show()
            etNote.text.clear()
            etNote.clearFocus()
            updateRV(noteDao.getNotes())
        }

    }


    private fun updateRV(list: List<Note>) {
        mainRecyclerView.adapter = NoteAdapter(list, this)
        mainRecyclerView.layoutManager = LinearLayoutManager(this)
        mainRecyclerView.scrollToPosition(list.size - 1)
    }

    fun updateNote(note: Note) {
        noteDao.updateNote(note)
        updateRV(noteDao.getNotes())
    }

    fun deleteNote(note: Note) {
        noteDao.deleteNote(note)
        updateRV(noteDao.getNotes())
    }
}
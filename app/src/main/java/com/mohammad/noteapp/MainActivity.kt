package com.mohammad.noteapp

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {

    private lateinit var mainRecyclerView: RecyclerView
    private lateinit var etNote: EditText
    private lateinit var addButton: Button

    private val mainViewModel by lazy { ViewModelProvider(this).get(MainViewModel::class.java) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mainRecyclerView = findViewById(R.id.rvMain)
        etNote = findViewById(R.id.etNote)
        addButton = findViewById(R.id.addBtn)

        mainViewModel.getNotes().observe(this){
            mainRecyclerView.adapter = NoteAdapter(it, this)
            mainRecyclerView.layoutManager = LinearLayoutManager(this)
            mainRecyclerView.scrollToPosition(it.size - 1)
        }

        addButton.setOnClickListener {
            mainViewModel.addNote(Note(0, etNote.text.toString()))
            Toast.makeText(applicationContext, "Note Added", Toast.LENGTH_SHORT).show()
            etNote.text.clear()
            etNote.clearFocus()
        }

    }

    fun updateNote(note: Note) {
        mainViewModel.updateNote(note)
    }

    fun deleteNote(note: Note) {
        mainViewModel.deleteNote(note)
    }
}
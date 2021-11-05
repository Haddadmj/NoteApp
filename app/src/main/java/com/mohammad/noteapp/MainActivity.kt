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

    private lateinit var dbHelper : DBHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mainRecyclerView = findViewById(R.id.rvMain)
        etNote = findViewById(R.id.etNote)
        addButton = findViewById(R.id.addBtn)
        dbHelper = DBHelper(this)

        updateRV(dbHelper.getNotes())

        addButton.setOnClickListener {
            dbHelper.insert(Note(0, etNote.text.toString()))
            Toast.makeText(applicationContext, "Note Added", Toast.LENGTH_SHORT).show()
            etNote.text.clear()
            etNote.clearFocus()
            updateRV(dbHelper.getNotes())
        }

    }


    private fun updateRV(list: ArrayList<Note>) {
        mainRecyclerView.adapter = NoteAdapter(list,this)
        mainRecyclerView.layoutManager = LinearLayoutManager(this)
        mainRecyclerView.scrollToPosition(list.size - 1)
    }

    fun updateNote(note: Note) {
        dbHelper.updateNote(note)
        updateRV(dbHelper.getNotes())
    }

    fun deleteNote(id: Int) {
        dbHelper.deleteNote(id)
        updateRV(dbHelper.getNotes())
    }
}
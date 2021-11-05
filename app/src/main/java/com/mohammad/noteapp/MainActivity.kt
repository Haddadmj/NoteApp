package com.mohammad.noteapp

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {

    lateinit var mainRecyclerView: RecyclerView
    lateinit var etNote: EditText
    lateinit var addButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mainRecyclerView = findViewById(R.id.rvMain)
        etNote = findViewById(R.id.etNote)
        addButton = findViewById(R.id.addBtn)
        val dbHelper = DBHelper(this)

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
        mainRecyclerView.adapter = NoteAdapter(list)
        mainRecyclerView.layoutManager = LinearLayoutManager(this)
        mainRecyclerView.scrollToPosition(list.size - 1)
    }
}
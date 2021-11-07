package com.mohammad.noteapp

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.Timestamp

class ListNotesFragment : Fragment() {

    private lateinit var mainRecyclerView: RecyclerView
    private lateinit var etNote: EditText
    private lateinit var addButton: Button
    private lateinit var noteAdapter: NoteAdapter

    lateinit var sharedPreferences: SharedPreferences


    val viewModel by lazy { ViewModelProvider(this)[ListNotesViewModel::class.java] }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.list_notes_fragment, container, false)

        mainRecyclerView = view.findViewById(R.id.rvMain)
        etNote = view.findViewById(R.id.etNote)
        addButton = view.findViewById(R.id.addBtn)
        noteAdapter = NoteAdapter(this)
        mainRecyclerView.adapter = noteAdapter
        mainRecyclerView.layoutManager = LinearLayoutManager(requireContext())

        sharedPreferences = requireActivity().getSharedPreferences(
            "Notes",Context.MODE_PRIVATE
        )


        viewModel.getNotes().observe(viewLifecycleOwner){
                notes -> noteAdapter.update(notes)
        }

        addButton.setOnClickListener {
            viewModel.addNote(Note("", etNote.text.toString()))
            Toast.makeText(requireContext(), "Note Added", Toast.LENGTH_SHORT).show()
            etNote.text.clear()
            etNote.clearFocus()
        }

        return view
    }

    fun deleteNote(note: Note) {
        viewModel.deleteNote(note)
    }
}
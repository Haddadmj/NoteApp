package com.mohammad.noteapp

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController

class UpdateNoteFragment : Fragment() {

    lateinit var sharedPreferences: SharedPreferences

    private val updateNoteViewModel by lazy { ViewModelProvider(this)[UpdateNoteViewModel::class.java] }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_update_note, container, false)

        val textView: TextView = view.findViewById(R.id.tvNote)
        val etNewNote: EditText = view.findViewById(R.id.etNewNote)
        val updateBtn: Button = view.findViewById(R.id.updateBtn)
        sharedPreferences = requireActivity().getSharedPreferences(
            "Notes",Context.MODE_PRIVATE
        )

        textView.text = sharedPreferences.getString("NoteText","")
        updateBtn.setOnClickListener {
            updateNoteViewModel.updateNote(Note(sharedPreferences.getString("NoteID","")!!,etNewNote.text.toString()))
            findNavController().navigate(R.id.action_updateNoteFragment_to_listNotesFragment)
        }

        return view
    }
}
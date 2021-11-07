package com.mohammad.noteapp

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.EditText
import androidx.appcompat.app.AlertDialog
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.mohammad.noteapp.databinding.NoteRowBinding

class NoteAdapter(val listNotesFragment: ListNotesFragment) :
    RecyclerView.Adapter<NoteAdapter.ViewHolder>() {
    var notes: List<Note> = emptyList()

    class ViewHolder(val binding: NoteRowBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            NoteRowBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val note = notes[position]
        holder.binding.apply {
            tvNote.text = note.noteText
            editBtn.setOnClickListener {
                with(listNotesFragment.sharedPreferences.edit()){
                    putString("NoteText",note.noteText)
                    putString("NoteID",note.id)
                    apply()
                }
                listNotesFragment.findNavController().navigate(R.id.action_listNotesFragment_to_updateNoteFragment)
            }
            deleteBtn.setOnClickListener { deleteDialog(note) }
        }
    }

    private fun deleteDialog(note: Note) {
        val alertDialog = AlertDialog.Builder(listNotesFragment.requireContext())
        alertDialog.setTitle("Confirm Delete")
        alertDialog.setPositiveButton("Confirm") { _, _ ->
            listNotesFragment.deleteNote(note)
        }
        alertDialog.setNegativeButton("Cancel") { dialog, _ ->
            dialog.dismiss()
        }
        alertDialog.show()
    }


    override fun getItemCount(): Int = notes.size

    fun update(list: List<Note>) {
        this.notes = list
        notifyDataSetChanged()
    }

}

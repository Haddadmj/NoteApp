package com.mohammad.noteapp

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.EditText
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.RecyclerView
import com.mohammad.noteapp.databinding.NoteRowBinding

class NoteAdapter(val list: List<Note>, val activity: MainActivity) : RecyclerView.Adapter<NoteAdapter.ViewHolder>() {
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
        val note = list[position]
        holder.binding.apply {
            tvNote.text = note.noteText
            editBtn.setOnClickListener { editDialog(note) }
            deleteBtn.setOnClickListener { deleteDialog(note) }
        }
    }

    private fun deleteDialog(note: Note) {
        val alertDialog = AlertDialog.Builder(activity)
        alertDialog.setTitle("Confirm Delete")
        alertDialog.setPositiveButton("Confirm"){
                _,_-> activity.deleteNote(note)
        }
        alertDialog.setNegativeButton("Cancel"){
                dialog,_ -> dialog.dismiss()
        }
        alertDialog.show()
    }

    private fun editDialog(note: Note) {
        val alertDialog = AlertDialog.Builder(activity)
        val editText = EditText(activity)
        editText.setText(note.noteText)
        alertDialog.setTitle("Edit Note")
        alertDialog.setView(editText)
        alertDialog.setPositiveButton("Save"){
            _,_-> activity.updateNote(Note(note.timestamp,editText.text.toString()))
        }
        alertDialog.setNegativeButton("Cancel"){
            dialog,_ -> dialog.dismiss()
        }
        alertDialog.show()
    }

    override fun getItemCount(): Int = list.size

}

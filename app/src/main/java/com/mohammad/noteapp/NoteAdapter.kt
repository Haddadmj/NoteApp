package com.mohammad.noteapp

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.mohammad.noteapp.databinding.NoteRowBinding

class NoteAdapter(val list: ArrayList<Note>) : RecyclerView.Adapter<NoteAdapter.ViewHolder>() {
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
            tvNote.text = note.text
        }
    }

    override fun getItemCount(): Int = list.size

}

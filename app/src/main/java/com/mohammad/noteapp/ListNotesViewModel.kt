package com.mohammad.noteapp

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.firestore.FieldValue
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class ListNotesViewModel : ViewModel() {
    private val db = Firebase.firestore
    private val notes: MutableLiveData<List<Note>> = MutableLiveData()
    private val TAG = "Firebase"


    fun getNotes(): LiveData<List<Note>> = notes

    private fun getData() {
        val tempList = arrayListOf<Note>()
        db.collection("Notes")
            .orderBy("timestamp")
            .get()
            .addOnSuccessListener { result ->
                for (document in result) {
                    tempList.add(Note(document.id, document.data["noteText"].toString()))
                }
                notes.postValue(tempList)
            }
            .addOnFailureListener {
                Log.d(TAG, "getNotes: Failed To Get Data")
            }
    }

    fun addNote(note: Note) {
        db.collection("Notes")
            .add(
                hashMapOf(
                    "noteText" to note.noteText,
                    "timestamp" to FieldValue.serverTimestamp()
                )
            )
            .addOnSuccessListener {
                Log.d(TAG, "addNote: Added Successfully")
                getData()
            }
            .addOnFailureListener {
                Log.d(TAG, "addNote: Failed To Add")
            }
    }


    fun deleteNote(note: Note) {
        db.collection("Notes").document(note.id).delete()
            .addOnSuccessListener {
                Log.d(TAG, "deleteNote: Success To Delete")
            }
            .addOnFailureListener {
                Log.d(TAG, "deleteNote: Failed To Delete")
            }
    }
}
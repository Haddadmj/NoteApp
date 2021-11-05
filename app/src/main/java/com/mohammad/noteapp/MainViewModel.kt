package com.mohammad.noteapp

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.google.firebase.firestore.FieldValue
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class MainViewModel(application: Application) : AndroidViewModel(application) {
    private val db = Firebase.firestore
    private val notes: MutableLiveData<List<Note>> = MutableLiveData()
    private val TAG = "Firebase"

    fun getNotes() = notes

    fun getData() {
        db.collection("Notes")
            .orderBy("timestamp")
            .get()
            .addOnSuccessListener {
                notes.postValue(it.toObjects(Note::class.java))
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

    fun updateNote(note: Note) {
        db.collection("Notes")
            .orderBy("timestamp")
            .get()
            .addOnSuccessListener {
                for (document in it)
                    if (document.data["timestamp"] == note.timestamp)
                        db.collection("Notes").document(document.id)
                            .update("noteText", note.noteText)
            }
            .addOnFailureListener {
                Log.d(TAG, "updateNote: Failed To Update")
            }
    }


    fun deleteNote(note: Note) {
        db.collection("Notes")
            .orderBy("timestamp")
            .get()
            .addOnSuccessListener {
                for (document in it)
                    if (document.data["timestamp"] == note.timestamp)
                        db.collection("Notes").document(document.id)
                            .delete()
            }
            .addOnFailureListener {
                Log.d(TAG, "deleteNote: Failed To Delete")
            }
    }

}
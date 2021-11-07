package com.mohammad.noteapp

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class UpdateNoteViewModel : ViewModel() {

    private val db = Firebase.firestore
    private val TAG = "Firebase"


    fun updateNote(note: Note) {
        db.collection("Notes").document(note.id).update("noteText",note.noteText)
            .addOnSuccessListener {
                Log.d(TAG, "updateNote: Success To Update")
            }
            .addOnFailureListener {
                Log.d(TAG, "updateNote: Failed To Update")
            }
    }
}
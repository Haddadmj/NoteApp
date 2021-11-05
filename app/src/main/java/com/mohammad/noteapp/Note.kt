package com.mohammad.noteapp

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.firebase.Timestamp

data class Note(
    val timestamp: Timestamp,
    val noteText:String
){
    constructor() :this(Timestamp.now(),"")
}

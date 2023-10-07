package com.example.mytodolistapp.repository


import androidx.lifecycle.LiveData
import com.example.mytodolistapp.data.NotesData
import com.example.mytodolistapp.db.NotesDatabase

class Repository(
    private val db:NotesDatabase
) {

    fun getNotes():LiveData<List<NotesData>> = db.getDao().getNotes()

    fun insertNotes(notesData: NotesData) = db.getDao().insertNotes(notesData)

    fun onDelete(notesData: NotesData) = db.getDao().delete(notesData)
}
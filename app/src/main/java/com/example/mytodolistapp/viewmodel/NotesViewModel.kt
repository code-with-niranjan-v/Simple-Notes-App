package com.example.mytodolistapp.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.mytodolistapp.data.NotesData
import com.example.mytodolistapp.repository.Repository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class NotesViewModel(
    private val repo:Repository
):ViewModel() {

    fun insertNotes(notesData: NotesData) =
        CoroutineScope(Dispatchers.IO)
        .launch { repo.insertNotes(notesData) }

    fun getNotes():LiveData<List<NotesData>> = repo.getNotes()

    fun onDelete(notesData: NotesData) = CoroutineScope(Dispatchers.IO).launch {
        repo.onDelete(notesData)
    }
}
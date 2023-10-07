package com.example.mytodolistapp.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.CreationExtras
import com.example.mytodolistapp.repository.Repository

class NoteViewModelFactory(
    private val repo:Repository
):ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>, extras: CreationExtras): T {
        return NotesViewModel(repo) as T
    }



}
package com.example.mytodolistapp.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.fragment.app.Fragment
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModelProvider
import com.example.mytodolistapp.R
import com.example.mytodolistapp.data.NotesData
import com.example.mytodolistapp.databinding.ActivityMainBinding
import com.example.mytodolistapp.db.NotesDatabase
import com.example.mytodolistapp.repository.Repository
import com.example.mytodolistapp.ui.fragments.NotesList
import com.example.mytodolistapp.ui.fragments.fragmentUtilities.NotesRecyclerAdapter
import com.example.mytodolistapp.viewmodel.NoteViewModelFactory
import com.example.mytodolistapp.viewmodel.NotesViewModel
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject
import kotlin.concurrent.thread

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    @Inject
    lateinit var viewModelFactory:NoteViewModelFactory
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        replaceFragment(NotesList())

        //val viewModel = ViewModelProvider(this,viewModelFactory).get(NotesViewModel::class.java)



    }

    fun replaceFragment(fragment:Fragment){
        supportFragmentManager.beginTransaction()
            .replace(R.id.container,fragment)
            .commit()
    }

}
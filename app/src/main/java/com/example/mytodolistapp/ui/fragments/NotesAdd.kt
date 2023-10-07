package com.example.mytodolistapp.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.example.mytodolistapp.R
import com.example.mytodolistapp.data.NotesData
import com.example.mytodolistapp.databinding.FragmentNotesAddBinding
import com.example.mytodolistapp.viewmodel.NoteViewModelFactory
import com.example.mytodolistapp.viewmodel.NotesViewModel
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class NotesAdd : Fragment() {
    @Inject
    lateinit var viewModelFactory: NoteViewModelFactory
    private lateinit var notesAddBinding: FragmentNotesAddBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        notesAddBinding = FragmentNotesAddBinding.inflate(inflater,container,false)
        // Inflate the layout for this fragment
        return notesAddBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val viewModel = ViewModelProvider(this,viewModelFactory).get(NotesViewModel::class.java)
        notesAddBinding.btnAdd.setOnClickListener {
            val title = notesAddBinding.etTitle.text.toString()
            val description = notesAddBinding.etDescription.text.toString()

            viewModel.insertNotes(NotesData(title = title, description = description))
            replaceFragment(NotesList())
        }

    }

    private fun replaceFragment(fragment: Fragment){
        parentFragmentManager.beginTransaction()
            .replace(R.id.container,fragment)
            .commit()
    }
}
package com.example.mytodolistapp.ui.fragments

import android.os.Bundle
import android.provider.ContactsContract.CommonDataKinds.Note
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
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

    private lateinit var notesAddBinding: FragmentNotesAddBinding
    private val viewModel:NotesViewModel by viewModels()
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
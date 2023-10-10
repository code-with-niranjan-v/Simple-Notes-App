package com.example.mytodolistapp.ui.fragments

import android.os.Bundle
import android.provider.ContactsContract.CommonDataKinds.Note
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import com.example.mytodolistapp.R
import com.example.mytodolistapp.data.NotesData
import com.example.mytodolistapp.databinding.FragmentNotesAddBinding
import com.example.mytodolistapp.viewmodel.NoteViewModelFactory
import com.example.mytodolistapp.viewmodel.NotesViewModel
import com.google.android.material.snackbar.Snackbar
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
        parentFragmentManager.setFragmentResultListener("notesData",viewLifecycleOwner){request,data->
            val data = data.getSerializable("note") as NotesData
            if (data.id != null && !data.title.isNullOrBlank()){
                notesAddBinding.etTitle.setText(data.title)
                notesAddBinding.etDescription.setText(data.description)
                notesAddBinding.btnAdd.setOnClickListener {
                    val title = notesAddBinding.etTitle.text.toString()
                    val description = notesAddBinding.etDescription.text.toString()

                    viewModel.insertNotes(NotesData(id = data.id,title = title, description = description))
                    replaceFragment(NotesList())
                }
            }


        }

        notesAddBinding.btnAdd.setOnClickListener {
            val title = notesAddBinding.etTitle.text.toString()
            val description = notesAddBinding.etDescription.text.toString()

            if(!title.isNullOrBlank() && !description.isNullOrBlank()){
                viewModel.insertNotes(NotesData(title = title, description = description))

            }
            else{
                Snackbar.make(requireView(),"Empty Notes Deleted",Snackbar.LENGTH_INDEFINITE).show()
            }
            replaceFragment(NotesList())
        }

        notesAddBinding.apply {
            imgBack.setOnClickListener {
                parentFragmentManager.beginTransaction()
                    .replace(R.id.container,NotesList())
                    .commit()
            }
        }


    }

    private fun replaceFragment(fragment: Fragment){
        parentFragmentManager.beginTransaction()
            .replace(R.id.container,fragment)
            .commit()
    }
}
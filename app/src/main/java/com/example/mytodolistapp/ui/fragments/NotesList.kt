package com.example.mytodolistapp.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mytodolistapp.R
import com.example.mytodolistapp.data.NotesData
import com.example.mytodolistapp.databinding.FragmentNotesListBinding
import com.example.mytodolistapp.db.NotesDatabase
import com.example.mytodolistapp.repository.Repository
import com.example.mytodolistapp.ui.fragments.fragmentUtilities.NotesRecyclerAdapter
import com.example.mytodolistapp.ui.fragments.fragmentUtilities.OnClickListener
import com.example.mytodolistapp.viewmodel.NoteViewModelFactory
import com.example.mytodolistapp.viewmodel.NotesViewModel
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class NotesList : Fragment(),OnClickListener {
    @Inject
    lateinit var viewModelFactory:NoteViewModelFactory
    private lateinit var viewModel: NotesViewModel
    private lateinit var notesListBinding: FragmentNotesListBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        notesListBinding = FragmentNotesListBinding.inflate(inflater,container,false)
        return notesListBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(this,viewModelFactory).get(NotesViewModel::class.java)

        viewModel.getNotes().observe(viewLifecycleOwner){
            val adapter = NotesRecyclerAdapter(it,this)
            notesListBinding.recyclerView.adapter = adapter
            notesListBinding.recyclerView.layoutManager = LinearLayoutManager(requireContext())
        }

        notesListBinding.floatingBtn.setOnClickListener {
            parentFragmentManager.beginTransaction()
                .replace(R.id.container,NotesAdd())
                .commit()
        }

    }

    override fun onClickDelete(notesData: NotesData) {
        viewModel.onDelete(notesData)
    }


}
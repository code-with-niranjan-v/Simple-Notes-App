package com.example.mytodolistapp.ui.fragments.fragmentUtilities

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView.Adapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.mytodolistapp.data.NotesData
import com.example.mytodolistapp.databinding.NotesListRvBinding

class NotesViewHolder(private val itemBinding:NotesListRvBinding):ViewHolder(itemBinding.root) {

    fun bindData(notesData: NotesData,listener: OnClickListener){

        itemBinding.tvTitle.text = notesData.title

        itemBinding.imgDelete.setOnClickListener {
            listener.onClickDelete(notesData)
        }
        itemBinding.root.setOnClickListener{
            listener.onClickView(notesData)
        }

    }

}

class NotesRecyclerAdapter(val listOfNotes:List<NotesData>,private val listener:OnClickListener):Adapter<NotesViewHolder>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NotesViewHolder {
        val binding = NotesListRvBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return NotesViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return listOfNotes.size
    }

    override fun onBindViewHolder(holder: NotesViewHolder, position: Int) {
        holder.bindData(listOfNotes[position],listener)
    }
}

interface OnClickListener{
    fun onClickDelete(notesData: NotesData)
    fun onClickView(notesData: NotesData)
}
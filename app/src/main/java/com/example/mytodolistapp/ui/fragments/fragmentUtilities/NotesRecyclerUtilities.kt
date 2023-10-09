package com.example.mytodolistapp.ui.fragments.fragmentUtilities

import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView.Adapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.mytodolistapp.data.NotesData
import com.example.mytodolistapp.databinding.NotesListRvBinding
import kotlin.random.Random

class NotesViewHolder(private val itemBinding:NotesListRvBinding):ViewHolder(itemBinding.root) {

    fun bindData(notesData: NotesData,listener: OnClickListener,color: Int){
        itemBinding.tvTitle.text = notesData.title
        itemBinding.root.setCardBackgroundColor(color)

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
        holder.bindData(listOfNotes[position],listener,getRandomLightColorCode())
    }

    private fun getRandomLightColorCode(): Int {
        val alpha = 255 // You can adjust the alpha value (transparency) if needed
        val minBrightness = 192 // Adjust this value to control the minimum brightness

        var red: Int
        var green: Int
        var blue: Int

        do {
            red = Random.nextInt(256)
            green = Random.nextInt(256)
            blue = Random.nextInt(256)
        } while (calculateBrightness(red, green, blue) < minBrightness)

        return Color.argb(alpha, red, green, blue)
    }

    private fun calculateBrightness(red: Int, green: Int, blue: Int): Double {
        return (0.299 * red + 0.587 * green + 0.114 * blue)
    }
}

interface OnClickListener{
    fun onClickDelete(notesData: NotesData)
    fun onClickView(notesData: NotesData)
}
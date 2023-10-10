package com.example.mytodolistapp.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.mytodolistapp.data.NotesData

@Dao
interface NotesDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertNotes(notes:NotesData)

    @Query("select * from notes_table")
    fun getNotes():LiveData<List<NotesData>>

    @Delete
    fun delete(notes: NotesData)

    @Query("select * from notes_table")
    fun getNotesData():List<NotesData>

    @Query("select * from notes_table order by id desc")
    fun getNotesByDesc():List<NotesData>

    @Query("select * from notes_table where title like :searchQuery")
    fun onSearchNotes(searchQuery: String):LiveData<List<NotesData>>

}
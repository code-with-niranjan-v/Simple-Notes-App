package com.example.mytodolistapp.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.mytodolistapp.data.NotesData

@Database(entities = [NotesData::class], version = 1)
abstract class NotesDatabase:RoomDatabase() {

    abstract fun getDao():NotesDao


    companion object{

        @Volatile
        private var instance:NotesDatabase? = null
        private val LOCK = Any()

        operator fun invoke(context: Context) = instance ?: synchronized(LOCK){
            instance ?: createDatabase(context).also {
                instance = it
            }
        }

        private fun createDatabase(context: Context):NotesDatabase = Room.databaseBuilder(
            context,NotesDatabase::class.java,"notes_database"
        ).build()


    }

}
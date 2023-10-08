package com.example.mytodolistapp.db

import android.content.Context
import android.provider.ContactsContract.CommonDataKinds.Note
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import com.example.mytodolistapp.data.NotesData
import junit.framework.TestCase
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@RunWith(JUnit4::class)
class NotesDatabaseTest:TestCase(){

    private lateinit var db:NotesDatabase
    @Before
    fun setup(){

        db = Room.databaseBuilder(ApplicationProvider.getApplicationContext(),NotesDatabase::class.java,"notes_database").build()

    }

    @After
    fun closeDb(){
        db.close()
    }

    @Test
    fun testInsertingNotes(){
        val note = NotesData(id = 1, title = "Title", description = "Sample description")
        db.getDao().insertNotes(note)
        assertTrue(db.getDao().getNotesData().contains(note))

    }

    @Test
    fun testingDelete(){
        val note = NotesData(id = 1, title = "Title", description = "Sample description")
        db.getDao().delete(note)
        assertFalse(db.getDao().getNotesData().contains(note))
    }

    @Test
    fun testingOrderByDesc    (){
        val note1 = NotesData(id = 1, title = "Title", description = "Sample description")
        val note2 = NotesData(id = 2, title = "Title", description = "Sample description")
        val note3 = NotesData(id = 3, title = "Title", description = "Sample description")
        db.getDao().insertNotes(note1)
        db.getDao().insertNotes(note2)
        db.getDao().insertNotes(note3)
        assertTrue(db.getDao().getNotesByDesc()[0]==note3)

    }

}
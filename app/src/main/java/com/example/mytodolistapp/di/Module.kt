package com.example.mytodolistapp.di

import android.content.Context
import androidx.room.Database
import com.example.mytodolistapp.db.NotesDatabase
import com.example.mytodolistapp.repository.Repository
import com.example.mytodolistapp.viewmodel.NoteViewModelFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class Module {

    @Provides
    @Singleton
    fun provideDb(@ApplicationContext app:Context):NotesDatabase = NotesDatabase(app)

    @Provides
    @Singleton
    fun provideRepo(db:NotesDatabase):Repository = Repository(db)

    @Provides
    @Singleton
    fun provideViewModelFactory(repo:Repository):NoteViewModelFactory = NoteViewModelFactory(repo)



}
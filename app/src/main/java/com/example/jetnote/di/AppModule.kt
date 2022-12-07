package com.example.jetnote.di

import android.content.Context
import androidx.room.Room
import com.example.jetnote.data.NoteDatabase
import com.example.jetnote.data.NoteDatabaseDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module

object AppModule {

    @Singleton
    @Provides
    fun provideNoteDao(noteDatabase: NoteDatabase) : NoteDatabaseDao
    = noteDatabase.noteDao()


    @Singleton
    @Provides
    fun provideNoteDatabase(@ApplicationContext context: Context) : NoteDatabase
    =Room.databaseBuilder(context, NoteDatabase::class.java, "note_db")
            .fallbackToDestructiveMigration().build()

}
package com.example.jetnote.repository

import com.example.jetnote.data.NoteDatabaseDao
import com.example.jetnote.model.Note
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.conflate
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class NoteRepository @Inject constructor(private val noteDatabaseDao: NoteDatabaseDao) {

    fun getAllNotes() : Flow<List<Note>> =
        noteDatabaseDao.getAllNotes().flowOn(Dispatchers.IO).conflate()

    suspend fun getNoteById(id : String) : Note
    = noteDatabaseDao.getNoteById(id)

    suspend fun insert(note: Note)
    = noteDatabaseDao.insert(note)

    suspend fun update(note: Note)
    = noteDatabaseDao.update(note)

    suspend fun deleteAll()
    = noteDatabaseDao.deleteAll()

    suspend fun deleteNote(note: Note)
    = noteDatabaseDao.deleteNote(note)
}
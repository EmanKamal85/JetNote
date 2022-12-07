package com.example.jetnote.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.jetnote.model.Note

@Dao
interface NoteDatabaseDao {

    @Query("SELECT * from notes_table")
    fun getAllNotes() : kotlinx.coroutines.flow.Flow<List<Note>>

    @Query("SELECT * from notes_table where id =:id")
    suspend fun getNoteById(id : String) : Note

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(note: Note)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    suspend fun update(note: Note)

    @Query("DELETE from notes_table")
    suspend fun deleteAll()

    @Delete
    suspend fun deleteNote(note: Note)

}

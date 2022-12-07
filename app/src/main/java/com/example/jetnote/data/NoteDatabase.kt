package com.example.jetnote.data

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.bawp.jetnote.util.DateConverter
import com.bawp.jetnote.util.UUIDConverter
import com.example.jetnote.model.Note

@Database(entities = [Note :: class], version = 1, exportSchema = false)
@TypeConverters(DateConverter :: class, UUIDConverter :: class)
abstract class NoteDatabase : RoomDatabase(){
    abstract fun noteDao() : NoteDatabaseDao
}
package com.example.jetnote.screen

import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.jetnote.data.NotesDataSource
import com.example.jetnote.model.Note
import com.example.jetnote.repository.NoteRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@RequiresApi(Build.VERSION_CODES.O)
@HiltViewModel
class NoteViewModel @Inject constructor(private val repository: NoteRepository): ViewModel(){

    //private var noteList = mutableStateListOf<Note>()

    private val _noteList = MutableStateFlow<List<Note>>(emptyList())
    val noteList = _noteList.asStateFlow()

    init {
        //noteList.addAll(NotesDataSource().loadNotes())

        viewModelScope.launch(Dispatchers.IO) {
            repository.getAllNotes().distinctUntilChanged()
                .collect(){listOfNotes ->
                    if (listOfNotes.isNullOrEmpty()){
                        Log.d("Empty", ": Empty List ")
                    }else{
                        _noteList.value = listOfNotes
                    }

                }
        }
    }


//    fun addNote(note: Note){
//        noteList.add(note)
//    }

     fun getNoteById(id : String) : Job =
        viewModelScope.launch { repository.getNoteById(id) }

     fun addNote(note: Note) =
        viewModelScope.launch { repository.insert(note) }

//    fun removeNote(note: Note){
//        noteList.remove(note)
//    }

     fun removeNote(note: Note) =
        viewModelScope.launch { repository.deleteNote(note) }

//    fun getNotesList() : List<Note>{
//        return noteList
//    }



     fun updateNote(note: Note) =
        viewModelScope.launch { repository.update(note) }

     fun deleteAllNotes() =
        viewModelScope.launch { repository.deleteAll() }


}
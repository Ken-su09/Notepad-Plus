package com.suonk.notepad_plus.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.suonk.notepad_plus.models.data.Note
import com.suonk.notepad_plus.repositories.NoteRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NoteViewModel @Inject constructor(private val repository: NoteRepository) : ViewModel() {

    //region ===================================== Database Operations ======================================

    val allNotes = repository.allNotes.asLiveData()
    val allFavoriteNotes = repository.allFavoriteNotes.asLiveData()
    val allDeletedNotes = repository.allDeletedNotes.asLiveData()

    fun createNewNote(note: Note) = viewModelScope.launch {
        repository.createNewNote(note)
    }

    fun updateNote(note: Note) = viewModelScope.launch {
        repository.updateNote(note)
    }

    fun deleteNote(note: Note) = viewModelScope.launch {
        repository.deleteNote(note)
    }

    //endregion

    //region ==================================== Data Between Fragments ====================================

    val searchBarText = MutableLiveData<String>()
    fun setSearchBarText(text: String) {
        searchBarText.value = text
    }

    val noteLiveData = MutableLiveData<Note>()
    fun setNoteLiveData(note: Note) {
        noteLiveData.value = note
    }

    //endregion

}
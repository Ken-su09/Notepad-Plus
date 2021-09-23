package com.suonk.notepad_plus.repositories

import androidx.annotation.WorkerThread
import com.suonk.notepad_plus.models.dao.NoteDao
import com.suonk.notepad_plus.models.data.Note
import javax.inject.Inject

class NoteRepository @Inject constructor(private val dao: NoteDao) {

    val allNotes = dao.getAllNotes()
    val allFavoriteNotes = dao.getAllFavoriteNotes()
    val allDeletedNotes = dao.getAllNotesDeleted()

    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun createNewNote(note: Note){
        dao.createNewNote(note)
    }

    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun updateNote(note: Note){
        dao.updateNote(note)
    }

    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun deleteNote(note: Note){
        dao.deleteNote(note)
    }
}
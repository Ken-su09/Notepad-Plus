package com.suonk.notepad_plus.models.dao

import androidx.room.*
import com.suonk.notepad_plus.models.data.Note
import kotlinx.coroutines.flow.Flow

@Dao
interface NoteDao {

    /**
     *  getAllNotes() = note1, note2, note3....
     */
    @Query("SELECT * FROM note WHERE isDeleted == 0 ORDER BY date ASC")
    fun getAllNotes(): Flow<List<Note>>

    /**
     *  getAllFavoriteNotes() = favoriteNote1, favoriteNote2, favoriteNote3....
     */
    @Query("SELECT * FROM note WHERE isFavorite == 1 ORDER BY date ASC")
    fun getAllFavoriteNotes(): Flow<List<Note>>

    /**
     *  getAllNotesDeleted() = deletedNote1, deletedNote2, deletedNote3....
     */
    @Query("SELECT * FROM note WHERE isDeleted == 1 ORDER BY date ASC")
    fun getAllNotesDeleted(): Flow<List<Note>>

    /**
     *  createNewNote(note)
     */
    @Insert
    suspend fun createNewNote(note: Note)

    /**
     *  updateNote(note)
     */
    @Update
    suspend fun updateNote(note: Note)

    /**
     *  deleteNote(note)
     */
    @Delete
    suspend fun deleteNote(note: Note)
}
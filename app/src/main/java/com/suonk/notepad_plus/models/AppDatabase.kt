package com.suonk.notepad_plus.models

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.suonk.notepad_plus.models.dao.NoteDao
import com.suonk.notepad_plus.models.data.Note
import com.suonk.notepad_plus.utils.Converters


@Database(entities = [Note::class], version = 1, exportSchema = false)
@TypeConverters(Converters::class)
abstract class AppDatabase : RoomDatabase() {

    abstract fun noteDao(): NoteDao
}
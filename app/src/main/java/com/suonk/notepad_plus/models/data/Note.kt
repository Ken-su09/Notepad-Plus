package com.suonk.notepad_plus.models.data

import android.graphics.Bitmap
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "note")
data class Note(
    @ColumnInfo(name = "title")
    var title: String,
    @ColumnInfo(name = "content")
    var content: String,
    @ColumnInfo(name = "img")
    var img: Bitmap? = null,
    @ColumnInfo(name = "date")
    var date: String,
    @ColumnInfo(name = "color")
    var color: String,
    @ColumnInfo(name = "isFavorite")
    var isFavorite: Int,
    @ColumnInfo(name = "isDeleted")
    var isDeleted: Int,
    @ColumnInfo(name = "id") @PrimaryKey(autoGenerate = true)
    var id: Int

)
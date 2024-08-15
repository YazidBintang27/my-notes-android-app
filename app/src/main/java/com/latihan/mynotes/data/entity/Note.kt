package com.latihan.mynotes.data.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "note_table")
data class Note(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,

    @ColumnInfo(name = "title")
    val title: String = "",

    @ColumnInfo(name = "note")
    val note: String = "",

    @ColumnInfo(name = "bookmark")
    val bookmarkStatus: Boolean = false
)
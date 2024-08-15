package com.latihan.mynotes.data.datasource

import androidx.room.Database
import androidx.room.RoomDatabase
import com.latihan.mynotes.data.entity.Note

@Database(entities = [Note::class], version = 1, exportSchema = false)
abstract class NoteDatabase: RoomDatabase() {
    abstract val noteDao: NoteDao
}
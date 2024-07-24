package com.latihan.mynotes.data.datasource

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.latihan.mynotes.data.entity.Note
import kotlinx.coroutines.flow.Flow

@Dao
interface NoteDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert(note: Note)

    @Update
    fun update(note: Note)

    @Delete
    fun delete(note: Note)

    @Query("SELECT * FROM note_table")
    fun getAllNote(): Flow<List<Note>>

    @Query("SELECT * FROM note_table WHERE id = :id")
    fun getNoteById(id: Int): Note

    @Query("SELECT * FROM note_table WHERE title = :title")
    fun getNoteByTitle(title: String): Flow<Note>
}
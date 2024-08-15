package com.latihan.mynotes.data.repository

import com.latihan.mynotes.data.entity.Note
import kotlinx.coroutines.flow.Flow

interface NoteRepository {
    suspend fun insertNote(note: Note)

    suspend fun updateNote(note: Note)

    suspend fun deleteNote(note: Note)

    suspend fun getAllNote(): Flow<List<Note>>

    suspend fun getNoteById(id: Int): Note

    suspend fun getNoteByTitle(title: String): Flow<Note>
}
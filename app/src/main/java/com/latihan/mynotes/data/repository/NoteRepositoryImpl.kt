package com.latihan.mynotes.data.repository

import com.latihan.mynotes.data.datasource.NoteDao
import com.latihan.mynotes.data.entity.Note
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class NoteRepositoryImpl @Inject constructor(
    private val noteDao: NoteDao
): NoteRepository {
    override suspend fun insertNote(note: Note) {
        noteDao.insert(note)
    }

    override suspend fun updateNote(note: Note) {
        noteDao.update(note)
    }

    override suspend fun deleteNote(note: Note) {
        noteDao.delete(note)
    }

    override suspend fun getAllNote(): Flow<List<Note>> {
        return noteDao.getAllNote()
    }

    override suspend fun getNoteById(id: Int): Note {
        return noteDao.getNoteById(id)
    }

    override suspend fun getNoteByTitle(title: String): Flow<Note> {
        return noteDao.getNoteByTitle(title)
    }
}
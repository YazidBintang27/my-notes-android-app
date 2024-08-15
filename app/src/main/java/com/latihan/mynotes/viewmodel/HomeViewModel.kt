package com.latihan.mynotes.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.latihan.mynotes.data.entity.Note
import com.latihan.mynotes.data.repository.NoteRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val noteRepository: NoteRepository
): ViewModel() {
    private val _allNotes = MutableStateFlow(emptyList<Note>())

    val allNotes: StateFlow<List<Note>> = _allNotes.asStateFlow()

    init {
        getAllNotes()
    }

    fun getAllNotes() {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                noteRepository.getAllNote().collectLatest {
                    _allNotes.tryEmit(it)
                }
            }
        }
    }

    fun deleteNote(note: Note) {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                noteRepository.deleteNote(note)
            }
        }
    }
}
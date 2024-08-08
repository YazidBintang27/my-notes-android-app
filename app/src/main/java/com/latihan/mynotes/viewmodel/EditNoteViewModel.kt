package com.latihan.mynotes.viewmodel

import android.util.Log
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
class EditNoteViewModel @Inject constructor(
    private val noteRepository: NoteRepository
): ViewModel() {

    private val _dataNotes = MutableStateFlow(Note())

    val dataNotes: StateFlow<Note>
        get() = _dataNotes.asStateFlow()

    fun getNote(index: Int) {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                noteRepository.getAllNote().collectLatest { note ->
                    _dataNotes.tryEmit(note[index])
                }
            }
        }
    }

    fun updateData(title: String, note: String) {
        viewModelScope.launch {
            if (title != "" || note != "") {
                val updatedNote = _dataNotes.value.copy(title = title, note = note)
                Log.d("dataupdateviewmodel", "Title: $title, note: $note")
                withContext(Dispatchers.IO) {
                    noteRepository.updateNote(updatedNote)
                    Log.d("dataupdateviewmodel", "datanote: ${updatedNote.title} ${updatedNote.note}")
                }
            }
        }
    }
}
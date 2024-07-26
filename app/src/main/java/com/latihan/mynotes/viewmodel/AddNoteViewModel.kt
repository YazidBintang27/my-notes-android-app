package com.latihan.mynotes.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.latihan.mynotes.data.entity.Note
import com.latihan.mynotes.data.repository.NoteRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class AddNoteViewModel @Inject constructor(
    private val noteRepository: NoteRepository
): ViewModel() {
    fun insertData(title: String, note: String) {
        viewModelScope.launch {
            if (title != "" || note != "") {
                val dataNote = Note(title = title, note = note)
                withContext(Dispatchers.IO) {
                    noteRepository.insertNote(dataNote)
                }
            }
        }
    }
}
package com.latihan.mynotes.ui.pages

import android.util.Log
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.latihan.mynotes.R
import com.latihan.mynotes.data.entity.Note
import com.latihan.mynotes.viewmodel.AddNoteViewModel
import com.latihan.mynotes.viewmodel.EditNoteViewModel

@Composable
fun EditNotePage(
    modifier: Modifier = Modifier,
    navHostController: NavHostController,
    index: Int?
) {
    EditNotePageContent(
        modifier = modifier,
        navHostController = navHostController,
        index = index
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EditNotePageContent(
    modifier: Modifier,
    navHostController: NavHostController,
    index: Int?
) {
    val editNoteViewModel: EditNoteViewModel = hiltViewModel()
    val dataNotes by editNoteViewModel.dataNotes.collectAsState()

    LaunchedEffect(index) {
        editNoteViewModel.getNoteById(index)
        Log.d("getnote", "Launch getnotebyid")
    }

    var title by remember { mutableStateOf(dataNotes.title) }
    var note by remember { mutableStateOf(dataNotes.note) }

    LaunchedEffect(dataNotes) {
        title = dataNotes.title
        note = dataNotes.note
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = "Update Note") },
                navigationIcon = {
                    IconButton(onClick = { navHostController.popBackStack() }) {
                        Icon(
                            painter = painterResource(id = R.drawable.arrow_left_01_stroke_rounded),
                            contentDescription = "Back Icon"
                        )
                    }
                },
                actions = {
                    IconButton(onClick = {
                        editNoteViewModel.updateData(title = title, note = note)
                        Log.d("datanote", "title: $title, note; $note")
                        navHostController.popBackStack()
                    }) {
                        Icon(
                            painter = painterResource(id = R.drawable.pencil_edit_02_stroke_rounded),
                            contentDescription = "Edit Icon"
                        )
                    }
                }
            )
        }
    ) { innerPadding ->
        Column(
            modifier = modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(horizontal = 20.dp)
        ) {
            TextField(
                value = title,
                onValueChange = { title = it },
                modifier = Modifier.fillMaxWidth(),
                placeholder = {
                    Text(text = "Title")
                },
                maxLines = 2,
                prefix = {
                    Icon(
                        painter = painterResource(id = R.drawable.pencil_edit_01_stroke_rounded),
                        contentDescription = null,
                        modifier = Modifier.padding(end = 8.dp)
                    )
                },
                colors = TextFieldDefaults.colors(
                    focusedContainerColor = Color.Transparent,
                    unfocusedContainerColor = Color.Transparent,
                ),
            )
            TextField(
                value = note,
                onValueChange = { note = it },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 12.dp),
                placeholder = {
                    Text(text = "Note")
                },
                maxLines = 20,
                prefix = {
                    Icon(
                        painter = painterResource(id = R.drawable.pencil_edit_01_stroke_rounded),
                        contentDescription = null,
                        modifier = Modifier.padding(end = 8.dp)
                    )
                },
                colors = TextFieldDefaults.colors(
                    focusedContainerColor = Color.Transparent,
                    unfocusedContainerColor = Color.Transparent,
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent
                ),
            )
        }
    }
}

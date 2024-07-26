package com.latihan.mynotes.ui.pages

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldColors
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.latihan.mynotes.R
import com.latihan.mynotes.viewmodel.AddNoteViewModel


@Composable
fun AddNotePage(
    modifier: Modifier = Modifier,
    navHostController: NavHostController
) {
    AddNotePageContent(modifier = modifier, navHostController = navHostController)
}
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddNotePageContent(
    modifier: Modifier,
    navHostController: NavHostController,
) {
    val addNoteViewModel: AddNoteViewModel = hiltViewModel()
    var title by remember { mutableStateOf("") }
    var note by remember { mutableStateOf("") }
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = "Add Note") },
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
                        addNoteViewModel.insertData(title, note)
                        navHostController.popBackStack()
                    }) {
                        Icon(
                            painter = painterResource(id = R.drawable.add_square_stroke_rounded),
                            contentDescription = "Add Icon"
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

package com.latihan.mynotes.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardColors
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.latihan.mynotes.R
import com.latihan.mynotes.data.entity.Note
import com.latihan.mynotes.routes.Pages
import com.latihan.mynotes.viewmodel.HomeViewModel

@Composable
fun NotesCard(
    modifier: Modifier = Modifier,
    note: Note,
    navHostController: NavHostController,
    index: Int
) {
    val homeViewModel: HomeViewModel = hiltViewModel()
    var isBookmark by remember { mutableStateOf(false) }
    var showDialog by remember { mutableStateOf(false) }
    Card(
        colors = CardColors(
            containerColor = MaterialTheme.colorScheme.primary,
            contentColor = MaterialTheme.colorScheme.onPrimary,
            disabledContentColor = MaterialTheme.colorScheme.onPrimary,
            disabledContainerColor = MaterialTheme.colorScheme.onPrimary
        ),
        modifier = Modifier
            .height(280.dp)
            .padding(12.dp)
            .clip(shape = RoundedCornerShape(14.dp)),
        onClick = {
            navHostController.navigate("${Pages.EditNote.route}/${index}") {
                popUpTo(Pages.EditNote.route) {
                    inclusive = true
                }
            }
        },
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
        ) {
            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 12.dp, end = 4.dp, top = 8.dp)
            ) {
                Text(
                    text = note.title,
                    fontFamily = FontFamily(Font(R.font.opensans_semibold)),
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                    maxLines = 2,
                    softWrap = true,
                    overflow = TextOverflow.Ellipsis,
                    modifier = Modifier.weight(3f)
                )
                IconButton(
                    onClick = {isBookmark = !isBookmark},
                    modifier = Modifier.weight(1f)
                ) {
                    Icon(
                        painter = if (isBookmark) {
                            painterResource(id = R.drawable.bookmark_true)
                        } else {
                            painterResource(id = R.drawable.bookmark_false)
                        },
                        contentDescription = "Bookmark"
                    )
                }
            }
            Spacer(modifier = Modifier.height(12.dp))
            Text(
                text = note.note,
                fontFamily = FontFamily(Font(R.font.opensans_regular)),
                color = Color(0xFF2A2A2A),
                maxLines = 6,
                softWrap = true,
                overflow = TextOverflow.Ellipsis,
                modifier = Modifier
                    .padding(horizontal = 12.dp)
                    .weight(1f)
            )
            IconButton(
                onClick = {
                    showDialog = true
                },
                modifier = Modifier.align(Alignment.CenterHorizontally)
            ) {
               Icon(
                   painter = painterResource(id = R.drawable.delete_01_stroke_rounded),
                   contentDescription = "Delete",
                   tint = Color(0xFFef233c)
               )
            }
        }
        if (showDialog) {
            AlertDialog(
                onDismissRequest = { showDialog = false },
                title = {
                    Text(text = "Are you sure?")
                },
                text = {
                    Text(text = "This note will be permanently deleted")
                },
                confirmButton = {
                    Button(onClick = {
                        homeViewModel.deleteNote(note)
                        showDialog = false
                    }) {
                        Text(text = "Confirm")
                    }
                },
                dismissButton = {
                    Button(onClick = { showDialog = false }) {
                        Text(text = "Cancel")
                    }
                }
            )
        }
    }
}
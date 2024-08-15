package com.latihan.mynotes.ui.pages

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController

@Composable
fun BookmarkListPage(
    modifier: Modifier = Modifier,
    navHostController: NavHostController
) {
    BookmarkListPageContent(
        modifier = modifier,
        navHostController = navHostController
    )
}

@Composable
fun BookmarkListPageContent(
    modifier: Modifier,
    navHostController: NavHostController
) {
    Text(
        text = "Bookmark List"
    )
}

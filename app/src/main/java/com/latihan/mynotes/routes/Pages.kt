package com.latihan.mynotes.routes

sealed class Pages(
    val route: String,
) {
    data object Home: Pages("home")
    data object AddNote: Pages("addnote")
    data object EditNote: Pages("editnote")
    data object BookmarkList: Pages("bookmarklist")
}
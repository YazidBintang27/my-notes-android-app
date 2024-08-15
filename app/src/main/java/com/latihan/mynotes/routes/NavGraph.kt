package com.latihan.mynotes.routes

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.latihan.mynotes.ui.pages.AddNotePage
import com.latihan.mynotes.ui.pages.BookmarkListPage
import com.latihan.mynotes.ui.pages.EditNotePage
import com.latihan.mynotes.ui.pages.HomePage

@Composable
fun NavGraph(
    modifier: Modifier = Modifier,
    navHostController: NavHostController
) {
    NavHost(
        navController = navHostController,
        startDestination = Pages.Home.route
    ) {
        composable(Pages.Home.route) {
            HomePage(navHostController = navHostController)
        }
        composable(Pages.AddNote.route) {
            AddNotePage(navHostController = navHostController)
        }
        composable(
            route = "${Pages.EditNote.route}/{index}",
            arguments = listOf(navArgument("index") {type = NavType.IntType})
        ) { backStackEntry ->
            EditNotePage(navHostController = navHostController, index = backStackEntry.arguments?.getInt("index") ?: -1)
        }
        composable(Pages.BookmarkList.route) {
            BookmarkListPage(navHostController = navHostController)
        }
    }
}
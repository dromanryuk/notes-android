package ru.dromanryuk.notes

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.activity.compose.setContent
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.material.ExperimentalMaterialApi
import androidx.navigation.NavType
import androidx.navigation.navArgument
import dagger.hilt.android.AndroidEntryPoint
import ru.dromanryuk.notes.feature_note.presentation.note.NoteScreen
import ru.dromanryuk.notes.feature_note.presentation.overview.OverviewScreen
import ru.dromanryuk.notes.feature_note.presentation.password.PasswordScreen
import ru.dromanryuk.notes.feature_note.presentation.util.Screen
import ru.dromanryuk.notes.ui.theme.NotesTheme

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @ExperimentalMaterialApi
    @ExperimentalFoundationApi
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            NotesTheme {
                val navController = rememberNavController()
                NavGraph(navController)
            }
        }
    }

    @ExperimentalMaterialApi
    @ExperimentalFoundationApi
    @Composable
    fun NavGraph(navController: NavHostController) {
        NavHost(
            navController = navController,
            startDestination = Screen.Overview.route,
        ) {
            composable(Screen.Overview.route) {
                OverviewScreen(
                    onNoteClick = { id ->
                        navController.navigate(Screen.Note.route + "/${id}")
                    },
                    withPassword = { id ->
                        navController.navigate(Screen.Password.route + "/${id}")
                    }
                )
            }
            composable(
                route = Screen.Note.route + "/{noteId}",
                arguments = listOf(navArgument("noteId") { type = NavType.IntType })
            ) {
                val noteId = it.arguments?.getInt("noteId")
                    ?: error("noteId argument is not passed")
                NoteScreen(
                    navigateToOverviewScreen = { navController.navigate(Screen.Overview.route) },
                    navigateToPasswordScreen = {
                        navController.navigate(Screen.Password.route + "/${noteId}")
                    }
                )
            }
            composable(
                route = Screen.Password.route + "/{noteId}",
                arguments = listOf(navArgument("noteId") { type = NavType.IntType })
            ) {
                val noteId = it.arguments?.getInt("noteId")
                    ?: error("noteId argument is not passed")
                PasswordScreen(
                    navigateBack = { navController.popBackStack() },
                    navigateToNoteScreen = {
                        navController.navigate(Screen.Note.route + "/${noteId}") }
                )
            }
        }
    }
}

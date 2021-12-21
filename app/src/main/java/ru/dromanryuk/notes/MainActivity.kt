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
import dagger.hilt.android.AndroidEntryPoint
import ru.dromanryuk.notes.feature_note.presentation.note.NoteScreen
import ru.dromanryuk.notes.feature_note.presentation.overview.OverviewScreen
import ru.dromanryuk.notes.feature_note.presentation.util.Screen
import ru.dromanryuk.notes.ui.theme.NotesTheme

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
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

    @ExperimentalFoundationApi
    @Composable
    fun NavGraph(navController: NavHostController) {
        NavHost(
            navController = navController,
            startDestination = Screen.Overview.route,
        ) {

        }
    }
}

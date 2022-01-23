package ru.dromanryuk.notes

import android.os.Bundle
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.material.ExperimentalMaterialApi
import androidx.navigation.NavType
import androidx.navigation.navArgument
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.InternalCoroutinesApi
import ru.dromanryuk.notes.feature_note.presentation.note.NoteScreen
import ru.dromanryuk.notes.feature_note.presentation.overview.OverviewScreen
import ru.dromanryuk.notes.feature_note.presentation.password.confirmPassword.ConfirmPasswordScreen
import ru.dromanryuk.notes.feature_note.presentation.password.createPassword.CreatePasswordScreen
import ru.dromanryuk.notes.feature_note.presentation.password.loginPassword.PasswordLoginScreen
import ru.dromanryuk.notes.feature_note.presentation.util.Screen
import ru.dromanryuk.notes.ui.theme.NotesTheme

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    @InternalCoroutinesApi
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

    @InternalCoroutinesApi
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
                        navController.navigate(Screen.PasswordLogin.route + "/${id}")
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
                        navController.navigate(Screen.CreatePassword.route + "/${noteId}")
                    }
                )
            }
            composable(
                route = Screen.PasswordLogin.route + "/{noteId}",
                arguments = listOf(navArgument("noteId") { type = NavType.IntType })
            ) {
                val noteId = it.arguments?.getInt("noteId")
                    ?: error("noteId argument is not passed")
                PasswordLoginScreen(
                    navigateBack = { navController.popBackStack() },
                    navigateFurther = {
                        navController.navigate(Screen.Note.route + "/${noteId}") }
                )
            }
            composable(
                route = Screen.CreatePassword.route + "/{noteId}",
                arguments = listOf(navArgument("noteId") { type = NavType.IntType })
            ) {
                val noteId = it.arguments?.getInt("noteId")
                    ?: error("noteId argument is not passed")
                CreatePasswordScreen(
                    navigateBack = {
                        navController.navigate(Screen.Note.route + "/${noteId}")
                    },
                    navigateFurther = { password ->
                        backStackEntry(navController, password)
                        navController.navigate(Screen.ConfirmPassword.route + "/${noteId}")
                    }
                )
            }
            composable(
                route = Screen.ConfirmPassword.route + "/{noteId}",
                arguments = listOf(navArgument("noteId") { type = NavType.IntType })
            ) {
                val noteId = it.arguments?.getInt("noteId")
                    ?: error("noteId argument is not passed")
                val password = navController.previousBackStackEntry?.savedStateHandle?.get<String>("password")
                ConfirmPasswordScreen(
                    navigateBack = {
                        navController.navigate(Screen.CreatePassword.route + "/${noteId}")
                    },
                    navigateFurther = {
                        navController.navigate(Screen.Note.route + "/${noteId}") },
                    password
                )
            }
        }
    }
}

private fun backStackEntry(navController: NavHostController, password: String) {
    navController.currentBackStackEntry?.savedStateHandle?.set("password", password)
}

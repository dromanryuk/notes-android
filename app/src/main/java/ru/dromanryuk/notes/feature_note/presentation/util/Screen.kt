package ru.dromanryuk.notes.feature_note.presentation.util

sealed class Screen(val route: String) {
    object Overview: Screen("OverviewScreen")
    object Note: Screen("NoteScreen")
    object PasswordLogin: Screen("PasswordLoginScreen")
    object CreatePassword: Screen("CreatePassword")
    object ConfirmPassword: Screen("ConfirmPassword")
}

package ru.dromanryuk.notes.feature_note.presentation.util

sealed class Screen(val route: String) {
    object Overview: Screen("OverviewScreen")
    object Note: Screen("NoteScreen")
    object Password: Screen("PasswordScreen")
}

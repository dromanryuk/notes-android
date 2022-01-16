package ru.dromanryuk.notes.feature_note.presentation.password.model

sealed class PasswordButtonType {
    data class NumberButton(val digit: Int) : PasswordButtonType()
    data class TextButton(val text: String) : PasswordButtonType()
    object Fingerprint : PasswordButtonType()
    object Clear : PasswordButtonType()
}
package ru.dromanryuk.notes.feature_note.presentation.password.model

sealed class PasswordInputEvent {
    data class OnPasswordChanged(val password: String) : PasswordInputEvent()

    object OnCleanPressed : PasswordInputEvent()

    object OnFingerprintLogin : PasswordInputEvent()

    object SaveEditing : PasswordInputEvent()

    object ExitScreen: PasswordInputEvent()
}

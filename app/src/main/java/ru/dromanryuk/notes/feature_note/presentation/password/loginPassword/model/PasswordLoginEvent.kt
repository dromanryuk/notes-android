package ru.dromanryuk.notes.feature_note.presentation.password.loginPassword.model

sealed class PasswordLoginEvent {
    data class OnEditingFinished(val password: String) : PasswordLoginEvent()

    object ResetCleanState : PasswordLoginEvent()
}
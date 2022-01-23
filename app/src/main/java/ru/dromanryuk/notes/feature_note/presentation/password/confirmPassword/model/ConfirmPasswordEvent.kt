package ru.dromanryuk.notes.feature_note.presentation.password.confirmPassword.model

sealed class ConfirmPasswordEvent {
    data class OnEditingFinished(val password: String) : ConfirmPasswordEvent()

    data class SetPasswordValue(val password: String) : ConfirmPasswordEvent()
}

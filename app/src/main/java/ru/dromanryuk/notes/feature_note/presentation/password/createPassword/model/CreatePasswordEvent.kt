package ru.dromanryuk.notes.feature_note.presentation.password.createPassword.model

sealed class CreatePasswordEvent {
    data class OnEditingFinished(val password: String) : CreatePasswordEvent()
}

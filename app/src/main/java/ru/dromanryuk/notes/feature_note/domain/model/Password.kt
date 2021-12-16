package ru.dromanryuk.notes.feature_note.domain.model

sealed class Password {
    object NonePassword : Password()
    class CodePassword(val encryptedPassword: String) : Password()
}

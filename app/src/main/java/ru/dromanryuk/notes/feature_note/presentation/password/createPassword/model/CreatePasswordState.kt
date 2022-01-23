package ru.dromanryuk.notes.feature_note.presentation.password.createPassword.model

import ru.dromanryuk.notes.feature_note.presentation.password.commonPassword.model.PasswordInputType
import ru.dromanryuk.notes.feature_note.presentation.password.commonPassword.model.PasswordInputType.REGISTRATION

data class CreatePasswordState(
    val passwordInputType: PasswordInputType = REGISTRATION,
    val isNavigateFurther: Boolean = false
)

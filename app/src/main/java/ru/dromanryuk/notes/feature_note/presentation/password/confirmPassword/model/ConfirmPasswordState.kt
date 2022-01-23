package ru.dromanryuk.notes.feature_note.presentation.password.confirmPassword.model

import ru.dromanryuk.notes.feature_note.presentation.password.commonPassword.model.PasswordInputType

data class ConfirmPasswordState(
    val password: String = "",
    val passwordInputType: PasswordInputType = PasswordInputType.REGISTRATION,
    val isNavigateFurther: Boolean = false,
    val isNavigateBack: Boolean = false,
)

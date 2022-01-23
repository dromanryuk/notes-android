package ru.dromanryuk.notes.feature_note.presentation.password.loginPassword.model

import ru.dromanryuk.notes.feature_note.presentation.password.commonPassword.model.PasswordInputType
import ru.dromanryuk.notes.feature_note.presentation.password.commonPassword.model.PasswordInputType.LOGIN

data class PasswordLoginState(
    val passwordInputType: PasswordInputType = LOGIN,
    val isNavigateFurther: Boolean = false,
    val resetInputValue: Boolean = false
)
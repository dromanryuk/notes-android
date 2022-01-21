package ru.dromanryuk.notes.feature_note.presentation.password.commonPassword.model

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import ru.dromanryuk.notes.core.UiComponentVisibility
import ru.dromanryuk.notes.feature_note.presentation.password.commonPassword.model.PasswordInputType.REGISTRATION

data class PasswordState(
    val password: Flow<String> = flowOf(""),
    val passwordInputType: PasswordInputType = REGISTRATION,
    val buttons: List<List<PasswordButtonType>> = generateButtonsMatrix(PasswordButtonType.Clear),
    val fingerprintDialogVisibility: UiComponentVisibility = UiComponentVisibility.Hide,
    val isExitFromScreen: Boolean = false,
    val isEditingFinished: Boolean = false,
)

enum class PasswordInputType {
    LOGIN, REGISTRATION
}

fun generateButtonsMatrix(lastButton : PasswordButtonType) = listOf(
    generateList(1..3),
    generateList(4..6),
    generateList(7..9),
    listOf(
        PasswordButtonType.TextButton("Выход"),
        PasswordButtonType.NumberButton(0),
        lastButton
    )
)

fun generateList(range: IntRange) = range.map {
    PasswordButtonType.NumberButton(it)
}
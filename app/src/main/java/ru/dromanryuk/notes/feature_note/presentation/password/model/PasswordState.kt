package ru.dromanryuk.notes.feature_note.presentation.password.model

data class PasswordState(
    val password: String = "",
    val verifiablePassword: String = "",
    val buttons: List<List<PasswordButtonType>> = generateButtonsMatrix(PasswordButtonType.Fingerprint),
    val isExitFromScreen: Boolean = false,
    val isLoginToNote: Boolean = false
)

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

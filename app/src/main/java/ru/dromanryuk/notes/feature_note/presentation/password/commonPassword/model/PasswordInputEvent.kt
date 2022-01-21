package ru.dromanryuk.notes.feature_note.presentation.password.commonPassword.model

import ru.dromanryuk.notes.core.UiComponentVisibility

sealed class PasswordInputEvent {
    data class OnPasswordChanged(val password: String) : PasswordInputEvent()

    data class SetScreenType(val type: PasswordInputType) : PasswordInputEvent()

    data class UpdateFingerprintDialogVisibility(val visibility: UiComponentVisibility) : PasswordInputEvent()

    object OnCleanPressed : PasswordInputEvent()

    object OnFingerprintLogin : PasswordInputEvent()

    object ExitScreen: PasswordInputEvent()

    object ClearAll : PasswordInputEvent()
}

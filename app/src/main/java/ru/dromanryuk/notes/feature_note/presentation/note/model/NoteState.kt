package ru.dromanryuk.notes.feature_note.presentation.note.model

import ru.dromanryuk.notes.core.UiComponentVisibility
import ru.dromanryuk.notes.feature_note.domain.model.Password

data class NoteState(
    val titleState: String = "",
    val contentState: NoteContentState = NoteContentState.Text(),
    val favouriteState: Boolean = false,
    val passwordState: Password = Password.NonePassword,
    val editingDateTime: String? = "",
    val isExitFromScreen: Boolean = false,
    val isNavigateToPasswordScreen: Boolean = false,
    val shareDialogVisible: Boolean = false,
    val removePasswordDialogVisible: Boolean = false,
    val notificationDialogVisibility: NotificationDialogState = NotificationDialogState(),
    val shareState: NoteShareType = NoteShareType.Text
)

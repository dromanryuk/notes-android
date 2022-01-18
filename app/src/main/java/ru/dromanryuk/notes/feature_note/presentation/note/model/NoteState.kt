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
    val shareDialogVisibility: UiComponentVisibility = UiComponentVisibility.Hide,
    val removePasswordDialogVisibility: UiComponentVisibility = UiComponentVisibility.Hide,
    val shareState: NoteShareType = NoteShareType.Text
)

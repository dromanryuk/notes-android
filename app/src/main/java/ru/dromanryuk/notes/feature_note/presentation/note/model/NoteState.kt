package ru.dromanryuk.notes.feature_note.presentation.note.model

import ru.dromanryuk.notes.core.UiComponentVisibility

data class NoteState(
    val titleState: String = "",
    val contentState: NoteContentState = NoteContentState.Text(),
    val favouriteState: Boolean = false,
    val editingDateTime: String? = "",
    val isExitFromScreen: Boolean = false,
    val shareDialogVisibility: UiComponentVisibility = UiComponentVisibility.Hide,
    val shareState: NoteShareType = NoteShareType.Text
)

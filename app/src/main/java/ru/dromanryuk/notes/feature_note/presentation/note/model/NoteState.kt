package ru.dromanryuk.notes.feature_note.presentation.note.model

data class NoteState(
    val titleState: String = "",
    val contentState: NoteContentState = NoteContentState.Text(),
    val favouriteState: Boolean = false,
    val editingDateTime: String? = "",
    val isExitFromScreen: Boolean = false
)

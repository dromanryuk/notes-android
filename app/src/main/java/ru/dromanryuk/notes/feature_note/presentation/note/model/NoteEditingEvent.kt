package ru.dromanryuk.notes.feature_note.presentation.note.model

sealed class NoteEditingEvent {
    data class OnTitleChanged(val title: String) : NoteEditingEvent()
    data class OnContentChanged(val content: String) : NoteEditingEvent()

    data class AdditionalActions(val action: ModalBottomSheetAction) : NoteEditingEvent()

    object SetReminder : NoteEditingEvent()

    object ToggleFavourite : NoteEditingEvent()

    object SaveEditing : NoteEditingEvent()

    object ExitScreen: NoteEditingEvent()
}

package ru.dromanryuk.notes.feature_note.presentation.note.model

import ru.dromanryuk.notes.core.UiComponentVisibility

sealed class NoteEditingEvent {
    data class OnTitleChanged(val title: String) : NoteEditingEvent()
    data class OnContentChanged(val content: String) : NoteEditingEvent()

    data class AdditionalActions(val action: ModalBottomSheetAction) : NoteEditingEvent()

    data class UpdateShareDialogVisibility(val visibility: UiComponentVisibility) : NoteEditingEvent()
    data class ShareTypeChanged(val sortingType: NoteShareType) : NoteEditingEvent()

    object SetReminder : NoteEditingEvent()

    object ToggleFavourite : NoteEditingEvent()

    object SaveEditing : NoteEditingEvent()

    object ExitScreen: NoteEditingEvent()
}

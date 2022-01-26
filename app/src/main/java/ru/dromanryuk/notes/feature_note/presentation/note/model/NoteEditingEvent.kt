package ru.dromanryuk.notes.feature_note.presentation.note.model

import ru.dromanryuk.notes.core.UiComponentVisibility
import ru.dromanryuk.notes.feature_note.domain.model.Checkbox

sealed class NoteEditingEvent {
    data class OnTitleChanged(val title: String) : NoteEditingEvent()
    data class OnTextContentChanged(val content: String) : NoteEditingEvent()
    data class OnCheckboxChanged(val checkbox: Checkbox) : NoteEditingEvent()

    data class AdditionalActions(val action: ModalBottomSheetAction) : NoteEditingEvent()

    data class UpdateDialogVisibility(val dialog: NoteDialogs, val visibility: Boolean) : NoteEditingEvent()
    data class UpdateNotificationDialogMenuVisibility(val type: DropdownType, val newVal: Boolean) : NoteEditingEvent()

    object AddCheckbox : NoteEditingEvent()

    object SetReminder : NoteEditingEvent()

    object ToggleFavourite : NoteEditingEvent()

    object SaveEditing : NoteEditingEvent()

    object RemoveNotePassword : NoteEditingEvent()

    object ExitScreen: NoteEditingEvent()
}

enum class NoteDialogs {
    SHARE, REMOVE_PASSWORD, NOTIFICATION
}

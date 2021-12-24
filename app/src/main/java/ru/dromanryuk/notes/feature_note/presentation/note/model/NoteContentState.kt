package ru.dromanryuk.notes.feature_note.presentation.note.model

import ru.dromanryuk.notes.feature_note.domain.model.Checkbox
import ru.dromanryuk.notes.feature_note.domain.model.NoteContent

sealed class NoteContentState {
    abstract var text: String?
    abstract var checklist: List<Checkbox>?

    data class Text(
        override var text: String? = "",
        override var checklist: List<Checkbox>? = null
    ) : NoteContentState()

    data class Checklist(
        override var text: String? = null,
        override var checklist: List<Checkbox>? = emptyList()
    ) : NoteContentState()
}

fun NoteContentState.toNoteTextContent() = NoteContent.TextNote(
    text = text!!
)

fun NoteContentState.toNoteChecklistContent() = NoteContent.ChecklistNote(
    checkboxes = checklist!!
)
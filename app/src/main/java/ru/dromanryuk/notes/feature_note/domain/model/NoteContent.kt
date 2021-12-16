package ru.dromanryuk.notes.feature_note.domain.model

sealed class NoteContent {
    class TextNote(val content: String): NoteContent()
    class ChecklistNote(val checkboxes: List<Checkbox>): NoteContent()
}





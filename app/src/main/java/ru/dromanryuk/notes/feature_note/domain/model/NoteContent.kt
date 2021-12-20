package ru.dromanryuk.notes.feature_note.domain.model

sealed class NoteContent {
    class TextNote(val text: String): NoteContent()
    class ChecklistNote(val checkboxes: List<Checkbox>): NoteContent()
}

fun NoteContent.ChecklistNote.toText(): String {
//    return checkboxes.joinToString {
//        it.content
//    }
    return checkboxes.joinToString(prefix = "□☐ ", separator = "\n")
}




